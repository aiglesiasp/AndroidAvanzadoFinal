package com.aiglesiaspubill.androidavanzadofinal.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiglesiaspubill.androidavanzadofinal.R
import com.aiglesiaspubill.androidavanzadofinal.databinding.ItemHeroListBinding
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class HeroListAdapter: ListAdapter<Hero, HeroListAdapter.HeroViewHolder>(HeroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero_list, parent, false)
        val binding = ItemHeroListBinding.bind(view)
        return  HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    //CLASE VIEWHOLDER
    class HeroViewHolder (private val binding: ItemHeroListBinding) : RecyclerView.ViewHolder(binding.root) {

        private  val name: TextView = itemView.findViewById(R.id.hero_name)

        fun bind(hero: Hero) {
            with(binding) {
                heroName.text = hero.name
                heroImage.load(hero.photo)
            }
        }
    }

    //Callback Item
    class HeroDiffCallback: DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }
    }
}