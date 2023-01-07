package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aiglesiaspubill.androidavanzadofinal.R
import com.aiglesiaspubill.androidavanzadofinal.databinding.FragmentHeroDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HeroDetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: HeroDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.heroName.text = args.hero.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}