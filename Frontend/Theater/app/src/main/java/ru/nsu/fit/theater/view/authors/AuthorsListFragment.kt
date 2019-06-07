package ru.nsu.fit.theater.view.authors

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.authors_list_item.view.*
import kotlinx.android.synthetic.main.fragment_authors_list.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.IAuthorFragmentListener
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.authors.IAuthorsController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.retrofit.model.AuthorData
import java.text.SimpleDateFormat
import java.util.*

class AuthorsListFragment : BaseFragment() {

    companion object {
        val TAG = "AuthorsListFragment"
        fun newInstance(): BaseFragment {
            return AuthorsListFragment()
        }
    }

    private val listener by lazy {
        context as? IAuthorFragmentListener
                ?: throw Exception("Activity must implement " +
                        "IAuthorFragmentListener")
    }

    override val layoutId: Int
        get() = R.layout.fragment_authors_list

    private lateinit var authorsList: List<AuthorData>
    private lateinit var adapter: AuthorsAdapter

    private val controller = App.controllers[IController.Type.AUTHORS] as RetrofitAuthorsController

    override fun onResume() {
        super.onResume()

        controller.getAuthors(object : IAuthorsController.IGetAuthorsCallback {
            override fun onAuthorsLoaded(authors: List<AuthorData>) {
                authorsList = authors
                adapter = AuthorsAdapter(authorsList)
                configRecycler()
            }

            override fun onError() {
                listener.onErrorAuthorsListLoading()
            }
        })
        authors_fab.setOnClickListener { listener.createAuthor() }

    }

    private fun configRecycler() {
        authors_recycler_view.layoutManager = LinearLayoutManager(activity)
        authors_recycler_view.adapter = adapter
    }


    private inner class AuthorsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val authorsListDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        fun bind(item: AuthorData) {
            view.apply {
                author_item_name.text = item.name
                author_item_surname.text = item.surname
                author_item_birth.text = authorsListDateFormat.format(item.birthDate)
                if (item.deathDate == null) {
                    author_item_dash.visibility = View.GONE
                    author_item_death.visibility = View.GONE
                } else {
                    author_item_death.text = authorsListDateFormat.format(item.deathDate)
                }
                author_item_country_name.text = item.countryName
            }
            view.setOnClickListener {
                //listener.viewAuthor(item.id)
            }
        }
    }

    private inner class AuthorsAdapter(private var items: List<AuthorData>) :
            RecyclerView.Adapter<AuthorsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AuthorsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.playbill_item, parent, false)
            return AuthorsViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(viewHolder: AuthorsViewHolder, position: Int) {
            viewHolder.bind(items[position])
        }
    }
}