# QranAdapter.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Adapter مسؤول عن عرض قائمة سور القرآن الكريم في RecyclerView مع زر تشغيل لكل سورة.

---

## تعريف الكلاس (Class Declaration)

### `class QranAdapter:RecyclerView.Adapter<QranAdapter.QranViewHolder>`

```kotlin
class QranAdapter:RecyclerView.Adapter<QranAdapter.QranViewHolder> {
    var datalist: ArrayList<String>
    
    constructor(datalist: ArrayList<String>) : super() {
        this.datalist = datalist
    }
    
    class QranViewHolder : RecyclerView.ViewHolder { /* ... */ }
    override fun onCreateViewHolder(...) { /* ... */ }
    override fun getItemCount(): Int { /* ... */ }
    override fun onBindViewHolder(...) { /* ... */ }
    
    var suranameonclick: Suranameclicklistener? = null
    var quran_playclick: Suranameclicklistener? = null
    interface Suranameclicklistener { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يربط بيانات سور القرآن (الأسماء) بـ RecyclerView ويتعامل مع التفاعلات (النقر على الاسم أو زر التشغيل).

---

## المتغيرات (Variables)

### `datalist`

```kotlin
var datalist: ArrayList<String>
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة أسماء سور القرآن (114 سورة).

---

## Constructor

### `constructor(datalist: ArrayList<String>)`

```kotlin
constructor(datalist: ArrayList<String>) : super() {
    this.datalist = datalist
}
```

**ما وظيفة هذا Constructor؟**

يستقبل قائمة الأسماء ويحفظها في المتغير.

**لماذا نستخدم constructor بدلاً من primary constructor؟**

يمكن استخدام primary constructor بشكل أبسط، لكن المطور اختار الطريقة التقليدية.

---

## Inner Class: QranViewHolder

### `class QranViewHolder : RecyclerView.ViewHolder`

```kotlin
class QranViewHolder : RecyclerView.ViewHolder {
    var suraname: TextView? = null
    var quran_play: ImageView? = null
    var quran_progress: ProgressBar? = null
    
    constructor(itemView: View) : super(itemView) {
        suraname = itemView.findViewById(R.id.suraname)
        quran_play = itemView.findViewById(R.id.quran_play)
        quran_progress = itemView.findViewById(R.id.quran_progress)
    }
}
```

**ما وظيفة هذا الكلاس؟**

يحفظ مراجع للـ Views داخل كل عنصر في RecyclerView.

**ما المتغيرات التي يحتويها؟**

- `suraname`: TextView لاسم السورة
- `quran_play`: ImageView لزر التشغيل/الإيقاف
- `quran_progress`: ProgressBar لشريط التحميل

**لماذا المتغيرات nullable؟**

احتياطاً في حالة عدم العثور على View، لكن يمكن استخدام lateinit بدلاً من ذلك.

---

## الدوال (Functions)

