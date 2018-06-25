package com.laowuandhisfriends.kotlin.grammer5

inline fun myCalculate(a:Int, b:Int) = a + b

fun main(args: Array<String>) {
    println(myCalculate(1,2))
}