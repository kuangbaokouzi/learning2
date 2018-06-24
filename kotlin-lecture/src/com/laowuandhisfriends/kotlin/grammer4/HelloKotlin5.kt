package com.laowuandhisfriends.kotlin.grammer4

import kotlin.properties.Delegates

class MyPerson {
    var address: String by Delegates.notNull()
}

fun main(args: Array<String>) {
    val myPerson = MyPerson()
    myPerson.address = "Shanghai"
    println(myPerson.address)
}