package com.pradiph31.learnjapanese

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradiph31.learnjapanese.data.hiragana
import com.pradiph31.learnjapanese.data.katakana
import com.pradiph31.learnjapanese.ui.screens.*
import com.pradiph31.learnjapanese.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnJapaneseTheme {
                LearnJapaneseApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnJapaneseApp() {
    val tabs = listOf("Start", "Hiragana", "Katakana", "Quiz", "Phrases", "Numbers", "Kanji")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Paper)
                    .statusBarsPadding()
                    .padding(top = 24.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "日本語",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Ink,
                    letterSpacing = 4.sp,
                )
                Text(
                    text = "Learn to read Japanese",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    color = Muted,
                    letterSpacing = 1.sp,
                )
            }
        },
        containerColor = Paper,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Paper,
                contentColor = Ink,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    if (pagerState.currentPage < tabPositions.size) {
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            color = Red,
                            height = 2.dp,
                        )
                    }
                },
                divider = { HorizontalDivider(thickness = 0.5.dp, color = Ink.copy(alpha = 0.1f)) }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                title.uppercase(),
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                                fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (pagerState.currentPage == index) Red else Muted,
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> StartScreen()
                    1 -> KanaGrid(hiragana)
                    2 -> KanaGrid(katakana)
                    3 -> QuizScreen()
                    4 -> PhrasesScreen()
                    5 -> NumbersScreen()
                    6 -> KanjiScreen()
                }
            }
        }
    }
}