package com.pattern.old

import com.pattern.old.const.CoffeeRecipe
import com.pattern.old.factory.CoffeeFactory

object Main
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val factory = CoffeeFactory()

        val coffee1 = factory.createCoffee(CoffeeRecipe.LATTE)
        println("1. ${coffee1.getCoffeeName()}")
        println(coffee1.getCoffeeDetailInfo())

        println()
        println()

        val coffee2 = factory.createCoffee(CoffeeRecipe.AMERICANO)
        println("2. ${coffee2.getCoffeeName()}")
        println(coffee2.getCoffeeDetailInfo())
    }
}