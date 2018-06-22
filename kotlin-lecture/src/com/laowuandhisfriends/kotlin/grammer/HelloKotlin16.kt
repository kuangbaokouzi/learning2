package com.laowuandhisfriends.kotlin.grammer

open class MyParent {
    open val name: String = "parent"
}

class MyChild : MyParent() {
    override val name: String = "child"
}

class MyChild2(override val name: String) : MyParent() {

}

open class MyParent3 {
    open fun method() {
        println("parent method")
    }

    open val name: String get() = "parent name"
}

class MyChild3 : MyParent3() {
    override fun method() {
        super.method()
        println("child method")
    }

    override val name: String
        get() = super.name + " and child name"
}

fun main(args: Array<String>) {
    var myChild3 = MyChild3();
    myChild3.method()
    println(myChild3.name)
}