package com.nibado.gpxrewrite.gpx

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GpxIOTest {

    @Test
    fun from() {
        val file = GpxIO.from(GpxIOTest::class.java.getResourceAsStream("/gpx/woon-werk.gpx"))

        println(GpxIO.toString(file))
    }
}