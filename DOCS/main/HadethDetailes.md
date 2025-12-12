# HadethDetailes.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Activity تعرض تفاصيل الحديث المختار، حيث يظهر اسم الحديث ومحتواه الكامل في RecyclerView.

---

## تعريف الكلاس (Class Declaration)

### `class HadethDetailes : AppCompatActivity()`

```kotlin
class HadethDetailes : AppCompatActivity() {
    lateinit var hadethname:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var hadethwrittenadapter:HadethWrittenAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeth_detailes)
        
        hadethname=findViewById(R.id.hadethdetailes)
        setrecyclerview()
        var posofhadeth=intent.getIntExtra(Constent.pos,-1)
        var titleofhadeth=intent.getStringExtra(Constent.name)
        
        hadethname.setText( titleofhadeth)
        readsuracontentfromassets(posofhadeth)
    }
    
    fun setrecyclerview() { /* ... */ }
    fun readsuracontentfromassets(pos:Int) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يعرض محتوى حديث نبوي كامل بعد اختياره من قائمة الأحاديث.

---

## المتغيرات (Variables)

### `hadethname`

```kotlin
lateinit var hadethname:TextView
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ TextView الذي يعرض عنوان الحديث (رقم الحديث) في أعلى الشاشة.

---

### `recyclerView`

```kotlin
lateinit var recyclerView: RecyclerView
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ RecyclerView الذي يعرض محتوى الحديث (قد يكون مقسم إلى أسطر متعددة).

---

### `hadethwrittenadapter`

```kotlin
lateinit var hadethwrittenadapter:HadethWrittenAdapter
```

**ما وظيفة هذا المتغير؟**

يحفظ مرجع لـ Adapter المسؤول عن ربط بيانات الحديث بالـ RecyclerView.

---

## الدوال (Functions)

### `onCreate()`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hadeth_detailes)
    
    hadethname=findViewById(R.id.hadethdetailes)
    setrecyclerview()
    var posofhadeth=intent.getIntExtra(Constent.pos,-1)
    var titleofhadeth=intent.getStringExtra(Constent.name)
    
    hadethname.setText( titleofhadeth)
    readsuracontentfromassets(posofhadeth)
}
```

**ما وظيفة هذه الدالة؟**

يتم استدعاؤها عند إنشاء الـ Activity، وتقوم بإعداد الشاشة وعرض تفاصيل الحديث.

---

#### Execution Code

```kotlin
super.onCreate(savedInstanceState)
```

**ما وظيفة هذا السطر؟**

يستدعي onCreate من الكلاس الأب.

---

```kotlin
setContentView(R.layout.activity_hadeth_detailes)
```

**ما وظيفة هذا السطر؟**

يربط الـ Activity بملف layout الخاص بها (activity_hadeth_detailes.xml).

---

#### View Initialization

```kotlin
hadethname=findViewById(R.id.hadethdetailes)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ TextView الخاص بعنوان الحديث.

---

```kotlin
setrecyclerview()
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لتهيئة RecyclerView و Adapter الخاص بها.

---

#### Receiving Intent Data

```kotlin
var posofhadeth=intent.getIntExtra(Constent.pos,-1)
```

**ما وظيفة هذا السطر؟**

يستقبل رقم موضع الحديث (index) من الـ Intent.

**لماذا القيمة الافتراضية -1؟**

للإشارة إلى خطأ أو عدم وجود بيانات إذا لم يتم العثور على الموضع.

---

```kotlin
var titleofhadeth=intent.getStringExtra(Constent.name)
```

**ما وظيفة هذا السطر؟**

يستقبل عنوان الحديث (مثل "الحديث رقم 1") من الـ Intent.

---

#### Display Data

```kotlin
hadethname.setText( titleofhadeth)
```

**ما وظيفة هذا السطر؟**

يعين عنوان الحديث إلى TextView ليتم عرضه على الشاشة.

**ما الفرق بين setText و text =؟**

- `setText()`: دالة Java التقليدية
- `text =`: خاصية Kotlin (أكثر حداثة)
- كلاهما يعمل بنفس الطريقة

---

```kotlin
readsuracontentfromassets(posofhadeth)
```

**ما وظيفة هذا السطر؟**

يستدعي دالة لقراءة محتوى الحديث من ملفات Assets.

**لماذا اسم الدالة "readsuracontentfromassets" مع أننا نقرأ حديث؟**

خطأ في التسمية (copy-paste من SuraDetailes)، كان يجب تسميتها "readhadethcontentfromassets"، لكن الكود يعمل بشكل صحيح.

---

### `setrecyclerview()`

