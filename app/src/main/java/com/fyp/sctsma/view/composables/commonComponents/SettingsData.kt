//package com.fyp.sctsma.view.composables.commonComponents
//
//package com.fyp.sctsma.view.uiexperiments
//
//import android.content.Context
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.fyp.sctsma.R
//import com.fyp.sctsma.model.userData.User
//import com.fyp.sctsma.repository.AppPrefRepository
//import com.fyp.sctsma.view.composables.commonComponents.CustomOutlinedTextField
//import com.fyp.sctsma.view.composables.commonComponents.CustomRowTextField
//import com.fyp.sctsma.view.composables.commonComponents.CustomTextField
//import com.fyp.sctsma.view.composables.commonComponents.UrlImageLoader
//
//@Composable
//fun SettingsData(
//    appPrefRepository: AppPrefRepository,
//    modifier: Modifier = Modifier,
//    context: Context,
//    editingState: Boolean
//) {
//    val user by remember { mutableStateOf(appPrefRepository.getUserData()) }
//    var isEditing by rememberSaveable { mutableStateOf(editingState) }
//
//    Column(
//
//        modifier = modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(
//                color = Color(0xFFFAFAFA)
//            )
//        ,
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = modifier.height(20.dp))
//        EditProfile(modifier = modifier, isEditing = isEditing, { isEditing = !isEditing }, context, appPrefRepository, user)
//        Spacer(modifier = modifier.height(20.dp))
//        SectionTitle(modifier = Modifier, "Profile Details")
//        Spacer(modifier = modifier.height(20.dp))
//        user?.let { ProfilePicture(modifier = Modifier, it) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { UserName(modifier = Modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { LockNumber(modifier = Modifier, user = it) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { FullName(modifier = Modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { UserAccountType(modifier = Modifier, user = it) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { AccountStatus(modifier = modifier, user = it) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { CreatorContact(modifier = modifier, user = it) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { OwnerNationality(modifier = modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(10.dp))
//        SectionTitle(modifier = Modifier, "Address")
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { PostalCode(modifier = modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { BusinessCity(modifier = modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(10.dp))
//        user?.let { BusinessState(modifier = modifier, user = it, isEditing) }
//        Spacer(modifier = modifier.height(20.dp))
//    }
//}
//
//@Composable
//fun EditProfile(modifier: Modifier, isEditing: Boolean, onEditTrigger: () -> Unit, context: Context, appPrefRepository: AppPrefRepository, user: User?) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 20.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.End
//    ) {
//        Text(
//            text = if (isEditing) "Save" else "Edit",
//            modifier = modifier.clickable {
//                Toast.makeText(context, if (isEditing) "Information Saved" else "Edit Information Below", Toast.LENGTH_LONG).show()
//                if (isEditing) {
//                    appPrefRepository.saveUserData(user!!)
//
//                }
//                onEditTrigger()
//            },
//            style = TextStyle(
//                fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_regular)),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Start
//            )
//        )
//    }
//}
//
//@Composable
//fun FullName(modifier: Modifier, user: User, isEditing: Boolean) {
//    var firstName by rememberSaveable { mutableStateOf(user.firstName ?: "") }
//    var lastName by rememberSaveable { mutableStateOf(user.lastName ?: "") }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.SpaceEvenly
//    ) {
//
//        if (isEditing) {
//            CustomOutlinedTextField(
//                title = "First Name",
//                value = firstName,
//            ){
//                firstName = it
//                user.firstName = it
//            }
//            Spacer(modifier = modifier.height(8.dp))
//            CustomOutlinedTextField(
//                title = "Last Name",
//                value = lastName,
//            ){
//                lastName = it
//                user.lastName = it
//            }
//        } else {
//            CustomRowTextField(title = "First Name", content = firstName)
//            Spacer(modifier = modifier.height(8.dp))
//            CustomRowTextField(title = "Last Name", content = lastName)
//        }
//
//    }
//}
//
//@Composable
//fun UserName(modifier: Modifier, user: User, isEditing: Boolean) {
//    var username by rememberSaveable { mutableStateOf(user.username) }
////end of row
//    //end of row
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight(Alignment.CenterVertically)
//            .padding(horizontal = 15.dp)
//    ) {
//        if (isEditing) {
//            CustomOutlinedTextField(
//                title = "Username",
//                value = username,
//            ){
//                username = it
//                user.username = it
//            }
//        } else {
//            CustomRowTextField(title = "Username", content = username)
//        }
//    }
//}
//
//@Composable
//fun LockNumber(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        CustomRowTextField(title = "LipaNamba", content = "${user.lockNumber}")
//    }
//}
//
//@Composable
//fun OwnerNationality(modifier: Modifier, user: User, isEditing: Boolean) {
//    var nationality by rememberSaveable { mutableStateOf(user.nationality ?: "") }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        if (isEditing) {
//            CustomOutlinedTextField(
//                title = "Nationality",
//                value = nationality,
//            ){
//                nationality = it
//                user.nationality = it
//            }
//        } else {
//            CustomRowTextField(
//                title = "Nationality", content = nationality)
//        }
//    }
//}
//
//@Composable
//fun PostalCode(modifier: Modifier, user: User, isEditing: Boolean) {
//    var postalCode by rememberSaveable { mutableStateOf(user.postalCode ?: "") }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        if (isEditing) {
//            CustomOutlinedTextField(
//                title = "Postal Code",
//                value = postalCode) {
//                postalCode = it
//                user.postalCode = it
//            }
//        } else {
//            CustomRowTextField(title = "Postal Code", content = postalCode )
//        }
//    }
//}
//
//@Composable
//fun BusinessCity(modifier: Modifier, user: User, isEditing: Boolean) {
//    var businessCity by rememberSaveable { mutableStateOf(user.city ?: "") }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        if (isEditing) {
//            CustomOutlinedTextField(
//                title ="Business City" ,
//                value = businessCity) {
//                businessCity = it
//                user.city = it
//            }
//        } else {
//            CustomRowTextField(title = "Business City", content = businessCity)
//        }
//    }
//}
//
//@Composable
//fun BusinessState(modifier: Modifier, user: User, isEditing: Boolean) {
//    var state by rememberSaveable { mutableStateOf(user.state ?: "") }
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        if (isEditing) {
//            CustomOutlinedTextField(title = "Business State", value = state ) {
//                state = it
//                user.state = it
//                user.country = it
//            }
//        } else {
//            CustomRowTextField(
//                title = "Business State",
//                content = state )
//        }
//    }
//}
//
//@Composable
//fun SectionTitle(modifier: Modifier, title: String) {
//    Text(
//        text = title,
//        style = TextStyle(
//            fontSize = 20.sp,
//            fontFamily = FontFamily(Font(R.font.poppins_bold)),
//            fontWeight = FontWeight(600),
//            color = Color(0xFF2F4858),
//            textAlign = TextAlign.Center
//        ),
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 15.dp, vertical = 10.dp)
//    )
//}
//
//@Composable
//fun ProfilePicture(modifier: Modifier, user: User, uploadImage: () -> Unit = {}) {
//    Column(
//        modifier = modifier
//            .wrapContentSize(Alignment.Center)
//            .padding(horizontal = 15.dp)
//            .clickable(
//                onClick = uploadImage
//            ),
//        verticalArrangement = Arrangement.Center,
//    ) {
//        if (user.photoUrl != null) {
//            UrlImageLoader(
//                imageUrl = user.photoUrl,
//                modifier = modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFFAFAFA))
//            )
//        } else {
//            // Display a placeholder or default image when photoUrl is null
//            Image(
//                painter = painterResource(id = R.drawable.user_avatar), // Replace with your default image
//                contentDescription = "Default Profile Picture",
//                modifier = modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//                    .background(Color.Gray)
//            )
//        }
//    }
//
//}
//
//@Composable
//fun UserAccountType(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight(Alignment.CenterVertically)
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//
//        CustomRowTextField(title = "Account Type: " , content = user.accountType)
//    }
//}
//
//@Composable
//fun AccountStatus(modifier: Modifier, user: User) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight(Alignment.CenterVertically)
//            .padding(horizontal = 15.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Text(
//            text = "Account Status: ",
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_bold)),
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//                textAlign = TextAlign.Start
//            ),
//            modifier = modifier.fillMaxWidth(.4f)
//        )
//        user.state?.let { CustomRowTextField(title = "Account Status", content = it) }
//    }
//}
//
//@Composable
//fun CreatorContact(modifier: Modifier, user: User) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = 15.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        CustomTextField(title = "Creator Contact", content = user.contactPhoneNumber )
//
//    }
//}
//
//@Preview
//@Composable
//fun ProfileDataPreview() {
//    ProfileData(
//        appPrefRepository = AppPrefRepository(LocalContext.current),
//        context = LocalContext.current,
//        editingState = false
//    )
//}