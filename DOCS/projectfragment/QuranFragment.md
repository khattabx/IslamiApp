# QuranFragment.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Fragment يعرض قائمة سور القرآن الكريم مع إمكانية تشغيل صوت كل سورة أو فتح تفاصيلها لقراءة الآيات.

---

## تعريف الكلاس (Class Declaration)

### `class QuranFragment: Fragment()`

```kotlin
class QuranFragment: Fragment() {
    var quran_mediaPlayer = MediaPlayer()
    lateinit var recyclerview: RecyclerView
    lateinit var quranadapter: QranAdapter
    var quran_list: List<DataItem?>? = null
    var is_play = 0
    
    val datalist = arrayListOf( /* أسماء السور */ )
    
    override fun onCreateView(...) { /* ... */ }
    override fun onViewCreated(...) { /* ... */ }
    fun init() { /* ... */ }
    fun suradetailes(...) { /* ... */ }
    fun get_quran_audio() { /* ... */ }
    fun quran_sound(...) { /* ... */ }
    fun isNetworkConnected(): Boolean { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يعرض قائمة سور القرآن الكريم (114 سورة) مع إمكانية تشغيل تلاوة كل سورة أو فتح صفحة لقراءة الآيات.

---

## المتغيرات (Variables)

### `quran_mediaPlayer`

```kotlin
var quran_mediaPlayer = MediaPlayer()
```

**ما وظيفة هذا المتغير؟**

يحفظ MediaPlayer الخاص بتشغيل تلاوة القرآن الكريم.

**لماذا هو public (بدون private)؟**

لأن Home Activity تحتاج الوصول إليه لإيقاف الصوت عند التبديل بين Fragments.

---

### `recyclerview` و `quranadapter`

```kotlin
lateinit var recyclerview: RecyclerView
lateinit var quranadapter: QranAdapter
```

**ما وظيفة هذه المتغيرات؟**

- `recyclerview`: مرجع لـ RecyclerView الذي يعرض قائمة السور
- `quranadapter`: الـ Adapter المسؤول عن ربط البيانات بالـ RecyclerView

---

### `quran_list`

```kotlin
var quran_list: List<DataItem?>? = null
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة معلومات السور التي يتم جلبها من API (تحتوي على روابط التلاوة وغيرها).

**لماذا nullable؟**

لأن البيانات قد لا تكون متاحة بعد (يتم جلبها من API).

---

### `is_play`

```kotlin
var is_play = 0
```

**ما وظيفة هذا المتغير؟**

يتتبع حالة التشغيل (0 = متوقف، 1 = يشتغل).

**لماذا Int وليس Boolean؟**

يمكن استخدام Boolean بدلاً منه، لكن المطور اختار Int (0 و 1).

---

### `datalist`

```kotlin
val datalist = arrayListOf(
    "الفاتحه",
    "البقرة",
    "آل عمران",
    // ... باقي أسماء السور (114 سورة)
    "الناس"
)
```

**ما وظيفة هذا المتغير؟**

يحتوي على أسماء جميع سور القرآن الكريم بالترتيب (من الفاتحة إلى الناس).

**لماذا نحفظ الأسماء في الكود وليس في API؟**

لأن الأسماء ثابتة ولا تتغير، وحفظها محلياً يسرع عرض القائمة ولا يتطلب انتظار API.

**ما الفرق بين val و var؟**

- `val`: قيمة ثابتة لا يمكن تغييرها (immutable)
- `var`: قيمة متغيرة يمكن تغييرها (mutable)

---

## الدوال (Functions)

### `onCreateView()`

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.quranfragment, container, false)
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى لإنشاء وإرجاع الـ View الخاص بالـ Fragment.

**ما هي المعاملات؟**

- `inflater: LayoutInflater`: أداة لتحويل XML إلى View objects
- `container: ViewGroup?`: الـ parent view الذي سيحتوي على هذا Fragment
- `savedInstanceState: Bundle?`: الحالة المحفوظة سابقاً (إن وجدت)

**ما معنى inflate؟**

يحول ملف XML layout إلى View object يمكن عرضه.

**لماذا المعامل الثالث false؟**

`false` يعني لا تضف الـ View للـ container الآن (سيتم ذلك تلقائياً لاحقاً).

---

### `onViewCreated()`

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى بعد إنشاء الـ View وجاهزيته، وهنا نهيئ المكونات.

**لماذا نستخدم onViewCreated بدلاً من onCreateView؟**

- `onCreateView`: ينشئ View
- `onViewCreated`: يتم استدعاؤه بعد إنشاء View ليتم تهيئة المكونات الداخلية

---

### `init()`

