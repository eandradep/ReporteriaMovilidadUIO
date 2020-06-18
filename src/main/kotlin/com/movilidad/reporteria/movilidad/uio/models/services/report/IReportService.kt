package com.movilidad.reporteria.movilidad.uio.models.services.report

import com.movilidad.reporteria.movilidad.uio.models.dto.ReportDepDTO
import com.movilidad.reporteria.movilidad.uio.models.dto.ReportSectorDTO
import com.movilidad.reporteria.movilidad.uio.models.dto.SectorUseDTO
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

}