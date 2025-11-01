package com.example.taskapp

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.ui.theme.TaskAppTheme
import java.util.Date

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val tasks by lazy { viewModel.allTasks }
    private val dates by lazy { viewModel.allDates }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TaskAppTheme {
                ListView()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ListView() {
        val items = tasks.collectAsState().value
        val dateItems = dates.collectAsState().value
        var state by remember { mutableIntStateOf(0) }
        val titles = listOf("Новые", "В работе", "Завершенные", "Отмененные")
        val showDialog =  remember { mutableStateOf(false) }
        val showConclusionDialog = remember { mutableStateOf(false) }
        val showDateDialog = remember { mutableStateOf(false) }
        var taskUpd: Task? = null
        var date = Date(0L)

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Task App")
                    },
                    actions = {
                        IconButton(onClick = {
//                            showDateDialog.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Sort"
                            )
                        }

                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showDialog.value = true
                    }, modifier = Modifier.padding(8.dp),
                    content = {
                        Icon(Icons.Filled.Add, "")
                    }
                )
            },
            content = { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    PrimaryTabRow(selectedTabIndex = state) {
                        titles.forEachIndexed { i, title ->
                            Tab(
                                selected = state == i,
                                onClick = { state = i },
                                text = {
                                    Text(
                                        text = title,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            )
                        }
                    }
                    if (showDateDialog.value) {
                        DatePickerModal(
                            onDateSelected = {
                                date = Date(it ?: 0L)
                            }
                        ) {
                            showDateDialog.value = false
                        }
                    }
                    if (showDialog.value) {
                        NewTaskDialog(
                            value = "",
                            setShowDialog = { showDialog.value = false },
                            setValue = { theme, taskTxt, date ->
                                val task = Task(
                                    id = 0,
                                    status = Status.NEW,
                                    theme = theme,
                                    text = taskTxt,
                                    date = Date(),
                                    conclusion = null,
                                    deadline = date
                                )
                                viewModel.addTask(task)
                            }
                        )

                    }
                    if (showConclusionDialog.value) {
                        ConclusionDialog("", { showConclusionDialog.value = false }) { con ->
                            taskUpd?.let { viewModel.updateStatus(it, Status.FINISHED, con) }
                        }
                    }
                    LazyColumn(contentPadding = PaddingValues(bottom = 80.dp, top = 10.dp)) {
                        items(items.filter {
                            when (state) {
                                0 -> it.status == Status.NEW
                                1 -> it.status == Status.PROGRESS
                                2 -> it.status == Status.FINISHED
                                3 -> it.status == Status.CANCELED
                                else -> {
                                    error("Tab dosen't exsist")
                                }
                            }
                        }) {
                            TaskView(it, {
                                if (it.status == Status.CANCELED)
                                    viewModel.deleteTask(it)
                                else
                                    viewModel.updateStatus(it, Status.CANCELED, null)
                            }, {
                                if (it.status == Status.NEW)
                                    viewModel.updateStatus(it, Status.PROGRESS, null)
                                else {
                                    taskUpd = it
                                    showConclusionDialog.value = true

                                }


                            })
                        }
                    }
                }
            }

        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview(showBackground = true)
    private fun PreviewTask() {
        var state by remember { mutableIntStateOf(0) }
        val titles = listOf("Новые", "В работе", "Завершенные", "Отмененные")
        val task = Task(id = 0, status = Status.NEW, theme = "Problem with wifi", text = "Please, can you help me?", date = Date(2L), conclusion = "", deadline = Date())
        Scaffold(
            topBar = {},
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            },
            content = { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    PrimaryTabRow(selectedTabIndex = state) {
                        titles.forEachIndexed { i, title ->
                            Tab(
                                selected = state == i,
                                onClick = { state = i},
                                text = { Text(title) }
                            )
                        }
                    }
                    TaskView(task, { }, {})
                    TaskView(task, { }, {})
                    TaskView(task, { }, {})
                }
            }
        )

    }
}
