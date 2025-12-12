# SuraDetailes.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Activity تعرض تفاصيل السورة المختارة، حيث يظهر اسم السورة وجميع آياتها في RecyclerView.

---

## تعريف الكلاس (Class Declaration)

### `class SuraDetailes : AppCompatActivity()`

```kotlin
class SuraDetailes : AppCompatActivity() {
    lateinit var suratitle:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var ayatadapter: AyatquranAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_detailes)
        init()
    }
    
    fun init() { /* ... */ }
    fun setrecyclerview() { /* ... */ }
    fun readsuracontentfromassets(pos: Int) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يعرض محتوى سورة قرآنية كاملة (جميع الآيات) بعد اختيار السورة من القائمة الرئيسية.

---

## المتغيرات (Variables)

### `suratitle`

```kotlin
lateinit var suratitle:TextView
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ TextView الذي يعرض اسم السورة في أعلى الشاشة.

---

### `recyclerView`

```kotlin
lateinit var recyclerView: RecyclerView
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ RecyclerView الذي يعرض قائمة الآيات.

---

### `ayatadapter`

```kotlin
lateinit var ayatadapter: AyatquranAdapter
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ Adapter المسؤول عن ربط بيانات الآيات بالـ RecyclerView.

---

## الدوال (Functions)

### `onCreate()`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sura_detailes)
    init()
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

يستدعي onCreate من الكلاس الأب.

---

```kotlin
setContentView(R.layout.activity_sura_detailes)
```

**ما وظيفة هذا السطر؟**

يربط الـ Activity بملف layout الخاص بها (activity_sura_detailes.xml).

---

```kotlin
init()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة init لتهيئة المكونات وقراءة بيانات السورة.

---

### `init()`

```kotlin
fun init() {
    suratitle = findViewById(R.id.suradetailes)
    setrecyclerview()
    
    // receive sura_name to set on title in fragment that is details of ayat_quran
    val posofsura = intent.getIntExtra(Constent.pos, -1)
    val titleofsura = intent.getStringExtra(Constent.name)
    suratitle.text = titleofsura
    readsuracontentfromassets(posofsura)
}
```

**ما وظيفة هذه الدالة؟**

تقوم بتهيئة جميع المكونات واستقبال البيانات من الـ Activity السابقة وعرضها.

---

#### Initialization

```kotlin
suratitle = findViewById(R.id.suradetailes)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ TextView الخاص بعنوان السورة.

---

```kotlin
setrecyclerview()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لتهيئة RecyclerView و Adapter الخاص بها.

---

#### Receiving Intent Data

```kotlin
val posofsura = intent.getIntExtra(Constent.pos, -1)
```

**ما وظيفة هذا السطر؟**

يستقبل رقم موضع السورة (index) من الـ Intent المُرسل من Activity السابقة.

**ما معنى getIntExtra؟**

دالة تستخرج قيمة من نوع Int من الـ Intent.

**ما هي المعاملات؟**

1. `Constent.pos`: المفتاح (key) الذي تم حفظ القيمة به
2. `-1`: القيمة الافتراضية إذا لم يتم العثور على البيانات

**لماذا القيمة الافتراضية -1؟**

لأن أرقام السور تبدأ من 0، فـ -1 يدل على خطأ أو عدم وجود بيانات.

---

```kotlin
val titleofsura = intent.getStringExtra(Constent.name)
```

**ما وظيفة هذا السطر؟**

يستقبل اسم السورة من الـ Intent.

**ما معنى getStringExtra؟**

دالة تستخرج قيمة من نوع String من الـ Intent.

**لماذا النوع nullable (String?)؟**

لأن القيمة قد لا تكون موجودة في الـ Intent.

---

```kotlin
suratitle.text = titleofsura
```

**ما وظيفة هذا السطر؟**

يعين اسم السورة المُستقبل إلى TextView ليتم عرضه على الشاشة.

---

```kotlin
readsuracontentfromassets(posofsura)
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لقراءة محتوى السورة (الآيات) من ملفات Assets.

---

### `setrecyclerview()`

```kotlin
fun setrecyclerview() {
    recyclerView = findViewById(R.id.recycleOfAyatAlquran)
    ayatadapter = AyatquranAdapter()
    recyclerView.adapter = ayatadapter
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإعداد RecyclerView وربطه بـ Adapter.

---

#### Setup Code

```kotlin
recyclerView = findViewById(R.id.recycleOfAyatAlquran)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ RecyclerView من الـ layout.

