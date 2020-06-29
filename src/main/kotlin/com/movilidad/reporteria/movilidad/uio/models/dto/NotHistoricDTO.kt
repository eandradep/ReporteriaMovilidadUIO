package com.movilidad.reporteria.movilidad.uio.models.dto

import java.io.Serializable

class NotHistoricDTO : Serializable{

    var userID: String = ""

    var userName: String = ""

    var userPhone: String = ""

    var userEmail: String = ""

    var reportInfo: String = ""

    var notID: Int = 0

    var notDate: String = ""

    var notFileAdd: Boolean = true

    var notMailStatus: Boolean = true

    var notStatus: Boolean = false

    var notMessage: String = ""

    var urlImagen: String = ""
    
}