package com.esa.submissionmade.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.esa.submissionmade.core.ui.MovieAdapter
import com.esa.submissionmade.detail.DetailActivity
import com.esa.submissionmade.favorite.databinding.FragmentFavoriteBinding
import com.esa.submissionmade.favorite.di.favoriteModule
import com.esa.submissionmade.favorite.utils.StateFavorite
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { dataMovie ->
                binding.progressBar.visibility =View.GONE
                if (dataMovie.isNullOrEmpty()) {
                    setDataState(StateFavorite.BLANK)
                } else {
                    setDataState(StateFavorite.SUCCESS)
                }
                movieAdapter.setData(dataMovie)
            })

            with(binding.rvFavo) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun setDataState(state: StateFavorite) {
        when (state) {
            StateFavorite.BLANK -> {
                binding.notFound.visibility = View.VISIBLE
            }
            StateFavorite.SUCCESS -> {
                binding.notFound.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}