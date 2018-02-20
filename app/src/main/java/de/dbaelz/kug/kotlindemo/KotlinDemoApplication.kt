package de.dbaelz.kug.kotlindemo

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class KotlinDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this);
    }
}