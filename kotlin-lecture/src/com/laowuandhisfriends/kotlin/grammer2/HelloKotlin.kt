package com.laowuandhisfriends.kotlin.grammer2

data class Person(val name: String, var age: Int, var address: String)

data class Person2(val name: String = "", var age: Int = 0, val address: String = "")

fun main(args: Array<String>) {
    var person = Person("Kate", 20, "LOS")
    println(person)
    var person2 = person.copy(address = "Flor")
    println(person2)
    var (name, age, address) = person
    println("$name, $age, $address")
}