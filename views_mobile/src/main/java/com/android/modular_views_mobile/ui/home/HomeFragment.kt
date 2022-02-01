package com.android.modular_views_mobile.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.modular_m_vm.models.data.Status
import com.android.modular_m_vm.viewmodels.HomeViewModel
import com.android.modular_views_mobile.databinding.FragmentHomeBinding
import com.android.modular_views_mobile.ui.base.BaseFragment
import timber.log.Timber

class HomeFragment : BaseFragment() {
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapter: PeopleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRV()
        initObservers()

        viewModel.getPeople()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("onConfigurationChanged")
    }

    private fun initRV() {
        adapter = PeopleAdapter()
        _binding?.rvHomePeople?.adapter = adapter
    }

    private fun initObservers() {
        viewModel.people.observe(viewLifecycleOwner) { data ->
            when (data.status) {
                Status.LOADING -> {
                    Timber.d("Loading")
                }
                Status.ERROR -> {
                    Timber.d("Error")
                }
                Status.SUCCESS -> {
                    Timber.d("Success ${data.data}")
                    data.data?.let { people ->
                        adapter?.setItems(people)
                    }
                }
            }
        }
    }
}