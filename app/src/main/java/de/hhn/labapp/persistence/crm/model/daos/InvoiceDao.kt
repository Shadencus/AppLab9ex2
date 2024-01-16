package de.hhn.labapp.persistence.crm.model.daos

import androidx.room.*
import de.hhn.labapp.persistence.crm.model.entities.Customer
import de.hhn.labapp.persistence.crm.model.entities.Invoice

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM customer")
    fun getAll(): List<Invoice>

    @Query("SELECT * FROM customer WHERE id = :id")
    fun get(id: Int): Invoice?

    @Insert
    fun insert(invoice: Invoice)

    @Insert
    fun insertAll(invoice: List<Invoice>)

    @Update
    fun update(invoice: Invoice)

    @Upsert
    fun upsert (invoice: Invoice)

    @Delete
    fun delete(invoice: Invoice)

}