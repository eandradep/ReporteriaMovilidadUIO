package com.movilidad.reporteria.movilidad.uio.models.dto

import java.io.Serializable

class ReportDepDTO :Serializable {

    var reportID: Int = 0

    var mainAddress: String = ""

    var secondAddress: String = ""

    var dependency: String = ""

    var registerDate: String = ""

    var additionalData: String = ""

    var addressLatitude: Double = 0.0

    var addressLongitude: Double= 0.0

    var reportP :String = ""

    var referenceReport: String = ""

    var routeReport: String = ""

    var statusReport: String = ""
}