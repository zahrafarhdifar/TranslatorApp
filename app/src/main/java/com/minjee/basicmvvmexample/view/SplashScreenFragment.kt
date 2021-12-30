package com.minjee.basicmvvmexample.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.minjee.basicmvvmexample.databinding.FragmentSplashScreenBinding

class SplashScreenFragment: Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToMain()
    }

    private fun navigateToMain() {
        Handler().postDelayed({
            val action = SplashScreenFragmentDirections.navigateToMainFragment()
            view!!.findNavController().navigate(action)
        }, 2000)
    }

}