package com.pattern.method.factory

import com.pattern.method.const.CoffeeRecipe
import com.pattern.method.template.coffee.AmericanoCoffee
import com.pattern.method.template.coffee.Coffee
import com.pattern.method.template.coffee.LatteCoffee

class CoffeeFactory
{
    fun createCoffee(recipe : CoffeeRecipe) : Coffee
    {
        return when(recipe)
        {
            CoffeeRecipe.LATTE ->
            {
                return LatteCoffee(BeansFactory().createBeans(recipe))
            }
            CoffeeRecipe.AMERICANO ->
            {
                return AmericanoCoffee(BeansFactory().createBeans(recipe))
            }
        }
    }
}