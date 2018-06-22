package com.laowuandhisfriends.kotlin.grammer3

class MyClass{
    private var myObject = object {
        fun output(){
            println("output invoked")
        }
    }

    fun myTest(){
        myObject.output()
    }
}

fun main(args: Array<String>) {
    MyClass().myTest()
}