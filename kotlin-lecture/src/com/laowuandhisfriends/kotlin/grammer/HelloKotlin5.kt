package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    println(convert2UpperCase("hello world"))
    println(convert2UpperCase(123))
}

fun convert2UpperCase(str: Any): String? {
    if (str is String) {
        return str.toUpperCase()
    } else {
        return null
    }
}