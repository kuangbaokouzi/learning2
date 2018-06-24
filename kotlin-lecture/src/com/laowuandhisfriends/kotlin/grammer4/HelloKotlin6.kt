package com.laowuandhisfriends.kotlin.grammer4

import kotlin.properties.Delegates

class Person {
    var age: Int by Delegates.observable(20) { property, oldValue, newValue -> println("${property.name}, oldValue: $oldValue, newValue: $newValue") }
}

class Person2 {
    var age: Int by Delegates.vetoable(20) { property, oldValue, newValue ->
        when {
            oldValue <= newValue -> true
            else -> false
        }
    }
}

fun main(args: Array<String>) {
    val person = Person()
    person.age = 30
    person.age = 40

    val person2 = Person2()
    println(person2.age)
    person2.age = 40
    println(person2.age)
    person2.age = 30
    println(person2.age)
}