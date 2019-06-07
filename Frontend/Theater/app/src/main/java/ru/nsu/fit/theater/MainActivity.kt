package ru.nsu.fit.theater

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.nsu.fit.theater.base.IAuthorFragmentListener
import ru.nsu.fit.theater.view.authors.AuthorsListFragment
import ru.nsu.fit.theater.view.authors.CreateAuthorFragment
import ru.nsu.fit.theater.view.authors.ViewAuthorFragment
import ru.nsu.fit.theater.view.playbill.PlaybillFragment
import ru.nsu.fit.theater.view.tickets.TicketsActivity

class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        IAuthorFragmentListener {

    companion object {
        const val OPEN_TICKETS_LIST = 7845
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val spectacleIntent = Intent(this, CreateSpectacle::class.java)
            startActivity(spectacleIntent)
        }
        supportActionBar?.title = "Афиша"

        onPlaybillCreate()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_actors -> {
                // Handle the camera action
            }
            R.id.nav_authors -> {
                openAuthorsList()
            }
            R.id.nav_playbill -> {
                onPlaybillCreate()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == OPEN_TICKETS_LIST) {
            //supportFragmentManager.popBackStack()
        }
    }

    override fun onErrorAuthorsListLoading() {
        Toast.makeText(this, "Error while loading author view", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack(AuthorsListFragment.TAG, POP_BACK_STACK_INCLUSIVE)
    }

    override fun onErrorAuthorLoading() {
        Toast.makeText(this, "Error while loading author view", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack(ViewAuthorFragment.TAG, POP_BACK_STACK_INCLUSIVE)
    }

    private fun onPlaybillCreate() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaybillFragment.newInstance())
                .addToBackStack(PlaybillFragment.TAG)
                .commit()
    }

    fun openTicketsList() {
        val intent = Intent(this, TicketsActivity::class.java)
        startActivityForResult(intent, OPEN_TICKETS_LIST)
    }

    override fun createAuthor() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreateAuthorFragment.newInstance())
                .addToBackStack(ViewAuthorFragment.TAG)
                .commit()
    }

    override fun viewAuthor(id: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewAuthorFragment.newInstance(id))
                .addToBackStack(ViewAuthorFragment.TAG)
                .commit()
    }

    private fun openAuthorsList() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthorsListFragment.newInstance())
                .addToBackStack(AuthorsListFragment.TAG)
                .commit()
    }

    override fun saveAuthor() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthorsListFragment.newInstance())
                .addToBackStack(AuthorsListFragment.TAG)
                .commit()
    }

}
