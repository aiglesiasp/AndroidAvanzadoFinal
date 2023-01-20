package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.aiglesiaspubill.androidavanzadofinal.R
import com.aiglesiaspubill.androidavanzadofinal.databinding.FragmentHeroDetailBinding
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!

    private val args: HeroDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHeroDetail(args.hero.name)
        observeDetailState()
    }

    private fun observeDetailState() {
        viewModel.stateDetail.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailState.Failure -> Toast.makeText(
                    requireContext(),
                    state.error,
                    Toast.LENGTH_LONG
                ).show()
                is DetailState.Succes -> {
                    setHero(state.hero)
                    setHeroLocations(state.hero)
                    setListeners(state.hero)
                }
                is DetailState.NetworkError -> println("NETWORK ERROR")
            }
        }
    }

    private fun setHero(hero: Hero) {
        binding.nameHeroDetail.text = hero.name
        binding.descriptionHeroDetail.text = hero.description
        binding.imageHeroDetail.load(hero.photo)
    }

    private fun setHeroLocations(hero: Hero) {
        binding.locationsDateShow.text = "FECHA: ${hero.locations?.first()?.dateShow}"
        binding.locationsLatitud.text = "LATITUD: ${hero.locations?.first()?.latitud}"
        binding.locationsLongitud.text = "LONGITUD: ${hero.locations?.first()?.longitud}"
    }

    private fun setHeroFavorite(hero: Hero) {
        when (hero.favorite) {
            true -> binding.buttonFavorite.setImageResource(R.drawable.ic_favorite)
            false -> binding.buttonFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

    private fun setListeners(hero: Hero) {
        with(binding) {
            buttonFavorite.setOnClickListener {
                viewModel.changeFavorite()
            }
        }
        setHeroFavorite(hero)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}