package com.example.pix.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailPicture(url: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlideImage(
            imageModel = { decodeUrl(url) },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            )
        )
    }

}

private fun decodeUrl(encodedUrl: String): String {
    return URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
}