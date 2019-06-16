package angga.com.movielist.network.service

import angga.com.movielist.network.response.MovieDetailResponse
import angga.com.movielist.network.response.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("?apikey=3ff55e6b")
    fun getMoviesByTitle( @Query("s") movieTitle : String, @Query("page") page : Int) : Observable<MovieListResponse>

    @GET("?apikey=3ff55e6b")
    fun getMoviewDetail(@Query("i") movieId : String) : Observable<MovieDetailResponse>

}