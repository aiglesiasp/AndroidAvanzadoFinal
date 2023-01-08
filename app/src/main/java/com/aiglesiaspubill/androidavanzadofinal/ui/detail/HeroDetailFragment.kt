package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.aiglesiaspubill.androidavanzadofinal.R
import com.aiglesiaspubill.androidavanzadofinal.databinding.FragmentHeroDetailBinding
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Argumentos que le paso por los layouts
    private val args: HeroDetailFragmentArgs by navArgs()
    //ViewModel
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
        observeDetailState()
        viewModel.getHeroDetail(args.hero.name)
    }

    //OBSERAR ESTADO DETALLE
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
                }
                is DetailState.NetworkError -> println("NETWORK ERROR")
            }
        }
    }

    //PINTAR HEROE NE PANTALLA
    private fun setHero(hero: Hero) {
        binding.nameHeroDetail.text = hero.name
        binding.descriptionHeroDetail.text = hero.description
        binding.imageHeroDetail.load(hero.photo)
    }

    //PINTAR LOCALIZACIONES HEROE
    private fun setHeroLocations(hero: Hero) {
        val pos = 0
        for (i in 0..(hero.locations?.size ?: 0)) {

        }
        binding.locationsDateShow.text = "FECHA: ${hero.locations?.first()?.dateShow}"
        binding.locationsLatitud.text = "LATITUD: ${hero.locations?.first()?.latitud}"
        binding.locationsLongitud.text = "LONGITUD: ${hero.locations?.first()?.longitud}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}