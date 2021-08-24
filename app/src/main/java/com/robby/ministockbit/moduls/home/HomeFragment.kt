package com.robby.ministockbit.moduls.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robby.ministockbit.adapter.HomeAdapter
import com.robby.ministockbit.databinding.FragmentHomeBinding
import com.robby.ministockbit.data.local.CryptoEntity
import com.robby.ministockbit.model.CryptoViewModel
import com.robby.ministockbit.utils.LoadingState
import com.robby.ministockbit.utils.setGone
import com.robby.ministockbit.utils.setVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : HomeAdapter
    private var page = 2
    private val userViewModel by viewModel<CryptoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.srlRefresh.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        userViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    showError(it.msg ?: "Error page")
                }
                LoadingState.Status.RUNNING -> {
                    startLoading()
                }
                LoadingState.Status.SUCCESS -> {
                    stopLoading()
                    userViewModel.data.observe(viewLifecycleOwner, Observer {
                        setRecyclerView(it)
                    })
                }
            }
        })
    }

    private fun setRecyclerView(data : List<CryptoEntity>) {
        with(binding) {
            adapter = HomeAdapter(data)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
            rvHome.layoutManager = layoutManager
            rvHome.adapter = adapter
        }
    }

    private fun startLoading() {
        with(binding) {
            shimmerViewContainer.setVisible()
            shimmerViewContainer.startShimmer()
            rvHome.setGone()
        }
    }

    private fun stopLoading() {
        with(binding) {
            shimmerViewContainer.stopShimmer()
            shimmerViewContainer.setGone()
            rvHome.setVisible()
            srlRefresh.isRefreshing = false
        }
    }

    private fun showError(msg:String) {
        with(binding) {
            shimmerViewContainer.stopShimmer()
            shimmerViewContainer.setGone()
            tvError.setText(msg)
            srlRefresh.isRefreshing = false
        }
    }
}