package com.pattern.renew.factory

import com.pattern.renew.template.beans.Beans
import com.pattern.renew.template.beans.JamaicaBeans
import com.pattern.renew.template.coffee.AmericanoCoffee
import com.pattern.renew.template.coffee.Coffee

class AmericanoCoffeeFactory : CoffeeFactory
{
    override fun createCoffee(beans: Beans) : Coffee
    {
        return AmericanoCoffee(beans)
    }

    override fun createBeans(): Beans
    {
        return JamaicaBeans()
    }
}