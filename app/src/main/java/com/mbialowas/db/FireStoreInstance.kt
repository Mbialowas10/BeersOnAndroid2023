package com.mbialowas.db

import com.google.firebase.firestore.FirebaseFirestore

object FireStoreInstance {
    private var instance: FirebaseFirestore? = null

    fun getInstance(): FirebaseFirestore{
        synchronized(this){
            if (instance == null){
                instance = FirebaseFirestore.getInstance()
            }
        }
        return instance!!
    }
}