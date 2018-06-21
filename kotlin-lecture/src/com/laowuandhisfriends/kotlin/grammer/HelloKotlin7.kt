package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    println(print("hello"))
    println(print("world"))
    println(print("test"))

    println("==============")

    println(print2("hello"))
    println(print2("world"))
    println(print2("test"))

    println("==============")

    var a = 6
    var result = when (a) {
        1 -> {
            println("a = 1")
            10
        }
        2 -> {
            println("a = 2")
            20
        }
        3, 4, 5 -> {
            println("a = 3, 4, 5")
            30
        }
        in 6..10 -> {
            println("a = 6 .. 10")
            40
        }
        else -> {
            println("a = $a")
            a
        }
    }
    println(result)
}

fun print(string: String): String {
    return when (string) {
        "hello" -> "HELLO"
        "world" -> "WORLD"
        else -> "other"
    }
}

fun print2(string: String) =
        when (string) {
            "hello" -> "HELLO"
            "world" -> "WORLD"
            else -> "other"
        }