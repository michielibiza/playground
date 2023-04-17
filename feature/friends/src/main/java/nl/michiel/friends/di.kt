package nl.michiel.friends

import nl.michiel.friends.domain.FriendsRepository
import nl.michiel.friends.domain.FriendsRepositoryImpl
import nl.michiel.friends.domain.createService
import nl.michiel.friends.viewmodel.FriendsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val friendsModule = module {

    factory { createService() }

    single<FriendsRepository> { FriendsRepositoryImpl(get()) }

    viewModel { FriendsViewModel(get()) }
}
