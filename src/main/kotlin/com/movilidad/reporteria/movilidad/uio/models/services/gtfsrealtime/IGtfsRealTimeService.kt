package com.movilidad.reporteria.movilidad.uio.models.services.gtfsrealtime

import org.springframework.core.io.Resource
import java.net.MalformedURLException

interface IGtfsRealTimeService {

    @Throws(MalformedURLException::class)
    fun uploadFile(): Resource

    @Throws(MalformedURLException::class)
    fun generateGtfsFile()

}