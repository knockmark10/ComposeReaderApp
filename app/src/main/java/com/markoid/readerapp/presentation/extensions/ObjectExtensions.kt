package com.markoid.readerapp.presentation.extensions

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.markoid.readerapp.domain.entities.AVATAR_URL
import com.markoid.readerapp.domain.entities.DISPLAY_NAME
import com.markoid.readerapp.domain.entities.EMAIL
import com.markoid.readerapp.domain.entities.PROFESSION
import com.markoid.readerapp.domain.entities.QUOTE
import com.markoid.readerapp.domain.entities.USER_ID
import com.markoid.readerapp.domain.entities.UserProfile
import com.markoid.readerapp.presentation.enums.FirestoreCollections
import org.json.JSONObject

fun Any?.asString(): String = if (this == null) ""
else JSONObject(Gson().toJson(this)).toString(4)

fun List<DocumentSnapshot>.toUserProfile(): List<UserProfile> = this.map {
  UserProfile(
    id = it.get(USER_ID)?.toString().orEmpty(),
    displayName = it.get(DISPLAY_NAME)?.toString().orEmpty(),
    avatarUrl = it.get(AVATAR_URL)?.toString().orEmpty(),
    profession = it.get(PROFESSION)?.toString().orEmpty(),
    email = it.get(EMAIL)?.toString().orEmpty(),
    quote = it.get(QUOTE)?.toString().orEmpty()
  )
}

fun FirebaseFirestore.getCollection(
  collection: FirestoreCollections
): CollectionReference = collection(collection.value)