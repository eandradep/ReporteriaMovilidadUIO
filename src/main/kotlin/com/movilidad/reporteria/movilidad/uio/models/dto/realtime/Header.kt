package com.movilidad.reporteria.movilidad.uio.models.dto.realtime

import java.io.Serializable

class Header : Serializable{

    var gtfs_realtime_version: String= ""

    var incrementality: String = ""

    var timestamp: Long = 0L

}