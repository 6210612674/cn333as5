package com.example.phonebook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.phonebook.domain.model.NoteModel
import com.example.phonebook.util.fromHex

@ExperimentalMaterialApi
@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        ListItem(
            text = { Text(text = note.name, maxLines = 1) },
            secondaryText = {
                Text(text = note.phoneNumber, maxLines = 1)
            },
            icon = {
                NoteColor(
                    color = Color.fromHex(note.color.hex),
                    size = 40.dp,
                    border = .5.dp
                )
            },
            modifier = Modifier.clickable {
                onNoteClick.invoke(note)
            }
        )
    }
}
