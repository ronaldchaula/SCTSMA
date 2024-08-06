package com.fyp.sctsma.view.composables//package com.fyp.sctsma.view
//
//import android.content.Context
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.LocalNavigator
//import cafe.adriel.voyager.navigator.Navigator
//import cafe.adriel.voyager.navigator.currentOrThrow
//import com.fyp.sctsma.R
//import com.fyp.sctsma.view.composables.BackgroundImage
//import com.fyp.sctsma.view.composables.HomeRoute
//import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
//import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
//import com.fyp.sctsma.viewmodel.SharedViewModel
//
//class Orders {
//    package com.fyp.sctsma.view.composables
//
//    import android.content.Context
//    import android.widget.Toast
//    import androidx.compose.foundation.Image
//    import androidx.compose.foundation.background
//    import androidx.compose.foundation.border
//    import androidx.compose.foundation.layout.Arrangement
//    import androidx.compose.foundation.layout.Box
//    import androidx.compose.foundation.layout.Column
//    import androidx.compose.foundation.layout.Row
//    import androidx.compose.foundation.layout.Spacer
//    import androidx.compose.foundation.layout.fillMaxHeight
//    import androidx.compose.foundation.layout.fillMaxSize
//    import androidx.compose.foundation.layout.fillMaxWidth
//    import androidx.compose.foundation.layout.height
//    import androidx.compose.foundation.layout.padding
//    import androidx.compose.foundation.layout.width
//    import androidx.compose.foundation.layout.wrapContentSize
//    import androidx.compose.foundation.rememberScrollState
//    import androidx.compose.foundation.shape.RoundedCornerShape
//    import androidx.compose.foundation.verticalScroll
//    import androidx.compose.material3.Button
//    import androidx.compose.material3.ButtonDefaults
//    import androidx.compose.material3.OutlinedTextField
//    import androidx.compose.material3.Text
//    import androidx.compose.runtime.Composable
//    import androidx.compose.runtime.LaunchedEffect
//    import androidx.compose.runtime.getValue
//    import androidx.compose.runtime.livedata.observeAsState
//    import androidx.compose.runtime.mutableStateOf
//    import androidx.compose.runtime.remember
//    import androidx.compose.ui.Alignment
//    import androidx.compose.ui.Modifier
//    import androidx.compose.ui.draw.clip
//    import androidx.compose.ui.graphics.Color
//    import androidx.compose.ui.platform.LocalContext
//    import androidx.compose.ui.res.painterResource
//    import androidx.compose.ui.text.TextStyle
//    import androidx.compose.ui.text.font.FontWeight
//    import androidx.compose.ui.text.style.TextAlign
//    import androidx.compose.ui.unit.dp
//    import androidx.compose.ui.unit.sp
//    import cafe.adriel.voyager.core.screen.Screen
//    import cafe.adriel.voyager.navigator.LocalNavigator
//    import cafe.adriel.voyager.navigator.Navigator
//    import cafe.adriel.voyager.navigator.currentOrThrow
//    import com.fyp.sctsma.R
//    import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
//    import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
//    import com.fyp.sctsma.viewmodel.SharedViewModel
//
//    class CreateOrdersScreen: Screen {
//
//        @Composable
//        override fun Content() {
//
//            val context = LocalContext.current
//            val navigator = LocalNavigator.currentOrThrow
//
//            //sharedViewModel
//            val sharedViewModel = remember { SharedViewModel(context) }
//
//            //state holders
//            val  orderTitle = remember { mutableStateOf("") }
//            val  onOrderTitleChange = remember { mutableStateOf("") }
//            val  orderQty = remember { mutableStateOf("") }
//            val  onOrderQuantityChange = remember { mutableStateOf("") }
//            val  orderDescription = remember { mutableStateOf("") }
//            val  onOrderDescriptionChange = remember { mutableStateOf("") }
//            val  orderCost = remember { mutableStateOf("") }
//            val  onOrderCostChange = remember { mutableStateOf("") }
//            val  lipaNumber = remember { mutableStateOf("") }
//            val  onLipaNumberChange = remember { mutableStateOf("") }
//            //observe entries
//            val validationMessage by sharedViewModel.error.observeAsState()
//            val success by sharedViewModel.success.observeAsState()
//            val isLoading by sharedViewModel.loading.observeAsState()
//
//            CreateOrder(
//                orderTitle = orderTitle.value,
//                onOrderTitleChange = {
//
//                    orderTitle.value = it
//                    sharedViewModel.orderTitle.value = it
//                },
//                orderQty = orderQty.value,
//                onOrderQuantityChange = {
//                    orderQty.value = it
//                    sharedViewModel.orderQty.value = it
//                },
//                orderDescription = orderDescription.value,
//                onOrderDescriptionChange = {
//                    orderDescription.value = it
//                    sharedViewModel.orderDescription.value = it
//                },
//                orderCost = orderCost.value,
//                onOrderCostChange = {
//                    orderCost.value = it
//                    sharedViewModel.orderCost.value = it
//                },
//                lipaNumber = lipaNumber.value,
//                onLipaNumberChange = {
//                    lipaNumber.value = it
//                    sharedViewModel.lipaNumber.value = it
//                },
//                navigator = navigator,
//                validationMessage = validationMessage!!
//                ,
//                isLoading = isLoading!!,
//                success = success!!,
//                onOrderSubmission = {
//                    sharedViewModel.postOrder()
//                },
//                context = context
//            )
//        }
//    }
//
//    @Composable
//    fun CreateOrder(
//        orderTitle: String = "",
//        onOrderTitleChange: (String) -> Unit,
//        orderQty: String = "",
//        onOrderQuantityChange: (String) -> Unit,
//        orderDescription: String = "",
//        onOrderDescriptionChange: (String) -> Unit,
//        orderCost: String = "",
//        onOrderCostChange: (String) -> Unit,
//        lipaNumber: String = "",
//        onLipaNumberChange: (String) -> Unit,
//        navigator: Navigator,
//        validationMessage: String = "",
//        isLoading: Boolean,
//        success: Boolean,
//        onOrderSubmission: () -> Unit = {},
//        context: Context
//    ){
//
//        Box(
//            modifier = Modifier.fillMaxSize()
//
//        ){
//            //everything lies on top of the next composable
//            BackgroundImage()
//            //vertical arrangement component
//            VerticalArrangement(
//                orderTitle = orderTitle,
//                onOrderTitleChange = onOrderTitleChange,
//                orderQty = orderQty,
//                onOrderQuantityChange = onOrderQuantityChange,
//                orderDescription = orderDescription,
//                onOrderDescriptionChange = onOrderDescriptionChange,
//                orderCost = orderCost,
//                onOrderCostChange = onOrderCostChange,
//                lipaNumber = lipaNumber,
//                onLipaNumberChange = onLipaNumberChange,
//                navigator = navigator,
//                validationMessage= validationMessage,
//                isLoading = isLoading,
//                success = success,
//                onOrderSubmission = onOrderSubmission,
//                context = context
//            )
//
//
//
//        }
//    }
//
//    @Composable
//    fun  VerticalArrangement(
//        orderTitle: String = "",
//        onOrderTitleChange: (String) -> Unit,
//        orderQty: String = "",
//        onOrderQuantityChange: (String) -> Unit,
//        orderDescription: String = "",
//        onOrderDescriptionChange: (String) -> Unit,
//        orderCost: String = "",
//        onOrderCostChange: (String) -> Unit,
//        lipaNumber: String = "",
//        onLipaNumberChange: (String) -> Unit,
//        navigator: Navigator,
//        validationMessage: String = "",
//        isLoading: Boolean,
//        success: Boolean,
//        onOrderSubmission: () -> Unit = {},
//        context: Context
//    ){
//        //horizontal arrangement component
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//            ,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            CircularProgressDisplay(isLoading = isLoading)
//            Spacer(modifier = Modifier.height(60.dp))
//            ValidationErrorMessage(validationMessage)
//            MiddleAreaContainer(
//                orderTitle = orderTitle,
//                onOrderTitleChange = onOrderTitleChange,
//                orderQty = orderQty,
//                onOrderQuantityChange = onOrderQuantityChange,
//                orderDescription = orderDescription,
//                onOrderDescriptionChange = onOrderDescriptionChange,
//                orderCost = orderCost,
//                onOrderCostChange = onOrderCostChange,
//                lipaNumber = lipaNumber,
//                onLipaNumberChange = onLipaNumberChange,
//                navigator = navigator,
//                success = success,
//                onOrderSubmission = onOrderSubmission,
//                context = context
//
//            )
//        }
//    }
//    @Composable
//    fun MiddleAreaContainer(
//        orderTitle: String = "",
//        onOrderTitleChange: (String) -> Unit,
//        orderQty: String = "",
//        onOrderQuantityChange: (String) -> Unit,
//        orderDescription: String = "",
//        onOrderDescriptionChange: (String) -> Unit,
//        orderCost: String = "",
//        onOrderCostChange: (String) -> Unit,
//        lipaNumber: String = "",
//        onLipaNumberChange: (String) -> Unit,
//        navigator: Navigator,
//        success: Boolean,
//        onOrderSubmission: () -> Unit = {},
//        context: Context
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top,
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .fillMaxHeight()
//
//
//        ){
////all the middle controls go here
//            NavigationPanel("Create Order")
//            Spacer(modifier = Modifier.height(1.dp))
//            OrderEntryPanel(
//                orderTitle = orderTitle,
//                onOrderTitleChange = onOrderTitleChange,
//                orderQty = orderQty,
//                onOrderQuantityChange = onOrderQuantityChange,
//                orderDescription = orderDescription,
//                onOrderDescriptionChange = onOrderDescriptionChange,
//                orderCost = orderCost,
//                onOrderCostChange = onOrderCostChange,
//                lipaNumber = lipaNumber,
//                onLipaNumberChange = onLipaNumberChange,
//                navigator = navigator,
//                success =success,
//                onOrderSubmission = onOrderSubmission,
//                context = context
//            )
//        }
//    }
//
//    @Composable
//    fun OrderEntryPanel(
//        orderTitle: String = "",
//        onOrderTitleChange: (String) -> Unit,
//        orderQty: String = "",
//        onOrderQuantityChange: (String) -> Unit,
//        orderDescription: String = "",
//        onOrderDescriptionChange: (String) -> Unit,
//        orderCost: String = "",
//        onOrderCostChange: (String) -> Unit,
//        lipaNumber: String = "",
//        onLipaNumberChange: (String) -> Unit,
//        navigator: Navigator,
//        success: Boolean,
//        onOrderSubmission: () -> Unit = {},
//        context: Context
//    ) {
//        val scrollState = rememberScrollState()
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(650.dp)
//                .padding(bottom = 8.dp)
//                .clip(shape = RoundedCornerShape(15.dp))
//                .background(color = Color(0xFAFAFAFA))
//
//                .border(
//                    width = .8.dp, color = Color(0xFF2F4858),
//                    shape = RoundedCornerShape(15.dp)
//                )
//                .verticalScroll(scrollState),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Spacer(modifier = Modifier.height(3.dp))
//            FirstEntryRow(
//                orderTitle = orderTitle,
//                onOrderTitleChange = onOrderTitleChange,
//                orderQty = orderQty,
//                onOrderQuantityChange = onOrderQuantityChange
//            )
//            Spacer(modifier = Modifier.height(3.dp))
//            SecondEntryRow(
//                orderDescription = orderDescription,
//                onOrderDescriptionChange = onOrderDescriptionChange
//            )
//            Spacer(modifier = Modifier.height(3.dp))
//            ThirdEntryRow(
//                orderCost = orderCost,
//                onOrderCostChange = onOrderCostChange
//            )
//            Spacer(modifier = Modifier.height(3.dp))
//            FourthEntryRow(
//                lipaNumber = lipaNumber,
//                onLipaNumberChange = onLipaNumberChange
//            )
//            Spacer(modifier = Modifier.height(5.dp))
//            CreateOrderButton(
//                navigator = navigator,
//                success = success,
//                onOrderSubmission = onOrderSubmission
//            )
//            LaunchedEffect(key1 = success) {
//                if (success) {
//                    Toast.makeText(context,
//                        "Order sent successfully",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    navigator.push(HomeRoute(modifier = Modifier)) // Navigate to LoginScreen
//                }
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//    }
//
//    @Composable
//    fun CreateOrderButton(
//        navigator: Navigator,
//        success: Boolean,
//        onOrderSubmission: () -> Unit,
//    ) {
//        Button(
//            onClick = onOrderSubmission,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF6FC07A)
//            ),
//            shape = RoundedCornerShape(15.dp),
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .height(50.dp)
//        ) {
//            Text(text = "Create Order",
//                modifier = Modifier.wrapContentSize(Alignment.Center),
//                style = TextStyle(
//                    fontSize = 16.sp,
////                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFFFAFAFA),
//                    textAlign = TextAlign.Center,
//                )
//            )
//        }
//    }
//
//    @Composable
//    fun FourthEntryRow(
//        lipaNumber:String = "",
//        onLipaNumberChange:(String)->Unit
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .height(70.dp)
//        ) {
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "SCTSMA Lipa Number",
//                fontSize = 12.5.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .fillMaxHeight(),
//                value = lipaNumber,
//                onValueChange = onLipaNumberChange,
//            )
//        }
//    }
//
//
//    @Composable
//    fun ThirdEntryRow(
//        orderCost:String = "",
//        onOrderCostChange:(String)->Unit
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .height(70.dp)
//        ) {
//            Text(
//                modifier = Modifier.fillMaxWidth()
//                ,
//                text = "Amount in Tsh",
//                fontSize = 12.5.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .fillMaxHeight(),
//                value = orderCost,
//                onValueChange = onOrderCostChange,
//            )
//        }
//    }
//    @Composable
//    fun SecondEntryRow(
//        orderDescription:String,
//        onOrderDescriptionChange:(String)->Unit
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .height(200.dp)
//        ) {
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "Order Description",
//                fontSize = 12.5.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//            )
//            OutlinedTextField(
//                textStyle = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight(500),
//                    color = Color(0xFF2F4858),
//                ),
//                singleLine = false,
//                maxLines = 100,
//                minLines = 100,
//                shape = RoundedCornerShape(15.dp),
//                modifier = Modifier.fillMaxSize(),
//                value = orderDescription,
//                onValueChange = onOrderDescriptionChange,
//            )
//        }
//    }
//    @Composable
//    fun FirstEntryRow(
//        orderTitle:String = "",
//        onOrderTitleChange:(String)->Unit,
//        orderQty:String = "",
//        onOrderQuantityChange:(String)->Unit
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .padding(top = 10.dp)
//                .height(70.dp)
//
//            ,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            OrderTitleComponent(
//                orderTitle = orderTitle,
//                onOrderTitleChange =  onOrderTitleChange
//            )
//            OrderQuantityComponent(
//                orderQty = orderQty,
//                onOrderQuantityChange = onOrderQuantityChange
//            )
//        }
//    }
//    @Composable
//    fun OrderTitleComponent(
//        orderTitle:String = "",
//        onOrderTitleChange:(String)->Unit
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(.6f)
//                .fillMaxHeight()
//        ) {
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "Title",
//                fontSize = 12.5.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//            )
//            OutlinedTextField(
//                textStyle = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight(500),
//                    color = Color(0xFF2F4858),
//                ),
//                singleLine = true,
//                maxLines = 1,
//                shape = RoundedCornerShape(15.dp),
//
//                modifier = Modifier.fillMaxSize(),
//                value = orderTitle,
//                onValueChange = onOrderTitleChange,
//            )
//        }
//    }
//
//    @Composable
//    fun  OrderQuantityComponent(
//        orderQty:String = "",
//        onOrderQuantityChange:(String)->Unit
//    ){
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(.55f)
//                .fillMaxHeight()
//        ) {
//            Text(
//
//                textAlign = TextAlign.Center,
//                text = "Quantity",
//                fontSize = 12.4.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//
//                )
//            OutlinedTextField(
//                textStyle = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight(500),
//                    color = Color(0xFF2F4858),
//                ),
//                singleLine = true,
//                maxLines = 1,
//                shape = RoundedCornerShape(15.dp),
//
//                modifier = Modifier.fillMaxSize(),
//                value = orderQty,
//                onValueChange = onOrderQuantityChange
//            )
//
//        }
//    }
//
//
//
//    @Composable
//    fun NavigationPanel(
//        controlName: String
//    ){
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(.1f)
//
//        ){
//            Image(
//                painterResource(
//                    R.drawable.back_button
//                ),
//                contentDescription = "Back button"
//                , modifier = Modifier
//                    .height(30.dp)
//                    .width(45.dp)
//            )
//            Text(
//                text = controlName,
//                fontSize = 20.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFF2F4858),
//
//                )
//            Spacer(modifier = Modifier
//                .width(30.dp))
//        }
//    }
//
//
//}