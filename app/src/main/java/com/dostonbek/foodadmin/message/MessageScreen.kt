package com.dostonbek.foodadmin.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MessageScreen(messageViewModel: MessageViewModel = viewModel()) {
    val foodMessages by messageViewModel.foodMessages.observeAsState(emptyList())
    var messageText by remember { mutableStateOf("") }
    var editingMessageId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        messageViewModel.getMessage()
    }

    Scaffold(bottomBar = {
        BottomAppBar(
            containerColor = Color.White, contentPadding = PaddingValues(8.dp)
        ) {
            MessageInputField(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendClicked = {
                    if (messageText.isNotEmpty()) {
                        if (editingMessageId != null) {
                            messageViewModel.editMessage(editingMessageId!!, messageText)

                            editingMessageId = null
                        } else {
                            messageViewModel.setMessage(messageText)
                        }
                        messageText = "" // Clear the input field after sending
                    }
                }
            )
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(foodMessages.size) { index ->
                    val message = foodMessages[index]
                    MessageItem(
                        message = message.message,
                        sendTime = message.sendTime,
                        isSent = true, // Replace with actual sender ID check
                        onEditClicked = {
                            messageText = message.message
                            editingMessageId = message.id
                        },
                        onDeleteClicked = {
                            messageViewModel.deleteMessage(message.id)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun MessageItem(
    message: String,
    sendTime: String,
    isSent: Boolean,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
) {
    val bubbleColor = if (isSent) Color.Blue else Color.Gray
    val textColor = if (isSent) Color.White else Color.Black
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(bubbleColor, RoundedCornerShape(6.dp))
                .padding(6.dp, 2.dp, 6.dp, 0.dp)
                .clickable { expanded = true }
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
                Text(
                    text = message,
                    color = textColor,
                    fontSize = 16.sp
                )
                Text(
                    text = sendTime,
                    color = textColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 2.dp)
                )
            }
        }

        // Dropdown menu for edit and delete
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                onEditClicked()
                expanded = false
            }, text = { Text("Edit") })
            DropdownMenuItem(onClick = {
                onDeleteClicked()
                expanded = false
            }, text = { Text("Delete") })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = messageText,
            onValueChange = onMessageChange,
            placeholder = { Text("Type a message...", fontSize = 14.sp) },
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray)
                .padding(end = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(onSend = {
                onSendClicked()
            }),
            singleLine = true,
            trailingIcon = {
                if (messageText.isNotEmpty()) {
                    IconButton(onClick = { onSendClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Send",
                            tint = Color.Blue
                        )
                    }
                }
            }
        )
    }
}
