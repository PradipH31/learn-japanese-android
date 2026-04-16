package com.pradiph31.learnjapanese.data

data class KanaEntry(val char: String, val romaji: String)

data class CharUnit(val char: String, val romaji: String)

data class Word(val meaning: String, val chars: List<CharUnit>)

data class Phrase(
    val meaning: String,
    val en: String,
    val tag: String,
    val words: List<Word>
)

data class KanjiEntry(
    val kanji: String,
    val meaning: String,
    val onReading: String,
    val kunReading: String
)

data class KanjiSection(val label: String, val items: List<KanjiEntry>)

data class NumberEntry(val digit: String, val kanji: String, val reading: String)

data class NumberSection(val label: String, val items: List<NumberEntry>)

enum class CharType { HIRAGANA, KATAKANA, KANJI, PUNCT }

fun charType(str: String): CharType {
    if (str.isEmpty()) return CharType.PUNCT
    val c = str[0].code
    return when {
        c in 0x3040..0x309F -> CharType.HIRAGANA
        c in 0x30A0..0x30FF -> CharType.KATAKANA
        c in 0x4E00..0x9FFF -> CharType.KANJI
        else -> CharType.PUNCT
    }
}

val hiragana = listOf(
    KanaEntry("あ", "a"), KanaEntry("い", "i"), KanaEntry("う", "u"), KanaEntry("え", "e"), KanaEntry("お", "o"),
    KanaEntry("か", "ka"), KanaEntry("き", "ki"), KanaEntry("く", "ku"), KanaEntry("け", "ke"), KanaEntry("こ", "ko"),
    KanaEntry("さ", "sa"), KanaEntry("し", "shi"), KanaEntry("す", "su"), KanaEntry("せ", "se"), KanaEntry("そ", "so"),
    KanaEntry("た", "ta"), KanaEntry("ち", "chi"), KanaEntry("つ", "tsu"), KanaEntry("て", "te"), KanaEntry("と", "to"),
    KanaEntry("な", "na"), KanaEntry("に", "ni"), KanaEntry("ぬ", "nu"), KanaEntry("ね", "ne"), KanaEntry("の", "no"),
    KanaEntry("は", "ha"), KanaEntry("ひ", "hi"), KanaEntry("ふ", "fu"), KanaEntry("へ", "he"), KanaEntry("ほ", "ho"),
    KanaEntry("ま", "ma"), KanaEntry("み", "mi"), KanaEntry("む", "mu"), KanaEntry("め", "me"), KanaEntry("も", "mo"),
    KanaEntry("や", "ya"), KanaEntry("ゆ", "yu"), KanaEntry("よ", "yo"),
    KanaEntry("ら", "ra"), KanaEntry("り", "ri"), KanaEntry("る", "ru"), KanaEntry("れ", "re"), KanaEntry("ろ", "ro"),
    KanaEntry("わ", "wa"), KanaEntry("を", "wo"), KanaEntry("ん", "n"),
)

val katakana = listOf(
    KanaEntry("ア", "a"), KanaEntry("イ", "i"), KanaEntry("ウ", "u"), KanaEntry("エ", "e"), KanaEntry("オ", "o"),
    KanaEntry("カ", "ka"), KanaEntry("キ", "ki"), KanaEntry("ク", "ku"), KanaEntry("ケ", "ke"), KanaEntry("コ", "ko"),
    KanaEntry("サ", "sa"), KanaEntry("シ", "shi"), KanaEntry("ス", "su"), KanaEntry("セ", "se"), KanaEntry("ソ", "so"),
    KanaEntry("タ", "ta"), KanaEntry("チ", "chi"), KanaEntry("ツ", "tsu"), KanaEntry("テ", "te"), KanaEntry("ト", "to"),
    KanaEntry("ナ", "na"), KanaEntry("ニ", "ni"), KanaEntry("ヌ", "nu"), KanaEntry("ネ", "ne"), KanaEntry("ノ", "no"),
    KanaEntry("ハ", "ha"), KanaEntry("ヒ", "hi"), KanaEntry("フ", "fu"), KanaEntry("ヘ", "he"), KanaEntry("ホ", "ho"),
    KanaEntry("マ", "ma"), KanaEntry("ミ", "mi"), KanaEntry("ム", "mu"), KanaEntry("メ", "me"), KanaEntry("モ", "mo"),
    KanaEntry("ヤ", "ya"), KanaEntry("ユ", "yu"), KanaEntry("ヨ", "yo"),
    KanaEntry("ラ", "ra"), KanaEntry("リ", "ri"), KanaEntry("ル", "ru"), KanaEntry("レ", "re"), KanaEntry("ロ", "ro"),
    KanaEntry("ワ", "wa"), KanaEntry("ヲ", "wo"), KanaEntry("ン", "n"),
)

