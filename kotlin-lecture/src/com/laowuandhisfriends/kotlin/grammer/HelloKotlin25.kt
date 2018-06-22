package com.laowuandhisfriends.kotlin.grammer

class ExtensionProperty

val ExtensionProperty.name: String
    get() = "hello"

class ExtensionCompanion{
    companion object MyObject
}
fun ExtensionCompanion.MyObject.method(){
    println("hello world")
}

fun main(args: Array<String>) {
    println(ExtensionProperty().name)
    ExtensionCompanion.method()
}