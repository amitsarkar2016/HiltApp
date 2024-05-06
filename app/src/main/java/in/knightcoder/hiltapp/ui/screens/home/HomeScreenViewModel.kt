package `in`.knightcoder.hiltapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.knightcoder.hiltapp.repository.DaggerHiltRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val daggerHiltRepository: DaggerHiltRepository) : ViewModel() {
    val homeResponse = daggerHiltRepository.homeResponse

    fun fetchHomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            daggerHiltRepository.fetchHomeData()
        }
    }

    init {
        fetchHomeData()
    }
}