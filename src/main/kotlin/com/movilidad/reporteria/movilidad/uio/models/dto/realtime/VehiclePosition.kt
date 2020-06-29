package com.movilidad.reporteria.movilidad.uio.models.dto.realtime

import java.io.Serializable

class VehiclePosition : Serializable {

    var trip: String =""
    var vehicle: Vehicle = Vehicle()
    var position:Position = Position()


}