package com.example.madlevel6task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.model.MovieResponse
import kotlinx.android.synthetic.main.fragment_movie_info.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieInfoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFragmentResult()
        activity?.title = "Movie information"

    }

    private fun observeFragmentResult() {
        setFragmentResultListener(REQ_MOVIE_KEY) { _, bundle ->
            bundle.getParcelable<MovieResponse.Movie>(BUNDLE_MOVIE_KEY)?.let { setElements(it) }
        }
    }

    private fun setElements(movie: MovieResponse.Movie) {
        movieTitle.text = movie.title
        movieDate.text = movie.release_date
        movieDescription.text = movie.overview
        movieScore.text = movie.vote_average.toString()
        context?.let { Glide.with(it).load(movie.getMovieImageUrl(movie.backdrop_path)).into(movieBanner) }
        context?.let { Glide.with(it).load(movie.getMovieImageUrl(movie.poster_path)).into(movieImage) }

    }
}