package com.pattern.old.template.coffee

import com.pattern.old.template.beans.Beans

abstract class Coffee
{
    protected abstract var m_benas : Beans
    protected lateinit var m_price : String
    protected lateinit var m_coffeeName : String

    abstract fun getCoffeeName() : String
    abstract fun getCoffeeDetailInfo() : String
}