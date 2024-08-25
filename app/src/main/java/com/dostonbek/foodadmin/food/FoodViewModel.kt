package com.dostonbek.foodadmin.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dostonbek.foodadmin.data.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FoodViewModel : ViewModel() {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status
    private val _foodItems = MutableLiveData<List<Food>>()
    val foodItems: LiveData<List<Food>> get() = _foodItems


    fun addFood(name: String, price: String, image:String) {
        if (name.isBlank() && price <= 0.toString()) {
            _status.value = "Invalid input"
            return
        }

        val foodItem = Food(name, price.toString(),image)
        val newItemRef = database.child("foodItems").push()

        newItemRef.setValue(foodItem)
            .addOnSuccessListener {
                _status.value = "Item added successfully"
            }
            .addOnFailureListener {
                _status.value = "Failed to add item: ${it.message}"
            }
    }
    fun readFood() {
        database.child("foodItems").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Food>()
                for (dataSnapshot in snapshot.children) {
                    val item = dataSnapshot.getValue(Food::class.java)
                    if (item != null) {
                        items.add(item)
                    }
                }
                _foodItems.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                _status.value = "Failed to read items: ${error.message}"
            }
        })
    }
}

