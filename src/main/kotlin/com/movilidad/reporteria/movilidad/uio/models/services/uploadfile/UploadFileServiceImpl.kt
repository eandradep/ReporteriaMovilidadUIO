package com.movilidad.reporteria.movilidad.uio.models.services.uploadfile

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*


@Service
class UploadFileServiceImpl: IUploadFileService {

    override fun uploadFile(fileName: String, file: String): Resource {
        var rutaAnterior = getPath(fileName , file)
        var recursoUrl: Resource = UrlResource(rutaAnterior.toUri())
        if (!recursoUrl.exists() && !recursoUrl.isReadable) {
            rutaAnterior = Paths.get("src/main/resources/static/image").resolve("nouser.jpg").toAbsolutePath()
            recursoUrl = UrlResource(rutaAnterior.toUri())
        }
        return recursoUrl
    }

    @Throws(IOException::class)
    @Transactional
    override fun copyFile(fileReport: MultipartFile, file: String): String {
        val uploadFile = this.getURLMediaConfiguration()
        val fileName = UUID.randomUUID().toString() + "_" + fileReport.originalFilename!!.replace(" ", "")
        println(Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath())
        val fileDir = Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath()
        Files.copy(fileReport.inputStream, fileDir)
        return fileName
    }

    override fun deleteFile(fileName: String?, file: String): Boolean {
        val uploadFile = this.getURLMediaConfiguration()
        if (!fileName!!.isNullOrEmpty()) {
            val rutaAnterior = Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath()
            val archivoAnterior = rutaAnterior.toFile()
            if (archivoAnterior.exists() && archivoAnterior.canRead())
                archivoAnterior.delete()
            return true
        }
        return false
    }

    override fun getPath(fileName: String, file: String): Path {
        val uploadFile = this.getURLMediaConfiguration()
        return Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath();
    }

    private fun getURLMediaConfiguration(): String {
        val prop = Properties()
        val propFileName = "/opt/wildfly/standalone/data/private/general.properties"
        val mediaPath :String
        val inputStream: InputStream? =  FileInputStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream)
            mediaPath = prop.getProperty("path.archivos.multimedia")
            if (mediaPath.isEmpty()){
                return "/opt/wildfly/standalone/data/uploads/springboot/"
            }
        } else {
            return "/opt/wildfly/standalone/data/uploads/springboot/"
        }
        return mediaPath
    }

}