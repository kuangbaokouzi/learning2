package com.laowuandhisfriends.kotlin.grammer5

fun main(args: Array<String>) {
    var sum = ""
    val strings = arrayOf("hello", "world", "bye")
    strings.filter { it.length > 3 }.forEach { sum += it }
    println(sum)
}