package angga.com.movielist.network.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(@SerializedName("Actors") var actors : String,
                               @SerializedName("Plot") var plot : String,
                               @SerializedName("Awards") var awards : String,
                               @SerializedName("Title") var title : String,
                               @SerializedName("Runtime") var runtime : String,
                               @SerializedName("Poster") var imageUrl : String,
                               @SerializedName("Genre") var genre : String,
                               @SerializedName("Production") var production : String,
                               @SerializedName("Year") var year : String,
                               @SerializedName("Website") var websiteUrl : String)