package com.laowuandhisfriends.kotlin.grammer2

import kotlin.test.assertTrue

class ParameterizedClass<A>(private val value: A) {
    fun getValue(): A {
        return this.value
    }
}

class ParameterizedProducer<out T>(private val value: T) {
    fun getValue(): T {
        return this.value
    }
}

class ParameterizedConsumer<in T> {
    fun toString(value: T): String {
        return value.toString()
    }
}

fun main(args: Array<String>) {
    val result = ParameterizedClass("hello world").getValue()
    assertTrue(result is String)
    val myRef: ParameterizedProducer<Any> = ParameterizedProducer("welcome")
    assertTrue(myRef is ParameterizedProducer<Any>)
    val myRef2: ParameterizedConsumer<Int> = ParameterizedConsumer<Number>()
    assertTrue(myRef2 is ParameterizedConsumer<Int>)
}