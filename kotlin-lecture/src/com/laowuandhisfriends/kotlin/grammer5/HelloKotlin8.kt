package com.laowuandhisfriends.kotlin.grammer5

fun main(args: Array<String>) {
    val strings = arrayOf("hello", "world", "welcome", "hello world", "bye")
    strings.filter { it.contains("h") }.forEach { println(it) }
    println("===========")
    strings.filter { it.length > 4 }.forEach { println(it) }
    println("===========")
    strings.filter { it.endsWith("d") }.map { it.toUpperCase() }.forEach { println(it) }
}