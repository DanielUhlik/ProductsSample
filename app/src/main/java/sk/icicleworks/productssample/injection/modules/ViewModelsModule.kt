package sk.icicleworks.productssample.injection.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sk.icicleworks.productssample.ui.productEditor.ProductEditorViewModel
import sk.icicleworks.productssample.ui.dashboard.DashboardViewModel

val viewModelsModule = module {

    viewModel { DashboardViewModel(get()) }
    viewModel { ProductEditorViewModel(get(), androidApplication()) }
}