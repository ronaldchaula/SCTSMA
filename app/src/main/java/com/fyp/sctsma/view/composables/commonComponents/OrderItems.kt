package com.fyp.sctsma.view.composables.commonComponents

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.view.composables.navigation.InternalRoutes

@Composable
fun OrderItems(
    modifier: Modifier = Modifier,
    agreement: Agreement,
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

                        val itemDetailsPath = InternalRoutes.DetailsScreen.route + "/${agreement.rowId}"
                        Log.d("Order", "OrderItems: $itemDetailsPath and the passed id is ${agreement.rowId}")
                        navController.navigate(itemDetailsPath)
                }

            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FirstRow(modifier = Modifier, agreement = agreement)
        SecondRow(modifier = Modifier, agreement = agreement)
        ThirdRow(modifier= Modifier, agreement=  agreement)

    }
}

@Composable
private fun ThirdRow(modifier: Modifier, agreement: Agreement) {
    val (date, time) = getDateTimeParts(agreement)
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
            text = "Date: $date",
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
            text = "Time: $time",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Center,
            ),
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
@Composable
private fun SecondRow(modifier: Modifier, agreement: Agreement) {
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
            text = "Order: ${clipTitleLength(agreement,6)}",
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
            text = "TZS ${agreement.amount}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Center,
            ),
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
private fun FirstRow(modifier: Modifier, agreement: Agreement) {
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
            text = "ID: ${clipIdLength(agreement,6)}",
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
            text = "Status ${orderStatus(agreement)}",
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

@Preview
@Composable
fun TablePreview() {
    OrderItems(
        modifier = Modifier,
        Agreement(
            "1323232","Shoes","Fenty Puma kilo kama 8 hivi",1000.00,1,"255653302720","Pending","Kariakoo","Dar","834198",                                                                                                                           "1","","","pending","",false
        ),
        rememberNavController()
    )
}

fun clipTitleLength(title: Agreement, maxLength: Int): String {
    return if (title.productName?.length ?: 0 > maxLength) {
        title.productName?.substring(0, maxLength) ?: "" // Provide empty string if productName is null
    } else {
        title.productName ?: "" // Return productName or empty string if it's null
    }
}