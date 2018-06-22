package com.laowuandhisfriends.kotlin.grammer

class ExtensionTest {
    fun add(a: Int, b: Int) = a + b
    fun sub(a: Int, b: Int) = a - b
}

fun ExtensionTest.multiply(a: Int, b: Int) = a * b

open class AA

class BB : AA()

fun AA.a() = "aa"

fun BB.b() = "bb"

fun myPrint(aa: AA) {
    println(aa.a())
}

class CC {
    fun foo() {
        println("foo")
    }
}

fun CC.foo() {
    println("CC.foo")
}

fun Any?.toString(): String {
    if (null == this)
        return "null"
    return toString()
}

fun main(args: Array<String>) {
    var extensionTest = ExtensionTest()
    println(extensionTest.add(1, 2))
    println(extensionTest.sub(1, 2))
    println(extensionTest.multiply(1, 2))

    myPrint(BB())
    CC().foo()
}