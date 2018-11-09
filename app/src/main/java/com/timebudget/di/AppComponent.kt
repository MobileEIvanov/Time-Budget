package com.timebudget.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by emil.ivanov on 9/28/18.
 *
 * https://medium.com/@rahul.singh/clean-architecture-kotlin-dagger-2-rxjava-mvvm-and-unit-testing-dc05dcdf3ce6
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent
