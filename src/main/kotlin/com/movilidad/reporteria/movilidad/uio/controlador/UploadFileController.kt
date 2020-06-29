package com.movilidad.reporteria.movilidad.uio.controlador

import com.movilidad.reporteria.movilidad.uio.models.services.uploadfile.IUploadFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import java.net.MalformedURLException


@RestController
@RequestMapping("/api/upload")
class UploadFileController {

    @Autowired
    private val uploadService: IUploadFileService? = null


    @PostMapping("/uploadFile")
    fun uploadImage(@RequestParam("reportFile") report_file: MultipartFile,
                    @RequestParam("fileType") file_type: Int):Any{
        val response = HashMap<String, Any>()
        val fileDirectory = if (file_type == 1){
            "image"
        } else{
            "video"
        }
        if (!report_file.isEmpty){
            val reportFileUpload: String?
            try {
                reportFileUpload = uploadService!!.copyFile(report_file,fileDirectory)
            }catch (e: Exception){
                response["mensaje"]="Error al intenar subir la Imagen"
                response["error"] = "${e.message} : ${e.cause!!.message}"
                return ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
            }
            response["url"] = "$fileDirectory/$reportFileUpload"
            response["mensaje"] = "Imagen Cargada Correctamente"
        }
        return ResponseEntity<Map<String, Any>>(response, HttpStatus.CREATED)
    }

    /**
     * Metodo Usado para poder Obtener la IMagen del Usario
     * */
    @GetMapping("/get/image/{nombreFoto:.+}")
    fun verFoto(@PathVariable nombreFoto: String): Any{
        var recurso: Resource? = null
        try {
            recurso = uploadService!!.uploadFile(nombreFoto,"image")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        val cabecera = HttpHeaders()
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso!!.filename + "\"")
        return ResponseEntity(recurso, cabecera, HttpStatus.OK)
    }

    /**
     * Metodo Usado para poder Obtener la IMagen del Usario
     * */
    @GetMapping("/get/video/{nombreFoto:.+}")
    fun verVideo(@PathVariable nombreFoto: String): Any{
        var recurso: Resource? = null
        try {
            recurso = uploadService!!.uploadFile(nombreFoto,"video")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        val cabecera = HttpHeaders()
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso!!.filename + "\"")
        return ResponseEntity(recurso, cabecera, HttpStatus.OK)
    }

}