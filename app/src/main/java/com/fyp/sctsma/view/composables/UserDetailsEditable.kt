//package com.fyp.sctsma.view.composables
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.fyp.sctsma.R
//import com.fyp.sctsma.model.userData.User
//import com.fyp.sctsma.view.composables.commonComponents.CallToAction
//import com.fyp.sctsma.view.composables.commonComponents.UrlImageLoader
//import com.fyp.sctsma.view.uiexperiments.AccountStatus
//import com.fyp.sctsma.view.uiexperiments.BusinessCity
//import com.fyp.sctsma.view.uiexperiments.BusinessState
//import com.fyp.sctsma.view.uiexperiments.CreatorContact
//import com.fyp.sctsma.view.uiexperiments.FullName
//import com.fyp.sctsma.view.uiexperiments.LockNumber
//import com.fyp.sctsma.view.uiexperiments.OwnerNationality
//import com.fyp.sctsma.view.uiexperiments.PostalCode
//import com.fyp.sctsma.view.uiexperiments.ProfilePicture
//import com.fyp.sctsma.view.uiexperiments.SectionTitle
//import com.fyp.sctsma.view.uiexperiments.UserName
//
//@Composable
//fun UserDetailsEditable(
//    modifier: Modifier = Modifier,
//    user: User
//) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .background(
//                color = Color(0xFFFAFAFA)
//            )
//            .padding(2.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = modifier.height(20.dp))
//
//        SectionTitle(modifier = Modifier, "Profile Details", user)
//        Spacer(modifier = modifier.height(20.dp))
//        UploadPicture(modifier = Modifier, user)
//        Spacer(modifier = modifier.height(10.dp))
//        EditUserName(modifier = Modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        LockNumber(modifier = Modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        EditFullName(modifier = Modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        UserAccountType()
//        Spacer(modifier = modifier.height(10.dp))
//        AccountStatus(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        CreatorContact(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        EditOwnerNationality(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        SectionTitle(modifier = Modifier, "Business Address", user)
//        Spacer(modifier = modifier.height(10.dp))
//        PostalCode(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        BusinessCity(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        BusinessState(modifier = modifier, user = user)
//        Spacer(modifier = modifier.height(10.dp))
//        CallToAction(modifier = modifier.fillMaxWidth(.98f), {}, callToActionButtonColor = Color(0xFF00576A), actionText = "Edit")
//        Spacer(modifier = modifier.height(20.dp))
//    }
//}
//
//@Composable
//fun UploadPicture(modifier: Modifier, user: User) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .clip(shape = CircleShape)
//            .width(100.dp)
//            .height(100.dp)
//            .border(
//                width = 2.dp,
//                color = Color(0xFF2F4858),
//                shape = CircleShape
//            )
//
//        ,
//
//        ) {
//        UrlImageLoader(imageUrl = user.photoUrl?:"", modifier = Modifier
//            .width(100.dp)
//            .height(100.dp))
//    }
//}
//@Composable
//fun EditUserName(modifier: Modifier, user: User) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxWidth()
//            .height(
//                40.dp
//            )
//            .padding(horizontal = 15.dp)
//
//    ) {
//        Text(
//            text = "ChaxStore",
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Center
//            ),
//            modifier = modifier
//
//                .fillMaxWidth(.4f)
//
//        )
//
//    }
//}
//@Composable
//fun EditFullName(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//
//        ) {
//        Text(
//            text = "First Name",
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_bold)),
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Start
//            ),
//            modifier = modifier
//
//                .fillMaxWidth(.4f)
//
//        )
//
//        Spacer(modifier = modifier.width(10.dp))
//
//        Text(
//            text = "Ronald",
//            style = TextStyle(
//                fontSize = 15.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight(400),
//                color = Color(0xFF00576A),
//                textAlign = TextAlign.Start,
//            ),
//            modifier = modifier
//                .fillMaxWidth(.45f)
//
//
//        )
//        Text(
//            text = "Chaula",
//            style = TextStyle(
//                fontSize = 15.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight(400),
//                color = Color(0xFF00576A),
//                textAlign = TextAlign.Start,
//            ),
//            modifier = modifier
//                .fillMaxWidth()
//
//
//        )
//
//    }
//}
//
//@Composable
//fun EditOwnerNationality(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(
//                40.dp
//            )
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//
//        ) {
//        Text(
//            text = "Nationality",
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_bold)),
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Start
//            ),
//            modifier = modifier
//
//                .fillMaxWidth(.4f)
//
//        )
//
//        Spacer(modifier = modifier.width(10.dp))
//
//        Text(
//            text = "15120",
//            style = TextStyle(
//                fontSize = 15.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight(400),
//                color = Color(0xFF00576A),
//                textAlign = TextAlign.Center,
//
//                ),
//            modifier = modifier
//                .wrapContentSize()
//                .padding(horizontal = 1.dp, vertical = 2.dp)
//
//
//        )
//
//    }
//}
//@Composable
//fun EditPostalCode(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(
//                40.dp
//            )
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//
//        ) {
//        Text(
//            text = "Postal code",
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_bold)),
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Start
//            ),
//            modifier = modifier
//
//                .fillMaxWidth(.4f)
//
//        )
//
//        Spacer(modifier = modifier.width(10.dp))
//
//        Text(
//            text = "15120",
//            style = TextStyle(
//                fontSize = 15.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight(400),
//                color = Color(0xFF00576A),
//                textAlign = TextAlign.Center,
//
//                ),
//            modifier = modifier
//                .wrapContentSize()
//                .padding(horizontal = 1.dp, vertical = 2.dp)
//
//
//        )
//
//    }
//}