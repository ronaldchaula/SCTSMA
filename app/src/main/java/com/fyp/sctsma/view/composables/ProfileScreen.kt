package com.fyp.sctsma.view.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.repository.AppPrefRepository
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.uiexperiments.ProfileData
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun ProfileScreen(navController: NavController) {

    val context = LocalContext.current

    //sharedViewModel
    val sharedViewModel = remember { SharedViewModel(context) }
    val appPrefRepository = sharedViewModel.appPrefRepository
    val editingState by remember {mutableStateOf(false)}
    val isLoading by sharedViewModel.loading.observeAsState()

    //this will run only when the required condition is true
    Profile(
        isLoading = isLoading,
        sharedViewModel = sharedViewModel,
        context = context,
        appPrefRepository = appPrefRepository,
        editingState = editingState
    )
}

@Composable
fun Profile(
    sharedViewModel: SharedViewModel,
    context: Context,
    appPrefRepository: AppPrefRepository,
    editingState: Boolean,
    isLoading: Boolean?
){
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scrollState.scrollTo(0)
    }
    Box(
        modifier = Modifier.fillMaxSize()

    ){
        //everything lies on top of the next composable
       // BackgroundImage()
        //vertical arrangement component
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth(.98f)
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
                    .background(
                        color = Color(0xFAFAFAFA)
                    )

            ) {

                    ProfileData(
                        sharedViewModel = sharedViewModel,
                        appPrefRepository = appPrefRepository,
                        context = context,
                        editingState = editingState
                    )

            }
        }
        if (isLoading == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)), // Semi-transparent background
                contentAlignment = Alignment.Center
            ) {
                CircularProgressDisplay(isLoading = true, color = Color(0xFF6FC07A))
                Text(text = "Syncing Data", color = Color(0xFF2F4858))
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
