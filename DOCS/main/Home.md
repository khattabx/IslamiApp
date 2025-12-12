# Home.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على الشاشة الرئيسية للتطبيق التي تحتوي على Bottom Navigation Bar للتنقل بين أربعة أقسام: القرآن، الراديو، الأحاديث، والسبحة.

---

## تعريف الكلاس (Class Declaration)

### `class Home : AppCompatActivity()`

```kotlin
class Home : AppCompatActivity() {
    lateinit var bottomnavigation: BottomNavigationView
    var Tasbehfragment = Tasbehfragment()
    var Hadethfragment = Hadethfragment()
    var quranFragment = QuranFragment()
    var radiofragment = Radiofragment()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        bottomnavigation.selectedItemId = R.id.quran
    }
    
    fun init() { /* ... */ }
    fun open_fragment(fragment: Fragment) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يمثل الشاشة الرئيسية للتطبيق، ويدير التنقل بين الـ Fragments المختلفة وإيقاف الصوت عند التبديل بينها.

---

## المتغيرات (Variables)

### `bottomnavigation`

```kotlin
lateinit var bottomnavigation: BottomNavigationView
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ Bottom Navigation View الموجود في layout للتحكم فيه.

**ما معنى lateinit؟**

تعني أن المتغير سيتم تهيئته لاحقاً (في دالة init) وليس عند إنشاء الكلاس.

**لماذا نستخدم lateinit بدلاً من nullable؟**

لأننا متأكدون أننا سنهيئ المتغير قبل استخدامه، فلا حاجة للتعامل مع null في كل مرة.

---

### Fragment Variables

```kotlin
var Tasbehfragment = Tasbehfragment()
var Hadethfragment = Hadethfragment()
var quranFragment = QuranFragment()
var radiofragment = Radiofragment()
```

**ما وظيفة هذه المتغيرات؟**

تحفظ instances من كل fragment لإعادة استخدامها عند التنقل بينها بدلاً من إنشاء instances جديدة في كل مرة.

**لماذا نخزن الـ Fragments كمتغيرات في الكلاس؟**

للحفاظ على حالة كل fragment عند التبديل بينها، مثل استمرار تشغيل الصوت أو الاحتفاظ بالبيانات المحملة.

**ما الفرق بين Tasbehfragment بحرف كبير و radiofragment بحرف صغير؟**

خطأ في التسمية (naming convention)، يجب أن تبدأ المتغيرات بحرف صغير، لكن الكود يعمل رغم ذلك.

---

## الدوال (Functions)

### `onCreate()`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    init()
    bottomnavigation.selectedItemId = R.id.quran
}
```

**ما وظيفة هذه الدالة؟**

يتم استدعاؤها عند إنشاء الـ Activity، وتقوم بإعداد الشاشة وتهيئة المكونات.

---

#### Execution Code

```kotlin
super.onCreate(savedInstanceState)
```

**ما وظيفة هذا السطر؟**

يستدعي onCreate من الكلاس الأب لتنفيذ العمليات الأساسية.

---

```kotlin
setContentView(R.layout.activity_home)
```

**ما وظيفة هذا السطر؟**

يربط الـ Activity بملف layout الخاص بها (activity_home.xml).

---

```kotlin
init()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة init لتهيئة المكونات وإعداد الـ listeners.

---

```kotlin
bottomnavigation.selectedItemId = R.id.quran
```

**ما وظيفة هذا السطر؟**

يحدد أن عنصر القرآن في الـ Bottom Navigation هو المختار افتراضياً عند فتح التطبيق.

**ماذا يحدث عند تنفيذ هذا السطر؟**

يتم تفعيل الـ listener المسجل على bottomnavigation وفتح QuranFragment تلقائياً.

---

### `init()`

```kotlin
fun init() {
    bottomnavigation = findViewById(R.id.buttonnavigation)
    bottomnavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
        // if quran or radio media_player is play on any fragment other than this own , media_player.stop()
        
        if (it.itemId == R.id.quran) {
            open_fragment(quranFragment)
            if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
            
        } else if (it.itemId == R.id.radio) {
            open_fragment(radiofragment)
            if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
            
        } else if (it.itemId == R.id.hadeth) {
            open_fragment(Hadethfragment)
            if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
            else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
            
        } else if (it.itemId == R.id.sebha) {
            open_fragment(Tasbehfragment)
            if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
            else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
        }
        return@OnItemSelectedListener true
    })
}
```

**ما وظيفة هذه الدالة؟**

