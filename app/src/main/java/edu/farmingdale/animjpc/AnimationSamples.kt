package edu.farmingdale.animjpc

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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

@Composable
fun TransitionAPIChangeAnimation() {
    var isSelected by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isSelected, label = "transition")
    val size by transition.animateDp(label = "size") { if (it) 300.dp else 100.dp }
    val color by transition.animateColor(label = "color") { if (it) Color.Red else Color.Blue }
    Column (modifier = Modifier.fillMaxSize()){
        Button(modifier = Modifier.padding(top = 50.dp)
            .fillMaxWidth().height(50.dp), onClick = {
            isSelected = !isSelected
        }) { Text("Click me", fontSize = (30.sp))  }
        Text("Color change animation", modifier = Modifier.
             size(size)
            .background(color))

    }
}

@Composable
fun GestureChangeAnimation() {
    val offsetX = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    var isSelected by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()){
        Button(modifier = Modifier.padding(top = 50.dp)
            .fillMaxWidth().height(50.dp), onClick = {
            isSelected = !isSelected
        }) { Text("Click me", fontSize = (30.sp))  }
        Text("Color change animation",
            modifier = Modifier.size(100.dp).background(Color.Yellow).
                    offset { IntOffset(offsetX.value.roundToInt(), 0) }
                        .pointerInput(Unit) {
                            detectDragGestures { _, dragAmount ->
                            coroutineScope.launch {
                            offsetX.animateTo(offsetX.value + dragAmount.x)
                        }
                    }
                })

    }
}