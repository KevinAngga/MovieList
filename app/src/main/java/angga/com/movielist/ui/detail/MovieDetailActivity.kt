package angga.com.movielist.ui.detail

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import android.widget.Toast
import angga.com.movielist.R
import angga.com.movielist.network.response.MovieDetailResponse
import angga.com.movielist.ui.list.MovieListAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    private  var movieTitle = ""

    companion object {
        const val NEGATIVE_VALUE = -1
    }

    override fun onLoadingData() {
        pgLoading.visibility = View.VISIBLE
    }

    override fun onFinishLoadingData() {
        pgLoading.visibility = View.GONE
    }

    override fun onGetMovieDetail(movieDetailResponse: MovieDetailResponse) {
        tvMovieActor.text = movieDetailResponse.actors
        tvMovieTitle.text = movieDetailResponse.title
        tvMovieGenre.text = movieDetailResponse.genre
        tvMovieRuntime.text = movieDetailResponse.runtime
        tvMovieWebsite.text = movieDetailResponse.websiteUrl
        Glide.with(this).load(movieDetailResponse.imageUrl)
            .into(ivDetailImage)
        tvMoviePlot.text = movieDetailResponse.plot
        tvMovieYear.text = movieDetailResponse.year
        movieTitle = movieDetailResponse.title
    }

    override fun onNetworkFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val movieDetailPresenter by lazy {
        MovieDetailPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieID = intent.getStringExtra(MovieListAdapter.MOVIE_ID)
        movieDetailPresenter.getMovieDetail(movieID)

        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)

        ivToolbarBack.setOnClickListener { onBackPressed() }

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, p1 ->
            if (p1 == appBarLayout?.totalScrollRange?.times(NEGATIVE_VALUE)) {
                collapsingToolbar.title = movieTitle
            } else {
                collapsingToolbar.title = ""
            }
        })

    }
}
