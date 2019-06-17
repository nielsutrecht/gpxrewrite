package com.nibado.gpxrewrite

import com.xenomachina.argparser.ArgParser

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::Args).run {
        println(this.atDate)
        println(this.timeShift)
        println(this.reverse)
    }
    println("Hello world")
}