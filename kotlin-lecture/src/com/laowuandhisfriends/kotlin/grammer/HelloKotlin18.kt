package com.laowuandhisfriends.kotlin.grammer

open class BaseClass {
    open fun method() {

    }
}

abstract class ChildClass : BaseClass() {
    abstract override fun method()
}