package com.movilidad.reporteria.movilidad.uio.models.dto.realtime

import java.io.Serializable

class FeedMessage : Serializable {

    var header: Header = Header()
    var entityList = arrayListOf<Entity>()
}