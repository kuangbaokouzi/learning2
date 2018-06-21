package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    var x = 10
    var y = 20

    var max: Int
    var min: Int

    if (x > y) {
        max = x
        min = y
    } else {
        max = y
        min = x
    }
    println("max=$max, min=$min")

    var max2 = if (x > y) x else y
    var min2 = if (x > y) y else x

    println("max=$max, min=$min")

    var max3 = if (x > y) {
        println("x > y")
        x
    } else {
        println("x <= y")
        y
    }

    var min3 = if(x > y){
        println("x > y")
        y
    }else{
        println("x <= y")
        x
    }

    println("max=$max, min=$min")
}