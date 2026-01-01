package com.example.apiintegration.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.usecase.local_user.DeleteUserUseCase
import com.example.apiintegration.domain.usecase.local_user.GetAllUsersUseCase
import com.example.apiintegration.domain.usecase.local_user.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val upsertUserUseCase: UpsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    val users: StateFlow<List<UserProfile>> = getAllUsersUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun upsertUser(firstName: String, lastName: String, id: Int? = null,place: String,age:String) {
        if (firstName.isBlank() || lastName.isBlank()||place.isBlank()||age.isBlank()) return


        val user = UserProfile(id = id, firstName = firstName, lastName = lastName,place=place,age=age)
        viewModelScope.launch {
            upsertUserUseCase(user)
        }
    }

    fun deleteUser(user: UserProfile) {
        viewModelScope.launch {
            deleteUserUseCase(user)
        }
    }
}
