package com.example.currencyhistory.dataSource

import com.example.currencyhistory.dataSource.localDataSourse.LocalDataSource
import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSource

interface RepositorySource : RemoteDataSource, LocalDataSource