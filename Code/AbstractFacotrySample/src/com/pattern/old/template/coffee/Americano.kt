package com.pattern.old.template.coffee

import com.pattern.old.template.beans.Beans

class Americano(override var m_benas: Beans) : Coffee()
{
    init
    {
        m_coffeeName = "아메리카노"
        m_price = "1500"
    }

    override fun getCoffeeName() : String
    {
        return m_coffeeName
    }

    override fun getCoffeeDetailInfo(): String
    {
        return "이 커피의 이름은 $m_coffeeName 입니다. 원두는 ${m_benas.getBeansName()}를 사용했습니다. 가격은 $m_price 입니다.강추!!"
    }
}