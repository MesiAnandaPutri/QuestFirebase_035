package com.example.myfirebase.view.route

import com.example.myfirebase.R
import com.example.myfirebase.view.route.DestinasiNavigasi

object DestinasiDetail: DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}