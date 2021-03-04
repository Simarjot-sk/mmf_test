package com.simarjot.test.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.test.R

class PrimeAdapter(private val primeList: List<Int>) : RecyclerView.Adapter<PrimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PrimeViewHolder.create(parent)

    override fun onBindViewHolder(holder: PrimeViewHolder, position: Int) {
        val prime = primeList[position]
        holder.bind(prime)
    }

    override fun getItemCount() = primeList.size
}

class PrimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.tv)
    fun bind(primeNumber: Int) {
        textView.text = primeNumber.toString()
    }

    companion object {
        fun create(parent: ViewGroup): PrimeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_prime, parent, false)
            return PrimeViewHolder(view)
        }
    }
}