package angga.com.movielist.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import angga.com.movielist.R
import angga.com.movielist.model.Movie
import angga.com.movielist.ui.detail.MovieDetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_movie_item_list.view.*
import org.jetbrains.anko.startActivity

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    companion object {
        const val MOVIE_ID = "movieId"
    }

    var movieLists  = mutableListOf<Movie>()

    lateinit var context : Context

    override fun onCreateViewHolder(view: ViewGroup, p1: Int): MovieListViewHolder {
        context = view.context
        return MovieListViewHolder(LayoutInflater.from(view.context).inflate(R.layout.row_movie_item_list, view, false))
    }

    override fun getItemCount(): Int {
        return movieLists.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieLists[position])

        holder.itemView.setOnClickListener {
            context.startActivity<MovieDetailActivity>(MOVIE_ID to movieLists[position].imdbId)
        }
    }


    fun setData(movieList: MutableList<Movie>) {
        this.movieLists.addAll(movieList)
        notifyItemInserted(movieLists.size - 1)
    }


    fun clearData() {
        movieLists.clear()
        notifyDataSetChanged()
    }

    class MovieListViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind (movie: Movie) {
            with(itemView) {

                tvMovieTitle.text = movie.title

                tvType.text = movie.type

                tvYear.text = movie.year

                Glide.with(context)
                    .load(movie.posterUrl)
                    .into(ivMovie)
            }
        }
    }
}