---

```kotlin
ayatadapter = AyatquranAdapter()
```

**ما وظيفة هذا السطر؟**

ينشئ instance جديد من AyatquranAdapter.

---

```kotlin
recyclerView.adapter = ayatadapter
```

**ما وظيفة هذا السطر؟**

يربط الـ Adapter بالـ RecyclerView ليعرض البيانات.

---

### `readsuracontentfromassets()`

```kotlin
fun readsuracontentfromassets(pos: Int) {
    val filename = "${pos + 1}.txt"
    val filecontent = assets.open(filename).bufferedReader().use { it.readText() }
    val datalist = filecontent.split("\n")
    ayatadapter.changedata(datalist)
}
```

**ما وظيفة هذه الدالة؟**

تقرأ محتوى السورة من ملف نصي في Assets وتحوله إلى قائمة آيات.

**ما هي المعاملات؟**

`pos: Int` - رقم موضع السورة (index).

---

#### File Reading

```kotlin
val filename = "${pos + 1}.txt"
```

**ما وظيفة هذا السطر؟**

ينشئ اسم الملف بناءً على موضع السورة.

**لماذا pos + 1؟**

لأن الموضع يبدأ من 0 (الفاتحة = 0)، لكن أسماء الملفات تبدأ من 1 (1.txt = الفاتحة).

**ما هو String Template (${})?**

طريقة لإدراج متغيرات أو تعبيرات داخل النصوص في Kotlin.

---

```kotlin
val filecontent = assets.open(filename).bufferedReader().use { it.readText() }
```

**ما وظيفة هذا السطر؟**

يفتح الملف من Assets ويقرأ محتواه كاملاً كـ String.

**ما هو assets؟**

مجلد خاص في التطبيق يحتوي على ملفات لا يتم معالجتها أثناء البناء.

**ما معنى open(filename)؟**

يفتح الملف ويعيد InputStream.

**ما معنى bufferedReader()؟**

يحول InputStream إلى BufferedReader لقراءة النص بكفاءة.

**ما معنى use { }؟**

يضمن إغلاق الملف تلقائياً بعد الانتهاء من القراءة (يمنع memory leaks).

**ما معنى it.readText()؟**

يقرأ كل محتوى الملف كـ String واحد.

---

```kotlin
val datalist = filecontent.split("\n")
```

**ما وظيفة هذا السطر؟**

يقسم النص إلى قائمة من الآيات باستخدام سطر جديد كفاصل.

**ما معنى split("\n")؟**

يقسم String عند كل سطر جديد (new line) ويعيد List من Strings.

**لماذا نستخدم "\n"؟**

لأن كل آية في الملف النصي موجودة في سطر منفصل.

---

```kotlin
ayatadapter.changedata(datalist)
```

**ما وظيفة هذا السطر؟**

يمرر قائمة الآيات إلى الـ Adapter ليعرضها في RecyclerView.

**ماذا يحدث داخل changedata؟**

يتم تحديث البيانات في الـ Adapter واستدعاء notifyDataSetChanged() لإعادة رسم RecyclerView.

---

## تدفق البيانات (Data Flow)

1. المستخدم يختار سورة من QuranFragment
2. يتم إنشاء Intent يحتوي على:
   - موضع السورة (pos)
   - اسم السورة (name)
3. يتم فتح SuraDetailes Activity
4. يتم استقبال البيانات من Intent
5. يتم عرض اسم السورة في TextView
6. يتم قراءة محتوى السورة من Assets
7. يتم عرض الآيات في RecyclerView

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `activity_sura_detailes.xml`: layout هذه الشاشة
- `QuranFragment.kt`: الـ fragment الذي يرسل Intent لفتح هذه الشاشة
- `AyatquranAdapter.kt`: الـ Adapter الذي يعرض الآيات
- ملفات Assets (`1.txt` إلى `114.txt`): ملفات نصية تحتوي على آيات كل سورة

---

## الملخص النهائي

هذا الملف يعرض تفاصيل سورة مختارة من القرآن الكريم، حيث يستقبل رقم واسم السورة من Activity السابقة، ثم يقرأ محتوى السورة من ملف نصي في Assets ويعرض جميع الآيات في RecyclerView.
