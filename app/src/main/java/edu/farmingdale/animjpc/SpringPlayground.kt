package edu.farmingdale.animjpc

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.zIndex

@Composable
fun SpringPlayground() {
    val dampingRatio = remember {
        mutableStateOf(0.1f)
    }
    val stiffness = remember {
        mutableStateOf(50f)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        BoxWithConstraints(
            Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .zIndex(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            val shape = RoundedCornerShape(50.dp)
            val size = min(100.dp, 100.dp)
            var onTop by remember {
                mutableStateOf(true)
            }
            val offset =
                animateIntOffsetAsState(
                    targetValue = if (onTop) IntOffset.Zero else IntOffset(0,
                        with(LocalDensity.current) {
                            (maxHeight - size).roundToPx()
                        }), spring(dampingRatio.value, stiffness.value)
                )
            Box(
                modifier = Modifier
                    .offset { offset.value }
                    .shadow(
                        8.dp,
                        shape,
                    )
                    .background(Color.Red)
                    .size(size)
                    .clickable { onTop = onTop.not() }
            )

        }
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.5f, true)
                .background(Color.Yellow, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            ValueSlider(
                "Damping ratio",
                dampingRatio,
                Spring.DampingRatioHighBouncy..Spring.DampingRatioNoBouncy, Modifier
            )
            ValueSlider(
                "Stiffness",
                stiffness,
                Spring.StiffnessVeryLow..Spring.StiffnessMedium, Modifier
            )
        }
    }
}

@Composable
private fun ValueSlider(
    title: String,
    value: MutableState<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,

    ) {
    Column(modifier) {
        Text(title + ": ${value.value}", style = MaterialTheme.typography.bodyLarge)
        Slider(value = value.value, onValueChange = { value.value = it }, valueRange = valueRange)
    }
}

