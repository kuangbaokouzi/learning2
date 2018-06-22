package com.laowuandhisfriends.kotlin.grammer

class EmptyClass

class MyClass(username: String) {
    private val username: String = username.toUpperCase()

    init {
        println(username)
    }
}

fun main(args: Array<String>) {
    var myClass = MyClass("zhangsan")
    println(myClass)
}