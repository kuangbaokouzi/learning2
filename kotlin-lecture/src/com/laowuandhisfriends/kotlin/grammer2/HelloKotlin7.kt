package com.laowuandhisfriends.kotlin.grammer2

class Star<out T>

class Star2<in T> {
    fun setValue(t: T) {

    }
}

class Star3<T>(private var t: T) {
    fun setValue(t: T) {

    }

    fun getValue(): T = this.t
}

fun main(args: Array<String>) {
    val star: Star<Number> = Star<Int>()
    val star2: Star<*> = star

    val star3: Star2<Int> = Star2<Number>()
    val star4: Star2<*> = star3
//    star4.setValue(1)

    val star5: Star3<String> = Star3("hello")
    val star6: Star3<*> = star5

    println(star6.getValue())
//    star6.setValue("world")

    val list: MutableList<*> = mutableListOf("hello", "world", "welcome")
    println(list[0])
//    list[0] = "bye"
}