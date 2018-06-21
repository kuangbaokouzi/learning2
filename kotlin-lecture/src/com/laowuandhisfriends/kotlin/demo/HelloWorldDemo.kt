package com.laowuandhisfriends.kotlin.demo

import java.util.function.Consumer

fun main(args: Array<String>) {
    println("Hello World!");

    var list: List<String> = listOf("hello", "world","hello world");
    for(str in list){
        println(str)
    }

    println("==================")

    list.forEach(Consumer{println(it)})

    println("==================")

    list.forEach(System.out::println)
}