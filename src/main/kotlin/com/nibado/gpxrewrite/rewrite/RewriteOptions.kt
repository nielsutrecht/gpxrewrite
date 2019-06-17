package com.nibado.gpxrewrite.rewrite

import java.time.Duration
import java.time.LocalDate

data class RewriteOptions(val date: LocalDate? = null, val shiftTime: Duration? = null)