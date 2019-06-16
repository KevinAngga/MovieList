package angga.com.movielist.ui.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import angga.com.movielist.R
import angga.com.movielist.network.response.MovieListResponse
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity(), MovieListView {

    companion object {
        const val MINIMUM_LENGTH = 4
        const val ONE_VALUE = 1
        const val ZERO_VALUE = 0
    }

    private var isLoading = false
    private var currentPage = ONE_VALUE
    private var totalCurrentPage = ZERO_VALUE

    override fun onLoadingData() {
        pgLoading.visibility = View.VISIBLE
    }

    override fun onFinishLoadingData() {
        pgLoading.visibility = View.GONE
    }

    override fun onGetMovieListData(movieListResponse: MovieListResponse) {
        movieListAdapter.setData(movieListResponse.movieList)
    }

    override fun onLimitPage() {

    }

    override fun onFailedGetData() {
        Log.e("error","gagal cuy")
    }

    override fun onNetworkFailed(message: String) {
        Log.e("error", ""+message)
    }

    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter()
    }

    private val movieListPresenter by lazy {
        MovieListPresenter(this)
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == movieListAdapter.itemCount - ONE_VALUE) {
                        currentPage++
                        if (currentPage > 4) {
                            Snackbar.make(rlParent, "End of the movie limit", Snackbar.LENGTH_SHORT).show()
                        }
                        else {
                            loadData(currentPage)
                        }
                    }
                }
            }
        }

        rvMovieList.apply {
            layoutManager = linearLayoutManager
            adapter = movieListAdapter
            isNestedScrollingEnabled = false
            addOnScrollListener(loadingListener)
        }


        btnSearch.setOnClickListener {
            loadFirstData()
        }

    }

    private fun loadData(page: Int) {
        currentPage = page
        movieListPresenter.getMovie(svMovieTitle.query.toString(), page)
    }

    private fun loadFirstData() {
        isLoading = false
        currentPage = ONE_VALUE
        movieListAdapter.clearData()
        loadData(currentPage)
    }
}
