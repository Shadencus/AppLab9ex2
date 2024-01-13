package de.hhn.labapp.persistence.crm.util

fun formatAmount(amount: Int): String {
    val dollars = String.format("%,d", amount / 100)
    return "$$dollars.${(amount % 100).toString().padStart(2, '0')}"
}

fun parseAmount(amount: String): Int {
    val parts = amount
        .replaceFirst("$", "")
        .replace(",", "")
        .split(".")
    val totalCents = parts.joinToString(separator = "")
    return if (totalCents.isEmpty()) 0 else totalCents.toInt()
}
