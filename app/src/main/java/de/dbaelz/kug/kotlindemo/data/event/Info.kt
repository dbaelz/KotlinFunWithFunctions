package de.dbaelz.kug.kotlindemo.data.event

import org.joda.time.DateTime

data class Info(
        val organizer: String? = null,
        val title: String? = null,
        val date: DateTime? = null,
        val link: String? = null
)
