package com.pattern.old.template.beans

abstract class Beans
{
    protected lateinit var m_beansName : String

    fun getBeansName() : String
    {
        return m_beansName
    }
}