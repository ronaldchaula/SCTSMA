package components.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fyp.sctsma.R

@Composable
fun DarkOverlayComponentBar() {
    Image(
        painter = painterResource(R.drawable.header_overlay),
        contentDescription = "African american stock",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()

    )
}