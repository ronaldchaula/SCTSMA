package com.fyp.sctsma.model.navigations

sealed class DrawerAction {
    object NavigateToHome : DrawerAction()
    object NavigateToProfile : DrawerAction()
    object NavigateToWallet : DrawerAction()
    object NavigateToComplaints : DrawerAction()
    object NavigateToSettings : DrawerAction()
    object Logout : DrawerAction()

}
