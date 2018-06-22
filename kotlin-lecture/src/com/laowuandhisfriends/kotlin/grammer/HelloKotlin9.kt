package com.laowuandhisfriends.kotlin.grammer

import java.util.*
import java.util.stream.Collectors

fun main(args: Array<String>) {
    var arrayList = listOf<String>("hello", "world", "welcome", "hello world", "goodbye")
    for (item in arrayList) {
        println(item)
    }

    println("====================")

    when {
        "world" in arrayList -> println("world in collection")
    }

    println("====================")

    var list = arrayList.filter { it.length > 5 }.map { it.toUpperCase() }.sorted().forEach { println(it) }
}