package ii_collections

import v_builders.data.getProducts

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() = orders.flatMap { it.products }.toSet()

val Shop.allOrderedProducts: Set<Product> get() = customers.flatMap {it.orders }.flatMap { it.products }.toSet()
