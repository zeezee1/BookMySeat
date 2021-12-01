package com.test.bookmyseat.ui.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.bookmyseat.Constants
import com.test.bookmyseat.R
import com.test.bookmyseat.databinding.FragmentMovieDetailsBinding
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MovieModel
import com.test.bookmyseat.models.ProductionCompany
import com.test.bookmyseat.ui.adapters.CompaniesAdapter
import com.test.bookmyseat.view_models.MovieDetailsViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    lateinit var movie: MovieModel
    private val movieDetailsVM: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getSerializable(Constants.BundleKeys.MOVIE) as MovieModel
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_movie_details
    }

    override fun initViews() {
        binding.movie = movie
        registerObservers()
        movieDetailsVM.getMovieDetails(movie.id)
    }

    private fun registerObservers() {
        movieDetailsVM.movie.observe(viewLifecycleOwner) {
            bindMovieDetails(it)
        }
    }

    private fun bindMovieDetails(movieDetails: MovieDetailsModel?) {
        if(movieDetails != null) {
            binding.movieDetails = movieDetails
            binding.adapter = CompaniesAdapter(movieDetails.productionCompanies as ArrayList<ProductionCompany>)
        }
    }

    companion object {
        fun getInstance(movie: MovieModel): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(Constants.BundleKeys.MOVIE, movie)
            fragment.arguments = bundle
            return fragment
        }
    }
}