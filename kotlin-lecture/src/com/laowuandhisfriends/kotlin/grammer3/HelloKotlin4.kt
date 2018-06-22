package com.laowuandhisfriends.kotlin.grammer3

interface MyInterface {
    fun myPrint(a: Int)
}

abstract class MyAbstractClass {
    abstract val age: Int
    abstract fun printMyAbstractClassInfo()
}

fun main(args: Array<String>) {
    var myObject = object : MyInterface {
        override fun myPrint(a: Int) {
            println("a = $a")
        }
    }
    myObject.myPrint(8)

    var myObject2 = object : MyAbstractClass() {
        override val age: Int
            get() = 20

        override fun printMyAbstractClassInfo() {
            println("age = $age")
        }
    }
    myObject2.printMyAbstractClassInfo()

    var myObject3 = object {
        init {
            println("init myObject3")
        }

        var myProperty = "hello world"

        fun myMethod() = {
            println("myMethod invoked!$myProperty")
        }
    }
    myObject3.myMethod()

    var myObject4 = object : MyInterface, MyAbstractClass() {
        override fun myPrint(a: Int) {
            println("a = $a")
        }

        override val age: Int
            get() = 100

        override fun printMyAbstractClassInfo() {
            println("age = $age")
        }
    }
    myObject4.myPrint(99)
    myObject4.printMyAbstractClassInfo()
}