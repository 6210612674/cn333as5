package com.example.phonebook.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonebook.routing.Screen
import com.example.phonebook.ui.components.AppDrawer
import com.example.phonebook.domain.model.NoteModel
import com.example.phonebook.domain.model.TagModel
import com.example.phonebook.ui.components.Note
import com.example.phonebook.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun TagsScreen(viewModel: MainViewModel) {
    val notes by viewModel.notesNotInTrash.observeAsState(listOf())
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Phonebook",
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Drawer Button"
                        )
                    }
                }
            )
        },
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Tags,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onCreateNewNoteClick() },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Contacts Button"
                    )
                }
            )
        }
    ) {
        if (notes.isNotEmpty()) {
            NotesFriendsList(
                notes = notes,
                onNoteCheckedChange = {
                    viewModel.onNoteCheckedChange(it)
                },
                onNoteClick = { viewModel.onNoteClick(it) }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
private fun NotesFriendsList(
    modifier: Modifier = Modifier,
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit,
) {
    var isFriendsExpanded by rememberSaveable { mutableStateOf(false) }
    var isHomeExpanded by rememberSaveable { mutableStateOf(false) }
    var isWorkExpanded by rememberSaveable { mutableStateOf(false) }
    var isUniversityExpanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable { isFriendsExpanded = !isFriendsExpanded }
        ) {
            Text(
                text = " Friends",
                fontSize = 20.sp,
            )
        }
        AnimatedVisibility(
            visible = isFriendsExpanded,
        ) {
            LazyColumn {
                items(count = notes.filter { it.tag.tagName == "Friends" }.size) { noteIndex ->
                    val note = notes.filter { it.tag.tagName == "Friends" }[noteIndex]
                    Note(
                        note = note,
                        onNoteClick = onNoteClick,
                    )
                }
            }
        }
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable { isHomeExpanded = !isHomeExpanded }
        ) {
            Text(
                text = " Home",
                fontSize = 20.sp,
            )
        }
        AnimatedVisibility(
            visible = isHomeExpanded,
        ) {
            LazyColumn {
                items(count = notes.filter { it.tag.tagName == "Home" }.size) { noteIndex ->
                    val note = notes.filter { it.tag.tagName == "Home" }[noteIndex]
                    Note(
                        note = note,
                        onNoteClick = onNoteClick,
                    )
                }
            }
        }
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable { isWorkExpanded = !isWorkExpanded }
        ) {
            Text(
                text = " Work",
                fontSize = 20.sp,
            )
        }
        AnimatedVisibility(
            visible = isWorkExpanded,
        ) {
            LazyColumn {
                items(count = notes.filter { it.tag.tagName == "Work" }.size) { noteIndex ->
                    val note = notes.filter { it.tag.tagName == "Work" }[noteIndex]
                    Note(
                        note = note,
                        onNoteClick = onNoteClick,
                    )
                }
            }
        }
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable { isUniversityExpanded = !isUniversityExpanded }
        ) {
            Text(
                text = " University",
                fontSize = 20.sp,
            )
        }
        AnimatedVisibility(
            visible = isUniversityExpanded,
        ) {
            LazyColumn {
                items(count = notes.filter { it.tag.tagName == "University" }.size) { noteIndex ->
                    val note = notes.filter { it.tag.tagName == "University" }[noteIndex]
                    Note(
                        note = note,
                        onNoteClick = onNoteClick,
                    )
                }
            }
        }
    }
}