package com.laowuandhisfriends.kotlin.grammer5

fun main(args: Array<String>) {
    fun(x: Int, y: Int) = x + y

    val strings = arrayOf("hello", "world", "bye")
    strings.filter(fun(item): Boolean = item.length > 3)
            .forEach(fun(item) { println(item) })
}