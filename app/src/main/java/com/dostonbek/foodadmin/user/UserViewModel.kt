package com.dostonbek.foodadmin.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserViewModel:ViewModel() {
    private val database:DatabaseReference=FirebaseDatabase.getInstance().reference
    private val _userItems=MutableLiveData<List<User>>()
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status
    val userItem:LiveData<List<User>> get() = _userItems
    fun getUser(){
        database.child("getUser").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val items= mutableListOf<User>()
                for (dataSnapshot in snapshot.children){
                    val item=dataSnapshot.getValue(User::class.java)
                    if (item != null){
                        items.add(item)
                    }

                }
                _userItems.value=items
            }

            override fun onCancelled(error: DatabaseError) {
                _status.value = "Failed to read items: ${error.message}"
            }

        })
    }
}