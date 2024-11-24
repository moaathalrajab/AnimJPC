package edu.farmingdale.animjpc

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorChangeAnimation() {
    var isSelected by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (isSelected) Color.Red else Color.Blue,
        animationSpec = tween(2500))
    Column (modifier = Modifier.fillMaxSize()){
        Button(modifier = Modifier.padding(top = 50.dp)
            .fillMaxWidth().height(50.dp), onClick = {
            isSelected = !isSelected
        }) { Text("Click me", fontSize = (30.sp))  }
        Text("Color change animation", modifier = Modifier.fillMaxWidth()
            .height(150.dp).background(color))

    }
}