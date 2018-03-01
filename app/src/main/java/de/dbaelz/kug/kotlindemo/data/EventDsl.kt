package de.dbaelz.kug.kotlindemo.data


import de.dbaelz.kug.kotlindemo.data.event.Event
import de.dbaelz.kug.kotlindemo.data.event.Info
import de.dbaelz.kug.kotlindemo.data.event.Location
import de.dbaelz.kug.kotlindemo.data.event.Topic
import org.joda.time.DateTime


// Example of an Kotlin DSL for the Event class.
// A simpler version is provided in the examples module
// More information about Kotlin DSL: https://kotlinlang.org/docs/reference/type-safe-builders.html


// Annotation to disallow inconsistent structure of the DSL
@DslMarker
annotation class EventDsl

// The outer function to create the DSL
fun event(block: EventBuilder.() -> Unit): Event = EventBuilder().apply(block).build()

// The event builder class to create the event and nested classes like info or location
@EventDsl
class EventBuilder {
    var info: Info? = null
    var location: Location? = null

    private val topics = mutableListOf<Topic>()

    fun info(block: InfoBuilder.() -> Unit) {
        info = InfoBuilder().apply(block).build()
    }

    // The Event consists of a list of topic elements
    // The easiest implementation consists of multiple topic children of the event element
    // For a better structured DSL we nest these topic elements in a topics element
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

    // Another nice trick: The type of the property date in the info class is DateTime
    // To make it easier for the user, we provide a public String property date and convert it later on to a DateTime object
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

// Nested topic builder to create a list of topic items
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