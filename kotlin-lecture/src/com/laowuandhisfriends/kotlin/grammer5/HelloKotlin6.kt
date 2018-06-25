package com.laowuandhisfriends.kotlin.grammer5

fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int) = calculate(a, b)

fun main(args: Array<String>) {
    println(myCalculate(1, 2) { a, b -> a + b })
    println(myCalculate(1, 2) { a, b -> a - b })
    println(myCalculate(1, 2) { a, b -> a * b })
    println(myCalculate(1, 2) { a, b -> a / b })
}