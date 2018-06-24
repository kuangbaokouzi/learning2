package com.laowuandhisfriends.kotlin.grammer3

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

object MyObject{
    fun method() = "hello world"
}

object MyObject2: ActionListener{
    override fun actionPerformed(e: ActionEvent?) {
        println("actionPerformed")
    }
}

fun main(args: Array<String>) {
    println(MyObject.method())
}