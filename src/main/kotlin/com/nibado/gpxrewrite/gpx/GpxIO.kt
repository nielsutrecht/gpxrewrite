package com.nibado.gpxrewrite.gpx

import com.fasterxml.jackson.databind.SerializationFeature
import java.io.InputStream
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


object GpxIO {
    private val mapper = XmlMapper().registerKotlinModule()
            .registerModule(JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    fun from(ins: InputStream) = mapper.readValue<GpxXml>(ins)

    fun toString(xml: GpxXml) = mapper.writeValueAsString(xml)
}