package com.pattern.renew.client

import com.pattern.renew.template.coffee.Coffee
import com.pattern.renew.factory.CoffeeFactory

class ClientCoffeeFactory(private val m_factory : CoffeeFactory)
{
    fun createCoffee() : Coffee
    {
        return m_factory.createCoffee(m_factory.createBeans())
    }
}