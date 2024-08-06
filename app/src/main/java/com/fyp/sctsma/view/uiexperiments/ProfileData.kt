package com.fyp.sctsma.view.uiexperiments

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.R
import com.fyp.sctsma.model.userData.User
import com.fyp.sctsma.repository.AppPrefRepository
import com.fyp.sctsma.view.composables.DatePicker
import com.fyp.sctsma.view.composables.commonComponents.CallToAction
import com.fyp.sctsma.view.composables.commonComponents.CustomMultiLineOutlinedTextField
import com.fyp.sctsma.view.composables.commonComponents.CustomOutlinedTextField
import com.fyp.sctsma.view.composables.commonComponents.CustomRowTextField
import com.fyp.sctsma.view.composables.commonComponents.CustomTextField
import com.fyp.sctsma.view.composables.commonComponents.DropDownSelection
import com.fyp.sctsma.view.composables.commonComponents.UrlImageLoader
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun ProfileData(
    appPrefRepository: AppPrefRepository,
    modifier: Modifier = Modifier,
    context: Context,
    editingState: Boolean,
    sharedViewModel: SharedViewModel
) {
    val user by remember { mutableStateOf(appPrefRepository.getUserData()) }
    var isEditing by rememberSaveable { mutableStateOf(editingState) }
    var saveClicked by remember { mutableStateOf(isEditing) } // State for explicit save action

    LaunchedEffect(key1 = saveClicked) { // Trigger on save click
        if (!saveClicked) {
            try {
           //     sharedViewModel.updateUserData()
                Toast.makeText(context, "Data saved successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "You've already saved the data", Toast.LENGTH_SHORT).show()
            } finally {
                saveClicked = false // Reset the saveClicked state
            }
        }
    }

    Column(

        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFAFAFA)
            )
            ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(20.dp))
        EditProfile(modifier = modifier, isEditing = isEditing, { isEditing = !isEditing }, context, appPrefRepository, user, sharedViewModel, )
        Spacer(modifier = modifier.height(20.dp))
        SectionTitle(modifier = Modifier, "Account Information")
        Spacer(modifier = modifier.height(20.dp))
        user?.let { ProfilePicture(modifier = Modifier, it) }
        Spacer(modifier = modifier.height(20.dp))
        user?.let { UserName(modifier = Modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { LockNumber(modifier = Modifier, user = it) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { UserAccountType(modifier = Modifier, user = it) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { CreatorContact(modifier = modifier, user = it) }
        Spacer(modifier = modifier.height(20.dp))

        HorizontalDivider( modifier = Modifier.fillMaxWidth(.8f), color = Color.Gray)

        Spacer(modifier = modifier.height(20.dp))
        SectionTitle(modifier = Modifier, "Personal Information")
        Spacer(modifier = modifier.height(10.dp))
        user?.let { FullName(modifier = Modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { Gender(modifier = Modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { DOB(modifier = Modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { OwnerNationality(modifier = modifier, user = it, isEditing) }

        Spacer(modifier = modifier.height(20.dp))
        HorizontalDivider( modifier = Modifier.fillMaxWidth(.8f), color = Color.Gray)
        Spacer(modifier = modifier.height(20.dp))

        SectionTitle(modifier = Modifier, "Contact Information")

        Spacer(modifier = modifier.height(10.dp))
        user?.let { Email(modifier = Modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { PostalCode(modifier = modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { AddressLine(modifier = modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { BusinessCity(modifier = modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(10.dp))
        user?.let { BusinessState(modifier = modifier, user = it, isEditing) }
        Spacer(modifier = modifier.height(20.dp))
        user?.let { SaveData(modifier = modifier, user = it, isEditing, sharedViewModel) }
        Spacer(modifier = modifier.height(20.dp))
    }
}

@Composable
fun EditProfile(modifier: Modifier, isEditing: Boolean, onEditTrigger: () -> Unit, context: Context, appPrefRepository: AppPrefRepository, user: User?, sharedViewModel: SharedViewModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = if (isEditing) "Save" else "Edit",
            modifier = modifier.clickable {
                Toast.makeText(context, if (isEditing) "Information Saved" else "Edit Information Below", Toast.LENGTH_LONG).show()
                if (isEditing) {
                    appPrefRepository.saveUserData(user!!)
                    sharedViewModel.updateUserData()
                }
                onEditTrigger()
            },
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
fun FullName(modifier: Modifier, user: User, isEditing: Boolean) {
    var firstName by rememberSaveable { mutableStateOf(user.firstName ?: "") }
    var lastName by rememberSaveable { mutableStateOf(user.lastName ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        if (isEditing) {
            CustomOutlinedTextField(
                title = "First Name",
                value = firstName,
            ){
                firstName = it
                user.firstName = it
            }
            Spacer(modifier = modifier.height(8.dp))
            CustomOutlinedTextField(
                title = "Last Name",
                value = lastName,
            ){
                lastName = it
                user.lastName = it
            }
        } else {
            CustomRowTextField(title = "First Name", content = firstName)
            Spacer(modifier = modifier.height(8.dp))
            CustomRowTextField(title = "Last Name", content = lastName)
        }

    }
}

@Composable
fun UserName(modifier: Modifier, user: User, isEditing: Boolean) {
    var username by rememberSaveable { mutableStateOf(user.username) }
//end of row
    //end of row
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(horizontal = 15.dp)
    ) {

           CustomRowTextField(title = "Username", content = username)

    }
}

@Composable
fun LockNumber(modifier: Modifier, user: User) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomRowTextField(title = "LipaNamba", content = "${user.lockNumber}")
    }
}

@Composable
fun OwnerNationality(modifier: Modifier, user: User, isEditing: Boolean) {
    var nationality by rememberSaveable { mutableStateOf(user.nationality ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomOutlinedTextField(
                title = "Nationality",
                value = nationality,
            ){
                    nationality = it
                    user.nationality = it
                }
        } else {
          CustomRowTextField(
              title = "Nationality", content = nationality)
        }
    }
}

@Composable
fun Gender(modifier: Modifier, user: User, isEditing: Boolean) {
    var gender by rememberSaveable { mutableStateOf(user.gender ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            DropDownSelection(
                selectedGender = gender,
                onGenderSelected = {
                    gender = it
                    user.gender = it
                }
            )
        } else {
            CustomRowTextField(
                title = "Gender", content = gender)
        }
    }
}

@Composable
fun DOB(modifier: Modifier, user: User, isEditing: Boolean) {
    var dateOfBirth by rememberSaveable { mutableStateOf(user.dateOfBirth ?: "") }
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isEditing) {
            if (showDialog.value) {
                DatePicker(
                    selectedDate = dateOfBirth,
                    onDateSelected = {
                        dateOfBirth = it
                        user.dateOfBirth = it
                        showDialog.value = false // Close the dialog after selection
                    }
                )
            } else {
                CallToAction(
                    modifier = Modifier,
                    onCallToActionClick = { showDialog.value = true },
                    actionText = "PICK A DATE OF BIRTH",
                    callToActionButtonColor = Color(0xFF2F4858),
                )
            }
        } else {
            CustomRowTextField(
                title = "DOB",
                content = dateOfBirth
            )
        }
    }
}

@Composable
fun PostalCode(modifier: Modifier, user: User, isEditing: Boolean) {
    var postalCode by rememberSaveable { mutableStateOf(user.postalCode ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomOutlinedTextField(
                title = "Postal Code",
                value = postalCode) {
                postalCode = it
                user.postalCode = it
            }
        } else {
          CustomRowTextField(title = "Postal Code", content = postalCode )
        }
    }
}

@Composable
fun AddressLine(modifier: Modifier, user: User, isEditing: Boolean) {
    var addressLine1 by rememberSaveable { mutableStateOf(user.addressLine1 ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomMultiLineOutlinedTextField(
                title = "Physical Address",
                value = addressLine1) {
                addressLine1 = it
                user.addressLine1 = it
            }
        } else {
            CustomTextField(title = "Physical Address", content = addressLine1 )
        }
    }
}

@Composable
fun Email(modifier: Modifier, user: User, isEditing: Boolean) {
    var email by rememberSaveable { mutableStateOf(user.email ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomOutlinedTextField(
                title = "Email",
                value = email) {
                email = it
                user.email = it
                user.contactEmail = it
            }
        } else {
            CustomTextField(title = "Email", content = email )
        }
    }
}

@Composable
fun BusinessCity(modifier: Modifier, user: User, isEditing: Boolean) {
    var businessCity by rememberSaveable { mutableStateOf(user.city ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomOutlinedTextField(
                title ="Business City" ,
                value = businessCity) {
                businessCity = it
                user.city = it
            }
        } else {
            CustomRowTextField(title = "Business City", content = businessCity)
        }
    }
}

@Composable
fun BusinessState(modifier: Modifier, user: User, isEditing: Boolean) {
    var state by rememberSaveable { mutableStateOf(user.state ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CustomOutlinedTextField(title = "State/Country", value = state ) {
                state = it
                user.state = it
                user.country = it
            }
        } else {
        CustomRowTextField(
            title = "Business State",
            content = state )
        }
    }
}

@Composable
fun SaveData(modifier: Modifier, user: User, isEditing: Boolean, sharedViewModel: SharedViewModel) {
    var state by rememberSaveable { mutableStateOf(user.state ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isEditing) {
            CallToAction(modifier = modifier, actionText = "Save", callToActionButtonColor = Color(0xFF2F4858), onCallToActionClick = {
                sharedViewModel.updateUserData()
            })
        }
    }
}

@Composable
fun SectionTitle(modifier: Modifier, title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF2F4858),
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    )
}

@Composable
fun ProfilePicture(modifier: Modifier, user: User, uploadImage: () -> Unit = {}) {
Column(
    modifier = modifier
        .wrapContentSize(Alignment.Center)
        .padding(horizontal = 15.dp)
        .clickable(
            onClick = uploadImage
        ),
    verticalArrangement = Arrangement.Center,
) {
    if (user.photoUrl != null) {
        UrlImageLoader(
            imageUrl = user.photoUrl,
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFFAFAFA))
        )
    } else {
        // Display a placeholder or default image when photoUrl is null
        Image(
            painter = painterResource(id = R.drawable.user_avatar), // Replace with your default image
            contentDescription = "Default Profile Picture",
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
    }
}

}

@Composable
fun UserAccountType(modifier: Modifier, user: User) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        CustomRowTextField(title = "Account Type: " , content = user.accountType)
    }
}

@Composable
fun CreatorContact(modifier: Modifier, user: User) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomTextField(title = "Creator Contact", content = user.contactPhoneNumber )

    }
}

@Preview
@Composable
fun ProfileDataPreview() {
    ProfileData(
        appPrefRepository = AppPrefRepository(LocalContext.current),
        context = LocalContext.current,
        editingState = false,
        sharedViewModel = SharedViewModel(context = LocalContext.current)
    )
}