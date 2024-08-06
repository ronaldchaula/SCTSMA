package com.fyp.sctsma.view.composables


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fyp.sctsma.R
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.repository.AppPrefRepository
import com.fyp.sctsma.view.composables.commonComponents.CallToAction
import com.fyp.sctsma.view.composables.commonComponents.CustomMultiLineOutlinedTextField
import com.fyp.sctsma.view.composables.navigation.InternalRoutes
import com.fyp.sctsma.viewmodel.SharedViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun OrderDetails(
    modifier: Modifier = Modifier,
    agreement: Agreement,
    appSharedPreference: AppPrefRepository,
    sharedViewModel: SharedViewModel,
    id: String?,
    navigation: NavController
) {

    val context = LocalContext.current
// states
    var accepted = remember { mutableStateOf(false) }
    var declined = remember { mutableStateOf(false) }
    var completed = remember { mutableStateOf(false) }
    var disputed = remember { mutableStateOf(false) }
    var paymentCompleted = remember { mutableStateOf(false) }
    var paymentDeclined = remember { mutableStateOf(false) }
    var phoneNumber = remember { mutableStateOf("") }
    val itemDetailsPath = InternalRoutes.DetailsScreen.route + "/${agreement.rowId}"




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
            if(
                agreement.status == "PENDING" &&
                agreement.paymentStatus == null
            ){
                CallToAction(modifier = modifier.fillMaxWidth(.98f), callToActionButtonColor = Color(0xFF73BA7F), onCallToActionClick = {
                    accepted.value = true
                    Toast.makeText(context, "Payment Complete", Toast.LENGTH_SHORT).show()
                    if (id != null) {
                      //  sharedViewModel.acceptAgreement(id)
                        navigation.navigate(InternalRoutes.OrderCompletionScreen.route)
                    }
                }, actionText = "Accept")
                Spacer(modifier = modifier.height(10.dp))
                CallToAction(modifier = modifier.fillMaxWidth(.98f), onCallToActionClick = {
                    declined.value = true
                    Toast.makeText(context, "Order Declined", Toast.LENGTH_SHORT).show()
                    if (id != null) {
                        sharedViewModel.declineAgreement(id)
                        navigation.navigate(itemDetailsPath)
                    }                                                     }, "Decline",Color(0xFFBA5056))
            }
            else if(
                agreement.status == "ACCEPTED" &&
                agreement.paymentStatus == null


            ){

                CustomMultiLineOutlinedTextField(title = "Raise Complaint", value = phoneNumber.value) {
                    phoneNumber.value = it
                }
                Spacer(modifier = modifier.height(10.dp))
                CallToAction(modifier = modifier.fillMaxWidth(.98f), callToActionButtonColor = Color(0xFFBA5056), onCallToActionClick =  {
                    if (id != null) {

                        sharedViewModel.orderPayment(id,phoneNumber.value)


                    }
                }, actionText = "Raise Complaint")
                Spacer(modifier = modifier.height(10.dp))
                CallToAction(modifier = modifier.fillMaxWidth(.98f), onCallToActionClick = {
                    if (id != null) {
                        sharedViewModel.orderDeclinePayment(id,phoneNumber.value)
                        // navigation.navigate(itemDetailsPath)
                    }
                }, "Complete Order",Color(0xFF73BA7F))
            }
            else if(
                agreement.status == "ACCEPTED" &&
                agreement.paymentStatus != null
            ){
                Spacer(modifier = modifier.height(10.dp))
                CallToAction(modifier = modifier.fillMaxWidth(.98f), callToActionButtonColor = Color(0xFF73BA7F), onCallToActionClick = { completed.value = true }, actionText = "Complete Order")
                Spacer(modifier = modifier.height(10.dp))
                CallToAction(modifier = modifier.fillMaxWidth(.98f), onCallToActionClick = { disputed.value = true }, "File Dispute",Color(0xFFBA5056))
            }


        }
        Spacer(modifier = modifier.height(20.dp))
    }
}

@Composable
fun OrderQuantity(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Quantity",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier

                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.quantity}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}
@Composable
fun OrderCost(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Amount",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier

                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.amount}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
