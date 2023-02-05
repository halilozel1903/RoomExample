package com.halil.ozel.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity("person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("person_id") @NotNull var person_id: Int,
    @ColumnInfo("person_name") @NotNull var person_name: String,
    @ColumnInfo("person_age") @NotNull var person_age: Int
)