```kotlin
fun setrecyclerview(){
    recyclerView=findViewById(R.id.recycleOfhadethwritten)
    hadethwrittenadapter= HadethWrittenAdapter()
    recyclerView.adapter= hadethwrittenadapter
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإعداد RecyclerView وربطه بـ Adapter.

---

#### Setup Code

```kotlin
recyclerView=findViewById(R.id.recycleOfhadethwritten)
```

**ما وظيفة هذا السطر؟**

يحصل على مرجع لـ RecyclerView من الـ layout.

---

```kotlin
hadethwrittenadapter= HadethWrittenAdapter()
```

**ما وظيفة هذا السطر؟**

ينشئ instance جديد من HadethWrittenAdapter.

**ما الفرق بين HadethWrittenAdapter و HadethAdapter؟**

- `HadethAdapter`: يعرض قائمة الأحاديث (العناوين فقط)
- `HadethWrittenAdapter`: يعرض محتوى حديث واحد (النص الكامل)

---

```kotlin
recyclerView.adapter= hadethwrittenadapter
```

**ما وظيفة هذا السطر؟**

يربط الـ Adapter بالـ RecyclerView.

---

### `readsuracontentfromassets()`

```kotlin
fun readsuracontentfromassets(pos:Int){
    var filename="h${pos+1}.txt"
    var filecontent=assets.open(filename).bufferedReader().use { it.readText() }
    var datalist=filecontent.split("\n")
    hadethwrittenadapter.changedata(datalist)
}
```

**ما وظيفة هذه الدالة؟**

تقرأ محتوى الحديث من ملف نصي في Assets وتحوله إلى قائمة أسطر.

**ما هي المعاملات؟**

`pos: Int` - رقم موضع الحديث (index).

---

#### File Reading

```kotlin
var filename="h${pos+1}.txt"
```

**ما وظيفة هذا السطر؟**

ينشئ اسم الملف بناءً على موضع الحديث.

**ما الفرق بين هذا وملفات السور؟**

- ملفات السور: `1.txt`, `2.txt`, ...
- ملفات الأحاديث: `h1.txt`, `h2.txt`, ... (حرف h قبل الرقم)

**لماذا pos+1؟**

لأن الموضع يبدأ من 0، لكن أسماء الملفات تبدأ من 1.

---

```kotlin
var filecontent=assets.open(filename).bufferedReader().use { it.readText() }
```

**ما وظيفة هذا السطر؟**

يفتح الملف من Assets ويقرأ محتواه كاملاً كـ String.

**ما هي خطوات القراءة؟**

1. `assets.open(filename)`: يفتح الملف
2. `.bufferedReader()`: يحوله لقارئ نصوص
3. `.use { }`: يضمن إغلاق الملف تلقائياً
4. `it.readText()`: يقرأ كل المحتوى

---

```kotlin
var datalist=filecontent.split("\n")
```

**ما وظيفة هذا السطر؟**

يقسم النص إلى قائمة من الأسطر باستخدام سطر جديد كفاصل.

**لماذا نقسم الحديث إلى أسطر؟**

لعرض كل سطر في عنصر منفصل في RecyclerView، مما يسهل القراءة والتنسيق.

---

```kotlin
hadethwrittenadapter.changedata(datalist)
```

**ما وظيفة هذا السطر؟**

يمرر قائمة الأسطر إلى الـ Adapter ليعرضها في RecyclerView.

---

## تدفق البيانات (Data Flow)

1. المستخدم يختار حديث من Hadethfragment
2. يتم إنشاء Intent يحتوي على:
   - موضع الحديث (pos)
   - عنوان الحديث (name)
3. يتم فتح HadethDetailes Activity
4. يتم استقبال البيانات من Intent
5. يتم عرض عنوان الحديث في TextView
6. يتم قراءة محتوى الحديث من Assets
7. يتم عرض محتوى الحديث في RecyclerView

---

## أوجه التشابه مع SuraDetailes

**التشابهات:**
- نفس البنية العامة للكود
- نفس طريقة استقبال البيانات من Intent
- نفس طريقة قراءة الملفات من Assets
- نفس طريقة عرض البيانات في RecyclerView

**الاختلافات:**
- أسماء ملفات مختلفة (h1.txt vs 1.txt)
- Adapter مختلف (HadethWrittenAdapter vs AyatquranAdapter)
- Layout مختلف (activity_hadeth_detailes vs activity_sura_detailes)

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `activity_hadeth_detailes.xml`: layout هذه الشاشة
- `Hadethfragment.kt`: الـ fragment الذي يرسل Intent لفتح هذه الشاشة
- `HadethWrittenAdapter.kt`: الـ Adapter الذي يعرض محتوى الحديث
- ملفات Assets (`h1.txt` إلى `h50.txt`): ملفات نصية تحتوي على نص كل حديث

---

## الملخص النهائي

هذا الملف يعرض تفاصيل حديث نبوي مختار، حيث يستقبل رقم وعنوان الحديث، ثم يقرأ محتواه من ملف نصي في Assets ويعرضه في RecyclerView. البنية مشابهة جداً لـ SuraDetailes لكن مع اختلافات في أسماء الملفات والـ Adapters.
