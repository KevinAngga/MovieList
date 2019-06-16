package angga.com.movielist.ui.detail

import angga.com.movielist.network.response.MovieDetailResponse

interface MovieDetailView {
    fun onLoadingData()
    fun onFinishLoadingData()
    fun onGetMovieDetail(movieDetailResponse: MovieDetailResponse)
    fun onNetworkFailed(message : String)
}