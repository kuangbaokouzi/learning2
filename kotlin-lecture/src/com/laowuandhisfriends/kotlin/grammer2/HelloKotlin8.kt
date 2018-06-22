package com.laowuandhisfriends.kotlin.grammer2

class MyStorage<out T>(private var t: T){
    fun getValue():T = this.t

    fun setValue(t: @UnsafeVariance T){
        this.t = t
    }
}

fun main(args: Array<String>) {
    var myStorage: MyStorage<Int> = MyStorage(5)
    val myStorage2: MyStorage<Any> = myStorage

    println(myStorage2.getValue())
    myStorage2.setValue("hello")
    println(myStorage2.getValue())
}