package com.example.phonebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String
) {
    companion object {
        val DEFAULT_TAGS = listOf(
            TagDbModel(1, "Mobile"),
            TagDbModel(2, "Friends"),
            TagDbModel(3, "Home"),
            TagDbModel(4, "Work"),
            TagDbModel(5, "University"),
        )
        val DEFAULT_TAG = DEFAULT_TAGS[0]
    }
}