package com.movilidad.reporteria.movilidad.uio.models.dto.realtime

import java.io.Serializable

class Entity : Serializable {

    var id: String = ""
    var vehiclePosition: VehiclePosition = VehiclePosition()
}