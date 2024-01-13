package de.hhn.labapp.persistence.crm.components.customers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.hhn.labapp.persistence.crm.factories.CustomerFactory
import de.hhn.labapp.persistence.crm.model.entities.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerCard(
    customer: Customer,
    onClick: (Customer) -> Unit,
) {
    Card(
        // Fill the available width
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = { onClick(customer) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                text = countryFlag(customer.country),
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 8.dp
                    )
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = customer.name,
                    fontSize = 16.sp
                )
                Text(
                    text = customer.city,
                    fontSize = 12.sp
                )
            }
        }

    }
}

fun countryFlag(country: String): String {
    return when (country) {
        "Austria" -> "🇦🇹"
        "Belgium" -> "🇧🇪"
        "Czech Republic" -> "🇨🇿"
        "Denmark" -> "🇩🇰"
        "Estonia" -> "🇪🇪"
        "France" -> "🇫🇷"
        "Germany" -> "🇩🇪"
        "Hungary" -> "🇭🇺"
        "Ireland" -> "🇮🇪"
        "Italy" -> "🇮🇹"
        "Latvia" -> "🇱🇻"
        "Malta" -> "🇲🇹"
        "Netherlands" -> "🇳🇱"
        "Poland" -> "🇵🇱"
        "Romania" -> "🇷🇴"
        "Slovakia" -> "🇸🇰"
        "Turkey" -> "🇹🇷"
        "Ukraine" -> "🇺🇦"
        "Vatican City" -> "🇻🇦"
        "Wales" -> "🏴󠁧󠁢󠁷󠁬󠁳󠁿"
        else -> ""
    }
}

@Preview
@Composable
fun PreviewCustomerCard() {
    CustomerCard(
        customer = CustomerFactory.createRandomCustomer(),
        onClick = {}
    )
}
