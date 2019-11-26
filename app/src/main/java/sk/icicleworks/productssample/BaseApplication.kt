package sk.icicleworks.productssample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sk.icicleworks.productssample.injection.modules.databaseModule
import sk.icicleworks.productssample.injection.modules.repositoriesModule
import sk.icicleworks.productssample.injection.modules.viewModelsModule

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(
                databaseModule,
                repositoriesModule,
                viewModelsModule
            ))
        }
    }
}