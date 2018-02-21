package de.dbaelz.kug.kotlindemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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


        val (info, topics, location) = EventService.getFirstEvent()


        if (info?.title != null) {
            titleTextView.text = info.title
            titleTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            if (info.link != null) {
                titleTextView.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                titleTextView.setOnClickListener({
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(info.link)))
                })
            }
        }

        if (location != null) {
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
    }
}
