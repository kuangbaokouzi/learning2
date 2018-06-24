package com.laowuandhisfriends.kotlin.grammer3

fun main(args: Array<String>) {
    var i = 100

    var myObject = object {
        fun method(){
            i++
        }
    }

    myObject.method()
    println(i)
}