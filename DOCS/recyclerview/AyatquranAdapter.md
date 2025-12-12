# AyatquranAdapter.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Adapter مسؤول عن عرض آيات سورة قرآنية في RecyclerView.

---

## تعريف الكلاس (Class Declaration)

### `class AyatquranAdapter:RecyclerView.Adapter<AyatquranAdapter.AyatquranViewholder>()`

```kotlin
class AyatquranAdapter:RecyclerView.Adapter<AyatquranAdapter.AyatquranViewholder> (){
    
    var list:List<String>?=null
    
    class AyatquranViewholder: RecyclerView.ViewHolder { /* ... */ }
    override fun onCreateViewHolder(...) { /* ... */ }
    override fun getItemCount(): Int { /* ... */ }
    override fun onBindViewHolder(...) { /* ... */ }
    fun changedata(list:List<String>) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يربط قائمة آيات سورة قرآنية بـ RecyclerView لعرضها.

---

## المتغيرات (Variables)

### `list`

```kotlin
var list:List<String>?=null
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة آيات السورة المراد عرضها.

**لماذا nullable؟**

لأن البيانات لا تكون متوفرة عند إنشاء Adapter، بل يتم تمريرها لاحقاً عبر `changedata()`.

**لماذا List وليس ArrayList؟**

List أكثر عمومية ويقبل أي نوع من القوائم (ArrayList, LinkedList, إلخ).

---

## Inner Class: AyatquranViewholder

### `class AyatquranViewholder: RecyclerView.ViewHolder`

```kotlin
class AyatquranViewholder: RecyclerView.ViewHolder {
    var content:TextView?=null
    constructor(itemView: View) : super(itemView){
        content= itemView.findViewById(R.id.ayatquran)
    }
}
```

**ما وظيفة هذا الكلاس؟**

يحفظ مرجع لـ TextView الذي يعرض نص الآية.

**ما المتغيرات التي يحتويها؟**

`content`: TextView لعرض نص الآية.

**لماذا nullable؟**

احتياطاً في حالة عدم العثور على View.

---

## الدوال (Functions)

### `onCreateViewHolder()`

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatquranViewholder {
    var view=LayoutInflater.from(parent.context).inflate(R.layout.ayatquranitem,parent,false)
    return AyatquranViewholder(view)
}
```

**ما وظيفة هذه الدالة؟**

تُنشئ ViewHolder جديد عند الحاجة.

**ما اسم ملف الـ layout المستخدم؟**

`ayatquranitem.xml` - layout عنصر واحد (آية واحدة).

---

### `getItemCount()`

```kotlin
override fun getItemCount(): Int {
   return list?.size ?:0
}
```

**ما وظيفة هذه الدالة؟**

تُرجع عدد الآيات في السورة.

**ما معنى ?: 0؟**

Elvis operator - إذا كان list null، تُرجع 0 بدلاً من null.

**لماذا نستخدم ?: بدلاً من !!؟**

لتجنب crash إذا كانت القائمة null (يُرجع 0 بأمان).

---

### `onBindViewHolder()`

```kotlin
override fun onBindViewHolder(holder: AyatquranViewholder, position: Int) {
    var data=list?.get(position)
    holder.content?.text=data
}
```

**ما وظيفة هذه الدالة؟**

تربط بيانات الآية بالـ TextView لعرضها.

**كيف تعمل؟**

1. تحصل على نص الآية من القائمة
2. تعرضه في TextView

**لماذا safe calls (?.)؟**

للتعامل مع احتمالية null بأمان.

---

### `changedata()`

```kotlin
fun changedata(list:List<String>){
   this.list=list
    notifyDataSetChanged()  //reload data
}
```

**ما وظيفة هذه الدالة؟**

تُحدث بيانات Adapter بقائمة آيات جديدة.

**ما هي المعاملات؟**

`list: List<String>` - القائمة الجديدة من الآيات.

**ما معنى notifyDataSetChanged()؟**

تُخبر RecyclerView أن البيانات تغيرت، فيعيد رسم القائمة.

**متى تُستدعى هذه الدالة؟**

في SuraDetailes بعد قراءة محتوى السورة من Assets.

---

## استخدام Adapter

```kotlin
// في SuraDetailes
ayatadapter = AyatquranAdapter()
recyclerView.adapter = ayatadapter

// بعد قراءة البيانات
val datalist = filecontent.split("\n")
ayatadapter.changedata(datalist)
```

---

## مقارنة مع QranAdapter

**أوجه التشابه:**
- نفس البنية العامة (ViewHolder + RecyclerView.Adapter)
- استخدام LayoutInflater لإنشاء Views

**الاختلافات:**
- أبسط بكثير (لا توجد listeners للنقرات)
- لا يوجد زر تشغيل أو progress bar
- البيانات تُمرر عبر `changedata()` وليس Constructor
- عرض نصوص فقط بدون تفاعل

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `ayatquranitem.xml`: layout كل آية
- `SuraDetailes.kt`: يستخدم هذا Adapter لعرض آيات السورة

---

## الملخص النهائي

هذا Adapter بسيط جداً مسؤول فقط عن عرض قائمة آيات القرآن في RecyclerView. لا يحتوي على أي تفاعلات أو ميزات معقدة، فقط عرض نصوص الآيات. يستخدم `changedata()` لتحديث البيانات بعد قراءتها من Assets.
