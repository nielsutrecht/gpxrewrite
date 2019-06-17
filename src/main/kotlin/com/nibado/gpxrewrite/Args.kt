package com.nibado.gpxrewrite

import com.nibado.gpxrewrite.rewrite.RewriteOptions
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.time.Duration
import java.time.LocalDate

class Args(parser: ArgParser) {
    val atDate by parser.storing(
            "-d", "--at-date",
            help = "Start at date"
    ).default<String?>(null).addValidator { matchesDate(value) }

    val timeShift by parser.storing("-s", "--shift",
            help = "Shift time by amount"
    ).default<String?>(null)

    val reverse by parser.flagging(
            "-r", "--reverse",
            help = "Reverse points"
    )

    fun toOptions() : RewriteOptions {
        val atDate = this.atDate?.let { LocalDate.parse(it) }
        val timeShift = this.timeShift?.let { Duration.parse(it) }

        return RewriteOptions(atDate, timeShift)
    }

    companion object {
        private val DATE_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}".toRegex()

        fun matchesDate(value: String?) {
            if(value == null) {
                return
            }
            if(!value.matches(DATE_REGEX)) {
                throw IllegalArgumentException("$value does not match ${DATE_REGEX.pattern}")
            }
        }
    }
}