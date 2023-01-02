package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiglesiaspubill.androidavanzadofinal.databinding.FragmentHeroListBinding
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.ui.commons.CoroutineViewModel
import com.aiglesiaspubill.androidavanzadofinal.ui.commons.HeroListAdapter
import java.util.UUID

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = HeroListAdapter()

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
            adapter.submitList(getHeros(1000))

            fab.setOnClickListener {

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getHeros(size: Int): List<Hero> {
        val heros = mutableListOf<Hero>()

        for (i in 0..size) {
            heros.add(Hero(UUID.randomUUID().toString(), "Super Heroe $i"))
        }

        return heros
    }
}