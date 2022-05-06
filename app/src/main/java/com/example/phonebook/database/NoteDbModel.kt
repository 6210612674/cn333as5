package com.example.phonebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "can_be_checked_off") val canBeCheckedOff: Boolean,
    @ColumnInfo(name = "is_checked_off") val isCheckedOff: Boolean,
    @ColumnInfo(name = "color_id") val colorId: Long,
    @ColumnInfo(name = "tag_id") val tagId: Long,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean
) {
    companion object {
        val DEFAULT_NOTES = listOf(
            NoteDbModel(1, "KnaB", "0845697358", false, false, 1, 2, false),
            NoteDbModel(2, "creamss", "0895475514", false, false, 2, 2, false),
            NoteDbModel(3, "mimimi", "0965855425", false, false, 3, 2, false),
            NoteDbModel(4, "JohnRuKa", "0854712459", false, false, 4, 4, false),
            NoteDbModel(5, "My Home", "025999999", false, false, 5, 3, false),
        )
    }
}
