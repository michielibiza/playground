package nl.michiel.portfolio

import android.app.Application
import timber.log.Timber

class PortfolioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
