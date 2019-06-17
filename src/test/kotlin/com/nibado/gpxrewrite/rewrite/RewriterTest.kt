package com.nibado.gpxrewrite.rewrite

import com.nibado.gpxrewrite.duration
import com.nibado.gpxrewrite.gpx
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime.of

internal class RewriterTest {
    private val input = gpx(of(0,0), of(0,1), of(0,2), of(23,59))

    @Test
    fun rewriteAtDate() {
        val options = RewriteOptions(LocalDate.of(2001,12,31))
        val new = Rewriter.rewrite(input, options)
        val result = new.track.segment.points.map { it.time.toString() }

        assertThat(result).containsExactly("2001-12-31T00:00Z", "2001-12-31T00:01Z", "2001-12-31T00:02Z", "2001-12-31T23:59Z")
        assertThat(duration(new)).isEqualTo(duration(input))

        println(duration(input))
    }

    @Test
    fun rewriteShiftMinutes() {
        val options = RewriteOptions(null, Duration.ofMinutes(5))

        val new = Rewriter.rewrite(input, options)
        val result = new.track.segment.points.map { it.time.toString() }

        assertThat(result).containsExactly("2000-01-01T00:05Z", "2000-01-01T00:06Z", "2000-01-01T00:07Z", "2000-01-02T00:04Z")
        assertThat(duration(new)).isEqualTo(duration(input))
    }

    @Test
    fun rewriteShiftDays() {
        val options = RewriteOptions(null, Duration.ofDays(5).plusHours(12))

        val new = Rewriter.rewrite(input, options)
        val result = new.track.segment.points.map { it.time.toString() }

        assertThat(result).containsExactly("2000-01-06T12:00Z", "2000-01-06T12:01Z", "2000-01-06T12:02Z", "2000-01-07T11:59Z")
        assertThat(duration(new)).isEqualTo(duration(input))
    }

    @Test
    fun rewriteAtDateAndShift() {
        val options = RewriteOptions(LocalDate.of(2001,12,31), Duration.ofMinutes(5))

        val new = Rewriter.rewrite(input, options)
        val result = new.track.segment.points.map { it.time.toString() }

        assertThat(result).containsExactly("2001-12-31T00:05Z", "2001-12-31T00:06Z", "2001-12-31T00:07Z", "2002-01-01T00:04Z")
        assertThat(duration(new)).isEqualTo(duration(input))
    }
}