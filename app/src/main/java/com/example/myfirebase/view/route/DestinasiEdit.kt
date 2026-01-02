package com.example.myfirebase.view.route

import com.example.myfirebase.R
import com.example.myfirebase.view.route.DestinasiNavigasi

object DestinasiEdit: DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}