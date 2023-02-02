package com.asmanmirza.schoolpen.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 21:08
 */
@Entity(tableName = "fake")
data class Fake(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
