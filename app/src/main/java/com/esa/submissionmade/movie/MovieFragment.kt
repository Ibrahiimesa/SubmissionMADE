package com.esa.submissionmade.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.esa.submissionmade.core.data.Resource
import com.esa.submissionmade.core.domain.model.Movie
import com.esa.submissionmade.core.ui.MovieAdapter
import com.esa.submissionmade.core.utils.SortUtils
import com.esa.submissionmade.databinding.FragmentMovieBinding
import com.esa.submissionmade.detail.DetailActivity
import com.esa.submissionmade.utils.DataState
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), View.OnClickListener {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private var sort = SortUtils.NEWEST

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        setData(sort)

        with(binding.rvMovie) {
            this.setHasFixedSize(true)
            this.adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.fabNew.setOnClickListener(this)
        binding.fabOld.setOnClickListener(this)
        binding.fabPopular.setOnClickListener(this)
    }

    private fun setData(sort: String) {
        movieViewModel.getMovie(sort).observe(viewLifecycleOwner, moviesObserver)
    }

    private val moviesObserver = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> setDataState(DataState.LOADING)
                is Resource.Success -> {
                    setDataState(DataState.SUCCESS)
                    movieAdapter.setData(movies.data)
                }
                is Resource.Error -> {
                    setDataState(DataState.ERROR)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setDataState(state: DataState) {
        when (state) {
            DataState.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.notFound.visibility = View.GONE
            }
            DataState.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
            }
            DataState.ERROR -> {
                binding.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.fabNew -> {
                binding.menu.close(true)
                sort = SortUtils.NEWEST
                setData(sort)
            }
            binding.fabOld -> {
                binding.menu.close(true)
                sort = SortUtils.OLDEST
                setData(sort)
            }
            binding.fabPopular -> {
                binding.menu.close(true)
                sort = SortUtils.POPULARITY
                setData(sort)
            }
        }
    }
}