package angga.com.movielist.ui.list

import angga.com.movielist.network.response.MovieListResponse

interface MovieListView {
    fun onLoadingData()
    fun onFinishLoadingData()
    fun onGetMovieListData(movieListResponse: MovieListResponse)
    fun onLimitPage()
    fun onFailedGetData()
    fun onNetworkFailed(message : String)
}