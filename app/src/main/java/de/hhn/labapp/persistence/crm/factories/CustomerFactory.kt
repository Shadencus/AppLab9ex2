package de.hhn.labapp.persistence.crm.factories

import de.hhn.labapp.persistence.crm.model.entities.Customer

object CustomerFactory {
    val FIRST_NAMES = listOf(
        "Arthur",
        "Bea",
        "Cecil",
        "Doris",
        "Eugene",
        "Fiona",
        "Gerald",
        "Helen",
        "Ivan",
        "Julia",
        "Kevin",
        "Linda",
        "Michael",
        "Nancy",
        "Oscar",
        "Pamela",
        "Quentin",
        "Rachel",
        "Stephen",
        "Tina",
        "Ursula",
        "Victor",
        "Wendy",
        "Xavier",
        "Yvonne",
        "Zachary"
    )

    val LAST_NAMES = listOf(
        "Adams",
        "Baker",
        "Carter",
        "Davis",
        "Edwards",
        "Fisher",
        "Garcia",
        "Harris",
        "Ingram",
        "Johnson",
        "King",
        "Lee",
        "Miller",
        "Nelson",
        "Owens",
        "Perez",
        "Quinn",
        "Roberts",
        "Smith",
        "Taylor",
        "Unger",
        "Vasquez",
        "Walker",
        "Xu",
        "Young",
        "Zimmerman"
    )

    val ADDRESSES = listOf(
        "Albany Road",
        "Baker Street",
        "Church Street",
        "Downing Street",
        "Elm Street",
        "Fifth Avenue",
        "Grove Street",
        "High Street",
        "Imperial Avenue",
        "Jermyn Street",
        "King Street",
        "Lombard Street",
        "Main Street",
        "Ninth Street",
        "Orchard Street",
        "Park Avenue",
        "Queen Street",
        "Regent Street",
        "State Street",
        "Tenth Street",
        "University Avenue",
        "Vine Street",
        "Wall Street",
        "Xenophon Street",
        "Yonge Street",
        "Zachary Street"
    )

    val CITIES = listOf(
        "Aachen",
        "Berlin",
        "Cologne",
        "Dortmund",
        "Essen",
        "Frankfurt",
        "Gelsenkirchen",
        "Hamburg",
        "Ingolstadt",
        "Jena",
        "Kiel",
        "Lübeck",
        "Munich",
        "Nuremberg",
        "Osnabrück",
        "Potsdam",
        "Quedlinburg",
        "Regensburg",
        "Stuttgart",
        "Trier",
        "Ulm",
        "Viersen",
        "Wiesbaden",
        "Xanten",
        "Ystad",
        "Zwickau"
    )

    val COUNTRIES = listOf(
        "Austria",
        "Belgium",
        "Czech Republic",
        "Denmark",
        "Estonia",
        "France",
        "Germany",
        "Hungary",
        "Ireland",
        "Italy",
        "Latvia",
        "Malta",
        "Netherlands",
        "Poland",
        "Romania",
        "Slovakia",
        "Turkey",
        "Ukraine",
        "Vatican City",
        "Wales"
    )

    fun createCustomer(): Customer {
        return Customer(
            name = "",
            address = "",
            city = "",
            postalCode = "",
            country = ""
        )
    }

    fun createRandomCustomer(): Customer {
        return Customer(
            name = randomName(),
            address = randomAddress(),
            city = randomCity(),
            postalCode = randomPostalCode(),
            country = randomCountry()
        )
    }

    fun createRandomCustomers(count: Int): List<Customer> {
        return (1..count).map { createRandomCustomer() }
    }

    fun randomName(): String {
        return "${FIRST_NAMES.random()} ${LAST_NAMES.random()}"
    }

    fun randomAddress(): String {
        return "${ADDRESSES.random()} ${(1..100).random()}"
    }

    fun randomCity(): String {
        return CITIES.random()
    }

    fun randomPostalCode(): String {
        val postalCode = StringBuilder("xxxxx")
        for (i in 0 until 5) {
            postalCode[i] = (0..9).random().toString().first()
        }
        return postalCode.toString()
    }

    fun randomCountry(): String {
        return COUNTRIES.random()
    }
}