package com.pattern.old.factory

import com.pattern.old.const.CoffeeRecipe
import com.pattern.old.template.beans.Beans
import com.pattern.old.template.beans.JamaicaBeans
import com.pattern.old.template.beans.SantosBeans

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