package com.example.demo.data

data class Item(val listId: Int, val name: String)


object DataSource {
    val data: List<Pair<Int, List<Item>>> = listOf(
        Pair(1, listOf(
            Item(1, "Item 100"),
            Item(1, "Item 200"),
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(2, listOf(
            Item(1, "Item 684"),
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(3, listOf(
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(4, listOf(
            Item(1, "Item 600"),
            Item(1, "Item 684"),
            Item(1, "Item 500"),
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(5, listOf(
            Item(1, "Item 684"),
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(6, listOf(
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
        Pair(7, listOf(
            Item(1, "Item 684"),
            Item(1, "Item 684"),
            Item(1, "Item 684"))),
    )
}