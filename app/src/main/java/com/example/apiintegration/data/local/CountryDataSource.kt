package com.example.apiintegration.data.local

import com.example.apiintegration.data.remote.dto.Country

object CountryDataSource {

    val asianCountries = listOf(
        Country("India", "IN", "+91"),
        Country("China", "CN", "+86"),
        Country("Japan", "JP", "+81"),
        Country("South Korea", "KR", "+82"),
        Country("Singapore", "SG", "+65"),
        Country("Thailand", "TH", "+66"),
        Country("Indonesia", "ID", "+62"),
        Country("Malaysia", "MY", "+60"),
        Country("Vietnam", "VN", "+84"),
        Country("Philippines", "PH", "+63")
    )
}