تقوم بتهيئة الـ Bottom Navigation وتسجيل listener للتعامل مع نقرات المستخدم على العناصر المختلفة.

---

#### Bottom Navigation Initialization

```kotlin
bottomnavigation = findViewById(R.id.buttonnavigation)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ BottomNavigationView من الـ layout ويحفظه في المتغير.

**ما معنى findViewById؟**

دالة تبحث عن view في الـ layout باستخدام ID معين.

---

#### OnItemSelectedListener

```kotlin
bottomnavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
    // code here
    return@OnItemSelectedListener true
})
```

**ما وظيفة هذا الكود؟**

يسجل listener يتم استدعاؤه عندما يختار المستخدم عنصر من الـ Bottom Navigation.

**ما معنى it.itemId؟**

`it` يمثل الـ MenuItem الذي تم اختياره، و `itemId` هو الـ ID الخاص به.

**ما معنى return@OnItemSelectedListener true؟**

يعيد true ليخبر النظام أن الحدث تم التعامل معه بنجاح، فيتم تحديث UI لإظهار العنصر كمختار.

---

#### Navigation Logic

**حالة القرآن (R.id.quran):**

```kotlin
if (it.itemId == R.id.quran) {
    open_fragment(quranFragment)
    if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
}
```

**ما الذي يحدث؟**

- يفتح fragment القرآن
- يتحقق إذا كان الراديو يعمل، يوقفه

**لماذا نوقف الراديو فقط وليس أي صوت آخر؟**

لأن fragments الأحاديث والسبحة لا تحتوي على مشغلات صوت.

---

**حالة الراديو (R.id.radio):**

```kotlin
else if (it.itemId == R.id.radio) {
    open_fragment(radiofragment)
    if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
}
```

**ما الذي يحدث؟**

- يفتح fragment الراديو
- يتحقق إذا كان القرآن يعمل، يوقفه

---

**حالة الأحاديث (R.id.hadeth):**

```kotlin
else if (it.itemId == R.id.hadeth) {
    open_fragment(Hadethfragment)
    if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
    else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
}
```

**ما الذي يحدث؟**

- يفتح fragment الأحاديث
- يتحقق من القرآن أو الراديو إذا كان يعمل، يوقفه

**لماذا نستخدم else if للتحقق من الراديو؟**

لأنه من المستحيل أن يعمل القرآن والراديو في نفس الوقت (الكود يمنع ذلك)، فإذا كان القرآن يعمل لا حاجة للتحقق من الراديو.

---

**حالة السبحة (R.id.sebha):**

```kotlin
else if (it.itemId == R.id.sebha) {
    open_fragment(Tasbehfragment)
    if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
    else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()
}
```

**ما الذي يحدث؟**

- يفتح fragment السبحة
- يتحقق من القرآن أو الراديو إذا كان يعمل، يوقفه

---

### `open_fragment()`

```kotlin
fun open_fragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.homefragment, fragment).commit()
}
```

**ما وظيفة هذه الدالة؟**

تفتح Fragment معين وتعرضه في المكان المخصص له في الـ layout.

**ما هي المعاملات؟**

`fragment: Fragment` - الـ Fragment الذي نريد عرضه.

---

#### Fragment Transaction

```kotlin
supportFragmentManager.beginTransaction().replace(R.id.homefragment, fragment).commit()
```

**ما وظيفة هذا السطر؟**

يقوم بعملية transaction لاستبدال Fragment الحالي بـ Fragment جديد.

**ما هو supportFragmentManager؟**

كلاس يدير الـ Fragments في الـ Activity.

**ما معنى beginTransaction()؟**

يبدأ عملية transaction جديدة لتعديل الـ Fragments.

**ما معنى replace(R.id.homefragment, fragment)؟**

يستبدل Fragment الموجود في container بـ ID "homefragment" بالـ Fragment الجديد.

**ما معنى commit()؟**

يحفظ التغييرات وينفذ الـ transaction.

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `activity_home.xml`: layout الشاشة الرئيسية
- `QuranFragment.kt`: fragment القرآن
- `Hadethfragment.kt`: fragment الأحاديث
- `Radiofragment.kt`: fragment الراديو
- `Tasbehfragment.kt`: fragment السبحة

---

## الملخص النهائي

هذا الملف يدير الشاشة الرئيسية للتطبيق ويتحكم في التنقل بين أربعة أقسام رئيسية. أهم ميزة فيه هي إدارة تشغيل الصوت بحيث يتم إيقاف أي صوت عند الانتقال إلى fragment آخر لمنع تشغيل أكثر من صوت في نفس الوقت.
