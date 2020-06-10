package com.movilidad.reporteria.movilidad.uio.controlador

import com.movilidad.reporteria.movilidad.uio.modelos.servicios.uploadfile.IUploadFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/upload")
class UploadFile {

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

}