package ru.nsu.fit.theater.control.genres

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.GenreData

interface IGenresController: IController {
    interface ICreateGenreCallback: ICallback {
        fun onGenreCreated()
    }

    interface IGetGenreCallback: ICallback {
        fun onGenreLoaded(genre: GenreData)
    }

    interface IGetGenresCallback: ICallback {
        fun onGenresLoaded(genres: List<GenreData>)
    }

    interface IUpdateGenreCallback: ICallback {
        fun onGenreUpdated()
    }

    interface IDeleteGenreCallback: ICallback {
        fun onGenreDeleted()
    }

    fun createGenre(name: String, callback: ICreateGenreCallback)

    fun getGenre(id: Int, callback: IGetGenreCallback)

    fun getGenres(callback: IGetGenresCallback)

    fun updateGenre(data: GenreData, callback: IUpdateGenreCallback)

    fun deleteGenre(id: Int, callback: IDeleteGenreCallback)
}