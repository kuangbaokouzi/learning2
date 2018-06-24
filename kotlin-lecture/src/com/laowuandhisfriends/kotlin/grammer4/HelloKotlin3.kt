package com.laowuandhisfriends.kotlin.grammer4

import kotlin.reflect.KProperty

class MyDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "$thisRef, your delegated property name is ${property.name}"
    operator fun setValue(thisRef: Any?,property: KProperty<*>, value:String) = println("$thisRef, new value is $value")
}

class MyPropertyClass {
    var string: String by  MyDelegate()
}

fun main(args: Array<String>) {
    val myPropertyClass = MyPropertyClass()
    myPropertyClass.string = "hello world"
    println(myPropertyClass.string)
}