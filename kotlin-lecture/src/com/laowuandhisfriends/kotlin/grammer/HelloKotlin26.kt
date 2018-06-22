package com.laowuandhisfriends.kotlin.grammer

class DD {
    fun method() {
        println("dd method")
    }
}

class EE {
    fun method2() {
        println("ee method")
    }

    fun DD.hello() {
        method()
        method2()
        println("hello")
    }

    fun world(dd: DD) {
        dd.hello()
    }

    fun DD.output() {
        println(toString())
        println(this@EE.toString())
    }

    fun test(dd: DD) {
        dd.output()
    }
}

fun main(args: Array<String>) {
    EE().world(DD())
    EE().test(DD())
}