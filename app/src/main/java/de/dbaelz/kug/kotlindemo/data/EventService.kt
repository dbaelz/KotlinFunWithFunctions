package de.dbaelz.kug.kotlindemo.data

import de.dbaelz.kug.kotlindemo.data.event.Event
import de.dbaelz.kug.kotlindemo.data.event.Info
import de.dbaelz.kug.kotlindemo.data.event.Location
import de.dbaelz.kug.kotlindemo.data.event.Topic
import org.joda.time.DateTime


object EventService {
    private val events = listOf(Event(
            info = Info(
                    organizer = "Kotlin Karlsruhe User Group",
                    title = "Kotlin Meetup Vol. 4",
                    date = DateTime("2018-02-22T19:30:00+01:00"),
                    link = "https://www.meetup.com/de-DE/Kotlin-Karlsruhe-User-Group/events/247320883/"),
            topics = listOf(
                    Topic(id = 1, title = "Building a Browser Extension with Kotlin", author = "Kirill Rakhman"),
                    Topic(id = 2, title = "A very personal dive into my favorite Kotlin features", author = "Daniel Bälz")),
            location = Location(
                    name = "Chrono24 GmbH",
                    address = "Haid-und-Neu-Straße 18",
                    zip = "76131",
                    city = "Karlsruhe")))


    fun getFirstEvent(): Event = events.firstOrNull() ?: Event()
}