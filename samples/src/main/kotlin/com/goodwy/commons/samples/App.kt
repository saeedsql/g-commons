package com.devgroup.commons.samples

import com.github.ajalt.reprint.core.Reprint
import com.devgroup.commons.RightApp

class App : RightApp() {
    override fun onCreate() {
        super.onCreate()
        Reprint.initialize(this)
    }
}
