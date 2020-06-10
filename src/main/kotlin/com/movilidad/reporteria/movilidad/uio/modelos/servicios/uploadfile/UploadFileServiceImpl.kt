package com.movilidad.reporteria.movilidad.uio.modelos.servicios.uploadfile

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import java.io.IOException

@Service
class UploadFileServiceImpl: IUploadFileService {

    private val uploadFile = (File (".").absolutePath)
            .subSequence(0, (File (".").absolutePath).length-1)

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
        println(uploadFile)
        val fileName = UUID.randomUUID().toString() + "_" + fileReport.originalFilename!!.replace(" ", "")
        println(Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath())
        val fileDir = Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath()
        Files.copy(fileReport.inputStream, fileDir)
        return fileName
    }

    override fun deleteFile(fileName: String?, file: String): Boolean {
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
        println("file: $file")
        return Paths.get("$uploadFile/$file").resolve(fileName).toAbsolutePath();
    }
}