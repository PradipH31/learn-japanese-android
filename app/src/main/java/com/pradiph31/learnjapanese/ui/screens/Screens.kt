package com.pradiph31.learnjapanese.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradiph31.learnjapanese.data.*
import com.pradiph31.learnjapanese.ui.theme.*

@Composable
fun KanaGrid(kanaList: List<KanaEntry>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 72.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(kanaList) { entry ->
            var flipped by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .clickable { flipped = !flipped }
                    .animateContentSize(),
                colors = CardDefaults.cardColors(
                    containerColor = if (flipped) FlippedBg else CardBg
                ),
                border = if (flipped) CardDefaults.outlinedCardBorder().copy(width = 1.dp) else null,
                shape = RoundedCornerShape(6.dp),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = entry.char,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        color = Ink
                    )
                    Text(
                        text = entry.romaji,
                        fontSize = 11.sp,
                        color = if (flipped) Red else Muted,
                        fontWeight = if (flipped) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun PhrasesScreen() {
    val tags = remember {
        listOf("All") + phrases.map { it.tag }.distinct()
    }
    var activeFilter by remember { mutableStateOf("All") }
    val filtered = remember(activeFilter) {
        if (activeFilter == "All") phrases else phrases.filter { it.tag == activeFilter }
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Legend
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text("READING KEY:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Muted, letterSpacing = 1.sp)
                LegendItem("Hiragana", HiraganaColor)
                LegendItem("Katakana", KatakanaColor)
                LegendItem("Kanji", KanjiColor)
            }
        }

        // Filter chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(tags) { tag ->
                    FilterChip(
                        selected = activeFilter == tag,
                        onClick = { activeFilter = tag },
                        label = { Text(tag, fontSize = 11.sp, letterSpacing = 0.5.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Ink,
                            selectedLabelColor = Paper,
                        ),
                        shape = RoundedCornerShape(4.dp),
                    )
                }
            }
        }

        // Phrase cards
        items(filtered, key = { it.meaning }) { phrase ->
            PhraseCard(phrase)
        }
    }
}

@Composable
private fun LegendItem(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        Text(label, fontSize = 10.sp, color = Muted)
    }
}

