package com.laowuandhisfriends.kotlin.grammer4

interface MyInterface {
    fun myPrint()
}

class MyInterfaceImpl(val string: String) : MyInterface {
    override fun myPrint() {
        println("welcome, $string")
    }
}

class MyClass(myInterface: MyInterface) : MyInterface by myInterface{
    override fun myPrint() {
        println("hello world")
    }
}

fun main(args: Array<String>) {
    MyClass(MyInterfaceImpl("Kate")).myPrint()
}