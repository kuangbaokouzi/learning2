package com.laowuandhisfriends.kotlin.grammer

class Person(username: String) {
    private var username: String
    private var age: Int
    private var address: String

    init {
        println(username)
        this.username = username
        this.age = 20
        this.address = "Shanghai"
    }

    constructor(username: String, age: Int) : this(username) {
        println("$username, $age")

        this.username = username
        this.age = age
        this.address = "Beijing"
    }

    constructor(username: String, age: Int, address: String) : this(username, age) {
        println("$address")

        this.address = address
    }

    fun printPerson() {
        println("username: ${this.username}, age: ${this.age}, address: ${this.address}")
    }
}

fun main(args: Array<String>) {
    val person = Person("kate")
    val person2 = Person("liming", 30)
    val person3 = Person("walt", 25, "Wuhan")

    person.printPerson()
    person2.printPerson()
    person3.printPerson()
}