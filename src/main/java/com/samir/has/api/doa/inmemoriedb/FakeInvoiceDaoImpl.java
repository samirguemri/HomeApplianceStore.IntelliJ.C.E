package com.samir.has.api.doa.inmemoriedb;

import com.samir.has.api.doa.IInvoiceDao;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeBillDao")
public class FakeInvoiceDaoImpl implements IInvoiceDao {

    private final List<Invoice> invoiceTable;

    public FakeInvoiceDaoImpl() {
        invoiceTable = new ArrayList<>();
    }

    @Override
    public void insertInvoice(Invoice invoice) {
        this.invoiceTable.add(invoice);
    }

    @Override
    public Invoice selectInvoiceByNumber(LocalUniqueId billNumber) {
        for (Invoice invoice : this.invoiceTable) {
            if (invoice.getInvoiceNumber().equals(billNumber))
                return invoice;
        }
        return null;
    }

    @Override
    public List<Invoice> selectInvoiceByCustomer(LocalUniqueId customerId) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Invoice invoice : this.invoiceTable) {
            if (invoice.getCustomerId().equals(customerId))
                invoiceList.add(invoice);
        }
        return invoiceList;
    }

    @Override
    public List<Invoice> selectInvoiceByDate(String date) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Invoice invoice : this.invoiceTable) {
            if (invoice.getIssueDate().toString().equals(date))
                invoiceList.add(invoice);
        }
        return invoiceList;
    }

    @Override
    public boolean updateInvoice(LocalUniqueId billRef, Invoice newInvoice) {
        if (removeInvoice(billRef)){
            this.invoiceTable.add(newInvoice);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeInvoice(LocalUniqueId billRef){
        for (Invoice invoice : this.invoiceTable) {
            if (invoice.getInvoiceNumber().equals(billRef))
                return this.invoiceTable.remove(invoice);
        }
        return false;
    }

    @Override
    public void addProductIntoInvoice(LocalUniqueId billNumber, LocalUniqueId productRef, int quantity) {
        updateProductQuantityOnInvoice(billNumber,productRef,quantity);
    }

    @Override
    public boolean updateProductQuantityOnInvoice(LocalUniqueId billNumber, LocalUniqueId productRef, int newQuantity) {
        Invoice invoice = this.selectInvoiceByNumber(billNumber);
        invoice.getProductList().put(productRef,newQuantity);
        return this.updateInvoice(billNumber, invoice);
    }

    @Override
    public boolean removeProductFromInvoice(LocalUniqueId billNumber, LocalUniqueId productRef) {
        Invoice invoice = this.selectInvoiceByNumber(billNumber);
        invoice.getProductList().remove(productRef);
        return this.updateInvoice(billNumber, invoice);
    }
}
