package com.asmanmirza.schoolpen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asmanmirza.schoolpen.data.database.entity.Fake

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 21:07
 */
@Database(entities = [Fake::class], version = 1, exportSchema = false)
abstract class SchoolDB : RoomDatabase()