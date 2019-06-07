package ru.nsu.fit.theater.view.authors

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_author_view.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.base.IAuthorFragmentListener
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.authors.IAuthorsController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.retrofit.model.AuthorData
import ru.nsu.fit.theater.retrofit.model.IdAuthorData
import java.text.SimpleDateFormat
import java.util.*

class ViewAuthorFragment : BaseFragment() {

    companion object {
        const val TAG = "ViewAuthorFragment"
        private const val INSTANCE_MESSAGE_KEY = "arguments for ViewAuthorFragment"

        fun newInstance(id: Int): BaseFragment {
            return ViewAuthorFragment().apply {
                val arguments = Bundle()
                arguments.putInt(INSTANCE_MESSAGE_KEY, id)
                setArguments(arguments)
            }

        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_author_view

    private val listener by lazy {
        context as? IAuthorFragmentListener
                ?: throw Exception("Activity must implement " +
                        "IAuthorFragmentListener")
    }

    private val controller = App.controllers[IController.Type.AUTHORS] as RetrofitAuthorsController

    val authorDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(INSTANCE_MESSAGE_KEY, -1)
        if (id == null || id == -1) {
            listener.onErrorAuthorLoading()
            return
        }
        controller.getAuthor(id, object : IAuthorsController.IGetAuthorCallback {
            override fun onAuthorLoaded(author: IdAuthorData) {
                author_name.text = author.name
                author_surname.text = author.surname
                author_birth.text = authorDateFormat.format(author.birthDate)
                if (author.deathDate != null) {
                    author_death.text = authorDateFormat.format(author.deathDate)
                } else {
                    author_death.text = "Пока не умер"
                }
                author_country_name.text = author.countryName
            }

            override fun onError() {
               Toast.makeText(activity, "Error while loading author view", Toast.LENGTH_SHORT).show()
            }
        })
    }
}