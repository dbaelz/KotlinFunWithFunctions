package de.dbaelz.kug.examples.scratch

// SimpleEventDsl.kt
/*
fun event(block: Event.() -> Unit): Event {
    return Event().apply(block)
}

fun Event.info(block: Info.() -> Unit) {
    info = Info().apply(block)
}*/



// EventDsl.kt
/*

import de.dbaelz.kug.kotlindemo.data.event.Event
import de.dbaelz.kug.kotlindemo.data.event.Info
import de.dbaelz.kug.kotlindemo.data.event.Location
import de.dbaelz.kug.kotlindemo.data.event.Topic
import org.joda.time.DateTime

@DslMarker
annotation class EventDsl

fun event(block: EventBuilder.() -> Unit): Event = EventBuilder().apply(block).build()

@EventDsl
class EventBuilder {
    var info: Info? = null
    var location: Location? = null

    private val topics = mutableListOf<Topic>()

    fun info(block: InfoBuilder.() -> Unit) {
        info = InfoBuilder().apply(block).build()
    }

    fun topics(block: TopicsBuilder.() -> Unit) {
        topics.addAll(TopicsBuilder().apply(block))
    }

    fun location(block: LocationBuilder.() -> Unit) {
        location = LocationBuilder().apply(block).build()
    }

    fun build(): Event = Event(info, topics, location)
}

@EventDsl
class InfoBuilder {
    var organizer: String? = null
    var title: String? = null

    private var jodaDate: DateTime? = null
    var date: String? = null
        set(value) {
            jodaDate = try {
                DateTime(value)
            } catch (e: Exception) {
                DateTime()
            }
        }

    var link: String? = null

    fun build(): Info = Info(organizer, title, jodaDate, link)
}

@EventDsl
class TopicsBuilder : ArrayList<Topic>() {
    fun topic(block: TopicBuilder.() -> Unit) {
        add(TopicBuilder().apply(block).build())
    }
}

@EventDsl
class TopicBuilder {
    var id: Int? = null
    var title: String? = null
    var author: String? = null

    fun build(): Topic = Topic(id, title, author)
}

@EventDsl
class LocationBuilder {
    var name: String? = null
    var address: String? = null
    var zip: String? = null
    var city: String? = null

    fun build(): Location = Location(name, address, zip, city)
}

*/

// Example Usage
/*
event {
    info {
        organizer = "Kotlin Karlsruhe User Group"
        title = "Another Kotlin Meetup"
        date = "2018-02-22T20:00"
    }

    topics {
        topic {
            author = "Jane Doe"
            title = "Kotlin"
        }
        topic {
            author = "John Doe"
            title = "More Kotlin"
        }
    }

    location {
        address = "Earth"
    }
}


 */