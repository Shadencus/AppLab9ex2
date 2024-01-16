package de.hhn.labapp.persistence.crm.model

/**
 * In-Memory repository for invoices. Not persisted across app restarts.
 * TODO: Persist invoices in the database
 */
object Invoices {
    private val list by lazy { mutableMapOf<Int, Invoice>() }
    private var nextId = 1
    /*

    fun getAll(): List<Invoice> {
        return list.values.toList()
    }

    fun get(id: Int): Invoice? {
        return list[id]
    }

    fun insert(invoice: Invoice) {
        invoice.id?.run { throw IllegalArgumentException("Invoice must not have an id") }
        nextId++.let {
            invoice.id = it
            list[it] = invoice
        }
    }

    fun insertAll(invoices: List<Invoice>) {
        invoices.forEach { insert(it) }
    }

    fun update(invoice: Invoice) {
        invoice.id?.let { list[it] = invoice }
    }

    fun upsert(invoice: Invoice) {
        if (invoice.id == null) {
            insert(invoice)
        } else {
            update(invoice)
        }
    }

    fun delete(invoice: Invoice) {
        invoice.id?.let { list.remove(it) }
    }

     */
}