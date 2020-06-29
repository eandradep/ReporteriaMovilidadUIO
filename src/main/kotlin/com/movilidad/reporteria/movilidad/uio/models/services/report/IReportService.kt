package com.movilidad.reporteria.movilidad.uio.models.services.report

import com.movilidad.reporteria.movilidad.uio.models.dto.*
import java.net.MalformedURLException

interface IReportService {

    @Throws(MalformedURLException::class)
    fun getUseSectors(): List<SectorUseDTO>?

    @Throws(MalformedURLException::class)
    fun getReportSector(idSec: Int): List<ReportSectorDTO>?

    @Throws(MalformedURLException::class)
    fun getReportDepDTO(dep: String): List<ReportDepDTO>?

    @Throws(MalformedURLException::class)
    fun getReportGen(): List<ReportDepDTO>?

    @Throws(MalformedURLException::class)
    fun getReportStops(initDate: String, endDate: String): List<ReportShapeStopDTO>?

    @Throws(MalformedURLException::class)
    fun getReportShapes(initDate: String, endDate: String): List<ReportShapeStopDTO>?

    @Throws(MalformedURLException::class)
    fun getNotHistoric(initDate: String, endDate: String): List<NotHistoricDTO>?

}