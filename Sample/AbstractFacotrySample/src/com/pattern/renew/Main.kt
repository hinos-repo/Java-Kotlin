package com.pattern.renew

import com.pattern.renew.factory.AmericanoCoffeeFactory
import com.pattern.renew.client.ClientCoffeeFactory
import com.pattern.renew.factory.LatteCoffeeFactory

object Main
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val clientFacotry1 = ClientCoffeeFactory(AmericanoCoffeeFactory())
        val coffee1 = clientFacotry1.createCoffee()

        println("1. ${coffee1.getCoffeeName()}")
        println(coffee1.getCoffeeDetailInfo())

        println()
        println()

        val clientFacotry2 = ClientCoffeeFactory(LatteCoffeeFactory())
        val coffee2 = clientFacotry2.createCoffee()
        println("2. ${coffee2.getCoffeeName()}")
        println(coffee2.getCoffeeDetailInfo())
    }
}