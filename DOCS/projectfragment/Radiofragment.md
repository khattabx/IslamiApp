# Radiofragment.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Fragment يشغّل محطات الراديو الإسلامية مع إمكانية التنقل بين المحطات المختلفة.

---

## تعريف الكلاس (Class Declaration)

### `class Radiofragment:Fragment()`

```kotlin
class Radiofragment:Fragment() {
    lateinit var radio_text: TextView
    lateinit var buttonStop: ImageButton
    lateinit var buttonnext: ImageButton
    lateinit var buttonprev: ImageButton
    lateinit var progressBar: ProgressBar
    var radio_item: List<RadiosItem?>? = null
    var position = 0
    var radio_mediaPlayer = MediaPlayer()
    var is_play = 0
    
    override fun onCreateView(...) { /* ... */ }
    override fun onViewCreated(...) { /* ... */ }
    fun init() { /* ... */ }
    fun get_radio_api() { /* ... */ }
    fun play_sound(position: Int) { /* ... */ }
    fun isNetworkConnected(): Boolean { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يشغّل محطات الراديو الإسلامية من الإنترنت مع إمكانية التحكم (تشغيل/إيقاف/التالي/السابق).

---

## المتغيرات (Variables)

### UI Components

```kotlin
lateinit var radio_text: TextView
lateinit var buttonStop: ImageButton
lateinit var buttonnext: ImageButton
lateinit var buttonprev: ImageButton
lateinit var progressBar: ProgressBar
```

**ما وظيفة هذه المتغيرات؟**

- `radio_text`: يعرض اسم المحطة الحالية
- `buttonStop`: زر تشغيل/إيقاف الراديو
- `buttonnext`: زر الانتقال للمحطة التالية
- `buttonprev`: زر الرجوع للمحطة السابقة
- `progressBar`: شريط تحميل يظهر أثناء تحضير الصوت

---

### Data Variables

```kotlin
var radio_item: List<RadiosItem?>? = null
var position = 0
var radio_mediaPlayer = MediaPlayer()
var is_play = 0
```

**ما وظيفة هذه المتغيرات؟**

- `radio_item`: قائمة محطات الراديو من API
- `position`: موضع المحطة الحالية
- `radio_mediaPlayer`: مشغل الصوت
- `is_play`: حالة التشغيل (0=متوقف، 1=يشتغل)

**لماذا radio_mediaPlayer ليس lateinit؟**

لأنه يتم تهيئته مباشرة عند الإعلان عنه.

**لماذا radio_mediaPlayer public؟**

لأن Home Activity تحتاج الوصول إليه لإيقاف الصوت عند التبديل.

---

## الدوال (Functions)

### `onCreateView()`

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.radiofragment, container, false)
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى لإنشاء وإرجاع الـ View الخاص بالـ Fragment.

---

### `onViewCreated()`

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
    progressBar.isVisible = false
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى بعد إنشاء الـ View، وتقوم بالتهيئة وإخفاء شريط التحميل.

---

### `init()`

```kotlin
fun init() {
    get_radio_api()
    
    radio_text = requireView().findViewById(R.id.ezaa)
    progressBar = requireView().findViewById(R.id.progress)
    buttonStop = requireView().findViewById(R.id.play)
    buttonnext = requireView().findViewById(R.id.next)
    buttonprev = requireView().findViewById(R.id.prev)
    
    if (!isNetworkConnected() && !(radio_mediaPlayer.isPlaying)) progressBar.isVisible = true
    
    buttonStop.setOnClickListener { /* ... */ }
    buttonnext.setOnClickListener { /* ... */ }
    buttonprev.setOnClickListener { /* ... */ }
}
```

**ما وظيفة هذه الدالة؟**

تقوم بتهيئة جميع المكونات وإعداد الـ listeners للأزرار.

---

#### جلب بيانات المحطات

```kotlin
get_radio_api()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لجلب قائمة محطات الراديو من API.

---

#### تهيئة Views

```kotlin
radio_text = requireView().findViewById(R.id.ezaa)
progressBar = requireView().findViewById(R.id.progress)
buttonStop = requireView().findViewById(R.id.play)
buttonnext = requireView().findViewById(R.id.next)
buttonprev = requireView().findViewById(R.id.prev)
```

**ما وظيفة هذه الأسطر؟**

تحصل على مراجع لجميع الـ Views من الـ layout.

---

#### التحقق من الاتصال

```kotlin
if (!isNetworkConnected() && !(radio_mediaPlayer.isPlaying)) progressBar.isVisible = true
```

**ما وظيفة هذا السطر؟**

إذا لم يكن هناك اتصال ولا يوجد صوت يشتغل، يظهر شريط التحميل.

---

#### زر التشغيل/الإيقاف

