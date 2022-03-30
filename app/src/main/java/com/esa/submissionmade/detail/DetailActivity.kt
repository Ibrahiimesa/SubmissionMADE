
package com.esa.submissionmade.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.esa.submissionmade.BuildConfig
import com.esa.submissionmade.R
import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        }
    }

    private fun showDetailMovie(detailMovie: Movie) {
        with(binding) {
            tvTitleDetail.text = detailMovie.title
            tvLanguage.text = detailMovie.originalLanguage
            tvPopularity.text = detailMovie.popularity.toString()
            tvOverview.text = detailMovie.overview
            tvRelease.text = detailMovie.releaseDate
            tvScore.text = detailMovie.voteAverage.toString()

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + detailMovie.posterPath)
                .centerCrop()
                .into(posterDetail)

            Glide.with(this@DetailActivity)
                .load(BuildConfig.IMAGE_URL + detailMovie.backdropPath)
                .into(ivBackdrop)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavoDetail.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavoDetail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fabFavoDetail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}