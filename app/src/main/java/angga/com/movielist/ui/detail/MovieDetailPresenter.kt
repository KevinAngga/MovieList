package angga.com.movielist.ui.detail

import angga.com.movielist.network.service.MovieService
import angga.com.movielist.network.service.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter (var movieDetailView: MovieDetailView) {

    private var mCompositeDisposable  =  CompositeDisposable()

    fun getMovieDetail (movieId : String) {
        movieDetailView.onLoadingData()
        mCompositeDisposable.add(
            ServiceGenerator.createService(MovieService::class.java)
                .getMoviewDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieDetailView.onFinishLoadingData()
                    movieDetailView.onGetMovieDetail(it)
                }, {
                    movieDetailView.onFinishLoadingData()
                    movieDetailView.onNetworkFailed(it.localizedMessage)
                })
        )
    }
}