package com.example.taghive.fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taghive.R
import com.example.taghive.model.CryptoModel

class CryptoListAdapter (private val dataSet: List<CryptoModel>, private val onClick: OnClick) :
    RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {
    val TAG = "CryptoListAdapter"

    /**
     * Provide a reference to the type of views that you are using
     * (CryptoListAdapter ViewHolder).
     */
    class ViewHolder(view: View, onClick: OnClick) : RecyclerView.ViewHolder(view),View.OnClickListener {
        val TAG = "ViewHolder"
        val baseAsset: TextView
        val quoteAsset: TextView
        val openPrice: TextView
        val lowPrice: TextView
        val highPrice: TextView
        val lastPrice: TextView
        val volume: TextView
        val bidPrice: TextView
        val askPrice: TextView
        val at: TextView
        val onClickLocal: OnClick

        init {
            // Define click listener for the ViewHolder's View.
            baseAsset = view.findViewById(R.id.base_asset)
            quoteAsset = view.findViewById(R.id.quote_asset)
            openPrice = view.findViewById(R.id.open_price)
            bidPrice = view.findViewById(R.id.bid_price)
            lowPrice = view.findViewById(R.id.low_price)
            highPrice = view.findViewById(R.id.high_price)
            lastPrice = view.findViewById(R.id.last_price)
            volume = view.findViewById(R.id.volume)
            askPrice = view.findViewById(R.id.ask_price)
            at = view.findViewById(R.id.at)
            onClickLocal = onClick
        }

        override fun onClick(v: View?) { }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crypto_item_layout, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val cryptoModel = dataSet[position]
        viewHolder.baseAsset.text = cryptoModel.baseAsset
        viewHolder.quoteAsset.text = cryptoModel.quoteAsset
        viewHolder.openPrice.text = cryptoModel.openPrice
        viewHolder.lowPrice.text = cryptoModel.lowPrice
        viewHolder.highPrice.text = cryptoModel.highPrice
        viewHolder.lastPrice.text = cryptoModel.lastPrice
        viewHolder.volume.text = cryptoModel.volume
        viewHolder.bidPrice.text = cryptoModel.bidPrice
        viewHolder.askPrice.text = cryptoModel.askPrice
        viewHolder.at.text = cryptoModel.at.toString()

        viewHolder.itemView.setOnClickListener {
            Log.i(TAG, "onBindViewHolder: ")
            onClick.onClick(cryptoModel.symbol, it)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnClick{
        fun onClick(position: String, view: View)
    }
}