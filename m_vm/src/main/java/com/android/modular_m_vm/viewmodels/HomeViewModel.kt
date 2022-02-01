package com.android.modular_m_vm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.modular_m_vm.controllers.IPeopleController
import com.android.modular_m_vm.models.data.Person
import com.android.modular_m_vm.models.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val peopleController: IPeopleController) :
    ViewModel() {

    private val _people = MutableLiveData<Resource<List<Person>>>()
    val people: LiveData<Resource<List<Person>>> = _people

    fun getPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            _people.postValue(Resource.loading())
            val response = peopleController.getPeople()

            if (response.success) {
                _people.postValue(Resource.success(response.body?.results))
            } else {
                _people.postValue(Resource.error(response.errorMessage))
            }
        }
    }
}