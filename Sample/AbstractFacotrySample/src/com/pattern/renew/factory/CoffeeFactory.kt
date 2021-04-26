package com.pattern.renew.factory

import com.pattern.renew.template.beans.Beans
import com.pattern.renew.template.coffee.Coffee

interface CoffeeFactory
{
    fun createCoffee(beans : Beans) : Coffee
    fun createBeans() : Beans
}