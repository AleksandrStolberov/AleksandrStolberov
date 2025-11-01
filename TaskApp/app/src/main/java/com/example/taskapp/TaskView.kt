package com.example.taskapp

import android.text.format.DateFormat
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.ui.theme.Green40
import com.example.taskapp.ui.theme.Red40
import kotlin.math.ceil

@Composable
fun TaskView(task: Task, onCancel: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column() {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(task.status.icon()),
                            contentDescription = "icon",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = task.status.statusName(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { onCancel() }) {
                            Icon(Icons.Filled.Close, "Icon Button")
                        }
                    }

                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(text = "Тема: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = task.theme, fontSize = 18.sp)

                }
                Row(
                    horizontalArrangement = Arrangement.Absolute.Center,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(text = "Задача: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = task.text, fontSize = 18.sp)

                }
                Row(
                    horizontalArrangement = Arrangement.Absolute.Center,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(text = "Дедлайн: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = DateFormat.format("dd.MM.yy", task.deadline).toString(), fontSize = 18.sp)
                    if (task.status == Status.NEW || task.status == Status.PROGRESS)
                        Text(text = getDeadLineText(task.deadline.time), fontSize = 18.sp)

                }

                if (task.conclusion != null) {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Center,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(text = "Заключение: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = task.conclusion, fontSize = 18.sp)

                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    //Text(DateFormat.getDateInstance().format(task.date))
                    if (task.status == Status.NEW || task.status == Status.PROGRESS) {
                        OutlinedButton(
                            onClick = {
                                onClick()
                            },
                            colors = buttonColor(task.status),
                            border = BorderStroke(0.dp, Color.Transparent)
                        ) {
                            Text(if (task.status == Status.NEW) "Взять в работу" else "Заврешить")
                        }
                    }
                }
            }
        }
    }
}

private fun buttonColor(status: Status): ButtonColors {
    return ButtonColors(
        containerColor = if (status == Status.NEW) Green40 else Red40,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.White
    )
}

private fun getDeadLineText(deadline: Long): String {
    val time = ((deadline - System.currentTimeMillis()))
    val days = time / 86400000L
    return if (days >= 0)
        "(Осталось дней: ${if (time > 0) days + 1 else days})"
    else
        "(Просрочено дней: ${days * -1})"
}