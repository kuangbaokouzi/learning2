package com.laowuandhisfriends.kotlin.grammer

object MyObject {
    fun method() {
        println("method")
    }
}

class MyTest {
    companion object MyObject {
        var a = 10
        @JvmStatic
        fun method() {
            println("method invoked!")
        }
    }
}

fun main(args: Array<String>) {
    MyTest.MyObject.method()
    println(MyTest.a)
    MyTest.method()

    println(MyTest.MyObject.javaClass)
}