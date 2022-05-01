package com.ohyooo.qrscan.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FileOpen
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Receipt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.ohyooo.qrscan.ScanActivity
import kotlinx.coroutines.launch

const val Result = "Result"
const val Edit = "Edit"
const val Local = "Local"
const val History = "History"

val icoResult = Icons.Rounded.Receipt
val icoEdit = Icons.Rounded.Edit
val icoLocal = Icons.Rounded.FileOpen
val icoHistory = Icons.Rounded.History
// val icoSetting = Icons.Rounded.Settings

private val tabList = listOf(Result, Edit, Local, History)
private val tabIconList = listOf(icoResult, icoEdit, icoLocal, icoHistory)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainUI(activity: ScanActivity) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Home(activity = activity)
        },
        sheetPeekHeight = 100.dp,
        modifier = Modifier.fillMaxSize(),
    ) {
        CameraUI(activity)
    }

    // val navController = rememberNavController()
    // NavHost(navController = navController, startDestination = Result) {
    //     composable(Result) { ResultUI(vm = activity.vm) }
    //     composable(Edit)   { EditUI(vm = activity.vm) }
    //     composable(Local)  { LocalUI(activity = activity) }
    //     composable(History) { HistoryUI(vm = activity.vm) }
    //     composable(Setting) { SettingUI() }
    // }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(activity: ScanActivity) {
    Column {
        val pagerState = rememberPagerState(
            // pageCount = tabList.size,
            // initialOffscreenLimit = 1,
            // infiniteLoop = false,
            // initialPage = 0,
        )
        val r = activity.vm.result.observeAsState(initial = "")
        if (!r.value.isNullOrEmpty()) {
            LaunchedEffect(key1 = "pagerState") {
                pagerState.scrollToPage(0)
            }
        }

        Tab(pagerState)

        HorizontalPager(state = pagerState, modifier = Modifier.weight(1F), count = tabList.size) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // navController.navigate(tabList[index])
                when (tabList[index]) {
                    Result -> ResultUI(vm = activity.vm)
                    Edit -> EditUI(vm = activity.vm)
                    Local -> LocalUI(activity = activity)
                    History -> HistoryUI(vm = activity.vm)
                    // Setting -> SettingUI()
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tab(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    val tabIndex = pagerState.currentPage

    TabRow(
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabIconList.forEachIndexed { index, ico ->
            Tab(selected = tabIndex == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                }, text = {
                    Icon(ico, contentDescription = "")
                })
        }
    }
}