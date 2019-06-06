package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

data class GpxTrackSegment(@JacksonXmlElementWrapper(useWrapping = false) @JsonProperty("trkpt") val points: List<GpxTrackPoint>)