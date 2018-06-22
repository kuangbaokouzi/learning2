package com.laowuandhisfriends.kotlin.grammer

open class Parent(name: String, age: Int) {
}

class Child(name: String, age: Int) : Parent(name, age) {

}

open class Parent2(name: String) {

}

class Child2 : Parent2 {
    constructor(name: String) : super(name) {
    }
}