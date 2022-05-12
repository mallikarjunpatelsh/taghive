package com.example.taghive.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taghive.model.CryptoModel
import com.example.taghive.repository.CryptoApi
import kotlinx.coroutines.launch
import java.lang.Exception

class CryptoViewModel : ViewModel() {
    private val TAG ="CryptoViewModel"
    private var cryptoList = MutableLiveData<List<CryptoModel>>()
    private var crypto = MutableLiveData<CryptoModel>()
    var error = MutableLiveData<String>()

    init {
        getAllCrypto()
    }

     fun getAllCrypto(){
        viewModelScope.launch {
            try {
                val allCrypto = CryptoApi.cryptoApiService.getAllCrypto()
                cryptoList.value = allCrypto
            }catch (e: Exception){
                Log.i(TAG, "getAllCrypto: "+e)
                error.value = "Could not fetch List from Server"
            }
        }
    }

    private fun getSymbol(symbol: String){
        viewModelScope.launch {
            try {
                val cryptoSymbol = CryptoApi.cryptoApiService.getSymbol("ticker/24hr?symbol="+symbol)
                crypto.value = cryptoSymbol
            }catch (e: Exception){
                e.printStackTrace()
                error.value = "Could not fetch Details from Server"
            }
        }
    }

     fun getCryptoList():LiveData<List<CryptoModel>>{
        return cryptoList;
    }

    fun getCrypto(symbol: String):LiveData<CryptoModel>{
        getSymbol(symbol)
        return crypto
    }
}