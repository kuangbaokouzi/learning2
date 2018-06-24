package com.laowuandhisfriends.kotlin.grammer5

fun test(a: Int = 0, b: Int = 1) = println(a - b)

fun test2(a: Int = 0, b: Int) = println(a - b)

fun test3(a: Int = 0, b: Int, compute: (x: Int, y: Int) -> Unit) {
    compute(a, b)
}

fun test4(vararg strings: String) {
    println(strings.javaClass)
    strings.forEach { println(it) }
}

open class A {
    open fun method(a: Int, b: Int = 4) = a + b
}

class B : A() {
    override fun method(a: Int, b: Int) = a + b
}

fun main(args: Array<String>) {
    test()
    test(2)
    test(2, 3)
    test(b = 2)

    println(B().method(1))

    test2(b = 2)

    test3(2, 3, ::test)

    test3(2, 3) { a, b -> println(a - b) }

    test4("hello", "world", "welcome", "hello world")

    test4(strings = *arrayOf("hello", "world", "welcome", "hello world"))
}