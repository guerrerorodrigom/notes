package com.rodrigoguerrero.notes.common.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation

@Composable
fun FullScreenLottie(
    composition: LottieComposition?,
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition, progress = progress)
    }
}
