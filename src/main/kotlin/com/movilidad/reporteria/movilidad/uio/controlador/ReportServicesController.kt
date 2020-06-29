package com.movilidad.reporteria.movilidad.uio.controlador

import com.movilidad.reporteria.movilidad.uio.models.services.report.IReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reporteria")
class ReportServicesController {
    @Autowired
    private val iReportService: IReportService? = null

//    OBTENER LOS SECTORES QUE ESTAN EN USO

    @GetMapping("/getUseSectors")
    fun getUseSectors(): Any {
        val response = HashMap<String, Any>()
        try {
            val sectorUseDTOList = iReportService!!.getUseSectors()
            return if (sectorUseDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = sectorUseDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

//    OBTENER LOS REPORTES EN BASE AL SECTOR DE LOS USUARIOS

    @GetMapping("/getReportSector/{idSec}")
    fun getReportSector(@PathVariable idSec: Int ): Any {
        val response = HashMap<String, Any>()
        try {
            val reportSectorDTOList = iReportService!!.getReportSector(idSec)
            return if (reportSectorDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = reportSectorDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


//    OBTENER EL NUMERO DE REPORTES QUE TIENE ASIGNADA CADA ENTIDAD.

    @GetMapping("/getReportDep/{dep}")
    fun getReportDep(@PathVariable dep: String ): Any {
        val response = HashMap<String, Any>()
        try {
            val reportDepDTOList = iReportService!!.getReportDepDTO(dep)
            return if (reportDepDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = reportDepDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

//    OBTENER UN REPORTE GENERAL
    @GetMapping("/getReportGen")
    fun getReportGen( ): Any {
        val response = HashMap<String, Any>()
        try {
            val reportDepDTOList = iReportService!!.getReportGen()
            return if (reportDepDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = reportDepDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

//  MIGRADOS POR FECHA
    @GetMapping("/getReportShapes/{initDate}/{endDate}/{figureType}")
    fun getReportShapes( @PathVariable initDate: String, // Fecha Inicial
                         @PathVariable endDate: String,  // Fecha Final
                         @PathVariable figureType: Int): Any // Historido Shape = 1, Historico Parada = 2
    {
        val response = HashMap<String, Any>()
        try {
            val reportShapeStopDTOList = if (figureType == 1){
                iReportService!!.getReportShapes(initDate.replace("-","/"),
                        endDate.replace("-","/"))
            } else {
                iReportService!!.getReportStops(initDate.replace("-","/"),
                        endDate.replace("-","/"))
            }

            return if (reportShapeStopDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = reportShapeStopDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //  MIGRADOS POR FECHA
    @GetMapping("/getNotHistoric/{initDate}/{endDate}")
    fun getNotHistoric( @PathVariable initDate: String, // Fecha Inicial
                         @PathVariable endDate: String  // Fecha Final
                         ): Any
    {
        val response = HashMap<String, Any>()
        try {
            val notHistoricDTOList = iReportService!!.getNotHistoric(
                    initDate.replace("-","/"),
                    endDate.replace("-","/"))

            return if (notHistoricDTOList!!.isEmpty()){
                response["mensaje"] = "DATOS NO ENCONTRADOS"
                response["resultado"] = listOf<Any>()
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["mensaje"] = "DATOS ENCONTRADOS"
                response["resultado"] = notHistoricDTOList
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: DataAccessException){
            response["mensaje"] = "ERROR EN EL SERVIDOR"
            response["error"] = "${e.mostSpecificCause.message}"
            return ResponseEntity<Map<*, *>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }





}