val phrases = listOf(
    Phrase("Thank you very much (formal)", "The polite, full form of \"thank you\"", "Greetings",
        listOf(
            Word("thank you", listOf(CharUnit("あ","a"), CharUnit("り","ri"), CharUnit("が","ga"), CharUnit("と","to"), CharUnit("う","u"))),
            Word("(polite ending)", listOf(CharUnit("ご","go"), CharUnit("ざ","za"), CharUnit("い","i"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Good morning (formal)", "Said before noon, usually to colleagues or elders", "Greetings",
        listOf(
            Word("good morning", listOf(CharUnit("お","o"), CharUnit("は","ha"), CharUnit("よ","yo"), CharUnit("う","u"))),
            Word("(polite ending)", listOf(CharUnit("ご","go"), CharUnit("ざ","za"), CharUnit("い","i"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Hello / Good afternoon", "The most common all-purpose greeting", "Greetings",
        listOf(
            Word("hello", listOf(CharUnit("こ","ko"), CharUnit("ん","n"), CharUnit("に","ni"), CharUnit("ち","chi"), CharUnit("は","wa"))),
        )),
    Phrase("Good evening", "Used from late afternoon onward", "Greetings",
        listOf(
            Word("good evening", listOf(CharUnit("こ","ko"), CharUnit("ん","n"), CharUnit("ば","ba"), CharUnit("ん","n"), CharUnit("は","wa"))),
        )),
    Phrase("Goodbye", "A formal farewell", "Greetings",
        listOf(
            Word("goodbye", listOf(CharUnit("さ","sa"), CharUnit("よ","yo"), CharUnit("う","u"), CharUnit("な","na"), CharUnit("ら","ra"))),
        )),
    Phrase("How are you?", "Literally \"are you well?\"", "Greetings",
        listOf(
            Word("(polite prefix)", listOf(CharUnit("お","o"))),
            Word("health / energy", listOf(CharUnit("元","gen"), CharUnit("気","ki"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("Good night", "Said when parting at night or before going to sleep", "Greetings",
        listOf(
            Word("rest", listOf(CharUnit("お","o"), CharUnit("や","ya"), CharUnit("す","su"), CharUnit("み","mi"))),
            Word("(polite command)", listOf(CharUnit("な","na"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
    Phrase("Excuse me / I'm sorry", "Used to get attention, apologise, or express gratitude", "Essential",
        listOf(
            Word("excuse me", listOf(CharUnit("す","su"), CharUnit("み","mi"), CharUnit("ま","ma"), CharUnit("せ","se"), CharUnit("ん","n"))),
        )),
    Phrase("I don't understand", "Polite negative form of わかる (to understand)", "Essential",
        listOf(
            Word("understand", listOf(CharUnit("わ","wa"), CharUnit("か","ka"), CharUnit("り","ri"))),
            Word("(polite negative)", listOf(CharUnit("ま","ma"), CharUnit("せ","se"), CharUnit("ん","n"))),
        )),
    Phrase("Please say that again", "Literally \"one more time, please\"", "Essential",
        listOf(
            Word("more / again", listOf(CharUnit("も","mo"), CharUnit("う","u"))),
            Word("one time", listOf(CharUnit("一","ichi"), CharUnit("度","do"))),
            Word("please", listOf(CharUnit("お","o"), CharUnit("願","nega"), CharUnit("い","i"), CharUnit("し","shi"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Yes", "Standard polite affirmative", "Essential",
        listOf(
            Word("yes", listOf(CharUnit("は","ha"), CharUnit("い","i"))),
        )),
    Phrase("No", "Polite negative", "Essential",
        listOf(
            Word("no", listOf(CharUnit("い","i"), CharUnit("い","i"), CharUnit("え","e"))),
        )),
    Phrase("How much is this?", "Essential shopping phrase", "Shopping",
        listOf(
            Word("this", listOf(CharUnit("こ","ko"), CharUnit("れ","re"))),
            Word("(topic marker)", listOf(CharUnit("は","wa"))),
            Word("how much", listOf(CharUnit("い","i"), CharUnit("く","ku"), CharUnit("ら","ra"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("Where is it?", "Add a location before this to ask \"Where is [place]?\"", "Navigation",
        listOf(
            Word("where", listOf(CharUnit("ど","do"), CharUnit("こ","ko"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("Where is the bathroom?", "トイレ is a loanword from English \"toilet\"", "Navigation",
        listOf(
            Word("toilet", listOf(CharUnit("ト","to"), CharUnit("イ","i"), CharUnit("レ","re"))),
            Word("(topic marker)", listOf(CharUnit("は","wa"))),
            Word("where", listOf(CharUnit("ど","do"), CharUnit("こ","ko"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("Delicious!", "One of the most useful words in Japan", "Food",
        listOf(
            Word("delicious", listOf(CharUnit("お","o"), CharUnit("い","i"), CharUnit("し","shi"), CharUnit("い","i"))),
        )),
    Phrase("Let's eat / I humbly receive", "Said before every meal — shows gratitude for the food", "Food",
        listOf(
            Word("I humbly receive", listOf(CharUnit("い","i"), CharUnit("た","ta"), CharUnit("だ","da"), CharUnit("き","ki"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Thank you for the meal", "Said after eating — literally \"it was a feast\"", "Food",
        listOf(
            Word("feast / treat", listOf(CharUnit("ご","go"), CharUnit("ち","chi"), CharUnit("そ","so"), CharUnit("う","u"), CharUnit("さ","sa"), CharUnit("ま","ma"))),
            Word("(past polite)", listOf(CharUnit("で","de"), CharUnit("し","shi"), CharUnit("た","ta"))),
        )),
    Phrase("Water, please", "水 (mizu) is one of the first kanji to learn", "Food",
        listOf(
            Word("water", listOf(CharUnit("水","mizu"))),
            Word("(object marker)", listOf(CharUnit("を","wo"))),
            Word("please (give me)", listOf(CharUnit("く","ku"), CharUnit("だ","da"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
    Phrase("The bill, please", "Used when you want to pay at a restaurant", "Food",
        listOf(
            Word("(polite prefix)", listOf(CharUnit("お","o"))),
            Word("account / bill", listOf(CharUnit("会","kai"), CharUnit("計","kei"))),
            Word("(object marker)", listOf(CharUnit("を","wo"))),
            Word("please", listOf(CharUnit("お","o"), CharUnit("願","nega"), CharUnit("い","i"), CharUnit("し","shi"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Nice to meet you", "Said when meeting someone for the first time", "Introductions",
        listOf(
            Word("first time", listOf(CharUnit("は","ha"), CharUnit("じ","ji"), CharUnit("め","me"))),
            Word("is", listOf(CharUnit("ま","ma"), CharUnit("し","shi"), CharUnit("て","te"))),
        )),
    Phrase("Please take care of me", "Said after introductions — has no exact English equivalent", "Introductions",
        listOf(
            Word("well / favorably", listOf(CharUnit("よ","yo"), CharUnit("ろ","ro"), CharUnit("し","shi"), CharUnit("く","ku"))),
            Word("please", listOf(CharUnit("お","o"), CharUnit("願","nega"), CharUnit("い","i"), CharUnit("し","shi"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("I'm from America", "Replace アメリカ with your country name", "Introductions",
        listOf(
            Word("America", listOf(CharUnit("ア","a"), CharUnit("メ","me"), CharUnit("リ","ri"), CharUnit("カ","ka"))),
            Word("from", listOf(CharUnit("か","ka"), CharUnit("ら","ra"))),
            Word("came", listOf(CharUnit("き","ki"), CharUnit("ま","ma"), CharUnit("し","shi"), CharUnit("た","ta"))),
        )),
    Phrase("It's beautiful!", "Used for scenery, people, art — very versatile", "Compliments",
        listOf(
            Word("beautiful / clean", listOf(CharUnit("き","ki"), CharUnit("れ","re"), CharUnit("い","i"))),
            Word("is (polite)", listOf(CharUnit("で","de"), CharUnit("す","su"))),
        )),
    Phrase("It's cute!", "かわいい — one of the most used words in Japan", "Compliments",
        listOf(
            Word("cute", listOf(CharUnit("か","ka"), CharUnit("わ","wa"), CharUnit("い","i"), CharUnit("い","i"))),
        )),
    Phrase("It's amazing!", "すごい — expresses surprise and admiration", "Compliments",
        listOf(
            Word("amazing", listOf(CharUnit("す","su"), CharUnit("ご","go"), CharUnit("い","i"))),
        )),
    Phrase("Welcome!", "You'll hear this in every shop and restaurant", "Culture",
        listOf(
            Word("welcome", listOf(CharUnit("い","i"), CharUnit("ら","ra"), CharUnit("っ","s"), CharUnit("し","sha"), CharUnit("ゃ","—"), CharUnit("い","i"))),
            Word("(polite ending)", listOf(CharUnit("ま","ma"), CharUnit("せ","se"))),
        )),
    Phrase("I'm home!", "Said when returning home — a daily ritual", "Culture",
        listOf(
            Word("I'm home", listOf(CharUnit("た","ta"), CharUnit("だ","da"), CharUnit("い","i"), CharUnit("ま","ma"))),
        )),
    Phrase("I'm heading out", "Said when leaving the house — a daily ritual", "Culture",
        listOf(
            Word("I'm leaving", listOf(CharUnit("行","it"), CharUnit("て","te"))),
            Word("come (polite)", listOf(CharUnit("き","ki"), CharUnit("ま","ma"), CharUnit("す","su"))),
        )),
    Phrase("Cheers! (toast)", "Said when raising your glass", "Culture",
        listOf(
            Word("cheers (dry cup)", listOf(CharUnit("乾","kan"), CharUnit("杯","pai"))),
        )),
    Phrase("Please help!", "Use in emergencies to get immediate attention", "Emergency",
        listOf(
            Word("help!", listOf(CharUnit("助","tasu"), CharUnit("け","ke"), CharUnit("て","te"))),
            Word("please", listOf(CharUnit("く","ku"), CharUnit("だ","da"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
    Phrase("I feel sick", "気分 (kibun) = feeling / mood", "Emergency",
        listOf(
            Word("feeling / mood", listOf(CharUnit("気","ki"), CharUnit("分","bun"))),
            Word("(subject marker)", listOf(CharUnit("が","ga"))),
            Word("bad", listOf(CharUnit("悪","waru"), CharUnit("い","i"))),
            Word("is (polite)", listOf(CharUnit("で","de"), CharUnit("す","su"))),
        )),
    Phrase("Where is the hospital?", "病院 (byōin) = hospital", "Emergency",
        listOf(
            Word("hospital", listOf(CharUnit("病","byō"), CharUnit("院","in"))),
            Word("(topic marker)", listOf(CharUnit("は","wa"))),
            Word("where", listOf(CharUnit("ど","do"), CharUnit("こ","ko"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("It's okay / No problem", "Used to reassure someone — very versatile", "Essential",
        listOf(
            Word("big", listOf(CharUnit("大","dai"))),
            Word("all right", listOf(CharUnit("丈","jō"), CharUnit("夫","bu"))),
            Word("is (polite)", listOf(CharUnit("で","de"), CharUnit("す","su"))),
        )),
    Phrase("I don't speak Japanese well", "A useful disclaimer when traveling", "Essential",
        listOf(
            Word("Japanese language", listOf(CharUnit("日","ni"), CharUnit("本","hon"), CharUnit("語","go"))),
            Word("(topic marker)", listOf(CharUnit("が","ga"))),
            Word("well / skillfully", listOf(CharUnit("上","jō"), CharUnit("手","zu"))),
            Word("not (polite neg.)", listOf(CharUnit("で","de"), CharUnit("は","wa"), CharUnit("あ","a"), CharUnit("り","ri"), CharUnit("ま","ma"), CharUnit("せ","se"), CharUnit("ん","n"))),
        )),
    Phrase("Please wait a moment", "ちょっと = \"a little\" — very common word", "Essential",
        listOf(
            Word("a little", listOf(CharUnit("ちょ","cho"), CharUnit("っ","t"), CharUnit("と","to"))),
            Word("wait", listOf(CharUnit("待","ma"), CharUnit("っ","t"), CharUnit("て","te"))),
            Word("please", listOf(CharUnit("く","ku"), CharUnit("だ","da"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
    Phrase("This one, please", "Point at what you want and say this", "Shopping",
        listOf(
            Word("this one", listOf(CharUnit("こ","ko"), CharUnit("れ","re"))),
            Word("(object marker)", listOf(CharUnit("を","wo"))),
            Word("please (give me)", listOf(CharUnit("く","ku"), CharUnit("だ","da"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
    Phrase("Where is the station?", "駅 (eki) is one of the most useful kanji in Japan", "Navigation",
        listOf(
            Word("station", listOf(CharUnit("駅","eki"))),
            Word("(topic marker)", listOf(CharUnit("は","wa"))),
            Word("where", listOf(CharUnit("ど","do"), CharUnit("こ","ko"))),
            Word("is it?", listOf(CharUnit("で","de"), CharUnit("す","su"), CharUnit("か","ka"))),
        )),
    Phrase("Please go straight", "Useful when giving or receiving directions", "Navigation",
        listOf(
            Word("straight", listOf(CharUnit("ま","ma"), CharUnit("っ","s"), CharUnit("す","su"), CharUnit("ぐ","gu"))),
            Word("go", listOf(CharUnit("行","i"), CharUnit("っ","t"), CharUnit("て","te"))),
            Word("please", listOf(CharUnit("く","ku"), CharUnit("だ","da"), CharUnit("さ","sa"), CharUnit("い","i"))),
        )),
)

val kanjiSections = listOf(
    KanjiSection("Nature & elements", listOf(
        KanjiEntry("日", "day / sun", "ニチ, ジツ", "ひ, か"),
        KanjiEntry("月", "month / moon", "ゲツ, ガツ", "つき"),
        KanjiEntry("火", "fire", "カ", "ひ"),
        KanjiEntry("水", "water", "スイ", "みず"),
        KanjiEntry("木", "tree / wood", "モク, ボク", "き"),
        KanjiEntry("金", "gold / money", "キン, コン", "かね"),
        KanjiEntry("土", "earth / soil", "ド, ト", "つち"),
        KanjiEntry("山", "mountain", "サン", "やま"),
        KanjiEntry("川", "river", "セン", "かわ"),
        KanjiEntry("天", "heaven / sky", "テン", "あめ, あま"),
        KanjiEntry("雨", "rain", "ウ", "あめ, あま"),
        KanjiEntry("花", "flower", "カ", "はな"),
        KanjiEntry("空", "sky / empty", "クウ", "そら, あ(く)"),
        KanjiEntry("海", "sea", "カイ", "うみ"),
        KanjiEntry("風", "wind", "フウ, フ", "かぜ"),
    )),
    KanjiSection("People & body", listOf(
        KanjiEntry("人", "person", "ジン, ニン", "ひと"),
        KanjiEntry("男", "man / male", "ダン, ナン", "おとこ"),
        KanjiEntry("女", "woman / female", "ジョ, ニョ", "おんな"),
        KanjiEntry("子", "child", "シ, ス", "こ"),
        KanjiEntry("父", "father", "フ", "ちち"),
        KanjiEntry("母", "mother", "ボ", "はは"),
        KanjiEntry("友", "friend", "ユウ", "とも"),
        KanjiEntry("生", "life / birth", "セイ, ショウ", "い(きる), う(まれる)"),
        KanjiEntry("手", "hand", "シュ", "て"),
        KanjiEntry("目", "eye", "モク, ボク", "め"),
        KanjiEntry("口", "mouth", "コウ, ク", "くち"),
        KanjiEntry("心", "heart / mind", "シン", "こころ"),
        KanjiEntry("力", "power / force", "リョク, リキ", "ちから"),
    )),
    KanjiSection("Numbers (kanji)", listOf(
        KanjiEntry("一", "one", "イチ", "ひと(つ)"),
        KanjiEntry("二", "two", "ニ", "ふた(つ)"),
        KanjiEntry("三", "three", "サン", "みっ(つ)"),
        KanjiEntry("四", "four", "シ", "よ(つ), よん"),
        KanjiEntry("五", "five", "ゴ", "いつ(つ)"),
        KanjiEntry("六", "six", "ロク", "むっ(つ)"),
        KanjiEntry("七", "seven", "シチ", "なな(つ)"),
        KanjiEntry("八", "eight", "ハチ", "やっ(つ)"),
        KanjiEntry("九", "nine", "キュウ, ク", "ここの(つ)"),
        KanjiEntry("十", "ten", "ジュウ", "とお"),
        KanjiEntry("百", "hundred", "ヒャク", "—"),
        KanjiEntry("千", "thousand", "セン", "ち"),
        KanjiEntry("万", "ten thousand", "マン, バン", "—"),
    )),
    KanjiSection("Common verbs & adjectives", listOf(
        KanjiEntry("食", "eat", "ショク", "た(べる)"),
        KanjiEntry("飲", "drink", "イン", "の(む)"),
        KanjiEntry("見", "see / look", "ケン", "み(る)"),
        KanjiEntry("聞", "hear / listen", "ブン, モン", "き(く)"),
        KanjiEntry("話", "speak / story", "ワ", "はな(す)"),
        KanjiEntry("読", "read", "ドク", "よ(む)"),
        KanjiEntry("書", "write", "ショ", "か(く)"),
        KanjiEntry("行", "go", "コウ, ギョウ", "い(く), おこな(う)"),
        KanjiEntry("来", "come", "ライ", "く(る), き(た)"),
        KanjiEntry("大", "big / great", "ダイ, タイ", "おお(きい)"),
        KanjiEntry("小", "small / little", "ショウ", "ちい(さい), こ"),
        KanjiEntry("新", "new", "シン", "あたら(しい)"),
        KanjiEntry("高", "tall / expensive", "コウ", "たか(い)"),
        KanjiEntry("安", "cheap / peaceful", "アン", "やす(い)"),
        KanjiEntry("白", "white", "ハク, ビャク", "しろ(い)"),
        KanjiEntry("赤", "red", "セキ", "あか(い)"),
        KanjiEntry("青", "blue / green", "セイ", "あお(い)"),
    )),
    KanjiSection("Places & directions", listOf(
        KanjiEntry("国", "country", "コク", "くに"),
        KanjiEntry("駅", "station", "エキ", "—"),
        KanjiEntry("店", "shop / store", "テン", "みせ"),
        KanjiEntry("学", "study / learn", "ガク", "まな(ぶ)"),
        KanjiEntry("校", "school", "コウ", "—"),
        KanjiEntry("東", "east", "トウ", "ひがし"),
        KanjiEntry("西", "west", "セイ, サイ", "にし"),
        KanjiEntry("南", "south", "ナン", "みなみ"),
        KanjiEntry("北", "north", "ホク", "きた"),
        KanjiEntry("上", "up / above", "ジョウ", "うえ, あ(げる)"),
        KanjiEntry("下", "down / below", "カ, ゲ", "した, さ(げる)"),
        KanjiEntry("中", "middle / inside", "チュウ", "なか"),
        KanjiEntry("右", "right", "ウ, ユウ", "みぎ"),
        KanjiEntry("左", "left", "サ", "ひだり"),
    )),
)

val numberSections = listOf(
    NumberSection("Basic digits (0–10)", listOf(
        NumberEntry("0", "零", "rei"),
        NumberEntry("1", "一", "ichi"),
        NumberEntry("2", "二", "ni"),
        NumberEntry("3", "三", "san"),
        NumberEntry("4", "四", "shi / yon"),
        NumberEntry("5", "五", "go"),
        NumberEntry("6", "六", "roku"),
        NumberEntry("7", "七", "shichi / nana"),
        NumberEntry("8", "八", "hachi"),
        NumberEntry("9", "九", "kyū / ku"),
        NumberEntry("10", "十", "jū"),
    )),
    NumberSection("Teens (11–19)", listOf(
        NumberEntry("11", "十一", "jū-ichi"),
        NumberEntry("12", "十二", "jū-ni"),
        NumberEntry("13", "十三", "jū-san"),
        NumberEntry("14", "十四", "jū-yon"),
        NumberEntry("15", "十五", "jū-go"),
        NumberEntry("16", "十六", "jū-roku"),
        NumberEntry("17", "十七", "jū-nana"),
        NumberEntry("18", "十八", "jū-hachi"),
        NumberEntry("19", "十九", "jū-kyū"),
    )),
    NumberSection("Tens (20–90)", listOf(
        NumberEntry("20", "二十", "ni-jū"),
        NumberEntry("30", "三十", "san-jū"),
        NumberEntry("40", "四十", "yon-jū"),
        NumberEntry("50", "五十", "go-jū"),
        NumberEntry("60", "六十", "roku-jū"),
        NumberEntry("70", "七十", "nana-jū"),
        NumberEntry("80", "八十", "hachi-jū"),
        NumberEntry("90", "九十", "kyū-jū"),
    )),
    NumberSection("Hundreds & big numbers", listOf(
        NumberEntry("100", "百", "hyaku"),
        NumberEntry("200", "二百", "ni-hyaku"),
        NumberEntry("300", "三百", "san-byaku"),
        NumberEntry("500", "五百", "go-hyaku"),
        NumberEntry("1,000", "千", "sen"),
        NumberEntry("10,000", "一万", "ichi-man"),
        NumberEntry("100,000", "十万", "jū-man"),
        NumberEntry("1,000,000", "百万", "hyaku-man"),
    )),
    NumberSection("Useful counters", listOf(
        NumberEntry("1 thing", "一つ", "hito-tsu"),
        NumberEntry("2 things", "二つ", "futa-tsu"),
        NumberEntry("3 things", "三つ", "mit-tsu"),
        NumberEntry("4 things", "四つ", "yot-tsu"),
        NumberEntry("5 things", "五つ", "itsu-tsu"),
        NumberEntry("6 things", "六つ", "mut-tsu"),
        NumberEntry("7 things", "七つ", "nana-tsu"),
        NumberEntry("8 things", "八つ", "yat-tsu"),
        NumberEntry("9 things", "九つ", "kokono-tsu"),
        NumberEntry("10 things", "十", "tō"),
    )),
    NumberSection("People counter", listOf(
        NumberEntry("1 person", "一人", "hito-ri"),
        NumberEntry("2 people", "二人", "futa-ri"),
        NumberEntry("3 people", "三人", "san-nin"),
        NumberEntry("4 people", "四人", "yo-nin"),
        NumberEntry("5 people", "五人", "go-nin"),
        NumberEntry("6 people", "六人", "roku-nin"),
        NumberEntry("7 people", "七人", "shichi-nin / nana-nin"),
        NumberEntry("8 people", "八人", "hachi-nin"),
        NumberEntry("9 people", "九人", "kyū-nin / ku-nin"),
        NumberEntry("10 people", "十人", "jū-nin"),
    )),
)