```kotlin
fun init() {
    get_quran_audio()
    
    recyclerview = requireView().findViewById(R.id.quranrecycler)
    quranadapter = QranAdapter(datalist)
    
    // Callback للنقر على اسم السورة
    quranadapter.suranameonclick = object : QranAdapter.Suranameclicklistener {
        override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
            suradetailes(pos, suraname)
        }
    }
    recyclerview.adapter = quranadapter
    
    // Callback لتشغيل الصوت
    quranadapter.quran_playclick = object : QranAdapter.Suranameclicklistener {
        override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
            if (isNetworkConnected()) {
                if (quran_mediaPlayer.isPlaying || is_play == 1) {
                    quranProgress.isVisible = false
                    quranPlay.setImageResource(R.drawable.ic_play_radio)
                    quran_mediaPlayer.stop()
                    is_play = 0
                } else {
                    quranPlay.setImageResource(R.drawable.ic_pause_radio)
                    quran_sound(pos, quranProgress)
                    is_play = 1
                }
            } else {
                quranProgress.isVisible = true
            }
        }
    }
}
```

**ما وظيفة هذه الدالة؟**

تقوم بتهيئة جميع المكونات وإعداد الـ listeners للتفاعل مع المستخدم.

---

#### استدعاء API

```kotlin
get_quran_audio()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لجلب معلومات السور (بما فيها روابط التلاوة) من API.

---

#### تهيئة RecyclerView

```kotlin
recyclerview = requireView().findViewById(R.id.quranrecycler)
quranadapter = QranAdapter(datalist)
```

**ما معنى requireView()؟**

دالة تُرجع الـ View الخاص بالـ Fragment، وترمي exception إذا كان null (لضمان أن View موجود).

**لماذا نمرر datalist لـ QranAdapter؟**

لأن Adapter يحتاج قائمة الأسماء لعرضها في RecyclerView.

---

#### Listener للنقر على اسم السورة

```kotlin
quranadapter.suranameonclick = object : QranAdapter.Suranameclicklistener {
    override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
        suradetailes(pos, suraname)
    }
}
```

**ما وظيفة هذا الكود؟**

يسجل listener يتم استدعاؤه عند النقر على اسم سورة، فيفتح صفحة التفاصيل.

**ما معنى object : Interface؟**

ينشئ anonymous class يطبق الواجهة المحددة.

**ما المعاملات التي يستقبلها الـ callback؟**

- `pos`: موضع السورة في القائمة
- `suraname`: اسم السورة
- `quranPlay`: زر التشغيل (لا يُستخدم هنا)
- `quranProgress`: شريط التحميل (لا يُستخدم هنا)

---

#### Listener لزر التشغيل

```kotlin
quranadapter.quran_playclick = object : QranAdapter.Suranameclicklistener {
    override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
        if (isNetworkConnected()) {
            if (quran_mediaPlayer.isPlaying || is_play == 1) {
                // إيقاف التشغيل
                quranProgress.isVisible = false
                quranPlay.setImageResource(R.drawable.ic_play_radio)
                quran_mediaPlayer.stop()
                is_play = 0
            } else {
                // بدء التشغيل
                quranPlay.setImageResource(R.drawable.ic_pause_radio)
                quran_sound(pos, quranProgress)
                is_play = 1
            }
        } else {
            quranProgress.isVisible = true
        }
    }
}
```

**ما وظيفة هذا الكود؟**

يسجل listener للتعامل مع نقرات زر التشغيل/الإيقاف.

**ما الذي يحدث عند النقر على زر التشغيل؟**

1. يتحقق من وجود اتصال بالإنترنت
2. إذا كان الصوت يشتغل: يوقفه ويغير الأيقونة لـ Play
3. إذا كان متوقفاً: يشغله ويغير الأيقونة لـ Pause
4. إذا لا يوجد إنترنت: يظهر شريط التحميل

**لماذا نتحقق من isPlaying و is_play معاً؟**

للتأكد من الحالة بطريقتين (double check) لتجنب أخطاء التشغيل.

---

### `suradetailes()`

```kotlin
fun suradetailes(pos: Int, suraname: String) {
    val intent = Intent(context, SuraDetailes::class.java)
    intent.putExtra(Constent.name, suraname)
    intent.putExtra(Constent.pos, pos)
    startActivity(intent)
}
```

**ما وظيفة هذه الدالة؟**

تفتح Activity جديدة لعرض تفاصيل السورة (الآيات).

**ما هي المعاملات؟**

- `pos`: موضع السورة
- `suraname`: اسم السورة

**ما معنى putExtra؟**

يضيف بيانات إلى Intent لإرسالها للـ Activity الجديدة.

---

### `get_quran_audio()`

```kotlin
fun get_quran_audio() {
    BuildRetrofit.get_api(Constant.quran_base_url).get_quran_audio()
        .enqueue(object : Callback<QuranResponse> {
            override fun onResponse(call: Call<QuranResponse>, response: Response<QuranResponse>) {
                quran_list = response.body()?.data
            }
            
            override fun onFailure(call: Call<QuranResponse>, t: Throwable) {
            }
        })
}
```

**ما وظيفة هذه الدالة؟**

تجلب معلومات السور (بما فيها روابط التلاوة) من API.

**كيف تعمل؟**

1. تنشئ API call باستخدام Retrofit
2. تستدعي `.enqueue()` للتنفيذ بشكل asynchronous
3. عند النجاح: تحفظ البيانات في `quran_list`
4. عند الفشل: لا تفعل شيء (يمكن إضافة error handling)

**لماذا لا نتعامل مع الفشل؟**

خطأ في التصميم، يجب إضافة معالجة للأخطاء (مثل عرض رسالة للمستخدم).

---

### `quran_sound()`

```kotlin
fun quran_sound(position: Int, quranProgress: ProgressBar) {
    quranProgress.isVisible = true
    val url = quran_list?.get(position)?.recitation?.full
    quran_mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        try {
            setDataSource(url.toString())
            prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    quran_mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            quranProgress.isVisible = false
            mp?.start()
        }
    })
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإعداد وتشغيل صوت تلاوة السورة.

