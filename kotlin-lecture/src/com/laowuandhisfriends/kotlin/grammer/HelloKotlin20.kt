package com.laowuandhisfriends.kotlin.grammer

class ThePerson(address: String, name: String) {
    val age: Int
        get() = 20

    var address: String = address
        get() {
            println("getAddress invoked!")
            return field
        }
        set(value) {
            println("setAddress invoked!")
            field = value
        }
}

fun main(args: Array<String>) {
    var person = ThePerson("Shanghai", "Walk")
    println(person.age)
    println(person.address)
    person.address = "NewYork"
    println(person.address)
}