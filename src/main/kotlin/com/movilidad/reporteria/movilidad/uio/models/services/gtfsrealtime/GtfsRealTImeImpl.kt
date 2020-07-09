package com.movilidad.reporteria.movilidad.uio.models.services.gtfsrealtime

import com.movilidad.reporteria.movilidad.uio.gtfs.GtfsRealTime
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.nio.file.Path
import java.nio.file.Paths

@Service
class GtfsRealTImeImpl: IGtfsRealTimeService {

//    private val urlFinalPath = "/opt/wildfly/standalone/data/private"
    private var urlFinalPath = "/home/edisonandrade/Documents/Servidores/wld-fnl/standalone/data/private"
    private var fileName = "fileGenerated.pb"

    override fun uploadFile(): Resource {
        val lastPath = getPath()
        return UrlResource(lastPath.toUri())
    }

    override fun generateGtfsFile() {
        val feedMessage = GtfsRealTime.FeedMessage.newBuilder()
                .setHeader(
                        GtfsRealTime.FeedHeader.newBuilder()
                                .setGtfsRealtimeVersion("1.0.0.0")
                                .setIncrementality(GtfsRealTime.FeedHeader.Incrementality.FULL_DATASET)
                                .setTimestamp(111321L)
                )

        for (i in 1..4){
            feedMessage.addEntity(
                    GtfsRealTime.FeedEntity.newBuilder()
                            .setId("asdasdasdasd")
                            .setVehicle(
                                    GtfsRealTime.VehiclePosition.newBuilder()
                                            .setTrip(
                                                    GtfsRealTime.TripDescriptor.newBuilder()
                                                            .setTripId("132132")
                                                            .setRouteId("231321")
                                                            .build()
                                            )
                                            .setVehicle(
                                                    GtfsRealTime.VehicleDescriptor.newBuilder()
                                                            .setId("asdasdasd")
                                                            .setLabel("65as4da65sd4")
                                                            .setLicensePlate("654sad6a5s4d")
                                                            .build()
                                            )
                                            .setPosition(
                                                    GtfsRealTime.Position.newBuilder()
                                                            .setLatitude("132".toFloat())
                                                            .setLongitude("-36.58".toFloat())
                                                            .setBearing("23".toFloat())
                                                            .build()
                                            )
                                            .setCurrentStopSequence(3)
                                            .setStopId("1")
                                            .setTimestamp(1231654L)

                                            .build()
                            )
            )
        }

        val output = FileOutputStream("${urlFinalPath}/${fileName}")
        feedMessage.build().writeTo(output)
        output.close()
    }

    private fun getPath(): Path {
        return Paths.get("$urlFinalPath/").resolve(fileName).toAbsolutePath();
    }

}