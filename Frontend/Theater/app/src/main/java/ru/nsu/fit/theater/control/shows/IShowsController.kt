package ru.nsu.fit.theater.control.shows

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.model.PlaybillItem
import ru.nsu.fit.theater.retrofit.model.IdShowData
import ru.nsu.fit.theater.retrofit.model.ShowData

interface IShowsController: IController {
    interface ICreateShowCallback: ICallback {
        fun onShowCreated()
    }

    interface IUpdateShowCallback: ICallback {
        fun onShowUpdated()
    }

    interface IGetShowCallback: ICallback {
        fun onShowLoaded(show: IdShowData)
    }

    interface IGetShowsCallback: ICallback {
        fun onShowsLoaded(shows: List<IdShowData>)
    }

    interface IDeleteShowCallback: ICallback {
        fun onShowDeleted()
    }

    interface IGetPlaybillsCallback: ICallback {
        fun onPlaybillsLoaded(playbills: List< PlaybillItem>)
    }

    fun createShow(data: ShowData, callback: ICreateShowCallback)

    fun updateShow(id: Int, data: ShowData, callback: IUpdateShowCallback)

    fun getShow(id: Int, callback: IGetShowCallback)

    fun getShows(callback: IGetShowsCallback)

    fun deleteShow(id: Int, callback: IDeleteShowCallback)

    fun getPlaybills(callback: IGetPlaybillsCallback)
}