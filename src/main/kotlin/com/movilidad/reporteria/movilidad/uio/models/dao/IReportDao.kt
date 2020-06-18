package com.movilidad.reporteria.movilidad.uio.models.dao

import com.movilidad.reporteria.movilidad.uio.models.entity.HistoricReport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.*


interface IReportDao : JpaRepository<HistoricReport, Long>{

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
    fun getReportDep( dep: String): List<Array<Any>>?

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

}