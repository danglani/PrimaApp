package com.example.primaapp.ui.homepage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.example.primaapp.R
import com.example.primaapp.adapter.CountryAdapter
import com.example.primaapp.model.Country
import com.example.primaapp.network.CountryRepository
import com.example.primaapp.network.CountryServiceImpl

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.v7.widget.DividerItemDecoration
import com.example.primaapp.ui.DetailActivity
import com.example.primaapp.utils.Constants.Companion.COUNTRY_EXTRA
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.example.primaapp.utils.Constants.Companion.IMAGE_TRANSITION_NAME_EXTRA
import kotlinx.android.synthetic.main.activity_main.toolbar


class MainActivity : AppCompatActivity(), CountryAdapter.OnItemCLickListener, CountryListContract.View, View.OnClickListener, View.OnTouchListener, View.OnKeyListener{

    private var countries: MutableList<Country> = mutableListOf()
    private var allCountries: MutableList<Country> = mutableListOf()
    private var presenter: CountryListPresenter? = null
    private var adapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        rvCountryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCountryList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter = CountryAdapter(this)
        rvCountryList.adapter = adapter
        val service = CountryServiceImpl()
        presenter = CountryListPresenter(this, CountryRepository(service))

//        call api
        presenter!!.getCountries()

        setListeners()

    }

    private fun setListeners() {
        fab.setOnClickListener(this)
        etSearch.setOnTouchListener(this)
        etSearch.setOnKeyListener(this)
    }

    override fun showCountries(countryList: MutableList<Country>) {
        if(allCountries.size == 0) {
            allCountries.addAll(countryList)
        }
        countries = countryList
        adapter!!.setCountries(countryList)
    }

    override fun onCountryItemClick(position: Int, country: Country, flag: ImageView) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(COUNTRY_EXTRA, country)
        intent.putExtra(IMAGE_TRANSITION_NAME_EXTRA, ViewCompat.getTransitionName(flag))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            flag,
            ViewCompat.getTransitionName(flag)
        )
        startActivity(intent, options.toBundle())
    }


    override fun onClick(v: View?) {
        fab.visibility = View.GONE
        toolbar.visibility = View.GONE
        etSearch.visibility = View.VISIBLE
        etSearch.requestFocus()
        forceKeyboardToShow()
    }

    private fun forceKeyboardToShow() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {
//            clear search filter
            if (event.rawX >= (etSearch.right - etSearch.compoundDrawables[2].bounds.width()*2)) {
                hideKeyboard()
                etSearch.text.clear()
                etSearch.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
                presenter?.getCountries()
                return true
            }
        }
        return false
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event!!.action == KeyEvent.ACTION_DOWN){
//            Remove white space at end
            etSearch.text.replace(0, etSearch.text.length, etSearch.text.trim())

            var list = searchByLanguage()
//            If empty maybe is searching by language
            if(list.isEmpty()) {
                list = searchByRegion()
                if (list.isEmpty()) {
//                Wrong input
                    showSnackbar(getString(R.string.no_countries))
                } else {
                    showCountries(list)
                }
            }else{
                showCountries(list)
            }
            return true
        }
        return false
    }


    private fun searchByLanguage(): MutableList<Country> {
        val list = mutableListOf<Country>()
        for(country in allCountries){
            //            Find language between languages array
            for(lang in country.languages) {
                if (lang.name.equals(etSearch.text.toString(), true)) {
                    list.add(country)
                }
            }
        }
        return list
    }

    private fun searchByRegion(): MutableList<Country> {
        return allCountries.filter{ it.region.equals(etSearch.text.toString(), true) }.toMutableList()
    }


    override fun showSnackbar(error: String?) {
        Snackbar.make(coordinator, error.toString(), BaseTransientBottomBar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
