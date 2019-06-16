package angga.com.movielist.model

import com.google.gson.annotations.SerializedName

data class Movie (@SerializedName("Title") var title : String,
                  @SerializedName("Year") var year : String,
                  @SerializedName("imdbID") var imdbId : String,
                  @SerializedName("Type") var type : String,
                  @SerializedName("Poster") var posterUrl : String)