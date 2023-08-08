package com.smartschools.android.di

import com.smartschools.android.data.dataSource.student.StudentRemoteDataSource
import com.smartschools.android.data.dataSource.student.StudentRemoteDataSourceImpl
import com.smartschools.android.data.dataSource.user.local.UserLocalDataSource
import com.smartschools.android.data.dataSource.user.local.UserLocalDataSourceImpl
import com.smartschools.android.data.dataSource.user.remote.UserRemoteDataSource
import com.smartschools.android.data.dataSource.user.remote.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {


    //User Remote and local Data Source
    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: UserRemoteDataSourceImpl): UserRemoteDataSource


//
    @Binds
    abstract fun provideStudentRemoteSource(remoteDataSource: StudentRemoteDataSourceImpl) : StudentRemoteDataSource
//
//    @Binds
//    abstract fun provideMailRemoteSource(remoteDataSource: MailRemoteSourceImpl) : MailRemoteSource
//
//
//    @Binds
//    abstract fun provideArchiveRemoteSource(remoteDataSource: ArchiveRemoteSourceImpl) : ArchiveRemoteSource
//    @Binds
//    abstract fun provideAddressRemoteSource(remoteDataSource: AddressRemoteSourceImpl) : AddressRemoteSource
}