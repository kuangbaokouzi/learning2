package com.laowuandhisfriends.kotlin.grammer4

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PropertyDelegate : ReadOnlyProperty<People, String> {
    override fun getValue(thisRef: People, property: KProperty<*>): String = "hello world"
}

class PeopleLauncher {
    operator fun provideDelegate(thisRef: People, property: KProperty<*>): ReadOnlyProperty<People, String> {
        println("provideDelegate")
        when (property.name) {
            "name", "address" -> return PropertyDelegate()
            else -> throw Exception("not valid name")
        }
    }
}

class People {
    val name: String by PeopleLauncher()
    val address: String by PeopleLauncher()
}

fun main(args: Array<String>) {
    val people = People()
    println(people.name)
    println(people.address)
}