```kotlin
buttonStop.setOnClickListener {
    if (isNetworkConnected()) {
        radio_text.text = radio_item?.get(position)?.name
        
        if (is_play == 0) {
            play_sound(position)
            buttonStop.setImageResource(R.drawable.ic_pause_radio)
            is_play = 1
        } else {
            progressBar.isVisible = false
            radio_mediaPlayer.stop()
            buttonStop.setImageResource(R.drawable.ic_play_radio)
            is_play = 0
        }
    } else {
        progressBar.isVisible = true
    }
}
```

**ما وظيفة هذا الكود؟**

يتعامل مع نقرات زر التشغيل/الإيقاف.

**ما الذي يحدث عند النقر؟**

1. يتحقق من وجود اتصال
2. يعرض اسم المحطة
3. إذا كان متوقفاً: يشغل الراديو ويغير الأيقونة
4. إذا كان يشتغل: يوقفه ويغير الأيقونة
5. إذا لا يوجد اتصال: يظهر شريط التحميل

---

#### زر التالي

```kotlin
buttonnext.setOnClickListener {
    if (isNetworkConnected()) {
        position++
        
        radio_text.text = radio_item?.get(position)?.name
        
        if (radio_mediaPlayer.isPlaying) {
            radio_mediaPlayer.stop()
        }
        play_sound(position)
    }
}
```

**ما وظيفة هذا الكود؟**

ينتقل للمحطة التالية.

**ما الخطوات؟**

1. يزيد الموضع بـ 1
2. يعرض اسم المحطة الجديدة
3. إذا كان هناك صوت يشتغل: يوقفه
4. يشغل المحطة الجديدة

**ما المشكلة في هذا الكود؟**

لا يتحقق من تجاوز حدود القائمة (قد يحدث IndexOutOfBoundsException إذا وصل لآخر محطة).

---

#### زر السابق

```kotlin
buttonprev.setOnClickListener {
    if (isNetworkConnected()) {
        position--
        
        //first audio
        if (position < 0) {
            radio_mediaPlayer.stop()
        } else {
            if (radio_mediaPlayer.isPlaying) radio_mediaPlayer.stop()
            radio_text.text = radio_item?.get(position)?.name
            play_sound(position)
        }
    }
}
```

**ما وظيفة هذا الكود؟**

يرجع للمحطة السابقة.

**ما الذي يحدث؟**

1. يقلل الموضع بـ 1
2. إذا أصبح سالب (قبل أول محطة): يوقف الصوت فقط
3. إذا لا: يوقف الصوت القديم ويشغل المحطة السابقة

**لماذا التعامل مع position < 0 مختلف؟**

لأنه لا توجد محطة قبل الأولى، فيوقف الصوت فقط.

---

### `get_radio_api()`

```kotlin
fun get_radio_api(){
    BuildRetrofit.get_api(Constant.radio_base_url).get_radio()
        .enqueue(object : Callback<RadioResponse> {
            override fun onResponse(
                call: Call<RadioResponse>,
                response: Response<RadioResponse>
            ) {
                radio_item = response.body()?.radios
            }
            
            override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
            }
        })
}
```

**ما وظيفة هذه الدالة؟**

تجلب قائمة محطات الراديو من API.

**كيف تعمل؟**

1. تنشئ API call باستخدام Retrofit
2. عند النجاح: تحفظ البيانات في `radio_item`
3. عند الفشل: لا تفعل شيء

---

### `play_sound()`

```kotlin
fun play_sound(position: Int) {
    progressBar.isVisible = true
    val url = radio_item?.get(position)?.uRL
    radio_mediaPlayer = MediaPlayer().apply {
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
    radio_mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            progressBar.isVisible = false
            mp?.start()
        }
    })
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإعداد وتشغيل محطة راديو.

**ما هي المعاملات؟**

`position: Int` - موضع المحطة في القائمة.

**كيف تعمل؟**

1. يظهر شريط التحميل
2. يحصل على رابط المحطة
3. ينشئ MediaPlayer جديد ويعده
4. عند الجاهزية: يخفي شريط التحميل ويشغل الصوت

**ما الفرق بين هذه و quran_sound في QuranFragment؟**

نفس الطريقة تماماً، فقط البيانات مختلفة (محطة راديو مقابل سورة قرآن).

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

تتحقق من وجود اتصال WiFi.

**نفس التحقق في QuranFragment؟**

نعم، نفس الكود تماماً.

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `radiofragment.xml`: layout هذا الـ Fragment
- `Home.kt`: الـ Activity التي تحتوي على هذا Fragment
- `BuildRetrofit.kt` و `ApiServices.kt`: للاتصال بالـ API
- `RadioResponse.kt`: نموذج البيانات للمحطات

---

## الملخص النهائي

هذا Fragment يشغّل محطات راديو إسلامية من الإنترنت. يجلب قائمة المحطات من API ويوفر واجهة بسيطة للتحكم (تشغيل/إيقاف/التالي/السابق). يتطلب اتصال بالإنترنت للعمل، ويتم إيقافه تلقائياً عند التبديل لـ fragment آخر.
