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
import com.example.taghive.R
import com.example.taghive.databinding.FragmentDeatilBinding
import com.example.taghive.model.CryptoModel
import com.example.taghive.viewmodel.CryptoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeatilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeatilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var symbol: String
    private val TAG = "DeatilFragment"
    private lateinit var _binding: FragmentDeatilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            symbol= it.getString("symbol").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeatilBinding.inflate(inflater, container, false)
        val root: View = _binding?.root
        _binding.detailProgressBar.visibility = View.VISIBLE
        val back = _binding.appbar.logoIcon
        back.visibility = View.VISIBLE
        back.setOnClickListener {
            Navigation.findNavController(root).popBackStack()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (symbol != null){
            var cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
            cryptoViewModel.getCrypto(symbol).observe(viewLifecycleOwner, Observer {
                Log.i(TAG, "onViewCreated: "+it)
                bindValues(view,it)
            })
            cryptoViewModel.error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context,it, Toast.LENGTH_LONG)
                view.findViewById<ProgressBar>(R.id.list_progrees_bar).visibility = View.GONE
            })
        }
    }

    private fun bindValues(view: View, it: CryptoModel?) {
        if (it != null){
            _binding.detailProgressBar.visibility = View.GONE
            _binding.symbol.text = it.symbol
            _binding.baseAsset.text = it.baseAsset
            _binding.quoteAsset.text = it.quoteAsset
            _binding.openPrice.text = it.openPrice
            _binding.lowPrice.text = it.lowPrice
            _binding.highPrice.text = it.highPrice
            _binding.lastPrice.text = it.lastPrice
            _binding.volume.text = it.volume
            _binding.bidPrice.text = it.bidPrice
            _binding.askPrice.text = it.askPrice
            _binding.at.text = it.at.toString()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeatilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeatilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}