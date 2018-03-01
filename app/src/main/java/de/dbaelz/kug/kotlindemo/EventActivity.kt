package de.dbaelz.kug.kotlindemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import de.dbaelz.kug.kotlindemo.data.EventService


class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val titleTextView = findViewById<TextView>(R.id.event_title)
        val locationTextView = findViewById<TextView>(R.id.event_location)
        val slot1TextView = findViewById<TextView>(R.id.event_slot1)
        val slot2TextView = findViewById<TextView>(R.id.event_slot2)


        // Some examples for higher order and extension functions
        // For more examples see the files in the examples module


        val (info, topics, location) = EventService.getFirstEvent()
                .also {
                    // also(): An extension function typically used for side-effects in chains
                    Log.d("TAG", it.toString())
                }


        if (info?.title != null) {
            titleTextView.apply {
                // apply(): Example of a function literal with receiver
                // Usage for initialization and configuration
                text = info.title
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                if (info.link != null) {
                    titleTextView.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                    titleTextView.setOnClickListener({
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(info.link)))
                    })
                }
            }

        }

        location?.let {
            // let(): An extension function typically used for nullability handling
            locationTextView.text = getString(R.string.event_location_text, location.name, location.address,
                    location.zip, location.city)
            locationTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        if (topics.size >= 2) {
            val slot1Topic = topics[0]
            slot1TextView.text = getString(R.string.event_slot_text, slot1Topic.author, slot1Topic.title)

            val slot2Topic = topics[1]
            slot2TextView.text = getString(R.string.event_slot_text, slot2Topic.author, slot2Topic.title)
        }

        // Usage of a higher order function
        // Execute the code in the lambda only when it's a debug build
        onDebug {
            val debugVersionTextView = findViewById<TextView>(R.id.event_debug_version)
            debugVersionTextView.text = getString(R.string.event_debug_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE)
            debugVersionTextView.visibility = View.VISIBLE
        }
    }
}

fun onDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        Log.d("DEBUG", "Debug mode active")
        block()
    }
}
