package com.smartschools.android.di

import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferences
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.domain.repository.UserRepository
import com.smartschools.android.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    //Shared Preferences
    @Binds
    abstract fun provideSharedPreferences(sharedPreferencesImpl: SharedPreferencesImpl): SharedPreferences


    //Repos
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

//    @Binds
//    abstract fun provideProjectRepository(projectRepositoryImpl: ProjectRepositoryImpl): ProjectRepository
//
//    @Binds
//    abstract fun provideMailRepository(mailRepositoryImpl: MailRepositoryImpl): MailRepository
//
//    @Binds
//    abstract fun provideArchiveRepository(mailRepositoryImpl: ArchiveRepositoryImpl): ArchiveRepository
//
//    @Binds
//    abstract fun provideAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepositoryImpl
}