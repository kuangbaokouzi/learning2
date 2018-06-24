package com.laowuandhisfriends.kotlin.grammer4

import java.util.*

class Student(map: Map<String, Any?>) {
    val name: String by map
    val address: String by map
    val age: Int by map
    val birthday: Date by map
}

class Student2(map: MutableMap<String, Any?>) {
    var address: String by map
}

fun main(args: Array<String>) {
    val student = Student(mapOf(
            "name" to "Kate",
            "address" to "NewYork",
            "age" to 20,
            "birthday" to Date()
    ))

    println(student.name)
    println(student.address)
    println(student.age)
    println(student.birthday)

    val map: MutableMap<String, Any?> = mutableMapOf(
            "address" to "Shanghai"
    )

    val student2 = Student2(map)
    println(map["address"])
    println(student2.address)
    student2.address = "Beijing"
    println(map["address"])
    println(student2.address)
}