@Composable
fun PhraseCard(phrase: Phrase) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        border = if (expanded) CardDefaults.outlinedCardBorder().copy(width = 1.dp) else null,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Tag badge
            Box(Modifier.align(Alignment.End)) {
                Text(
                    text = phrase.tag.uppercase(),
                    fontSize = 9.sp,
                    letterSpacing = 1.sp,
                    color = Muted,
                    modifier = Modifier
                        .background(Color(0x0F1A1208), RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            // Character-aligned reading display - THE KEY FEATURE
            Row(
                modifier = Modifier.padding(end = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                phrase.words.forEach { word ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Characters row
                        Row {
                            word.chars.forEach { charUnit ->
                                val type = charType(charUnit.char)
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.widthIn(min = 28.dp).padding(horizontal = 1.dp)
                                ) {
                                    // Japanese character on top
                                    Text(
                                        text = charUnit.char,
                                        fontSize = 22.sp,
                                        color = when (type) {
                                            CharType.HIRAGANA -> HiraganaColor
                                            CharType.KATAKANA -> KatakanaColor
                                            CharType.KANJI -> KanjiColor
                                            CharType.PUNCT -> Muted
                                        },
                                        textAlign = TextAlign.Center,
                                        lineHeight = 28.sp,
                                    )
                                    // Romaji reading directly below, aligned
                                    Text(
                                        text = charUnit.romaji,
                                        fontSize = 10.sp,
                                        color = when (type) {
                                            CharType.KATAKANA -> Color(0xFF5C92C7)
                                            CharType.KANJI -> Color(0xFFD4796B)
                                            else -> Muted
                                        },
                                        textAlign = TextAlign.Center,
                                        lineHeight = 14.sp,
                                    )
                                }
                            }
                        }
                        // Word meaning label (shown when expanded)
                        if (expanded) {
                            HorizontalDivider(
                                modifier = Modifier.padding(top = 3.dp).width(
                                    (word.chars.size * 30).dp
                                ),
                                thickness = 0.5.dp,
                                color = Color(0x1A1A1208)
                            )
                            Text(
                                text = word.meaning,
                                fontSize = 9.sp,
                                color = Muted,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }

            // Expanded details
            if (expanded) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 12.dp),
                    thickness = 0.5.dp,
                    color = Color(0x141A1208)
                )
                Text(
                    text = phrase.meaning,
                    fontSize = 14.sp,
                    color = Ink,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = phrase.en,
                    fontSize = 12.sp,
                    color = Muted,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 2.dp)
                )

                // Word breakdown chips
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    phrase.words.forEach { word ->
                        val fullJp = word.chars.joinToString("") { it.char }
                        Column(
                            modifier = Modifier
                                .background(Color(0x0A1A1208), RoundedCornerShape(4.dp))
                                .border(0.5.dp, Color(0x141A1208), RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(fullJp, fontSize = 14.sp, color = Ink)
                            Text(word.meaning, fontSize = 9.sp, color = Muted)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizScreen() {
    var mode by remember { mutableStateOf("hiragana") }
    var correct by remember { mutableIntStateOf(0) }
    var total by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var currentEntry by remember { mutableStateOf(hiragana.random()) }
    var options by remember { mutableStateOf(listOf<KanaEntry>()) }
    var feedback by remember { mutableStateOf("") }
    var feedbackGood by remember { mutableStateOf(true) }

    fun pool() = when (mode) {
        "hiragana" -> hiragana
        "katakana" -> katakana
        else -> hiragana + katakana
    }

    fun nextQ() {
        answered = false
        feedback = ""
        val p = pool()
        currentEntry = p.random()
        val wrongs = p.filter { it.romaji != currentEntry.romaji }.shuffled().take(3)
        options = (wrongs + currentEntry).shuffled()
    }

    fun resetQuiz() {
        correct = 0; total = 0
        nextQ()
    }

    LaunchedEffect(Unit) { nextQ() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Mode toggles
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("hiragana", "katakana", "both").forEach { m ->
                FilterChip(
                    selected = mode == m,
                    onClick = { mode = m; resetQuiz() },
                    label = { Text(m.replaceFirstChar { it.uppercase() }, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Ink, selectedLabelColor = Paper
                    ),
                    shape = RoundedCornerShape(4.dp),
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Score: $correct / $total", fontSize = 11.sp, color = Muted, letterSpacing = 1.sp)

        // Progress bar
        LinearProgressIndicator(
            progress = { if (total > 0) correct.toFloat() / total else 0f },
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp).height(3.dp),
            color = Red,
            trackColor = Color(0x141A1208),
        )

        Text("What is the romaji for this character?", fontSize = 12.sp, color = Muted)
        Spacer(Modifier.height(12.dp))

        Text(
            text = currentEntry.char,
            fontSize = 80.sp,
            color = Ink,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(24.dp))

        // Options grid 2x2
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for (row in options.chunked(2)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { opt ->
                        val isCorrect = opt.romaji == currentEntry.romaji
                        val bgColor = when {
                            !answered -> CardBg
                            isCorrect -> CorrectBg
                            opt.romaji == feedback && !feedbackGood -> WrongBg
                            else -> CardBg
                        }
                        OutlinedButton(
                            onClick = {
                                if (answered) return@OutlinedButton
                                answered = true
                                total++
                                feedback = opt.romaji
                                if (isCorrect) {
                                    correct++
                                    feedbackGood = true
                                } else {
                                    feedbackGood = false
                                }
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = bgColor),
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            Text(opt.romaji, fontSize = 16.sp, color = Ink, letterSpacing = 0.5.sp)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        if (answered) {
            Text(
                text = if (feedbackGood) "✓ Correct!" else "✗ It was \"${currentEntry.romaji}\"",
                color = if (feedbackGood) CorrectGreen else Red,
                fontSize = 14.sp,
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { nextQ() },
                colors = ButtonDefaults.buttonColors(containerColor = Ink, contentColor = Paper),
                shape = RoundedCornerShape(4.dp),
            ) {
                Text("NEXT →", fontSize = 12.sp, letterSpacing = 1.sp)
            }
        }
    }
}

@Composable
fun KanjiScreen() {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CardBg),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Two types of readings: Most kanji have an on'yomi (音, Chinese-derived, used in compounds) and a kun'yomi (訓, native Japanese, used standalone). Tap any card to see both.",
                    fontSize = 12.sp, color = Muted, lineHeight = 20.sp,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        kanjiSections.forEach { section ->
            item {
                Text(
                    section.label.uppercase(),
                    fontSize = 10.sp, fontWeight = FontWeight.Bold,
                    color = Muted, letterSpacing = 1.5.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                )
            }
            items(section.items.chunked(3)) { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { entry ->
                        var flipped by remember { mutableStateOf(false) }
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { flipped = !flipped }
                                .animateContentSize(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (flipped) FlippedBg else CardBg
                            ),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(entry.kanji, fontSize = 32.sp, color = KanjiColor)
                                Text(entry.meaning, fontSize = 10.sp, color = Ink, fontWeight = FontWeight.Bold)
                                if (flipped) {
                                    Spacer(Modifier.height(4.dp))
                                    Text("ON: ${entry.onReading}", fontSize = 9.sp, color = KatakanaColor)
                                    Text("KUN: ${entry.kunReading}", fontSize = 9.sp, color = Ink)
                                }
                            }
                        }
                    }
                    // Fill remaining space if row is incomplete
                    repeat(3 - row.size) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun NumbersScreen() {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CardBg),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Japanese uses kanji for numbers. The readings change depending on what you're counting (counters), but the base readings below are the most common. Tap any card to reveal its reading.",
                    fontSize = 12.sp, color = Muted, lineHeight = 20.sp,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        numberSections.forEach { section ->
            item {
                Text(
                    section.label.uppercase(),
                    fontSize = 10.sp, fontWeight = FontWeight.Bold,
                    color = Muted, letterSpacing = 1.5.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                )
            }
            items(section.items.chunked(3)) { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { entry ->
                        var flipped by remember { mutableStateOf(false) }
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { flipped = !flipped }
                                .animateContentSize(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (flipped) FlippedBg else CardBg
                            ),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(entry.digit, fontSize = 10.sp, color = Muted, letterSpacing = 0.5.sp)
                                Text(entry.kanji, fontSize = 28.sp, color = KanjiColor)
                                Text(
                                    entry.reading, fontSize = 10.sp,
                                    color = if (flipped) Red else Muted,
                                    fontWeight = if (flipped) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                    repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
fun StartScreen() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("THE THREE WRITING SYSTEMS", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                color = Muted, letterSpacing = 1.5.sp)
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    Triple("Hiragana", "あいうえお", "46 phonetic characters for native Japanese words and grammar."),
                    Triple("Katakana", "アイウエオ", "46 characters with same sounds. Used for foreign loanwords."),
                    Triple("Kanji", "日本語", "Chinese-derived characters. ~2,000 needed for daily reading."),
                ).forEach { (title, sample, desc) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = CardBg),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Red)
                            Text(sample, fontSize = 20.sp, color = Ink, modifier = Modifier.padding(vertical = 6.dp))
                            Text(desc, fontSize = 10.sp, color = Muted, lineHeight = 16.sp)
                        }
                    }
                }
            }
        }

        item {
            Text("RECOMMENDED PATH", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                color = Muted, letterSpacing = 1.5.sp, modifier = Modifier.padding(top = 8.dp))
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = CardBg),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf(
                        "Week 1–2" to "Learn all 46 hiragana. Use the table, then the quiz.",
                        "Week 3" to "Learn all 46 katakana. Spot foreign words everywhere.",
                        "Week 4+" to "Read the Phrases tab. Connect reading to words you know by sound.",
                        "Ongoing" to "Pick up kanji gradually. Start with 日, 月, 人, 大, 水, 本.",
                    ).forEach { (week, desc) ->
                        Row {
                            Text("$week — ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Ink)
                            Text(desc, fontSize = 12.sp, color = Muted, lineHeight = 18.sp)
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Key insight: Since you already know spoken Japanese, your job isn't learning new language — just matching shapes to sounds you already recognize!",
                        fontSize = 12.sp, color = Red, lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

