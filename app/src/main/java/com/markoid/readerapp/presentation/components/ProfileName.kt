package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markoid.readerapp.domain.entities.UserProfile
import com.markoid.readerapp.presentation.enums.DataState
import com.markoid.readerapp.presentation.enums.DataState.Data

@Composable
fun ProfileName(nameState: State<DataState<UserProfile>?>) {
  val displayName = when (val result = nameState.value) {
    is Data -> result.data.displayName
    else -> "Name"
  }
  Text(
    text = displayName,
    modifier = Modifier.padding(2.dp),
    style = MaterialTheme.typography.overline,
    color = Color.Red,
    fontSize = 16.sp,
    maxLines = 1,
    overflow = TextOverflow.Clip
  )
}