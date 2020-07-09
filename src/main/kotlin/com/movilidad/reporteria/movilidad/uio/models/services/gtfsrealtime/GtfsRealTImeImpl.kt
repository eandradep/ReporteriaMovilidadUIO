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

    private val urlFinalPath = "/opt/wildfly/standalone/data/private"
//    private var urlFinalPath = "/home/edisonandrade/Documents/Servidores/wld-fnl/standalone/data/private"
    private var fileName = "fileGenerated.pb"

    override fun uploadFile(): Resource {
        val lastPath = getPath()
        return UrlResource(lastPath.toUri())
    }

    override fun generateGtfsFile() {
        val feedMessage = GtfsRealTime.FeedMessage.newBuilder()
                .setFeedheader(
                        GtfsRealTime.FeedMessage.FeedHeader.newBuilder()
                                .setGtfsRealtimeVersion("1.0.0.0")
                                .setIncrementality(GtfsRealTime.FeedMessage.FeedHeader.Incrementality.FULL_DATASET)
                                .setTimestamp(111321L)
                )
        for (i in 1..4) {
            feedMessage.addEntity(
                    GtfsRealTime.FeedMessage.FeedEntity.newBuilder()
                            .setId("asdasd13")
                            .setVehicle(
                                    GtfsRealTime.FeedMessage.FeedEntity.VehiclePosition.newBuilder()
                                            .setTrip(
                                                    GtfsRealTime.FeedMessage.FeedEntity.VehiclePosition.TripDescriptor.newBuilder()
                                                            .setTripId("123456")
                                                            .setRouteId("132456")
                                                            .setStartTime("19:00:00")
                                                            .setStartDate("20200708")
                                            )
                                            .setPosition(
                                                    GtfsRealTime.FeedMessage.FeedEntity.VehiclePosition.Position.newBuilder()
                                                            .setLatitude(42.46401f)
                                                            .setLongitude(-70.94477f)
                                                            .setBearing(288.0f)
                                            )
                                            .setCurrentStopSequence(3)
                                            .setCurrentStatus(GtfsRealTime.FeedMessage.FeedEntity.VehiclePosition.VehicleStopStatus.IN_TRANSIT_TO)
                                            .setTimestamp(1594249332)
                                            .setStopId("7240")
                                            .setVehicle(
                                                    GtfsRealTime.FeedMessage.FeedEntity.VehiclePosition.VehicleDescriptor.newBuilder()
                                                            .setId("y0771")
                                                            .setLabel("0771")
                                                            .setLicensePlate("6546654")
                                            )
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