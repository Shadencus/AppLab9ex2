package de.hhn.labapp.persistence.crm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.hhn.labapp.persistence.crm.model.DatabaseProvider
import de.hhn.labapp.persistence.crm.model.DatabaseProvider.withDatabase
import de.hhn.labapp.persistence.crm.model.entities.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomersOverviewViewModel : ViewModel() {
    val customers = mutableStateOf(emptyList<Customer>())
        @Synchronized get

    fun init() {
        viewModelScope.launch {
            loadCustomers()
        }
    }

    private fun loadCustomers() {
        withDatabase {
            val temp = customerDao().getAll()

            // prevent concurrent modification, but do not lock the variable during
            // the database operation
            synchronized(customers) {
                customers.value = temp
            }
        }
    }
}