package com.laowuandhisfriends.kotlin.grammer2

fun <T> getValue(item: T): T {
    return item
}

class UpperBoundsClass2<T> where T : Comparable<T>, T : Any

fun main(args: Array<String>) {
    val item = getValue(3)
    val item2 = getValue("hello")
    println("$item, $item2")

    val upperBoundsClass = UpperBoundsClass2<String>()
}