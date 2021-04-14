package com.pattern.renew.template.coffee

import com.pattern.renew.template.beans.Beans

class LatteCoffee(private val m_beans : Beans) : Coffee
{
    private val m_coffeeName : String = "라뗴"
    private val m_price : String = "2000"

    override fun getCoffeeName() : String
    {
        return m_coffeeName
    }

    override fun getCoffeeDetailInfo(): String
    {
        return "이 커피의 이름은 $m_coffeeName 입니다. 원두는 ${m_beans.getBeansName()}를 사용했습니다. 가격은 $m_price 입니다. 강추!!"
    }
}