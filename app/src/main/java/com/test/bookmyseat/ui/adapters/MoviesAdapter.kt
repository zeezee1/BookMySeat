package com.test.bookmyseat.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.bookmyseat.R
import com.test.bookmyseat.callbacks.BookSeatClickListener
import com.test.bookmyseat.models.MovieModel

class MoviesAdapter(var movies: ArrayList<MovieModel>, public var listener: BookSeatClickListener) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
        holder.tvBookSeat.setOnClickListener {
            listener.onBookSeatClicked(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBanner = itemView.findViewById<ImageView>(R.id.ivBanner)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvBookSeat = itemView.findViewById<TextView>(R.id.tvViewDetails)
        val tvReleaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        fun bindView(movie: MovieModel) {
            Glide.with(ivBanner.context).load("https://image.tmdb.org/t/p/w780" + movie.backdropPath).into(ivBanner)
            tvTitle.text = movie.originalTitle
            tvReleaseDate.text = "Release date: ${movie.releaseDate}"
            ratingBar.rating = movie.getRating()
        }
    }
}