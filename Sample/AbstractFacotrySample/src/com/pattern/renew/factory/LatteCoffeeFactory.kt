package com.pattern.renew.factory

import com.pattern.renew.template.beans.Beans
import com.pattern.renew.template.beans.SantosBeans
import com.pattern.renew.template.coffee.Coffee
import com.pattern.renew.template.coffee.LatteCoffee

class LatteCoffeeFactory : CoffeeFactory
{
    override fun createCoffee(beans: Beans) : Coffee
    {
        return LatteCoffee(beans)
    }

    override fun createBeans(): Beans
    {
        return SantosBeans()
    }
}