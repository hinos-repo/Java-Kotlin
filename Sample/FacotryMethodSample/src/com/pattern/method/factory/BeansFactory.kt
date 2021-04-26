package com.pattern.method.factory

import com.pattern.method.const.CoffeeRecipe
import com.pattern.method.template.beans.Beans
import com.pattern.method.template.beans.JamaicaBeans
import com.pattern.method.template.beans.SantosBeans

class BeansFactory
{
    fun createBeans(recipe : CoffeeRecipe) : Beans
    {
        return when(recipe)
        {
            CoffeeRecipe.LATTE ->
            {
                return SantosBeans()
            }
            CoffeeRecipe.AMERICANO ->
            {
                return JamaicaBeans()
            }
            else -> throw IllegalArgumentException("생성 할 수 없는 객체명입니다.")
        }
    }
}