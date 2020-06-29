package com.movilidad.reporteria.movilidad.uio.models.dto.realtime

import java.io.Serializable

class Position : Serializable{

    var latitude: Float = 0.0F
    var longitude: Float = 0.0F
    var bearing: Float = 0.0F
    var odometer: Double = 0.0
    var speed: Float = 0.0F
}