package com.example.jetpackapi.presention.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.example.jetpackapi.presention.Dimens.IndicatorWidth
import com.example.jetpackapi.presention.Dimens.MediumPadding2
import com.example.jetpackapi.presention.common.NewButton
import com.example.jetpackapi.presention.common.NewsTextButton
import com.example.jetpackapi.presention.onboarding.components.OnBoardingPage
import com.example.jetpackapi.presention.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent)->Unit
){
    Column(modifier = Modifier.fillMaxWidth()) {
        val pageState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val  buttonState = remember {
            derivedStateOf {
                when(pageState.currentPage){
                    0-> listOf("","next")
                    1-> listOf("back","next")
                    2-> listOf("back","get start")
                    else -> listOf("","")
                }
            }
        }
        
        HorizontalPager(state = pageState) {index->
            OnBoardingPage(page = pages[index])
            
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            PageIndicator(modifier = Modifier.width(IndicatorWidth), pageSize = pages.size, selectedPage = pageState.currentPage )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope= rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()){
                    NewsTextButton(text = buttonState.value[0], onClick = {
                        scope.launch {
                            pageState.animateScrollToPage(page = pageState.currentPage-1)

                        }
                    })

                }
                NewButton(text = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pageState.currentPage==2){
                            event(OnBoardingEvent.SaveAppEntry)
                        }else{
                            pageState.animateScrollToPage(page = pageState.currentPage+1)

                        }
                    }
                })

            }
        }


        Spacer(modifier = Modifier.weight(1f))
    }

}