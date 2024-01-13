package de.hhn.labapp.persistence.crm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import de.hhn.labapp.persistence.crm.factories.CustomerFactory
import de.hhn.labapp.persistence.crm.model.DatabaseProvider.withDatabase
import de.hhn.labapp.persistence.crm.model.entities.Customer
import kotlinx.coroutines.launch

class CustomerDetailsViewModel(
    private val customer: Customer,
    private val navController: NavController
) : ViewModel() {
    var name by mutableStateOf(customer.name)
    var address by mutableStateOf(customer.address)
    var postalCode by mutableStateOf(customer.postalCode)
    var city by mutableStateOf(customer.city)
    var country by mutableStateOf(customer.country)

    val isSaveEnabled: Boolean
        get() = ((name.isNotBlank())
                && (address.isNotBlank())
                && (postalCode.isNotBlank())
                && (city.isNotBlank())
                && (country.isNotBlank())
                )

    fun setCustomer(customer: Customer) {
        name = customer.name
        address = customer.address
        postalCode = customer.postalCode
        city = customer.city
        country = customer.country
    }

    fun onCancel() {
        navController.popBackStack()
    }

    fun onSave() {
        customer.name = name
        customer.address = address
        customer.postalCode = postalCode
        customer.city = city
        customer.country = country

        if (customer.id == null) {
            withDatabase { customerDao().insert(customer) }
        }

        navController.popBackStack()
    }

    fun onGenerateRandom() {
        setCustomer(CustomerFactory.createRandomCustomer())
    }
}