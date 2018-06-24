package com.laowuandhisfriends.kotlin.grammer3

class MyClass{
    private var myObject = object {
        fun output(){
            println("output invoked")
        }
    }

    fun myTest(){
        println(myObject.javaClass)
        myObject.output()
    }
}

class MyClass2{
    private fun method() = object{
        val str = "hello"
    }

    internal fun method2() = object {
        val str = "world"
    }

    fun test(){
        val str = method().str
        // val str2 = method2().str not work
    }
}

fun main(args: Array<String>) {
    MyClass().myTest()
}