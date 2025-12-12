# HadethWrittenAdapter.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Adapter مسؤول عن عرض محتوى حديث نبوي كامل (سطر سطر) في RecyclerView.

---

## تعريف الكلاس (Class Declaration)

### `class HadethWrittenAdapter: RecyclerView.Adapter<HadethWrittenAdapter.HadethwrittenViewholder>()`

```kotlin
class HadethWrittenAdapter: RecyclerView.Adapter<HadethWrittenAdapter.HadethwrittenViewholder>() {
    
    var list:List<String>?=null
    
    class HadethwrittenViewholder: RecyclerView.ViewHolder { /* ... */ }
    override fun onCreateViewHolder(...) { /* ... */ }
    override fun getItemCount(): Int { /* ... */ }
    override fun onBindViewHolder(...) { /* ... */ }
    fun changedata(list:List<String>) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يربط محتوى حديث نبوي (مقسم إلى أسطر) بـ RecyclerView لعرضه.

---

## المتغيرات (Variables)

### `list`

```kotlin
var list:List<String>?=null
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة أسطر الحديث المراد عرضها.

**لماذا nullable؟**

لأن البيانات لا تكون متوفرة عند إنشاء Adapter، بل يتم تمريرها لاحقاً عبر `changedata()`.

---

## Inner Class: HadethwrittenViewholder

### `class HadethwrittenViewholder: RecyclerView.ViewHolder`

```kotlin
class HadethwrittenViewholder: RecyclerView.ViewHolder {
    var content: TextView?=null
    constructor(itemView: View) : super(itemView){
        content= itemView.findViewById(R.id.hadethwritten)
    }
}
```

**ما وظيفة هذا الكلاس؟**

يحفظ مرجع لـ TextView الذي يعرض سطر من الحديث.

**ما المتغيرات التي يحتويها؟**

`content`: TextView لعرض سطر من نص الحديث.

---

## الدوال (Functions)

### `onCreateViewHolder()`

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethwrittenViewholder {
    var view= LayoutInflater.from(parent.context).inflate(R.layout.hadethweittenitem,parent,false)
    return HadethwrittenViewholder(view)
}
```

**ما وظيفة هذه الدالة؟**

تُنشئ ViewHolder جديد عند الحاجة.

**ما اسم ملف الـ layout المستخدم؟**

`hadethweittenitem.xml` - layout عنصر واحد (سطر واحد من الحديث).

**ملاحظة على الاسم:**

"weitten" على الأرجح خطأ إملائي، كان يجب أن يكون "written".

---

### `getItemCount()`

```kotlin
override fun getItemCount(): Int {
    return list?.size ?:0
}
```

**ما وظيفة هذه الدالة؟**

تُرجع عدد الأسطر في الحديث.

**ما معنى ?: 0؟**

Elvis operator - إذا كان list null، تُرجع 0 بدلاً من null.

---

### `onBindViewHolder()`

```kotlin
override fun onBindViewHolder(holder: HadethwrittenViewholder, position: Int) {
    var data=list?.get(position)
    holder.content?.text=data
}
```

**ما وظيفة هذه الدالة؟**

تربط بيانات السطر بالـ TextView لعرضه.

**كيف تعمل؟**

1. تحصل على نص السطر من القائمة
2. تعرضه في TextView

---

### `changedata()`

```kotlin
fun changedata(list:List<String>){
    this.list=list
    notifyDataSetChanged()  //reload data
}
```

**ما وظيفة هذه الدالة؟**

تُحدث بيانات Adapter بقائمة أسطر جديدة.

**ما هي المعاملات؟**

`list: List<String>` - القائمة الجديدة من أسطر الحديث.

**ما معنى notifyDataSetChanged()؟**

تُخبر RecyclerView أن البيانات تغيرت، فيعيد رسم القائمة.

**متى تُستدعى هذه الدالة؟**

في HadethDetailes بعد قراءة محتوى الحديث من Assets.

---

## استخدام Adapter

```kotlin
// في HadethDetailes
hadethwrittenadapter = HadethWrittenAdapter()
recyclerView.adapter = hadethwrittenadapter

// بعد قراءة البيانات
val datalist = filecontent.split("\n")
hadethwrittenadapter.changedata(datalist)
```

---

## مقارنة مع AyatquranAdapter

**أوجه التشابه:**
- نفس البنية تماماً
- نفس الطريقة (`changedata()` لتحديث البيانات)
- نفس البساطة (لا توجد listeners)
- كلاهما يعرض نصوص فقط

**الاختلافات:**
- الاسم وملف الـ layout فقط
- AyatquranAdapter للآيات
- HadethWrittenAdapter للأحاديث

---

## ملاحظة مهمة

هذا Adapter شبه مطابق تماماً لـ AyatquranAdapter. كان يمكن إنشاء adapter واحد يُستخدم للحالتين بدلاً من نسخ الكود.

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `hadethweittenitem.xml`: layout كل سطر
- `HadethDetailes.kt`: يستخدم هذا Adapter لعرض محتوى الحديث

---

## الملخص النهائي

هذا Adapter بسيط جداً مسؤول فقط عن عرض قائمة أسطر حديث نبوي في RecyclerView. مطابق تقريباً لـ AyatquranAdapter لكن مع ملف layout مختلف. يستخدم `changedata()` لتحديث البيانات بعد قراءتها من Assets.
