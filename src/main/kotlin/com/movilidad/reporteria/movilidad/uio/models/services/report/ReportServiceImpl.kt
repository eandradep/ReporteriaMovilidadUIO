package com.movilidad.reporteria.movilidad.uio.models.services.report

import com.movilidad.reporteria.movilidad.uio.models.dao.IReportDao
import com.movilidad.reporteria.movilidad.uio.models.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl : IReportService {

    @Autowired
    private val reportDao:IReportDao? = null

    @Transactional(readOnly = true)
    override fun getUseSectors(): List<SectorUseDTO>? {
        val sectorUseDTOList = arrayListOf<SectorUseDTO>()
        for ( objectResult in reportDao!!.getUseSectors()!!) {
            val sectorUseDTO = SectorUseDTO()
            sectorUseDTO.secID = objectResult[0] as Int
            sectorUseDTO.secName = objectResult[1] as String
            sectorUseDTO.userCount = objectResult[2] as Int
            sectorUseDTOList.add(sectorUseDTO)
        }
        return sectorUseDTOList
    }

    @Transactional(readOnly = true)
    override fun getReportSector(idSec: Int): List<ReportSectorDTO>? {
        val  reportSectorDTOList = arrayListOf<ReportSectorDTO>()
        for ( objectResult in reportDao!!.getReportSector(idSec)!!) {
            val reportSectorDTO = ReportSectorDTO()
            reportSectorDTO.idReport = objectResult[0] as Int
            reportSectorDTO.categoryReport = objectResult[1] as String
            reportSectorDTO.dateReport = objectResult[2] as String
            reportSectorDTO.addInfoReport = objectResult[3] as String
            reportSectorDTO.dependencyReport = objectResult[4] as String
            reportSectorDTO.statusReport = objectResult[5] as String
            reportSectorDTOList.add(reportSectorDTO)
        }
        return reportSectorDTOList
    }

    @Transactional(readOnly = true)
    override fun getReportDepDTO(dep: String): List<ReportDepDTO>? {
        val reportDepDTOList = arrayListOf<ReportDepDTO>()
        for ( objectResult in reportDao!!.getReportDep(dep)!!) {
            val reportDepDTO = ReportDepDTO()
            reportDepDTO.reportID = objectResult[0] as Int
            reportDepDTO.mainAddress = objectResult[1] as String
            reportDepDTO.secondAddress = objectResult[2] as String
            reportDepDTO.dependency = objectResult[3] as String
            reportDepDTO.registerDate = objectResult[4] as String
            reportDepDTO.additionalData = objectResult[5] as String
            reportDepDTO.addressLatitude = objectResult[6] as Double
            reportDepDTO.addressLongitude = objectResult[7] as Double
            reportDepDTO.reportP = objectResult[8] as String
            reportDepDTO.referenceReport = objectResult[9] as String
            reportDepDTO.routeReport = objectResult[10] as String
            reportDepDTO.statusReport = objectResult[11] as String
            reportDepDTOList.add(reportDepDTO)
        }
        return reportDepDTOList
    }

    @Transactional(readOnly = true)
    override fun getReportGen(): List<ReportDepDTO>? {
        val reportDepDTOList = arrayListOf<ReportDepDTO>()
        for ( objectResult in reportDao!!.getReportGen()!!) {
            val reportDepDTO = ReportDepDTO()
            reportDepDTO.reportID = objectResult[0] as Int
            reportDepDTO.mainAddress = objectResult[1] as String
            reportDepDTO.secondAddress = objectResult[2] as String
            reportDepDTO.dependency = objectResult[3] as String
            reportDepDTO.registerDate = objectResult[4] as String
            reportDepDTO.additionalData = objectResult[5] as String
            reportDepDTO.addressLatitude = objectResult[6] as Double
            reportDepDTO.addressLongitude = objectResult[7] as Double
            reportDepDTO.reportP = objectResult[8] as String
            reportDepDTO.referenceReport = objectResult[9] as String
            reportDepDTO.routeReport = objectResult[10] as String
            reportDepDTO.statusReport = objectResult[11] as String
            reportDepDTOList.add(reportDepDTO)
        }
        return reportDepDTOList
    }

    @Transactional(readOnly = true)
    override fun getReportStops(initDate: String, endDate: String): List<ReportShapeStopDTO>? {
        val reportShapeStopDTOList = arrayListOf<ReportShapeStopDTO>()
        for ( objectResult in reportDao!!.getReportStops(initDate, endDate)!!) {
            reportShapeStopDTOList.add(this.getReportShapeStopDTO(objectResult))
        }
        return reportShapeStopDTOList
    }

    @Transactional(readOnly = true)
    override fun getReportShapes(initDate: String, endDate: String): List<ReportShapeStopDTO>? {
        val reportShapeStopDTOList = arrayListOf<ReportShapeStopDTO>()
        for ( objectResult in reportDao!!.getReportShapes(initDate, endDate)!!) {
            reportShapeStopDTOList.add(this.getReportShapeStopDTO(objectResult))
        }
        return reportShapeStopDTOList
    }

    @Transactional(readOnly = true)
    override fun getNotHistoric(initDate: String, endDate: String): List<NotHistoricDTO>? {
        val notHistoricDTOList = arrayListOf<NotHistoricDTO>()
        for ( objectResult in reportDao!!.getNotHistoric(initDate, endDate)!!) {
            val notHistoricDTO = NotHistoricDTO()
            notHistoricDTO.userID = objectResult[0] as String
            notHistoricDTO.userName = objectResult[1] as String
            notHistoricDTO.userPhone = objectResult[2] as String
            notHistoricDTO.userEmail = objectResult[3] as String
            notHistoricDTO.reportInfo = objectResult[4] as String
            notHistoricDTO.notID = objectResult[5] as Int
            notHistoricDTO.notDate = objectResult[6] as String
            notHistoricDTO.notFileAdd = objectResult[7] as Boolean
            notHistoricDTO.notMailStatus = objectResult[8] as Boolean
            notHistoricDTO.notStatus = objectResult[9] as Boolean
            notHistoricDTO.notMessage = objectResult[10] as String
            notHistoricDTOList.add(notHistoricDTO)
        }
        return notHistoricDTOList
    }

    private fun getReportShapeStopDTO (objectResult: Array<Any>) : ReportShapeStopDTO{
        val reportShapeStopDTO = ReportShapeStopDTO()
        reportShapeStopDTO.shapeId = objectResult[0] as Int
        reportShapeStopDTO.shapeDescriptor = objectResult[1] as String
        reportShapeStopDTO.shapeDate = objectResult[2] as String
        reportShapeStopDTO.shapesCount = objectResult[3] as Int
        return reportShapeStopDTO;
    }
}