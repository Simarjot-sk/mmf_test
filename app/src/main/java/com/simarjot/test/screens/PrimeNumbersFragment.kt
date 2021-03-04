package com.simarjot.test.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.simarjot.test.R

class PrimeNumbersFragment : Fragment(R.layout.fragment_prime_numbers) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.prime_rv)
        recyclerView.adapter = PrimeAdapter(primes)
    }

    private val primes: List<Int>
        get() {
            val n = 541
            val li: MutableList<Int> = mutableListOf(2)
            for (num in 2..n + 1) {
                for (i in 2..num) {
                    if (num % i == 0) {
                        if (num == i) li.add(num) else break
                    }
                }
            }
            li.removeAt(0)
            return li
        }
}