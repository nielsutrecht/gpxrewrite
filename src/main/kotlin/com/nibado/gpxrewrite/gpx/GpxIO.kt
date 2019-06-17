package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.InputStream


object GpxIO {
    private val mapper = XmlMapper().registerKotlinModule()
            .registerModule(JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    fun from(ins: InputStream) = validate(mapper.readValue(ins))

    fun toString(xml: GpxXml) = mapper.writeValueAsString(xml)

    fun validate(xml: GpxXml) : GpxXml {
        if(xml.track.segment.points.isEmpty()) {
            throw IllegalArgumentException("Nothing to rewrite, no points in track")
        }

        return xml
    }
}