package com.fyp.sctsma.view.uiexperiments

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.model.navigations.NavigationDrawerList
import components.commonComponents.DarkOverlayComponentBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val context = LocalContext.current
    val selected = remember { Icons.Default.Home}
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {mutableStateOf(false)}

    //Modal drawer

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
           
         Column(
             modifier = Modifier
                 .clip(
                     shape = RoundedCornerShape(bottomEnd = 10.dp)
                 )
                 .background(
                     color = Color(
                         0xFFFDFDFD
                     )
                 )
                 .border(
                     width = 1.dp,
                     color = Color.Black,
                     shape = RoundedCornerShape( bottomEnd = 10.dp)
                 )
                 .fillMaxSize(.8f)
                 .fillMaxHeight()
                ,
             verticalArrangement = Arrangement.Top,
             horizontalAlignment = Alignment.CenterHorizontally
         ){

             Spacer(modifier = Modifier.padding(10.dp))
         Column(
             modifier = Modifier
                 .fillMaxSize(.9f)
                 .fillMaxHeight(),
             verticalArrangement = Arrangement.SpaceAround,
             horizontalAlignment = Alignment.CenterHorizontally
         ) {

         }
         }
        }) {
        
    }
    }

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(navController = rememberNavController())
}



