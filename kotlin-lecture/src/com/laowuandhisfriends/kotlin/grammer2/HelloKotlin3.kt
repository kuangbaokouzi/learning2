package com.laowuandhisfriends.kotlin.grammer2

class MyGeneric<T>(var t: T)

class MyClass<out T, in M>(t: T, m: M) {
    private var t: T
    private var m: M

    init {
        this.t = t
        this.m = m
    }

    fun get(): T = this.t
    fun set(m: M) {
        this.m = m
    }
}

fun myTest(myClass: MyClass<String, Number>) {
    var myObject: MyClass<Any, Int> = myClass
    println(myObject.get())
}

fun main(args: Array<String>) {
    var myGeneric = MyGeneric("hello world")
    println(myGeneric.t)
    myTest(MyClass("welcome", 20))
}