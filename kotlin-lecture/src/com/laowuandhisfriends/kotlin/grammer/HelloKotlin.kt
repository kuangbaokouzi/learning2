package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    println(add(1, 2))
    println(add(1,2,3))
    printSum(1,2)
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun add(a: Int, b: Int, c: Int) = a + b + c

fun printSum(a:Int, b:Int){
    println(a + b)
    println("$a + $b = ${a + b}")
}