**ما هي المعاملات؟**

- `position`: موضع السورة
- `quranProgress`: شريط التحميل

---

#### إظهار شريط التحميل

```kotlin
quranProgress.isVisible = true
```

**ما وظيفة هذا السطر؟**

يظهر شريط التحميل أثناء تحضير الملف الصوتي.

---

#### الحصول على رابط الصوت

```kotlin
val url = quran_list?.get(position)?.recitation?.full
```

**ما وظيفة هذا السطر؟**

يحصل على رابط التلاوة من البيانات المجلوبة من API.

**ما معنى السلسلة من ?. ؟**

Safe calls - إذا كانت أي قيمة null، تعيد الكل null بدلاً من رمي exception.

---

#### إعداد MediaPlayer

```kotlin
quran_mediaPlayer = MediaPlayer().apply {
    setAudioAttributes(...)
    try {
        setDataSource(url.toString())
        prepareAsync()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
```

**ما وظيفة هذا الكود؟**

ينشئ MediaPlayer جديد ويعده لتشغيل الصوت.

**ما معنى apply { }؟**

scope function يسمح بتنفيذ عدة عمليات على object واحد بشكل مبسط.

**ما معنى setAudioAttributes؟**

يحدد نوع الصوت (موسيقى/ميديا) للنظام ليتعامل معه بشكل صحيح.

**ما معنى prepareAsync()؟**

يحضّر الملف الصوتي بشكل asynchronous (في الخلفية) لتجنب تجميد الشاشة.

**لماذا نستخدم try-catch؟**

لمعالجة الأخطاء التي قد تحدث عند تحميل الصوت (مثل رابط خاطئ أو مشكلة في الشبكة).

---

#### Listener للجاهزية

```kotlin
quran_mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
    override fun onPrepared(mp: MediaPlayer?) {
        quranProgress.isVisible = false
        mp?.start()
    }
})
```

**ما وظيفة هذا الكود؟**

يسجل listener يُستدعى عندما يكون الصوت جاهزاً للتشغيل.

**ما الذي يحدث عند الجاهزية؟**

1. يخفي شريط التحميل
2. يبدأ تشغيل الصوت

---

### `isNetworkConnected()`

```kotlin
fun isNetworkConnected(): Boolean {
    val wifi_manager = requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wifi_is_enabled = wifi_manager.isWifiEnabled
    
    if (!wifi_is_enabled) {
        Toast.makeText(context, "للاسف ليس لديك اتصال بالانترنت", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}
```

**ما وظيفة هذه الدالة؟**

تتحقق من وجود اتصال بالإنترنت (WiFi).

**كيف تعمل؟**

1. تحصل على WifiManager من النظام
2. تتحقق إذا كان WiFi مفعّل
3. إذا لم يكن مفعّل: تعرض رسالة وتعيد false
4. إذا كان مفعّل: تعيد true

**ما المشكلة في هذا التحقق؟**

يتحقق فقط من تفعيل WiFi، لا من الاتصال الفعلي بالإنترنت. قد يكون WiFi مفعّل لكن غير متصل.

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `quranfragment.xml`: layout هذا الـ Fragment
- `Home.kt`: الـ Activity التي تحتوي على هذا Fragment
- `SuraDetailes.kt`: Activity تفاصيل السورة
- `QranAdapter.kt`: Adapter لعرض قائمة السور
- `BuildRetrofit.kt` و `ApiServices.kt`: للاتصال بالـ API

---

## الملخص النهائي

هذا Fragment يعرض قائمة سور القرآن الكريم (114 سورة) مع إمكانيات:
1. عرض أسماء جميع السور
2. فتح تفاصيل أي سورة لقراءة آياتها
3. تشغيل تلاوة أي سورة من الإنترنت
4. إيقاف التلاوة عند التبديل لـ fragment آخر
5. التحقق من الاتصال بالإنترنت قبل التشغيل
