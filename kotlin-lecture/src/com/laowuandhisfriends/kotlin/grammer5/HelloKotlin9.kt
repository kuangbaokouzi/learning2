package com.laowuandhisfriends.kotlin.grammer5

fun main(args: Array<String>) {
    val strings = arrayOf("hello", "world", "bye")
    strings.filter {
        val mayFilter = it.length > 3
        mayFilter
    }
    strings.filter {
        val mayFilter = it.length > 3
//        return@filter mayFilter
        return@filter it.length > 3
    }
}