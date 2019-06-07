package ru.nsu.fit.theater.view.authors

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_author_edit.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.base.IAuthorFragmentListener
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.authors.IAuthorsController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.control.countries.ICountriesController
import ru.nsu.fit.theater.control.countries.RetrofitCountriesController
import ru.nsu.fit.theater.retrofit.model.AuthorData
import ru.nsu.fit.theater.retrofit.model.CountryData
import java.sql.Date

class CreateAuthorFragment : BaseFragment() {

    companion object {
        const val TAG = "ViewAuthorFragment"

        fun newInstance(): BaseFragment {
            return CreateAuthorFragment()
        }
    }

    private val listener by lazy {
        context as? IAuthorFragmentListener
                ?: throw Exception("Activity must implement " +
                        "IAuthorFragmentListener")
    }

    override val layoutId: Int
        get() = R.layout.fragment_author_edit

    private val countriesController = App.controllers[IController.Type.COUNTRIES] as RetrofitCountriesController
    private val authorsController = App.controllers[IController.Type.AUTHORS] as RetrofitAuthorsController
    private lateinit var countriesList: List<CountryData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesController.getCountries(object : ICountriesController.IGetCountriesCallback {
            override fun onCountriesLoaded(countries: List<CountryData>) {
                countriesList = countries
                val arrayAdapter = ArrayAdapter(activity,
                        android.R.layout.simple_spinner_dropdown_item, countriesList)
                author_country_name_spinner.adapter = arrayAdapter
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        author_edit_button_save.setOnClickListener { saveAuthor() }
        author_edit_button_cancel.setOnClickListener { }
    }

    private fun saveAuthor() {
        if (isDataValid()) {
            val deathDate =
                    if (TextUtils.isEmpty(author_death_edit.text.toString())) {
                        null
                    } else {
                        Date.valueOf(author_death_edit.text.toString())
                    }
            authorsController.createAuthor(AuthorData(
                    author_name_edit.text.toString(),
                    author_surname_edit.text.toString(),
                    Date.valueOf(author_birth_edit.text.toString()),
                    deathDate,
                    author_country_name_spinner.selectedItem.toString()
            ), object : IAuthorsController.ICreateAuthorCallback {
                override fun onAuthorCreated() {
                    Toast.makeText(activity, "Successful loading author view", Toast.LENGTH_SHORT).show()
                }

                override fun onError() {
                    Toast.makeText(activity, "Error while loading author view", Toast.LENGTH_SHORT).show()
                }
            })
            listener.saveAuthor()
        }
    }


    private fun isDataValid(): Boolean {
        var validator = true

        if (TextUtils.isEmpty(author_name_edit.text.toString())) {
            author_name_edit.error = getString(R.string.validation_error_text)
            validator = false
        }
        if (TextUtils.isEmpty(author_surname_edit.text.toString())) {
            author_surname_edit.error = getString(R.string.validation_error_text)
            validator = false
        }
        if (TextUtils.isEmpty(author_birth_edit.text.toString())) {
            author_birth_edit.error = getString(R.string.validation_error_text)
            validator = false
        }
        try {
            Date.valueOf(author_birth_edit.text.toString())
        } catch (ex: IllegalArgumentException) {
            author_birth_edit.error = getString(R.string.date_validation_error_text)
            validator = false
        }
        if (!TextUtils.isEmpty(author_death_edit.text.toString())) {
            try {
                Date.valueOf(author_death_edit.text.toString())
            } catch (ex: IllegalArgumentException) {
                author_death_edit.error = getString(R.string.date_validation_error_text)
                validator = false
            }
        }
        try {
            val l: Long = Date.valueOf(author_birth_edit.text.toString()).time -
                    Date.valueOf(author_death_edit.text.toString()).time
            if (l < 0) {
                author_birth_edit.error = getString(R.string.date_order_error_text)
                author_death_edit.error = getString(R.string.date_order_error_text)
                validator = false
            }
        } catch (ex: IllegalArgumentException) {
        }
        return validator
    }

}