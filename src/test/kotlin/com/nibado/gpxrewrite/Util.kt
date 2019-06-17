package com.nibado.gpxrewrite

import com.nibado.gpxrewrite.gpx.*
import java.time.*

fun gpx(vararg time: LocalTime) = GpxXml(
        "Test",
        "1.0",
        GpxMetaData(zdt(time.first())),
        GpxTrack(
                "track",
                1,
                GpxTrackSegment(time.map(::point))))

fun point(time: LocalTime) = GpxTrackPoint(0.0, 0.0, 0.0, zdt(time))
fun zdt(time: LocalTime) = ZonedDateTime.of(LocalDate.of(2000, 1, 1), time, ZoneOffset.UTC)
fun duration(gpx: GpxXml) = Duration.between(gpx.track.segment.points.first().time, gpx.track.segment.points.last().time)