package com.fyp.sctsma.view.composables.notifications

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.repository.AppPrefRepository
import com.fyp.sctsma.view.composables.BackgroundImage
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.composables.commonComponents.NavigationPanel
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun Notification(navController: NavController, id:String?) {

    val context = LocalContext.current
    //sharedViewModel
    val sharedViewModel = remember { SharedViewModel(context) }

    //observe entries
    val agreementDetails by sharedViewModel.agreementDetails.observeAsState()
    val validationMessage by sharedViewModel.error.observeAsState()
    val isLoading by sharedViewModel.loading.observeAsState()
    val scrollState = rememberScrollState()
    val appSharedPreference = sharedViewModel.appPrefRepository



    //request makers agreement details
    LaunchedEffect(Unit) {
        if (id != null) {
            sharedViewModel.fetchAgreementDetails(id)
        }

    }

    //chooses what to show based on the condition of reception
    if (isLoading == true) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White
                )
        ) {
            BackgroundImage()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)), // Semi-transparent background
                contentAlignment = Alignment.Center
            ) {
                CircularProgressDisplay(isLoading = true, color = Color(0xFF6FC07A))
            }
        }
    } else {
        // Now pass the agreementDetails to Details, ensuring they are not null
        agreementDetails?.let {
            NotificationContainer(
                sharedViewModel = sharedViewModel,
                agreement = it,
                navigator = navController,
                scrollState = scrollState,
                appSharedPreference = appSharedPreference,
                id = id
            )
        } ?: run {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.2f)), // Semi-transparent background
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ErrorScreen("Error loading order details.")
                }
            }
        }
    }
    agreementDetails?.let {

    }
}

@Composable
fun ErrorScreen(s: String) {
    Text(text = s)
}


@Composable
fun NotificationContainer(
    sharedViewModel: SharedViewModel,
    agreement: Agreement,
    navigator: NavController,
    scrollState: ScrollState,
    appSharedPreference: AppPrefRepository,
    id: String?,

    ){

    Box(
        modifier = Modifier.fillMaxSize()

    ){
        //everything lies on top of the next composable
        BackgroundImage()
        //vertical arrangement component
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()

        ) {

            NavigationPanel("Order Details", navController = navigator)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.98f)
                    .verticalScroll(scrollState)
//                    .background(
//                        color = Color(0xFAFAFAFA)
//                    )
//                    .border(
//                        width = 1.dp,
//                        Color(0x302F4858),
//                        shape = RoundedCornerShape(15.dp)
//                    )
//                    .shadow( // Add shadow properties here
//                        elevation = .1.dp, // Adjust shadow elevation (distance)
//                        ambientColor = Color.LightGray, // Color for ambient light
//                        spotColor = Color.DarkGray // Color for light source
//                    )

            ) {
                //whole scope

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
//                    NotificationDetails(
//                        agreement = agreement,
//                        appSharedPreference = appSharedPreference,
//                        sharedViewModel = sharedViewModel,
//                        id = id,
//                        navigation = navigator
//                    )

                }



            }
        }



    }
}


@Preview
@Composable
fun DetailsScreenPreview(){
    val context = LocalContext.current
    NotificationContainer(
        sharedViewModel = remember { SharedViewModel(context) },
        agreement = Agreement(
            "1323232","Shoes","Fenty Puma kilo kama 8 hivi",1000.00,1,"255653302720","Pending","Kariakoo","Dar","834198","1","","","pending",null,false
        ),
        navigator = rememberNavController(),
        scrollState = rememberScrollState(),
        appSharedPreference = remember { AppPrefRepository(context) },
        id = ""
    )
}
