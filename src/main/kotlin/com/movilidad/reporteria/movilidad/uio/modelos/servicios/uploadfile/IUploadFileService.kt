package com.movilidad.reporteria.movilidad.uio.modelos.servicios.uploadfile

import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import org.springframework.core.io.Resource
import java.nio.file.Path

interface IUploadFileService {

    @Throws(MalformedURLException::class)
    fun uploadFile(fileName: String, file: String): Resource

    @Throws(IOException::class)
    fun copyFile(fileReport: MultipartFile, file: String): String

    fun deleteFile(fileName: String?, file: String): Boolean

    fun getPath(fileName: String, file: String): Path

//    private fun getURLMediaConfiguration():String

}