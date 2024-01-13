package de.hhn.labapp.persistence.crm.components.shared

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> AutoCompleteTextField(
    text: String? = null,
    items: List<T>,
    selectedItem: T? = null,
    toString: (T) -> String = { it.toString() },

    onItemSelected: (T) -> Unit = {},

    label: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    // We use remember with a key (`selectedItem` in this case) to trigger a recompose when
    // the `selectedItem` changes.
    // See https://developer.android.com/jetpack/compose/state#retrigger-remember
    var value by remember(selectedItem) {
        mutableStateOf(selectedItem?.let(toString) ?: text ?: "")
    }

    // same here
    var selectedItem by remember(selectedItem) { mutableStateOf(selectedItem) }

    fun onValueChange(newValue: String) {
        if (newValue != value) {
            selectedItem = null
        }
        value = newValue
    }

    fun onItemClicked(item: T) {
        value = toString(item)
        selectedItem = item
        onItemSelected(item)
    }

    Column(modifier = modifier.animateContentSize()) {
        TextField(value, label, ::onValueChange, keyboardOptions, modifier)

        if (selectedItem == null && value.isNotEmpty()) {
            Options(
                items = items.filter { toString(it).contains(value, ignoreCase = true) },
                toString = toString,
                onItemClicked = ::onItemClicked,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextField(
    value: String,
    label: String?,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        label = label?.let { { Text(it) } },
        placeholder = label?.let { { Text(it) } },
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
    )
}

@Composable
private fun <T> Options(
    items: List<T>,
    toString: (T) -> String,
    onItemClicked: (T) -> Unit,
) {
    Spacer(modifier = Modifier.size(1.dp))

    if (items.isEmpty()) {
        NoItemsHint()
    } else {
        OptionsList(items, toString, onItemClicked)
    }
}

@Composable
private fun NoItemsHint() {
    Text(
        text = "None Found",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .optionsBox()
            .padding(
                vertical = 16.dp,
            ),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun <T> OptionsList(
    items: List<T>,
    toString: (T) -> String,
    onItemClicked: (T) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.optionsBox()
    ) {
        items(items) { item ->
            ClickableText(
                text = AnnotatedString(
                    text = toString(item),
                    spanStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                onClick = { onItemClicked(item) }
            )
        }
    }
}

@Composable
fun Modifier.optionsBox(): Modifier {
    return this
        .fillMaxWidth()
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onBackground,
            shape = MaterialTheme.shapes.extraSmall,
        )
        .padding(8.dp)
}

@Preview
@Composable
fun AutoCompleteTextFieldPreview() {
    AutoCompleteTextField(
        text = "Hello",
        items = listOf("Hello", "World", "Foo", "Bar"),
        onItemSelected = { println(it) },
        label = "Label",
    )
}
