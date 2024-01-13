package de.hhn.labapp.persistence.crm.model

data class Invoice(
    var id: Int? = null,
    var customerId: Int,
    var amount: Int,
    var isPaid: Boolean,
    var date: String,
)
