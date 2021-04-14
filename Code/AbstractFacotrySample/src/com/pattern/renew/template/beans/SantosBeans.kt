package com.pattern.renew.template.beans

class SantosBeans : Beans // 라떼 원두는 이걸로 사용한다고 가정
{
    private val m_beansName = "산투스 원두"
    override fun getBeansName(): String
    {
        return m_beansName
    }
}