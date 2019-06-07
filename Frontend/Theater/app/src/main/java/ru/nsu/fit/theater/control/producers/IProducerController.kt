package ru.nsu.fit.theater.control.producers

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.ProducerData

interface IProducerController {

    interface IgetProducerByIdCallback: ICallback {
        fun onProducerLoaded(producer: ProducerData)
    }

    interface IgetProducerByFioCallback: ICallback {
        fun onProducerLoaded(producer: ProducerData)
    }

    interface IgetProducersCallback: ICallback {
        fun onProducersLoaded(producers: List<ProducerData>)
    }

    interface ICreateProducerCallback: ICallback {
        fun onProducerCreated()
    }

    interface IUpdateProducerCallback: ICallback {
        fun onProducerUpdated()
    }

    interface IDeleteProducerCallback: ICallback {
        fun onProducerDeleted()
    }

    fun getProducerById(id: Int, callback: IgetProducerByIdCallback)
    fun getProducerByFio(fio: String, callback: IgetProducerByFioCallback)
    fun getProducers(callback: IgetProducersCallback)
    fun createProducer(data: ProducerData, callback: ICreateProducerCallback)
    fun deleteProducer(id: Int, callback: IDeleteProducerCallback)
    fun updateProducer(data: ProducerData, callback: IUpdateProducerCallback)
}