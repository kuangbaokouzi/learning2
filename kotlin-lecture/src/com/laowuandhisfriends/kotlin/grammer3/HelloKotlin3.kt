package com.laowuandhisfriends.kotlin.grammer3

class Person(val name: String, var age: Int) {

    private val string: String = "Person property"

    private inner class PersonFuture(var height: Int, var weight: Int) {

        private val string: String = "PersonFuture property"

        fun getPersonFuture() {
            val string: String = "getPersonFuture property"
            println("height: $height, weight: $weight")
            this@Person.method()

            println(string)
            println(this.string)
            println(this@Person.string)
        }
    }

    private fun method() {
        println("Person.method invoked!")
    }

    fun getPerson() {
        val personFuture = PersonFuture(120, 80)
        personFuture.getPersonFuture()
    }
}

fun main(args: Array<String>) {
    val person = Person("Kate", 20)
    person.getPerson()
}