package com.samir.has.api.doa;

import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;

import java.util.List;

public interface IInvoiceDao {
    void insertInvoice(Invoice invoice);
    Invoice selectInvoiceByNumber(LocalUniqueId invoiceNumber);
    List<Invoice> selectInvoiceByCustomer(LocalUniqueId customerId);
    List<Invoice> selectInvoiceByDate(String date);
    boolean updateInvoice(LocalUniqueId billRef, Invoice newInvoice);
    boolean removeInvoice(LocalUniqueId billRef);
    void addProductIntoInvoice(LocalUniqueId billRef, LocalUniqueId productRef, int quantity);
    boolean updateProductQuantityOnInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef, int newQuantity);
    boolean removeProductFromInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef);
}
