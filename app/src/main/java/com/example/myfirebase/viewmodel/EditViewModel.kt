package com.example.myfirebase.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.repositori.RepositorySiswa
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.modeldata.toUiStateSiswa
import com.example.myfirebase.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class EditViewModel(savedStateHandle: SavedStateHandle, private val repositorySiswa:
RepositorySiswa
) : ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Long =
        savedStateHandle.get<String>(DestinasiDetail.itemIdArg)?.toLong()
            ?: error("idSiswa tidak ditemukan")

    init {
        viewModelScope.launch {
            uiStateSiswa = repositorySiswa.getSatuSiswa(idSiswa)!!
                .toUiStateSiswa(true)
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput
                (detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa) : Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun editSatuSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)){
            try {
                repositorySiswa.editSatuSiswa(idSiswa, uiStateSiswa.detailSiswa.toDataSiswa
                    ())
                println("Update Sukses: $idSiswa")
            } catch (e: Exception) {
                println("Update Error: ${e.message}")
            }
        }
    }

}