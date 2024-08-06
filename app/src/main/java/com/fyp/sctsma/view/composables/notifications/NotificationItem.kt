package com.fyp.sctsma.view.composables.notifications


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.sctsma.R
import com.fyp.sctsma.model.notifications.NotificationContent

@Composable
fun NotificationItems(
    modifier: Modifier = Modifier,
    notificationContent: NotificationContent,
    navController: NavController
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    15.dp
                )
            )
            .fillMaxWidth(.98f)
            .wrapContentHeight()
            .background(
                color = Color(0xFFFAFAFA)
            )
            .border(
                width = 1.dp,
                Color(0x302F4858),
                shape = RoundedCornerShape(15.dp)
            )
            .shadow( // Add shadow properties here
                elevation = .1.dp, // Adjust shadow elevation (distance)
                ambientColor = Color.LightGray, // Color for ambient light
                spotColor = Color.DarkGray // Color for light source
            )
            .padding(2.dp)
            .clickable(
                onClick = {

//                    val itemDetailsPath = InternalRoutes.DetailsScreen.route + "/${agreement.rowId}"
//                    Log.d("Order", "OrderItems: $itemDetailsPath and the passed id is ${agreement.rowId}")
//                    navController.navigate(itemDetailsPath)
                }

            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        FirstRow(modifier = Modifier, item = notificationContent)
        SecondRow(modifier = Modifier, item = notificationContent)


    }
}


@Composable
private fun SecondRow(modifier: Modifier, item: NotificationContent) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${clipRowIdLength(item.message,68)}",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)

        )

    }
}

@Composable
private fun FirstRow(modifier: Modifier, item: NotificationContent) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ID: ${clipRowIdLength(item.rowId,6)}",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)

        )
        Text(
            text = "Status ${item.status}",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Center,
            ),
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF00576A)
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}



fun clipRowIdLength(rowId: String, maxLength: Int): String? {
    return if (rowId.length > maxLength) {
        rowId.substring(0, maxLength)
    } else {
        rowId
    }
}
