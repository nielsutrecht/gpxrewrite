package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

data class GpxTrack(
        val name: String,
        val type: Int,
        @JsonProperty("trkseg") val segment: GpxTrackSegment)