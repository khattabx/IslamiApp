# Hadethfragment.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Fragment يعرض قائمة الأحاديث النبوية (50 حديث) مع إمكانية النقر على أي حديث لفتح تفاصيله.

---

## تعريف الكلاس (Class Declaration)

### `class Hadethfragment:Fragment()`

```kotlin
class Hadethfragment:Fragment() {
    lateinit var datalist: ArrayList<String>
    lateinit var recyclerView: RecyclerView
    lateinit var hadethAdapter: HadethAdapter
    
    override fun onCreateView(...) { /* ... */ }
    override fun onViewCreated(...) { /* ... */ }
    fun init() { /* ... */ }
    fun creatdata() { /* ... */ }
    fun hadethdetailes(...) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يعرض قائمة بعناوين الأحاديث النبوية (50 حديث) ويسمح بالنقر عليها لفتح محتواها.

---

## المتغيرات (Variables)

### `datalist`

```kotlin
lateinit var datalist: ArrayList<String>
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة عناوين الأحاديث (مثل "الحديث رقم 1"، "الحديث رقم 2"، إلخ).

---

### `recyclerView` و `hadethAdapter`

```kotlin
lateinit var recyclerView: RecyclerView
lateinit var hadethAdapter: HadethAdapter
```

**ما وظيفة هذه المتغيرات؟**

- `recyclerView`: مرجع لـ RecyclerView الذي يعرض قائمة الأحاديث
- `hadethAdapter`: الـ Adapter المسؤول عن ربط البيانات بالـ RecyclerView

---

## الدوال (Functions)

### `onCreateView()`

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.ahadethfragment, container, false)
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى لإنشاء وإرجاع الـ View الخاص بالـ Fragment.

**ما اسم ملف الـ layout المستخدم؟**

`ahadethfragment.xml` - layout Fragment الأحاديث.

---

### `onViewCreated()`

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى بعد إنشاء الـ View، وتقوم باستدعاء دالة init للتهيئة.

---

### `init()`

```kotlin
fun init() {
    recyclerView = requireView().findViewById(R.id.hadethrecyclerview)
    
    creatdata()
    
    hadethAdapter = HadethAdapter(datalist)
    hadethAdapter.hadethnameonclicklistener = object : HadethAdapter.HadethOnClickListener {
        override fun hadethonclick(pos: Int, hadethname: String) {
            hadethdetailes(pos, hadethname)
        }
    }
    recyclerView.adapter = hadethAdapter
}
```

**ما وظيفة هذه الدالة؟**

تقوم بتهيئة جميع المكونات وإعداد الـ listeners.

---

#### تهيئة RecyclerView

```kotlin
recyclerView = requireView().findViewById(R.id.hadethrecyclerview)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ RecyclerView من الـ layout.

---

#### إنشاء البيانات

```kotlin
creatdata()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لإنشاء قائمة عناوين الأحاديث.

---

#### إعداد Adapter

```kotlin
hadethAdapter = HadethAdapter(datalist)
hadethAdapter.hadethnameonclicklistener = object : HadethAdapter.HadethOnClickListener {
    override fun hadethonclick(pos: Int, hadethname: String) {
        hadethdetailes(pos, hadethname)
    }
}
recyclerView.adapter = hadethAdapter
```

**ما وظيفة هذا الكود؟**

1. ينشئ Adapter ويمرر له قائمة العناوين
2. يسجل listener للنقر على الحديث
3. يربط Adapter بـ RecyclerView

**ماذا يحدث عند النقر على حديث؟**

يتم استدعاء دالة `hadethdetailes` لفتح صفحة تفاصيل الحديث.

---

### `creatdata()`

```kotlin
fun creatdata() {
    datalist = arrayListOf()
    for (i in 1..50) {
        datalist.add("  الحديث رقم$i ")
    }
}
```

**ما وظيفة هذه الدالة؟**

تنشئ قائمة بعناوين الأحاديث (من "الحديث رقم 1" إلى "الحديث رقم 50").

**كيف تعمل؟**

1. تنشئ ArrayList فارغة
2. تستخدم حلقة for من 1 إلى 50
3. في كل دورة: تضيف عنوان حديث جديد

**لماذا نستخدم String Template ($i)؟**

لإدراج رقم الحديث داخل النص.

**كم عدد الأحاديث؟**

50 حديث (من 1 إلى 50).

---

### `hadethdetailes()`

```kotlin
fun hadethdetailes(pos: Int, suraname: String) {
    val intent = Intent(context, HadethDetailes::class.java)
    intent.putExtra(Constent.name, suraname)
    intent.putExtra(Constent.pos, pos)
    startActivity(intent)
}
```

**ما وظيفة هذه الدالة؟**

تفتح Activity جديدة لعرض تفاصيل الحديث المختار.

**ما هي المعاملات؟**

- `pos`: موضع الحديث في القائمة
- `suraname`: عنوان الحديث (مثل "الحديث رقم 1")

**لماذا اسم المعامل suraname مع أنه حديث؟**

خطأ في التسمية (تم نسخ الكود من QuranFragment)، لكن الكود يعمل بشكل صحيح.

**ما البيانات المُرسلة؟**

- عنوان الحديث (للعرض)
- موضع الحديث (لقراءة الملف الصحيح)

---

## مقارنة مع QuranFragment

**أوجه التشابه:**
- نفس البنية العامة للكود
- استخدام RecyclerView لعرض القائمة
- استخدام Intent لفتح صفحة التفاصيل

**الاختلافات الرئيسية:**
- لا يوجد تشغيل صوت في Hadethfragment
- البيانات يتم إنشاؤها برمجياً (ليست من API)
- عدد العناصر: 50 حديث مقابل 114 سورة
- أبسط بكثير (لا MediaPlayer ولا API calls)

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `ahadethfragment.xml`: layout هذا الـ Fragment
- `Home.kt`: الـ Activity التي تحتوي على هذا Fragment
- `HadethDetailes.kt`: Activity تفاصيل الحديث
- `HadethAdapter.kt`: Adapter لعرض قائمة الأحاديث
- ملفات Assets (`h1.txt` إلى `h50.txt`): ملفات نصية لمحتوى الأحاديث

---

## الملخص النهائي

هذا Fragment بسيط يعرض قائمة بـ 50 حديث نبوي. عند النقر على أي حديث، يتم فتح صفحة تفاصيل تعرض محتوى الحديث كاملاً. البيانات محلية (لا تحتاج إنترنت) ويتم قراءتها من ملفات نصية في Assets.
