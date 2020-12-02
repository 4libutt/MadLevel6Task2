package com.example.madlevel6task2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.model.MovieResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private val movies: List<MovieResponse.Movie>, private val onClick: (MovieResponse.Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onClick(movies[adapterPosition])
            }
        }

        fun dataBind(movie: MovieResponse.Movie) {
            itemView.movieNumber.text = "%s.".format((movies.indexOf(movie)+1).toString())
            Glide.with(context).load(movie.getMovieImageUrl(movie.poster_path)).into(itemView.movieImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(movies[position])
    }
}