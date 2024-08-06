package com.fyp.sctsma.view.composables.commonComponents


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.model.navigations.BottomMenuList

@Composable
fun BottomNavBar(
    modifier: Modifier,
    homeItemClicked: () -> Unit,
    orderItemClicked: () -> Unit,
    notificationItemClicked: () -> Unit,
    historyItemClicked: () -> Unit,

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
            .height(60.dp)



    ){
       Row(
           modifier = Modifier.fillMaxSize(),
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically,
       ) {
           Row(
               modifier
                   .fillMaxWidth()
                   .height(70.dp)
                   .padding(bottom = 0.dp)
                   .background(
                       Color(0xFFFAFAFA)
                   )
               ,
               horizontalArrangement = Arrangement.SpaceEvenly,
               verticalAlignment = Alignment.CenterVertically
           ) {
               NavBarItem(modifier = Modifier, icon = BottomMenuList.Home.icon, label = BottomMenuList.Home.label, onItemClicked = homeItemClicked)
               NavBarItem(modifier = Modifier, icon = BottomMenuList.Orders.icon, label = BottomMenuList.Orders.label,onItemClicked = orderItemClicked)
               NavBarItem(modifier = Modifier, icon = BottomMenuList.Notification.icon, label = BottomMenuList.Notification.label, onItemClicked = notificationItemClicked)
               NavBarItem(modifier = Modifier, icon = BottomMenuList.History.icon, label = BottomMenuList.History.label, onItemClicked = historyItemClicked)
           }
       }

    }


}


@Composable
fun NavBarItem(
modifier: Modifier = Modifier,
icon: Int,
label: String,
onItemClicked: () -> Unit
){

     IconButton(
         modifier = Modifier
             .width(85.dp)
             .height(70.dp)
             .padding(4.dp),
         onClick = onItemClicked
     ){
         Column(
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center,
             modifier = modifier
                 .width(60.dp)
                 .fillMaxHeight()
         ) {
             Image(
                 painter = painterResource(id = icon),
                 contentDescription = label,
                 modifier = Modifier
                 )
             Spacer(modifier = modifier.height(2.dp))
             Text(
                 text = label,
                 fontSize = 9.sp,
                 color = Color(0xFF2F4858),
                 fontWeight = FontWeight(500)

             )
     }
    }
}
@Preview(showBackground = true)
@Composable
fun NavBarItemPreview(){
    BottomNavBar(
        modifier = Modifier,
        homeItemClicked = {},
        orderItemClicked = {},
        notificationItemClicked = {},
        historyItemClicked = {}
    )
}