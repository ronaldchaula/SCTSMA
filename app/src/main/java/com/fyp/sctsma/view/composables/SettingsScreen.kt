//package com.fyp.sctsma.view.composables
//
//import android.content.Context
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavHostController
//import cafe.adriel.voyager.core.screen.Screen
//import com.fyp.sctsma.repository.AppPrefRepository
//import com.fyp.sctsma.view.uiexperiments.EditableUserData
//import com.fyp.sctsma.viewmodel.SharedViewModel
//
//@Composable
//fun SettingsScreen(navController: NavHostController){
//
//        val context = LocalContext.current
//        val sharedViewModel = remember { SharedViewModel(context) }
//        val appPrefRepository = sharedViewModel.appPrefRepository
//       SettingScreenHolders(
//           modifier = Modifier,
//           appPrefRepository = appPrefRepository,
//           context = context
//       )
//
//
//}
//
//@Composable
//fun SettingScreenHolders(
//    modifier: Modifier = Modifier
//        .fillMaxWidth()
//        .wrapContentHeight(),
//    appPrefRepository: AppPrefRepository,
//    context: Context
//){
//    Box(
//        modifier = modifier.fillMaxSize()
//    ){
//        BackgroundImage()
//        Column(
//            verticalArrangement = Arrangement.Center,
//            modifier = modifier.fillMaxWidth()
//        ) {
//            EditableUserData(appPrefRepository, context, modifier = modifier)
//        }
//
//    }
//
//}
//
//@Preview
//@Composable
//fun SettingScreenHoldersPreview(){
//    val context = LocalContext.current
//    val sharedViewModel = remember { SharedViewModel(context) }
//    SettingScreenHolders(appPrefRepository = sharedViewModel.appPrefRepository, context = context)
//}
