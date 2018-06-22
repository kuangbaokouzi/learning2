package com.laowuandhisfriends.kotlin.grammer3

class OuterClass2 {
    private val string: String = "welcome"

    inner class InnerClass {
        fun innerMethod() = this@OuterClass2.string + " hello world"
    }
}

fun main(args: Array<String>) {
    println(OuterClass2().InnerClass().innerMethod())
}