package com.asmanmirza.schoolpen

import android.app.Application
import com.asmanmirza.schoolpen.UI.Student.di.ApplicationComponent
import com.asmanmirza.schoolpen.UI.Student.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp


/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 20:57
 */

@HiltAndroidApp
class SchoolApp : Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}