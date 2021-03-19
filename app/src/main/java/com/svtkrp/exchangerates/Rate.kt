package com.svtkrp.exchangerates

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rate(
    @SerialName("NumCode")
    val id: Int,
    @SerialName("CharCode")
    val name: String,
    @SerialName("Nominal")
    val nominal:Int,
    @SerialName("Name")
    val fullName: String,
    @SerialName("Value")
    val value: Double)

@Serializable
data class Response(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String, Rate>
)