package com.laowuandhisfriends.kotlin.grammer2

sealed class Calculator

class Add : Calculator()

class Sub : Calculator()

class Multiply : Calculator()

fun calculate(a: Int, b: Int, calculator: Calculator) = when (calculator) {
    is Add -> a + b
    is Sub -> a - b
    is Multiply -> a * b
}

fun main(args: Array<String>) {
    println(calculate(1, 2, Add()))
    println(calculate(1, 2, Sub()))
    println(calculate(1, 2, Multiply()))
}