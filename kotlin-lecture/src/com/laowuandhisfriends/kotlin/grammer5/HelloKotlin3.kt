package com.laowuandhisfriends.kotlin.grammer5

class InfixTest(private var a: Int) {
    infix fun add(b: Int) = this.a + b
}

fun main(args: Array<String>) {
    var infixTest = InfixTest(2)
    println(infixTest.add(5))
    println(infixTest add 5)
}