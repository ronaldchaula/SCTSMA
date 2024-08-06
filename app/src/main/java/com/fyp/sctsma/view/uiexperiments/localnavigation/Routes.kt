package com.fyp.sctsma.view.uiexperiments.localnavigation

sealed class Routes(route:String) {
    data object Home:Routes("home")
    data object About:Routes("about")
    data object Contact:Routes("contact")
}