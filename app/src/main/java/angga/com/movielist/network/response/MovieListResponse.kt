package angga.com.movielist.network.response

import angga.com.movielist.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse (@SerializedName("Response") var response : String,
                              @SerializedName("Search") var movieList: MutableList<Movie>){

    object responseType {
        const val SUCCESS = "True"
    }

    fun isSuccess(): Boolean {
        return response.contains(responseType.SUCCESS, ignoreCase = true)
    }

}