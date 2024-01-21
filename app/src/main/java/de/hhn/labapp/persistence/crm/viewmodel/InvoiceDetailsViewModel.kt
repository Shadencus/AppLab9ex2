package de.hhn.labapp.persistence.crm.viewmodel

import android.os.Handler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.hhn.labapp.persistence.crm.factories.InvoicesFactory
import de.hhn.labapp.persistence.crm.model.DatabaseProvider.withDatabase
import de.hhn.labapp.persistence.crm.model.entities.Customer
import de.hhn.labapp.persistence.crm.model.entities.Invoice
import kotlinx.coroutines.launch

class InvoiceDetailsViewModel(
    private val invoice: Invoice,
    private val navController: NavController,
    private val mainThreadHandler: Handler
) : ViewModel() {
    val snackbarHostState = SnackbarHostState()
    var showDeleteDialog by mutableStateOf(false)

    var amount by mutableStateOf(invoice.amount)
    var isPaid by mutableStateOf(invoice.isPaid)
    var customerId by mutableStateOf(invoice.customerId)
    var date by mutableStateOf(invoice.date)

    private var _customers by mutableStateOf<List<Customer>?>(null)
    val customers: List<Customer>
        get() {
            if (_customers == null) {
                viewModelScope.launch {
                    withDatabase {
                        _customers = customerDao().getAll()
                    }
                }
            }
            return _customers ?: emptyList()
        }

    val isSaveEnabled: Boolean
        get() = ((customerId != 0)
                && (amount > 0)
                && (date.isNotBlank())
                )

    fun setInvoice(invoice: Invoice) {
        amount = invoice.amount
        isPaid = invoice.isPaid
        customerId = invoice.customerId
        date = invoice.date
    }

    fun onCancel() {
        navController.popBackStack()
    }

    fun onSave() {
        invoice.amount = amount
        invoice.isPaid = isPaid
        invoice.customerId = customerId
        invoice.date = date

        /*
        if (invoice.id == null) {
            //Invoices.upsert(invoice)
            withDatabase { invoiceDao().insert(invoice) }
        }

         */
        withDatabase { invoiceDao().upsert(invoice) }
        navController.popBackStack()
    }

    fun onDelete() {
        //Invoices.delete(invoice)
        withDatabase { invoiceDao().delete(invoice) }
        navController.popBackStack()
    }

    fun onGenerateRandom() {
        if (customers.isEmpty()) {
            return
        }

        val customer = customers.random()
        val invoice = InvoicesFactory.createRandomInvoice(customer)
        setInvoice(invoice)
    }
}