package com.example.phonebook.domain.model

const val NEW_NOTE_ID = -1L

data class NoteModel(
    val id: Long = NEW_NOTE_ID, // This value is used for new notes
    val name: String = "",
    val phoneNumber: String = "",
    val isCheckedOff: Boolean? = null, // null represents that the note can't be checked off
    val tag: TagModel = TagModel.DEFAULT,
    val color: ColorModel = ColorModel.DEFAULT
)