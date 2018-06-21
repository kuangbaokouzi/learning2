package com.laowuandhisfriends.kotlin.grammer

import com.laowuandhisfriends.kotlin.grammer.multiply as doubleMultiply

fun main(args: Array<String>) {
    val a: Int = 1;
    val b = 2;
    println("$a, $b")

    var c: Int
    c = 3

    var d = 3
    d = 4

    println("$c, $d")

    var x = 10
    var y: Byte = x.toByte()

    println("$x, $y")

    println(doubleMultiply(2,3))

    val m = intArrayOf(1,2,3)
    m.set(0,4)
    m[2] = 5

    for(item in m){
        println(item)
    }
}