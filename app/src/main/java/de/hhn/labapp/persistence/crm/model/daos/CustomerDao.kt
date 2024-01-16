package de.hhn.labapp.persistence.crm.model.daos

import androidx.annotation.RequiresPermission
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.hhn.labapp.persistence.crm.model.entities.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query("SELECT * FROM customer WHERE id = :id")
    fun get(id: Int): Customer?

    @Insert
    fun insert(customer: Customer)

    @Insert
    fun insertAll(customers: List<Customer>)

    @Update
    fun update(customer: Customer)

}
