package com.laowuandhisfriends.kotlin.grammer2

interface Producer<out T> {
    fun produce(): T
}

interface Consumer<in T> {
    fun consume(t: T)
}

interface ProducerConsumer<T> {
    fun produce(): T
    fun consume(t: T)
}

open class Fruit

open class Apple : Fruit()

class ApplePear : Apple()

class FruitProducer : Producer<Fruit> {
    override fun produce(): Fruit {
        println("produce fruit")
        return Fruit()
    }
}

class AppleProducer : Producer<Apple> {
    override fun produce(): Apple {
        println("produce apple")
        return Apple()
    }
}

class ApplePearProducer : Producer<ApplePear> {
    override fun produce(): ApplePear {
        println("produce apple pear")
        return ApplePear()
    }
}

class Human : Consumer<Fruit> {
    override fun consume(t: Fruit) {
        println("consume fruit")
    }
}

class Man : Consumer<Apple> {
    override fun consume(t: Apple) {
        println("consume apple")
    }
}

class Boy : Consumer<ApplePear> {
    override fun consume(t: ApplePear) {
        println("consume apple pear")
    }
}

fun main(args: Array<String>) {
    val producer1: Producer<Fruit> = FruitProducer()
    val producer2: Producer<Fruit> = AppleProducer()
    val producer3: Producer<Fruit> = ApplePearProducer()

    val consumer1: Consumer<ApplePear> = Human()
    val consumer2: Consumer<ApplePear> = Man()
    val consumer3: Consumer<ApplePear> = Boy()
}