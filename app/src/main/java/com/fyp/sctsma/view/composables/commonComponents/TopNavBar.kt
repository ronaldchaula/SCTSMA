package components.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.R

@Composable
fun TopNavBar(
    modifier: Modifier
    ) {
    Box(
        modifier = Modifier
            .height(56.dp)

    ){
        DarkOverlayComponentBar()
        Spacer(modifier = Modifier.height(15.dp))
        Row(

            modifier
                .fillMaxWidth(1f)
                .height(56.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.menu_button),
                colorFilter = ColorFilter.tint(Color(0xFFFAFAFA)),
                contentDescription = "Hamburger",
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(start = 16.dp)
            )
            Text(
                text = stringResource(R.string.app_name) ,
                fontSize = 20.sp,
                color = Color(0xFFFAFAFA),
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(top = 16.dp)
            )
            Image(
                painter = painterResource(R.drawable.user_avatar),
                colorFilter = ColorFilter.tint(Color(0xFFFAFAFA)),
                contentDescription = "userAvatar",
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
                    .padding(end = 16.dp),

                )
        }
    }


}




