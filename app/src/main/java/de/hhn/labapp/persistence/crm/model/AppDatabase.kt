package de.hhn.labapp.persistence.crm.model

import androidx.room.Database
import androidx.room.RoomDatabase
import de.hhn.labapp.persistence.crm.model.daos.CustomerDao
import de.hhn.labapp.persistence.crm.model.entities.Customer

@Database(
    entities = [
        Customer::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}
