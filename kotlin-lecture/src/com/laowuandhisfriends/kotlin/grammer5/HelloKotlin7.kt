package com.laowuandhisfriends.kotlin.grammer5

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuffer()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element))
            sb.append(element)
    }
    return sb.toString()
}

fun main(args: Array<String>) {
    println("ab2cd3ef4gh".filter { it.isLetter() })
}