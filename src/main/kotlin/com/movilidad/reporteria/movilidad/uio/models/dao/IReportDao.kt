package com.movilidad.reporteria.movilidad.uio.models.dao

import com.movilidad.reporteria.movilidad.uio.models.entity.HistoricReport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.*


interface IReportDao : JpaRepository<HistoricReport, Long>{

    @Query(value = "" +
            "select  s.id_sector as codigoSector,\n" +
            "        s.nombre_sector as nombreSector,\n" +
            "        count(*) as numeroUsuarios\n" +
            "from    sector_cli s ,\n" +
            "        usuario_cli uc\n" +
            "where   s.id_sector = uc.sector_id\n" +
            "group by s.id_sector, s.nombre_sector\n" +
            "order by s.id_sector;",
            nativeQuery = true)
    fun getUseSectors(): List<Array<Any>>?

    @Query(value = "" +
            "select  ur.id_reporte,\n" +
            "        c.text,\n" +
            "        ur.fecha_registro,\n" +
            "        ur.informacion_adicional,\n" +
            "        ur.dependency,\n" +
            "        ur.status\n" +
            "from    usuario_cli uc,\n" +
            "        user_report ur,\n" +
            "        category c\n" +
            "where   uc.sector_id = ?1\n" +
            "and     uc.id_usuario = ur.id_usuario\n" +
            "and     c.id = ur.id_categoria;",
            nativeQuery = true)
    fun getReportSector(idSec: Int): List<Array<Any>>?

    @Query(value = "" +
            "select  id_reporte,\n" +
            "        calle_principal,\n" +
            "        calle_secundaria,\n" +
            "        dependency,\n" +
            "        fecha_registro,\n" +
            "        informacion_adicional,\n" +
            "        latitud_direccion,\n" +
            "        longitud_direccion,\n" +
            "        placa_reporte,\n" +
            "        referencia_reporte,\n" +
            "        ruta_reporte,\n" +
            "        status\n" +
            "from    user_report\n" +
            "where   dependency like ?1\n" +
            "order by id_reporte asc;",
            nativeQuery = true)
    fun getReportDep(dep: String): List<Array<Any>>?

    @Query(value = "" +
            "select  id_reporte,\n" +
            "        calle_principal,\n" +
            "        calle_secundaria,\n" +
            "        dependency,\n" +
            "        fecha_registro,\n" +
            "        informacion_adicional,\n" +
            "        latitud_direccion,\n" +
            "        longitud_direccion,\n" +
            "        placa_reporte,\n" +
            "        referencia_reporte,\n" +
            "        ruta_reporte,\n" +
            "        status\n" +
            "from    user_report\n" +
            "order by id_reporte asc;",
            nativeQuery = true)
    fun getReportGen(): List<Array<Any>>?

    @Query(value = "" +
            "select  sp.stop_id as shapeId,\n" +
            "        sp.stop_name as shapeDescriptor,\n" +
            "        sp.fecha_registro as shapeDate,\n" +
            "        1 as shapesCount\n" +
            "from    stops sp\n" +
            "where   CONVERT (datetime, sp.fecha_registro, 103)  BETWEEN CONVERT (datetime, ?1, 103) AND CONVERT (datetime, ?2, 103)\n" +
            "order by sp.stop_id desc;",
            nativeQuery = true)
    fun getReportStops(initDate: String, endDate: String): List<Array<Any>>?

    @Query(value = "" +
            "select  sp.shape_id as shapeId,\n" +
            "        sp.description as shapeDescriptor,\n" +
            "        sp.fecha_registro as shapeDate,\n" +
            "        count(sp.shape_id) as shapesCount\n" +
            "from    shape_parent sp,\n" +
            "        shapes s\n" +
            "where   sp.shape_id = s.shape_id\n" +
            "and     CONVERT (datetime, sp.fecha_registro, 103)  BETWEEN CONVERT (datetime, ?1, 103) AND CONVERT (datetime, ?2, 103)\n" +
            "group by sp.shape_id, sp.description, sp.fecha_registro\n" +
            "order by sp.shape_id desc;",
            nativeQuery = true)
    fun getReportShapes(initDate: String, endDate: String): List<Array<Any>>?

    @Query(value = "" +
            "select  uc.cedula_usuario,\n" +
            "        uc.nombre_usuario,\n" +
            "        uc.numero_telefono,\n" +
            "        uc.correo_usuario,\n" +
            "        ur.informacion_adicional,\n" +
            "        nu.id_notificacion,\n" +
            "        nu.fecha,\n" +
            "        nu.archivo_adjunto,\n" +
            "        nu.estado_email,\n" +
            "        nu.estado_notificacion,\n" +
            "        nu.mensaje,\n" +
            "        ur.url_archivo_respaldo \n" +
            "from    notificaciones_usuario nu,\n" +
            "        user_report ur,\n" +
            "        usuario_cli uc\n" +
            "where   uc.id_usuario = ur.id_usuario\n" +
            "and     ur.id_reporte = nu.id_reporte\n" +
            "and     CONVERT (datetime, nu.fecha, 103)  BETWEEN CONVERT (datetime, ?1, 103) AND CONVERT (datetime, ?2, 103)\n" +
            "order by nu.id_notificacion desc;",
            nativeQuery = true)
    fun getNotHistoric(initDate: String, endDate: String): List<Array<Any>>?

}