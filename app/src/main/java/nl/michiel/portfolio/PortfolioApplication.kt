package nl.michiel.portfolio

import android.app.Application
import nl.michiel.friends.friendsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PortfolioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidContext(this@PortfolioApplication)
            androidLogger()
            modules(friendsModule)
        }
    }
}
