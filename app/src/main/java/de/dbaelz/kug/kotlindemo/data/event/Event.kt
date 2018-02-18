package de.dbaelz.kug.kotlindemo.data.event

data class Event(
        val info: Info? = null,
        val topics: List<Topic> = emptyList(),
        val location: Location? = null
)
