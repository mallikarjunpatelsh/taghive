package com.example.taghive.model

data class CryptoModel(
    var symbol: String,
    var baseAsset: String,
    var quoteAsset: String,
    var openPrice: String,
    var lowPrice: String,
    var highPrice: String,
    var lastPrice: String,
    var volume: String,
    var bidPrice: String,
    var askPrice: String,
    var at: Long
) {
}