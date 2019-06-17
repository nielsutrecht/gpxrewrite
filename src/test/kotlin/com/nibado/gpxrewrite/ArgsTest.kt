package com.nibado.gpxrewrite

import com.xenomachina.argparser.ArgParser
import org.junit.jupiter.api.Test

internal class ArgsTest {

    @Test
    fun empty() {
        val args = arrayOf<String>()

        ArgParser(args).parseInto(::Args).run {
            println(this.atDate)
            println(this.timeShift)
            println(this.reverse)
        }
    }

    @Test
    fun atDate() {
        val args = "-d 2011-01-01".split(" ").toTypedArray()

        ArgParser(args).parseInto(::Args).run {
            println(this.atDate)
            println(this.timeShift)
            println(this.reverse)
        }
    }
}