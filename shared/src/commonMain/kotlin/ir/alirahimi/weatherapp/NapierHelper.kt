package ir.alirahimi.weatherapp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initializeNapier() {
    Napier.base(DebugAntilog())
}

fun doSomething(){
    Napier.v("verbose", tag = "verboseTag")
    Napier.d("debug", tag = "debugTag")
    Napier.i("info", tag = "infoTag")
    Napier.w("warning", tag = "warningTag")
    Napier.e("error", tag = "errorTag")
}
