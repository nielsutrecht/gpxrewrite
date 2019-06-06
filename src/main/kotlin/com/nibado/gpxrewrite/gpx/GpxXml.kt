package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "gpx")
@JsonIgnoreProperties(ignoreUnknown = true)
data class GpxXml(
        @JacksonXmlProperty(localName = "creator", isAttribute = true) val creator:String,
        @JacksonXmlProperty(localName = "version", isAttribute = true) val version: String,
        @JsonProperty("metadata") val metaData: GpxMetaData,
        @JsonProperty("trk") val track: GpxTrack)