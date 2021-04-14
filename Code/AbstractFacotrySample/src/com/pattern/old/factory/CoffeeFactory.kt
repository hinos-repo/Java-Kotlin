package com.pattern.old.factory

import com.pattern.old.const.CoffeeRecipe
import com.pattern.old.template.beans.Beans
import com.pattern.old.template.coffee.Americano
import com.pattern.old.template.coffee.Coffee
import com.pattern.old.template.coffee.Latte
import java.lang.IllegalArgumentException

class CoffeeFactory
{
    fun createCoffee(recipe : CoffeeRecipe) : Coffee
    {
        return when(recipe)
        {
            CoffeeRecipe.LATTE ->
            {
                return Latte(BeansFactory().createBeans(recipe))
            }
            CoffeeRecipe.AMERICANO ->
            {
                return Americano(BeansFactory().createBeans(recipe))
            }
        }
    }
}