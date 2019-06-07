package ru.nsu.fit.theater.controller

import ru.nsu.fit.theater.model.PlaybillItem
import java.util.*

class PlaybillController(){
    private val baseProductsList = ArrayList<PlaybillItem>().apply {
//        add(PlaybillItem(Date(2019, 6, 8, 13, 0), true, "Красная шапочка", 0))
//        add(PlaybillItem(Date(2019, 6, 28, 13, 0), true, "Летучая мышь", 6))
//        add(PlaybillItem(Date(2019, 6, 16, 13, 0), false, "Бал в честь Жака", 12))
//        add(PlaybillItem(Date(2019, 6, 14, 13, 0), true, "Оловянный солдатик", 0))
    }

    fun getSpectacles(): ArrayList<PlaybillItem>{
        println(baseProductsList)
        return baseProductsList
    }
}