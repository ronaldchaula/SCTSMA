package com.fyp.sctsma.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.fyp.sctsma.R
import components.commonComponents.TopNavBar

class CreateOrdersScreen:Screen{

    @Composable
    override fun Content() {

     CreateOrder()
    }
}

@Composable
fun CreateOrder(){
    //state holders
//    var orderTitle by remember { mutableStateOf("") }
//    var orderQuantity by remember { mutableIntStateOf(0) }
//    var orderDescription by remember { mutableStateOf("")}
//    var orderCost by remember {mutableDoubleStateOf(0.0)}
//    var buyersContact by remember {mutableStateOf("")}
    Box(
        modifier = Modifier.fillMaxSize()

    ){
        //everything lies on top of the next composable
        BackgroundImage()
        //vertical arrangement component
        VerticalArrangement()



    }
}

@Composable
fun  VerticalArrangement(){
    //horizontal arrangement component
    Column(
        modifier = Modifier
            .fillMaxSize()
           ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TopNavBar(modifier = Modifier
            .height(56.dp)
            )
        Spacer(modifier = Modifier.height(2.dp))
        MiddleAreaContainer()
    }
}
@Composable
fun MiddleAreaContainer() {
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxWidth(.9f)
        .fillMaxHeight()


){
//all the middle controls go here
    NavigationPanel("Create Order")
    Spacer(modifier = Modifier.height(1.dp))
    OrderEntryPanel()
}
}

@Composable
fun OrderEntryPanel() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
            .padding(bottom = 8.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color(0xFAFAFAFA))

            .border(
                width = .8.dp, color = Color(0xFF2F4858),
                shape = RoundedCornerShape(15.dp)
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(3.dp))
        FirstEntryRow()
        Spacer(modifier = Modifier.height(3.dp))
        SecondEntryRow()
        Spacer(modifier = Modifier.height(3.dp))
        ThirdEntryRow()
        Spacer(modifier = Modifier.height(3.dp))
        FourthEntryRow()
        Spacer(modifier = Modifier.height(5.dp))
        CreateOrderButton()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun CreateOrderButton() {
    Button(
        onClick = {
//            navigator.push(HomeRoute())
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6FC07A)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
    ) {
        Text(text = "Create Order",
            modifier = Modifier.wrapContentSize(Alignment.Center),
            style = TextStyle(
                fontSize = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFAFAFA),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun FourthEntryRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(70.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Phone Number",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            value = "",
            onValueChange = {},
        )
    }
}


@Composable
fun ThirdEntryRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(70.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth()
                ,
            text = "Amount in Tsh",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            value = "",
            onValueChange = {},
        )
    }
}
@Composable
fun SecondEntryRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(200.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Order Description",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = "",
            onValueChange = {},
        )
    }
}
@Composable
fun FirstEntryRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(top = 10.dp)
            .height(70.dp)

           ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        OrderTitleComponent()
        OrderQuantityComponent()
    }
}
@Composable
fun OrderTitleComponent(
    orderTitle:String = ""
) {
    Column(
modifier = Modifier
    .fillMaxWidth(.6f)
    .fillMaxHeight()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Title",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = orderTitle,
            onValueChange = {},
        )
    }
}

@Composable
fun  OrderQuantityComponent(){
    Column(
modifier = Modifier
    .fillMaxWidth(.55f)
    .fillMaxHeight()
    ) {
        Text(

            textAlign = TextAlign.Center,
            text = "Quantity",
            fontSize = 12.4.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),

            )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = "",
            onValueChange = {},
        )
    }
}



@Composable
fun NavigationPanel(
    controlName: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .fillMaxHeight(.1f)

    ){
        Image(
            painterResource(
                R.drawable.back_button
            ),
            contentDescription = "Back button"
            , modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )
        Text(
            text = controlName,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),

        )
        Spacer(modifier = Modifier
            .width(30.dp))
    }
}
@Preview
@Composable
fun CreateOrdersScreenPreview(){
    CreateOrder()
}