package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection(
  label: String,
  modifier: Modifier = Modifier
) {
  Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
    Column {
      Text(
        text = label,
        fontSize = 19.sp,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
      )
    }
  }
}
