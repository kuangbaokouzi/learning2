package com.laowuandhisfriends.kotlin.grammer

class Student (private val username: String,
               private val age: Int,
               private var address: String){
    fun printStudent(){
        println("username: $username, age: $age, address: $address")
    }
}

class Teacher(val username: String = "Clark"){

}

fun main(args: Array<String>) {
    Student("Smith", 30, "NewYork").printStudent()
    println(Teacher().username)
    println(Teacher("Allen").username)
}