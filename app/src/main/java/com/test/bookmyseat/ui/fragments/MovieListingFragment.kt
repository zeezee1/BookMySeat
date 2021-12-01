package com.test.bookmyseat.ui.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.bookmyseat.Constants.HIDE_PROGRESS
import com.test.bookmyseat.Constants.SHOW_PROGRESS
import com.test.bookmyseat.R
import com.test.bookmyseat.callbacks.BookSeatClickListener
import com.test.bookmyseat.databinding.FragmentMovieListingBinding
import com.test.bookmyseat.models.MovieModel
import com.test.bookmyseat.ui.adapters.MoviesAdapter
import com.test.bookmyseat.view_models.MoviesListViewModel

class MovieListingFragment: BaseFragment<FragmentMovieListingBinding>(), BookSeatClickListener {

    private val movieListVM: MoviesListViewModel by viewModels()
    private var adapter: MoviesAdapter? = null

    override fun fragmentLayout(): Int {
        return R.layout.fragment_movie_listing
    }

    override fun initViews() {
        registerObservers()
        movieListVM.getMovies()
    }

    private fun registerObservers() {
        movieListVM.movies.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        movieListVM.loadingState.observe(viewLifecycleOwner) {
            when(it) {
                SHOW_PROGRESS -> showProgressDialog()
                HIDE_PROGRESS -> hideProgressDialog()
            }
        }
    }

    private fun setAdapter(movies: ArrayList<MovieModel>) {
        binding.rvMovies.layoutManager = LinearLayoutManager(activity)
        if (adapter == null || binding.rvMovies.adapter == null) {
            adapter = MoviesAdapter(movies, this)
            binding.rvMovies.adapter = adapter
        } else {
            adapter?.notifyDataSetChanged()
        }
    }

    companion object {
        fun getInstance(): MovieListingFragment {
            return MovieListingFragment()
        }
    }

    override fun onBookSeatClicked(movie: MovieModel) {
        replaceFragment(MovieDetailFragment.getInstance(movie))
    }
}