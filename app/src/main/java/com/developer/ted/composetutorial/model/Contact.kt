package com.developer.ted.composetutorial.model

data class Contact(
    val name: String,
    val phone: String
) {
    companion object {
        fun getMockList(): Map<String, List<Contact>> {
            return listOf(
                Contact("Adam Powell", "1"),
                Contact("Anastasia Soboleva", "2"),
                Contact("Andrey Kulikov", "3"),
                Contact("Bob Willis", "1"),
                Contact("Adam Powell", "1"),
                Contact("Anastasia Soboleva", "2"),
                Contact("Andrey Kulikov", "3"),
                Contact("Bob Willis", "1"),
                Contact("Ben Port", "1"),
                Contact("Bob Willis", "1"),
                Contact("Ben Port", "1"),
                Contact("Chris Dwain", "1"),
                Contact("Clara Kim", "1"),
                Contact("Cathie Johnson", "1"),
                Contact("Chris Dwain", "1"),
                Contact("Clara Kim", "1"),
                Contact("Cathie Johnson", "1"),
                Contact("Florina Alex", "1"),
                Contact("Patty Lee", "1"),
                Contact("Ted Kim", "1"),
                Contact("John Prosor", "1"),
                Contact("Zack Wield", "1"),
                Contact("Florina Alex", "1"),
                Contact("Patty Lee", "1"),
                Contact("Ted Kim", "1"),
                Contact("John Prosor", "1"),
                Contact("Zack Wield", "1")
            )
                .sortedBy { it.name }
                .groupBy { it.name[0].toString() }
        }
    }
}