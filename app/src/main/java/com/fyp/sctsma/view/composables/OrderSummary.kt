package com.fyp.sctsma.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.repository.AppPrefRepository
import com.fyp.sctsma.view.composables.commonComponents.CallToAction
import com.fyp.sctsma.view.composables.commonComponents.CustomOutlinedTextField
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun OrderSummary(
    modifier: Modifier = Modifier,
    agreement: Agreement,
    appSharedPreference: AppPrefRepository,
    sharedViewModel: SharedViewModel,
    id: String?,
) {

    val context = LocalContext.current
// states
    val accepted = remember { mutableStateOf(false) }
    val declined = remember { mutableStateOf(false) }
    val completed = remember { mutableStateOf(false) }
    var disputed = remember { mutableStateOf(false) }
    var paymentCompleted = remember { mutableStateOf(false) }
    var paymentDeclined = remember { mutableStateOf(false) }
    var phoneNumber = remember { mutableStateOf("") }




    //accept or decline agreement
    LaunchedEffect(accepted) {
        if (id != null && accepted.value) {
            sharedViewModel.acceptAgreement(id)
            accepted.value = false
        }
    }

    LaunchedEffect(declined) {
        if (id != null && declined.value) {
            sharedViewModel.declineAgreement(id)
            declined.value = false
        }
    }

//    LaunchedEffect(declined) {
//        if (id != null && declined.value) {
//            sharedViewModel.declineAgreement(id)
//            declined.value = false
//        }
//    }

    //make payment


    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    15.dp
                )
            )
            .fillMaxWidth()
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
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(20.dp))
        OrderIcon(modifier = Modifier, agreement = agreement)
        Spacer(modifier = modifier.height(20.dp))
        OrderTitle(modifier = Modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        OrderQuantity(modifier = Modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        OrderDescription(modifier = Modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        OrderCost(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        CreatorName(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        CreatorLockNumber(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        CreatorContact(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        ReceiverLockNumber(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        Date(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        Time(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        OrderStatus(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        PaymentStatus(modifier = modifier, agreement = agreement)
        Spacer(modifier = modifier.height(10.dp))
        if(agreement.targetPerson == appSharedPreference.getUserData()?.lockNumber){

            if(!completed.value){

            }
        CustomOutlinedTextField(title = "Enter Number ", value = phoneNumber.value, onValueChange = { phoneNumber.value = it })
        Spacer(modifier = modifier.height(10.dp))
        CallToAction(modifier = modifier.fillMaxWidth(.98f), callToActionButtonColor = Color(0xFF73BA7F), onCallToActionClick = { completed.value = true }, actionText = "Complete Order")
        }
        }
        Spacer(modifier = modifier.height(20.dp))
    }



