package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markoid.readerapp.presentation.actions.OnSimpleClick

@Composable
fun RoundedButton(
  label: String,
  radius: Int = 29,
  onClick: OnSimpleClick = {}
) {
  Surface(
    modifier = Modifier.clip(
      RoundedCornerShape(
        bottomEndPercent = radius,
        topStartPercent = radius
      )
    ),
    color = Color(0xFF92CBDF)
  ) {
    Column(
      modifier = Modifier
        .width(90.dp)
        .heightIn(40.dp)
        .clickable { onClick() },
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = label,
        style = TextStyle(color = Color.White, fontSize = 15.sp)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview() {
  RoundedButton(
    label = "Reading"
  )
}