package com.laowuandhisfriends.kotlin.grammer3

class OuterClass {
    private val string: String = "hello world"

    class NestedClass {
        fun nestedMethod() = "method"
    }
}

fun main(args: Array<String>) {
    println(OuterClass.NestedClass().nestedMethod())
}