package com.fyp.sctsma.view.composables


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.composables.commonComponents.NavigationPanel
import com.fyp.sctsma.view.composables.commonComponents.OrderItems
import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun CompleteOrdersScreen(navController: NavController){

    val context = LocalContext.current
    //sharedViewModel
    val sharedViewModel = remember { SharedViewModel(context) }
    val agreements by sharedViewModel.agreements.observeAsState(emptyList())
    val isLoading by sharedViewModel.loading.observeAsState()
    val validationMessage by sharedViewModel.error.observeAsState()

    // Fetch agreements when the screen is opened
    LaunchedEffect(Unit) {
        sharedViewModel.fetchAgreements()
    }



    //this will run only when the required condition is true


    ShowOrdersToBeCompleted(
        validationMessage = validationMessage?:"",
        isLoading = isLoading!!,
        agreements = agreements!!,
        navController = navController,
        context = context
    )
}


@Composable
fun ShowOrdersToBeCompleted(
    validationMessage: String,
    isLoading: Boolean,
    agreements: List<Agreement> = emptyList(),
    navController: NavController,
    context: Context
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

            NavigationPanel("Complete Orders", navController = navController)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.98f)
                    .background(
                        color = Color(0xFAFAFAFA)
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

            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.0f)), // Semi-transparent background
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressDisplay(isLoading = true, color = Color(0xFF6FC07A))
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                ) {
                    if (validationMessage.isNotEmpty()){
                        ValidationErrorMessage(validationMessage = validationMessage)}

                    CircularProgressDisplay(
                        isLoading,color = Color(0xFF6FC07A)
                    )

                }

//show agreements that are not paid for
                if (agreements.isNotEmpty()) {
                    val filteredAgreements = agreements.filter { agreement ->
                        agreement.status == "ACCEPTED"
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.1f)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(filteredAgreements) { agreement ->
                            OrderItems(
                                modifier = Modifier.padding(vertical = 5.dp),
                                agreement = agreement,
                                navController = navController
                            )
                        }
                    }
                }
                else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.0f)), // Semi-transparent background
                        contentAlignment = Alignment.Center){
                        if(
                            isLoading
                        ){
                            Text(
                                text = "Loading",
                                color = Color.Gray)
                        }else{
                            Text(
                                text = "Orders Awaiting Your Approval Will Appear Here",
                                color = Color.Gray)
                        }


                    }
                }





            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)), // Semi-transparent background
                contentAlignment = Alignment.Center
            ) {
                CircularProgressDisplay(isLoading = true, color = Color(0xFF6FC07A))
            }
        }

    }
}


@Preview
@Composable
fun ShowOrdersToBeCompletedPreview(){
    ShowOrders(
        context = LocalContext.current,
        validationMessage = "",
        isLoading = false,
        agreements = listOf(
            Agreement(
                "1323232","Shoes","Fenty Puma kilo kama 8 hivi",1000.00,1,"255653302720","Pending","Kariakoo","Dar","834198","1","","","pending",null, false

            )
        ),
        navController = rememberNavController()
    )

}
