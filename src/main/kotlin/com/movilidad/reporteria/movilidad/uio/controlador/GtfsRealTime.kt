package com.movilidad.reporteria.movilidad.uio.controlador

import com.movilidad.reporteria.movilidad.uio.models.dto.realtime.*
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/gtfs")
class GtfsRealTime {


    @GetMapping("/getgts")
    fun getUseSectors(): Any {
        val response = HashMap<String, Any>()

        val feedMessageList: FeedMessage = FeedMessage()

        val header: Header = Header()
        header.gtfs_realtime_version = "2.0"
        header.incrementality = "FULL_DATASET"
        header.timestamp = 1284457468



        val entityList = arrayListOf<Entity>()


        entityList.add(getEntity())
        entityList.add(getEntity())
        entityList.add(getEntity())


        feedMessageList.header = header
        feedMessageList.entityList = entityList



        response["mensaje"] = "DATOS ENCONTRADOS"
        response["resultado"] = feedMessageList
        ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)

        return response;

    }

    fun getEntity(): Entity {
        val entity = Entity()
        entity.id = "IDENTIFICADOR UNICO"
        val vehiclePosition = VehiclePosition()

        val position = Position()
        position.latitude = -75.684213F
        position.longitude = -75.684213F


        val vehicle = Vehicle()

        vehicle.id = "123"
        vehicle.label = "BUS DE JUANITO"
        vehicle.license_plate = "HU786Q"

        vehiclePosition.trip = "TRIP DE LA BD"
        vehiclePosition.position = position
        vehiclePosition.vehicle = vehicle

        entity.vehiclePosition = vehiclePosition

        return entity
    }

}