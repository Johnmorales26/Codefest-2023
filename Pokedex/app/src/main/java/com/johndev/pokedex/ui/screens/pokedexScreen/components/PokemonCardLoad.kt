package com.johndev.pokedex.ui.screens.pokedexScreen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokemonCardLoad(color: Color) {
    val gradient = listOf(
        color.copy(alpha = 0.9f),
        color.copy(alpha = 0.4f),
        color.copy(alpha = 0.9f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation by transition.animateFloat(
        initialValue = 8f,
        targetValue = 1808f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(width = 2.dp, color = Color(0xffF5F5F5), shape = RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            val gradientBrush = linearGradient(
                colors = gradient,
                start = Offset(200f, 200f),
                end = Offset(translateAnimation, translateAnimation)
            )

            val cardModifier = Modifier
                .weight(3f)
                .fillMaxSize()
                .padding(16.dp)

            val spacerModifier = Modifier
                .background(brush = gradientBrush, shape = RoundedCornerShape(8.dp))
                .weight(1f)

            val cardTextStyle = MaterialTheme.typography.titleMedium.copy(color = Color.Transparent)

            Column(modifier = cardModifier, verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "      ", modifier = spacerModifier, style = cardTextStyle)
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(text = "      ", modifier = spacerModifier, style = cardTextStyle)
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "      ", modifier = spacerModifier, style = cardTextStyle)
                    Text(text = "      ", modifier = spacerModifier, style = cardTextStyle)
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 64.dp,
                                bottomStart = 64.dp,
                                topEnd = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(brush = gradientBrush, shape = RoundedCornerShape(8.dp))
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}