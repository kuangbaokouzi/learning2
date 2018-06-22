package com.laowuandhisfriends.kotlin.grammer

fun method(){

}

open class Clazz() {
    val a = 2
    private val b = 3
    protected open val c = 4
    internal val d = 5
}

class SubClass : Clazz() {

}

fun main(args: Array<String>) {
    var subClass = SubClass()
    println(subClass.a)
//    println(subClass.b)
//    println(subClass.c)
    println(subClass.d)
}