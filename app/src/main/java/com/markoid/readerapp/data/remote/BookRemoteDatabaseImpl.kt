package com.markoid.readerapp.data.remote

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.presentation.enums.FirestoreCollections.BOOKS
import com.markoid.readerapp.presentation.extensions.getCollection
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BookRemoteDatabaseImpl
@Inject constructor(
  remoteDatabase: FirebaseFirestore
) : BookRemoteDatabase {

  private val bookCollection = remoteDatabase.getCollection(BOOKS)

  override suspend fun saveBook(book: Book): Boolean = suspendCancellableCoroutine { continuation ->
    bookCollection.add(book)
      .addOnSuccessListener {
        updateBookId(
          bookCollection = bookCollection,
          documentReferenceId = it.id,
          onSuccess = { isSuccessful -> continuation.resume(isSuccessful) },
          onFailure = { exception -> continuation.resumeWithException(exception) }
        )
      }
      .addOnFailureListener { continuation.resumeWithException(it) }
  }

  private fun updateBookId(
    bookCollection: CollectionReference,
    documentReferenceId: String,
    onSuccess: (Boolean) -> Unit,
    onFailure: (Throwable) -> Unit
  ) {
    bookCollection.document(documentReferenceId)
      .update(hashMapOf("id" to documentReferenceId) as Map<String, Any>)
      .addOnCompleteListener { onSuccess(it.isSuccessful) }
      .addOnFailureListener { onFailure(it) }
  }

  override suspend fun getBooksByUserId(
    userId: String
  ): List<Book> = bookCollection.get()
    .await()
    .documents
    .mapNotNull { it.toObject(Book::class.java) }
    .filter { it.userId == userId }
}