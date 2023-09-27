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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.johndev.pokedex.common.utils.capitalizeFirstLetter
import com.johndev.pokedex.common.utils.formatId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCardLoad(color: Color) {
    val gradient = listOf(
        color.copy(alpha = 0.9f),
        color.copy(alpha = 0.4f),
        color.copy(alpha = 0.9f),
    )
    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 8f,
        targetValue = 1808f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(width = 2.dp, color = Color(0xffF5F5F5))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "",
                        modifier = Modifier.background(brush).weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "",
                        modifier = Modifier.background(brush).weight(1f),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier.background(brush).weight(1f),
                    )
                    Box(
                        modifier = Modifier.background(brush).weight(1f),
                    )
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
                        modifier = Modifier.width(70.dp).height(70.dp).background(brush).align(
                            Alignment.Center),
                    )
                }
            }
        }
    }
}