fun CreatorContact(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Sender Number",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.ownerPhoneNumber}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
fun ReceiverLockNumber(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Receiver Lipa No.",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.targetPerson}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
fun Date(modifier: Modifier, agreement: Agreement) {
    val (date, time) = getDateTimeParts(agreement)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Date Created:",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = " $date",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
fun Time(modifier: Modifier, agreement: Agreement) {
    val (date, time) = getDateTimeParts(agreement)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Time Created:",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = " $time",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}
@Composable
fun CreatorName(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Sender Name",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.ownerFullName}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
fun CreatorLockNumber(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Sender Lipa No.",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.ownerLockNumber}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}
@Composable
fun OrderStatus(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Order Status",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier

                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = orderStatus(agreement),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Center,

                ),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp, vertical = 2.dp)
                .border(
                    width = 1.dp,
                    color = changeBorderColor(orderStatus(agreement)),
                    shape = RoundedCornerShape(15.dp)
                )

        )

    }
}

@Composable
fun PaymentStatus(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Payment Status",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier

                .fillMaxWidth(.5f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = paymentStatus(agreement),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Center,

                ),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp, vertical = 2.dp)
                .border(
                    width = 1.dp,
                    color = changeBorderColor(orderStatus(agreement)),
                    shape = RoundedCornerShape(15.dp)
                )

        )

    }
}
@Composable
fun OrderTitle(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Order Title",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier

                .fillMaxWidth(.4f)

        )

        Spacer(modifier = modifier.width(10.dp))

        Text(
            text = "${agreement.productName}",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF00576A),
                textAlign = TextAlign.Start,
            ),
            modifier = modifier
                .fillMaxWidth()


        )

    }
}

@Composable
 fun OrderDescription(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Order Description",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth()

        )

    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(
                40.dp
            )
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "${agreement.description}",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth()

        )

    }
}
@Composable
fun OrderIcon(modifier: Modifier, agreement: Agreement) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = changeIcon(paymentStatus(agreement))
            ),
            contentDescription = "Order Status Icon",
            modifier = modifier
                .width(200.dp)
        )
    }
}

@Preview
@Composable
fun OrderDetailsPreview() {
    val context = LocalContext.current
    OrderDetails(
        agreement = Agreement(
            "1323232","Shoes","Fenty Puma kilo kama 8 hivi",1000.00,1,"255653302720","123456","Kariakoo","Dar","834198","1","","","pending",null,false
        ),
        appSharedPreference = AppPrefRepository(context),
        sharedViewModel = SharedViewModel(context),
        id = "1323232",
        navigation = NavController(context)
    )
}

fun clipIdLength(item: Agreement, maxLength: Int): String? {
    return if ((item.rowId?.length ?: 0) > maxLength) {
        item.rowId?.substring(0, maxLength)
    } else {
        item.rowId
    }
}
fun clipTitleLength(item: Agreement, maxLength: Int): String? {
    return if ((item.productName?.length ?: 0) > maxLength) {
        item.productName?.substring(0, maxLength)
    } else {
        item.productName
    }
}
fun orderStatus(orderState: Agreement): String {
    return when (orderState.status) {
        "ACCEPTED" -> {
            "Accepted"
        }
        "DECLINED" -> {
            "Declined"
        }
        else -> {
            "Pending"
        }
    }
}

fun paymentStatus(paymentState: Agreement): String {
    return when (paymentState.paymentStatus) {
        "PENDING" -> {
            "Pending"
        }
        "COMPLETED" -> {
            "Completed"
        }
        "EXPIRED" -> {
            "Expired"
        }
        "DECLINED" -> {
            "Declined"
        }
        else -> {
            "Not paid"
        }
    }
}

fun changeBorderColor(orderState: String): Color {
    return when (orderState) {
        "Completed", "Accepted" -> {
            Color(0xFF73BA7F)
        }
        "Pending","Not paid" -> {
            Color(0xFFEEB627)
        }
        else -> {
            Color(0xFFBA5056)
        }
    }
}

fun changeIcon(orderState: String): Int {
    when(orderState) {
        "Completed" -> {
            return R.drawable.completed
        }
        "Pending","Not paid" -> {
            return R.drawable.waiting_for_payment
        }
        "Declined" -> {
            return R.drawable.declined
        }
        "Expired" -> {
            return R.drawable.expired
        }
    }
    return 1
}

fun getDateTimeParts(agreement: Agreement): Pair<String, String> {
    val dateTime = LocalDateTime.parse(agreement.agreementCreatedAt, DateTimeFormatter.ISO_DATE_TIME)
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val date = dateTime.toLocalDate().format(dateFormatter)
    val time = dateTime.toLocalTime().format(timeFormatter)

    return Pair(date, time)
}
