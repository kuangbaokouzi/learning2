package com.laowuandhisfriends.kotlin.grammer

open class Fruit {
    open fun printName() {
        println("Fruit")
    }

    fun printDate() {
        println("1 month")
    }
}

class Apple : Fruit() {
    override fun printName() {
        println("apple")
    }
}

open class Orange : Fruit() {
    final override fun printName() {
        println("orange")
    }
}

fun main(args: Array<String>) {
    var apple = Apple()
    apple.printName()
    apple.printDate()
}