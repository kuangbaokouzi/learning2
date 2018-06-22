package com.laowuandhisfriends.kotlin.grammer

class TheClass {
    lateinit var name: String

    fun init() {
        this.name = "Sun"
    }

    fun print() {
        println(this.name)
    }
}

fun main(args: Array<String>) {
    var theClass = TheClass()
    theClass.init()
    theClass.print()
}