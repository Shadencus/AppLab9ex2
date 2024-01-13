package de.hhn.labapp.persistence.crm.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyTextField(
    property: String,
    value: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChanged: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier,
        value = value ?: "",
        label = { Text(property) },
        placeholder = { Text(property) },
        onValueChange = { onValueChanged(it) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
    )
}