### `onCreateViewHolder()`

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QranViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.quranitem, parent, false)
    return QranViewHolder(view)
}
```

**ما وظيفة هذه الدالة؟**

تُنشئ ViewHolder جديد عند الحاجة.

**كيف تعمل؟**

1. تحوّل ملف XML (quranitem.xml) إلى View object
2. تُنشئ QranViewHolder وتمرر له الـ View
3. تُرجع ViewHolder

**متى تُستدعى؟**

عندما يحتاج RecyclerView لعنصر جديد لعرضه.

---

### `getItemCount()`

```kotlin
override fun getItemCount(): Int {
    return datalist.size
}
```

**ما وظيفة هذه الدالة؟**

تُرجع عدد العناصر في القائمة (114 سورة).

**لماذا هي مهمة؟**

RecyclerView يستخدمها لمعرفة كم عنصر سيعرض.

---

### `onBindViewHolder()`

```kotlin
override fun onBindViewHolder(holder: QranViewHolder, position: Int) {
    val data = datalist.get(position)
    holder.suraname!!.setText(data)
    holder.quran_progress?.isVisible = false
    
    if (suranameonclick != null) {
        holder.suraname?.setOnClickListener {
            suranameonclick?.suraonclick(
                position,
                data,
                holder.quran_play!!,
                holder.quran_progress!!
            )
        }
    }
    if (quran_playclick != null) {
        holder.quran_play?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                quran_playclick?.suraonclick(
                    position,
                    data,
                    holder.quran_play!!,
                    holder.quran_progress!!
                )
            }
        })
    }
}
```

**ما وظيفة هذه الدالة؟**

تربط البيانات بالـ Views وتُعد الـ listeners.

**ما هي المعاملات؟**

- `holder`: ViewHolder الذي سنربط البيانات به
- `position`: موضع العنصر في القائمة

---

#### ربط البيانات

```kotlin
val data = datalist.get(position)
holder.suraname!!.setText(data)
holder.quran_progress?.isVisible = false
```

**ما وظيفة هذه الأسطر؟**

1. تحصل على اسم السورة من القائمة
2. تعرض الاسم في TextView
3. تخفي شريط التحميل

**لماذا نستخدم !! و ? معاً؟**

- `!!`: للتأكيد أن القيمة ليست null (قد يسبب crash إذا كانت null)
- `?`: safe call (لن يسبب crash إذا كانت null)

---

#### Listener لاسم السورة

```kotlin
if (suranameonclick != null) {
    holder.suraname?.setOnClickListener {
        suranameonclick?.suraonclick(
            position,
            data,
            holder.quran_play!!,
            holder.quran_progress!!
        )
    }
}
```

**ما وظيفة هذا الكود؟**

إذا تم تعيين listener من الخارج، يُسجله على TextView لاسم السورة.

**ماذا يحدث عند النقر؟**

يتم استدعاء callback يُمرر له:
- موضع السورة
- اسم السورة
- زر التشغيل
- شريط التحميل

---

#### Listener لزر التشغيل

```kotlin
if (quran_playclick != null) {
    holder.quran_play?.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            quran_playclick?.suraonclick(
                position,
                data,
                holder.quran_play!!,
                holder.quran_progress!!
            )
        }
    })
}
```

**ما وظيفة هذا الكود؟**

إذا تم تعيين listener من الخارج، يُسجله على زر التشغيل.

**لماذا استخدام object : View.OnClickListener هنا ولم يُستخدم في listener الاسم؟**

كلاهما يعمل، لكن lambda (الطريقة الأولى) أقصر وأوضح.

---

## Interface: Suranameclicklistener

### `interface Suranameclicklistener`

```kotlin
var suranameonclick: Suranameclicklistener? = null
var quran_playclick: Suranameclicklistener? = null

interface Suranameclicklistener {
    fun suraonclick(
        pos: Int,
        suraname: String,
        quranPlay: ImageView,
        quranProgress: ProgressBar
    )
}
```

**ما وظيفة هذه الواجهة؟**

تحدد عقد (contract) للتعامل مع نقرات المستخدم.

**لماذا نستخدم Interface؟**

لفصل منطق العرض (Adapter) عن منطق التفاعل (Fragment)، مما يجعل الكود أكثر مرونة.

**ما المتغيرات التي تستخدم هذه الواجهة؟**

- `suranameonclick`: للنقر على اسم السورة
- `quran_playclick`: للنقر على زر التشغيل

**كيف يتم تعيين هذه Listeners؟**

من QuranFragment عند تهيئة الـ Adapter.

---

## استخدام Adapter

```kotlin
// في QuranFragment
quranadapter = QranAdapter(datalist)

// Listener للنقر على الاسم
quranadapter.suranameonclick = object : QranAdapter.Suranameclicklistener {
    override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
        suradetailes(pos, suraname)
    }
}

// Listener لزر التشغيل
quranadapter.quran_playclick = object : QranAdapter.Suranameclicklistener {
    override fun suraonclick(pos: Int, suraname: String, quranPlay: ImageView, quranProgress: ProgressBar) {
        // منطق التشغيل
    }
}

recyclerview.adapter = quranadapter
```

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `quranitem.xml`: layout كل عنصر في القائمة
- `QuranFragment.kt`: يستخدم هذا Adapter لعرض قائمة السور

---

## الملخص النهائي

هذا Adapter يربط قائمة أسماء سور القرآن بـ RecyclerView. يوفر واجهتين للتفاعل: النقر على اسم السورة (لفتح التفاصيل) والنقر على زر التشغيل (لتشغيل التلاوة). يستخدم ViewHolder pattern لتحسين الأداء وInterface pattern لفصل المسؤوليات.
