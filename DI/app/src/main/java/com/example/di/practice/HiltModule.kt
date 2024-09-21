package com.example.di.practice

import com.example.di.Car
import com.example.di.Engine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideCar(@Engine2 engine: Engine) : Car {
        return Car(engine)
    }

    @Provides
    @Engine2
    fun provideEngine() : Engine {
        return Engine()
    }

    @Provides
    @Engine1
    fun provideEngine1() : Engine {
        return Engine()
    }

    /* this is called qualifiers and it gives unique identity to
    each dependency whose type is same
     */
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Engine2

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Engine1

}