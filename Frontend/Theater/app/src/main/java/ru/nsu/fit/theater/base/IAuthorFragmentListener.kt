package ru.nsu.fit.theater.base

interface IAuthorFragmentListener {

    fun onErrorAuthorsListLoading()

    fun onErrorAuthorLoading()

    fun createAuthor()

    fun viewAuthor(id: Int)

    fun saveAuthor()
}