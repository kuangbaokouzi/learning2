package com.laowuandhisfriends.kotlin.grammer5

val multiply: (Int, Int) -> Int = { a, b -> a * b }
val add: (Int, Int) -> Int = { a, b -> a + b }
val sub = { a: Int, b: Int -> a - b }
val myAction = { println("hello world") }

val mayResultNull: (Int, Int) -> Int? = { _, _ -> null }
val functionMaybeNull: ((Int, Int) -> Int)? = null