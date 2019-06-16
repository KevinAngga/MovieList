package angga.com.movielist.ui.list

import angga.com.movielist.network.service.MovieService
import angga.com.movielist.network.service.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListPresenter (var movieListView: MovieListView) {

    private var mCompositeDisposable  =  CompositeDisposable()

    fun getMovie(movieTitle : String, page : Int) {
        movieListView.onLoadingData()
        mCompositeDisposable.add(
            ServiceGenerator.createService(MovieService::class.java)
            .getMoviesByTitle(movieTitle, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieListView.onFinishLoadingData()
                if (it.isSuccess()) {
                    movieListView.onGetMovieListData(it)
                }else {
                    movieListView.onFailedGetData()
                }
            },{
                movieListView.onFinishLoadingData()
                movieListView.onNetworkFailed(it.localizedMessage)
            }))
    }
}