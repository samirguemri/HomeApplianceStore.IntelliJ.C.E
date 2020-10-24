package com.samir.has.api.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.samir.has.api.doa.IInvoiceDao;
import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.MapAdapter;
import com.samir.has.api.object.person.Customer;
import com.samir.has.api.object.person.CustomerAdapter;
import com.samir.has.api.object.product.Product;

import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

@Service("invoiceService")
public class InvoiceService {

    private final IInvoiceDao invoiceDao;
    private final IProductDao productDao;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public InvoiceService(@Qualifier("fakeProductDao") IProductDao productDao,
                          @Qualifier("fakeBillDao") IInvoiceDao invoiceDao,
                          @Qualifier("customerService") CustomerService customerService,
                          @Qualifier("productService") ProductService productService) {
        this.productDao = productDao;
        this.invoiceDao = invoiceDao;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void addInvoice(Invoice invoice){
        this.invoiceDao.insertInvoice(invoice);
    }

    public Invoice getInvoiceByRef(LocalUniqueId billRef){
        return this.invoiceDao.selectInvoiceByNumber(billRef);
    }

    public List<Invoice> getInvoiceByCustomer(LocalUniqueId customerId){
        return this.invoiceDao.selectInvoiceByCustomer(customerId);
    }

    public List<Invoice> getInvoiceByDate(String date){
        return this.invoiceDao.selectInvoiceByDate(date);
    }

    public boolean updateInvoice(LocalUniqueId billRef, Invoice newInvoice){
        return this.invoiceDao.updateInvoice(billRef, newInvoice);
    }

    public void addProductIntoInvoice(LocalUniqueId billRef, LocalUniqueId productRef, int quantity){
        this.invoiceDao.addProductIntoInvoice(billRef,productRef,quantity);
    }

    public boolean updateProductQuantityOnInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef, int newQuantity){
        return this.invoiceDao.updateProductQuantityOnInvoice(invoiceNumber,productRef,newQuantity);
    }

    public boolean removeProductFromInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef){
        return this.invoiceDao.removeProductFromInvoice(invoiceNumber,productRef);
    }

    public void totalCost(LocalUniqueId invoiceNumber){
        float price = 0;
        Map<LocalUniqueId,Integer> productList = this.invoiceDao.selectInvoiceByNumber(invoiceNumber).getProductList();
        for ( Map.Entry<LocalUniqueId,Integer> entry : productList.entrySet() ) {
            Product product = this.productDao.selectProduct(entry.getKey());
            price += product.getPrice() * productList.get(entry.getValue());
        }
        this.invoiceDao.selectInvoiceByNumber(invoiceNumber).setPrice(price);
    }

    private File generateXml(LocalUniqueId invoiceNumber){
        File file = null;
        try {
            file = new File("invoice.xml");
            file.createNewFile();

            Class deliveryModeClass = getInvoiceByRef(invoiceNumber).getDeliveryMode().getImplementedObject().getClass();
            Reflections reflections = new Reflections(Product.class.getPackageName());
            Set<Class<? extends Product>> extendedClassesOfProductClass = reflections.getSubTypesOf(Product.class);
            Class[] classesToBeBound = (Class[]) ArrayUtils.addAll(new Class[] {Invoice.class, deliveryModeClass, Customer.class}, extendedClassesOfProductClass.toArray());

            JAXBContext jaxbContext = JAXBContext.newInstance(classesToBeBound);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            CustomerAdapter customerAdapter = new CustomerAdapter(customerService);
            MapAdapter mapAdapter = new MapAdapter(productService);
            jaxbMarshaller.setAdapter(customerAdapter);
            jaxbMarshaller.setAdapter(mapAdapter);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Invoice invoice = getInvoiceByRef(invoiceNumber);
            jaxbMarshaller.marshal(invoice, file);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void generatePdf(LocalUniqueId invoiceNumber) throws IOException, FOPException, TransformerException {

        File xmlFile = generateXml(invoiceNumber);
        if (xmlFile == null)
            throw new  NullPointerException("xml File can't be opened");

        StreamSource xmlSource = new StreamSource(xmlFile);
        File xsltFile = new File("xmlTransformation.xsl");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        OutputStream out = null;
        try {
            out = new java.io.FileOutputStream("invoice.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltFile));
            Result saxResult = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, saxResult);
        } catch (FOPException e){
            e.printStackTrace();
        }
        finally {
            out.close();
        }

    }

}
