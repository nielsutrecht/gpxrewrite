package com.nibado.gpxrewrite.rewrite

import com.nibado.gpxrewrite.gpx.GpxTrackPoint
import com.nibado.gpxrewrite.gpx.GpxTrackSegment
import com.nibado.gpxrewrite.gpx.GpxXml
import java.time.Duration
import java.time.ZonedDateTime

object Rewriter {
    fun rewrite(xml: GpxXml, options: RewriteOptions): GpxXml {
        val firstTime = xml.track.segment.points.first().time

        val newPoints = xml.track.segment.points.map { rewrite(it, options, firstTime) }
        val segment = GpxTrackSegment(newPoints)
        val track = xml.track.copy(segment = segment)

        return xml.copy(track = track)
    }

    private fun rewrite(point: GpxTrackPoint, options: RewriteOptions, firstTime: ZonedDateTime): GpxTrackPoint {
        var dateTime = point.time

        if (options.date != null) {
            val delta = Duration.between(firstTime, point.time)
            val firstTimeAt = firstTime.withYear(options.date.year)
                    .withMonth(options.date.monthValue)
                    .withDayOfMonth(options.date.dayOfMonth)

            dateTime = firstTimeAt.plus(delta)
        }

        if (options.shiftTime != null) {
            dateTime = dateTime.plus(options.shiftTime)
        }

        return point.copy(time = dateTime)
    }
}