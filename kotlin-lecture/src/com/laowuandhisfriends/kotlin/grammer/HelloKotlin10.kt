package com.laowuandhisfriends.kotlin.grammer

fun main(args: Array<String>) {
    var a: String = "hello \\n world"
    println(a)

    var b: String = """hello
        world,,123!@%^#&*$(\n/;'""
    """
    println(b)
}