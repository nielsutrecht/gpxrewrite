package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.time.ZonedDateTime

data class GpxTrackPoint(
        @JacksonXmlProperty(localName = "lat", isAttribute = true) val lat: Double,
        @JacksonXmlProperty(localName = "lon", isAttribute = true) val lon: Double,
        val ele: Double,
        val time: ZonedDateTime)