package ru.nsu.fit.theater.control.spectacles

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.model.Spectacle
import ru.nsu.fit.theater.retrofit.model.SpectacleData
import java.sql.Timestamp

interface ISpectaclesController: IController {
    interface ICreateSpectacleCallback: ICallback {
        fun onSpectcleCreated()
    }

    interface ICreateAuthorOfSpectacleCallback: ICallback {
        fun onAuthorCreated()
    }

    interface IGetSpectacleCallback: ICallback {
        fun onSpectacleLoaded(spectacle: Spectacle)
    }

    interface IGetSpectaclesCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)
    }

    interface IGetSpectaclesOfGenreCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)

    }

    interface IGetSpectaclesOfAuthorCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)

    }

    interface IGetSpectaclesOfCountryCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)
    }

    interface IGetSpectaclesOfCenturyCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)
    }

    interface IGetSpectaclesOfPeriodCallback: ICallback {
        fun onSpectaclesLoaded(spectacles: List<Spectacle>)
    }

    interface IUpdateSpectacleCallback: ICallback {
        fun onSpectacleUpdated()
    }

    interface IDeleteSpectacleCallback: ICallback {
        fun onSpectacleDeleted()
    }

    fun createSpectacle(data: SpectacleData, callback: ICreateSpectacleCallback)

    fun createAuthorOfSpectacle(
            authId: Int,
            specName: String,
            callback: ICreateAuthorOfSpectacleCallback
    )

    fun getSpectacle(id: Int, callback: IGetSpectacleCallback)

    fun getSpectacles(callback: IGetSpectaclesCallback)

    fun getSpectaclesOfGenre(genre: String, callback: IGetSpectaclesOfGenreCallback)

    fun getSpectaclesOfAuthor(id: Int, callback: IGetSpectaclesOfAuthorCallback)

    fun getSpectaclesOfCountry(name: String, callback: IGetSpectaclesOfCountryCallback)

    fun getSpectaclesOfCentury(cent: Int, callback: IGetSpectaclesOfCenturyCallback)

    fun getSpectaclesOfPeriod(
            from: Timestamp,
            to: Timestamp,
            callback: IGetSpectaclesOfPeriodCallback
    )

    fun updateSpectacle(id: Int, data: SpectacleData, callback: IUpdateSpectacleCallback)

    fun deleteSpectacle(id: Int, callback: IDeleteSpectacleCallback)
}