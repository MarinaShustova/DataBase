package ru.nsu.fit.theater.control.authors

import ru.nsu.fit.theater.retrofit.model.AuthorData

class RetrofitAuthorsController: IAuthorsController {
    override fun createAuthor(data: AuthorData, callback: IAuthorsController.ICreateAuthorCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthor(id: Int, callback: IAuthorsController.IGetAuthorCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuhtors(callback: IAuthorsController.IGetAuthorsCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorsOfCountry(name: String, callback: IAuthorsController.IGetAuthorsOfCountryCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorsOfCentury(century: Int, callback: IAuthorsController.IGetAuthorsOfCenturyCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateAuthor(id: Int, data: AuthorData, callback: IAuthorsController.IUpdateAuthorCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAuthor(id: Int, callback: IAuthorsController.IDeleteAuthorCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}