package com.movilidad.reporteria.movilidad.uio.controlador



import com.movilidad.reporteria.movilidad.uio.models.services.gtfsrealtime.IGtfsRealTimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.MalformedURLException


@RestController
@RequestMapping("/api/gtfs")
class GtfsRealTime {

    @Autowired
    private val iGtfsRealTimeService: IGtfsRealTimeService? = null

    /**
     * Metodo Usado para poder Obtener la IMagen del Usario
     * */
    @GetMapping("/getFile/{fileName:.+}")
    fun verVideo(@PathVariable fileName: String): Any{
        var recurso: Resource? = null
        val response = HashMap<String, Any>()
        try {
            try{
                iGtfsRealTimeService!!.generateGtfsFile()
            } catch (e: Exception){
                response["mensaje"]="Error al intenar subir la Imagen"
                response["error"] = "${e.message} : ${e.cause!!.message}"
                return ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
            }
            recurso = iGtfsRealTimeService.uploadFile()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso!!.filename + "\"")
        return ResponseEntity(recurso, httpHeaders, HttpStatus.OK)
    }


}