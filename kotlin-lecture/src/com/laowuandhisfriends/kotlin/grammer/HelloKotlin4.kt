package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    println(convertString2Int("ab"))
    printMultiply("a", "2")
}

fun convertString2Int(str: String): Int? {
    try {
        return str.toInt()
    } catch (ex: NumberFormatException) {
        return null
    }
}

fun printMultiply(str1: String, str2: String) {
    val str12Int = convertString2Int(str1)
    var str22Int = convertString2Int(str2)

    if (null != str12Int && null != str22Int) {
        println(str12Int * str22Int)
    } else {
        println("params not int")
    }
}

fun printMultiply2(str1: String, str2: String) {
    val str12Int = convertString2Int(str1)
    var str22Int = convertString2Int(str2)

    // println(str12Int * str22Int)

    if (null == str12Int) {
        println("str1 not int")
    } else if (null == str22Int) {
        println("str2 not int")
    } else {
        println(str12Int * str22Int)
    }
}