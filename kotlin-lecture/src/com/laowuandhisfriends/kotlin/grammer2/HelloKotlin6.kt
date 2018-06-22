package com.laowuandhisfriends.kotlin.grammer2

import kotlin.test.assertTrue

fun copy(from: Array<out Any>, to: Array<Any>) {
    assertTrue(from.size == to.size)

    for (i in from.indices) {
        to[i] = from[i]
    }
}

fun setValue(to: Array<in String>, index: Int, value: String) {
    to[index] = value
}

fun main(args: Array<String>) {
    val from: Array<Int> = arrayOf(1, 2, 3, 4)
    val to: Array<Any> = Array(4, { "hello" + it })
    copy(from, to)

    for (it in to) {
        println(it)
    }

    val array: Array<Any> = Array(4, { _ -> "hello" })

    setValue(array, 2, "welcome")

    for (it in array) {
        println(it)
    }
}