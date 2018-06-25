package com.laowuandhisfriends.kotlin.grammer5

fun myPrint(name: String) {
    println(name)
}

fun add(a: Int, b: Int) = a + b

fun add2(a: Int, b: Int): Int {
    return a + b
}

fun <T> convert2List(vararg elements: T): List<T> {
    val result = ArrayList<T>()
    elements.forEach { result.add(it) }
    return result
}

fun main(args: Array<String>) {
    println(convert2List("hello", "world", "hello world"))
    var elements = arrayOf("Kate", "Allen", "Smith")
    println(convert2List("John", "Brown", *elements))
}