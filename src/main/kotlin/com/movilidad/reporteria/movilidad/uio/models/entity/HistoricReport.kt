package com.movilidad.reporteria.movilidad.uio.models.entity


import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name="historic_report")
class HistoricReport: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historic_report")
    var idHistoricReport:Long = 0

    @NotNull
    @Size(min = 4, max = 505)
    @Column(name="descripcion_zona_climatica", nullable = false)
    var descripcionZonaClimatica:String =""
}