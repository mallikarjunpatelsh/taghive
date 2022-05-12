package com.example.taghive.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taghive.R
import com.example.taghive.fragment.adapter.CryptoListAdapter
import com.example.taghive.viewmodel.CryptoViewModel


class ListFragment : Fragment(), CryptoListAdapter.OnClick {
    private val TAG = "ListFragment"
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        var recyclerView = view.findViewById<RecyclerView>(R.id.crypto_recycler_view)
        cryptoViewModel.getCryptoList().observe(viewLifecycleOwner, Observer {
            view.findViewById<ProgressBar>(R.id.list_progrees_bar).visibility = View.GONE
            Log.i(TAG, "onViewCreated: "+it)
            val cryptoListAdapter = CryptoListAdapter(it, this)
            recyclerView.adapter = cryptoListAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        })



    }

    override fun onResume() {
        super.onResume()
        cryptoViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,it,Toast.LENGTH_LONG)
            view?.findViewById<ProgressBar>(R.id.list_progrees_bar)?.visibility = View.GONE
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(symbol: String, view: View) {
        Log.i(TAG, "onClick:selected item "+symbol)
        val bundle = Bundle()
        bundle.putString("symbol",symbol)
        Navigation.findNavController(view).navigate(R.id.deatilFragment, bundle)
    }
}