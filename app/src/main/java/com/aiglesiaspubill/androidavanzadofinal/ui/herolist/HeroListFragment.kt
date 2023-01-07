package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiglesiaspubill.androidavanzadofinal.databinding.FragmentHeroListBinding
import com.aiglesiaspubill.androidavanzadofinal.ui.commons.HeroListAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = HeroListAdapter {
        Log.d("Adapter click", it.toString())
        //Navegar al fragment detail
        //OPCION 1 sin pasar argumentos
        //findNavController().navigate(R.id.action_HeroeListFragment_to_DetailFragment)
        //OPCION 2
        //PASANDO ARGUMENTOS
        findNavController().navigate(HeroListFragmentDirections.actionHeroeListFragmentToDetailFragment(it))
    }

    private val viewModel: HeroesListViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            heroList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            heroList.adapter = adapter

            observeHeroesListState()
            viewModel.getHeroes()
        }
    }

    private fun observeHeroesListState() {
        viewModel.stateHeroes.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HeroListState.Failure -> Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                is HeroListState.Succes -> adapter.submitList(state.heros)
                is HeroListState.NetworkError -> println("NETWORK ERROR")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}