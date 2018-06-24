package com.laowuandhisfriends.kotlin.grammer4

val myLazyValue: Int by lazy(LazyThreadSafetyMode.NONE) {
    println("hello world")
    30
}

fun main(args: Array<String>) {
    println(myLazyValue)
    println(myLazyValue)
}