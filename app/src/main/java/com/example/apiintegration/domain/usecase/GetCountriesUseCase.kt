package com.example.apiintegration.domain.usecase

import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): List<Country> {
        return repository.getCountries()
    }
}
