package de.dbaelz.kug.kotlindemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import de.dbaelz.kug.kotlindemo.data.EventService


class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val titleText = findViewById<TextView>(R.id.event_title)
        val locationText = findViewById<TextView>(R.id.event_location)
        val slot1Text = findViewById<TextView>(R.id.event_slot1)
        val slot2Text = findViewById<TextView>(R.id.event_slot2)


        val (info, topics, location) = EventService.getFirstEvent()


        if (info?.title != null) {
            titleText.text = info.title
            if (info.link != null) {
                titleText.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                titleText.setOnClickListener({
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(info.link)))
                })
            }
        }

        if (location != null) {
            locationText.text = getString(R.string.event_location_text, location.name, location.address,
                    location.zip, location.city)
            locationText.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        if (topics.size >= 2) {
            val slot1Topic = topics[0]
            slot1Text.text = getString(R.string.event_slot_text, slot1Topic.author, slot1Topic.title)

            val slot2Topic = topics[1]
            slot2Text.text = getString(R.string.event_slot_text, slot2Topic.author, slot2Topic.title)
        }
    }
}
