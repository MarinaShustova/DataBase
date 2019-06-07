package ru.nsu.fit.theater.control.authors

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.AuthorData

interface IAuthorsController: IController {
    interface ICreateAuthorCallback: ICallback {
        fun onAuthorCreated()
    }

    interface IGetAuthorCallback: ICallback {
        fun onAuthorLoaded(author: AuthorData)
    }

    interface IGetAuthorsCallback: ICallback {
        fun onAuthorsLoaded(authors: List<AuthorData>)
    }

    interface IGetAuthorsOfCountryCallback: ICallback {
        fun onAuthorsLoaded(authors: List<AuthorData>)
    }

    interface IGetAuthorsOfCenturyCallback: ICallback {
        fun onAuthorsLoaded(authors: List<AuthorData>)
    }

    interface IUpdateAuthorCallback: ICallback {
        fun onAuthorUpdated()
    }

    interface IDeleteAuthorCallback: ICallback {
        fun onAuthorDeleted()
    }

    fun createAuthor(data: AuthorData, callback: ICreateAuthorCallback)

    fun getAuthor(id: Int, callback: IGetAuthorCallback)

    fun getAuthors(callback: IGetAuthorsCallback)

    fun getAuthorsOfCountry(name: String, callback: IGetAuthorsOfCountryCallback)

    fun getAuthorsOfCentury(century: Int, callback: IGetAuthorsOfCenturyCallback)

    fun updateAuthor(id: Int, data: AuthorData, callback: IUpdateAuthorCallback)

    fun deleteAuthor(id: Int, callback: IDeleteAuthorCallback)
}