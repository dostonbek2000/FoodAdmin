package com.dostonbek.foodadmin.message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dostonbek.foodadmin.data.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageViewModel : ViewModel() {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status
    private val _foodMessages = MutableLiveData<List<Message>>()
    val foodMessages: LiveData<List<Message>> get() = _foodMessages


    fun setMessage(messageText: String) {
        val currentTimestamp = System.currentTimeMillis()
        val sentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(currentTimestamp))

        if (messageText.isBlank()) {
            _status.value = "Invalid input"
            return
        }

        val newItemRef = database.child("message").push()
        val messageId = newItemRef.key ?: return

        val foodMessage = Message(id = messageId, message = messageText, sendTime = sentTime)

        newItemRef.setValue(foodMessage)
            .addOnSuccessListener {
                _status.value = "Message sent successfully"
            }
            .addOnFailureListener {
                _status.value = "Failed to send message: ${it.message}"
            }
    }


    fun getMessage() {
        database.child("message").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Message>()
                for (dataSnapshot in snapshot.children) {
                    // Log the raw data for debugging
                    Log.d("MessageViewModel", "Raw data: ${dataSnapshot.value}")

                    // Convert the data snapshot to a Message object
                    val item = dataSnapshot.getValue(Message::class.java)
                    if (item != null) {
                        items.add(item)
                    }
                }
                _foodMessages.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                _status.value = "Failed to read items: ${error.message}"
            }
        })
    }

    fun deleteMessage(messageId: String) {
        database.child("message").child(messageId).removeValue()
            .addOnSuccessListener {
                _status.value = "Message deleted successfully"
            }
            .addOnFailureListener {
                _status.value = "Failed to delete message: ${it.message}"
            }
    }
    fun editMessage(messageId: String, newMessageText: String) {
        val updatedMessage = mapOf(
            "message" to newMessageText
        )
        database.child("message").child(messageId).updateChildren(updatedMessage)
            .addOnSuccessListener {
                _status.value = "Message updated successfully"
            }
            .addOnFailureListener {
                _status.value = "Failed to update message: ${it.message}"
            }
    }

}

