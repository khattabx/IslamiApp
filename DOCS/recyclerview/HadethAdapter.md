# HadethAdapter.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Adapter مسؤول عن عرض قائمة عناوين الأحاديث النبوية في RecyclerView.

---

## تعريف الكلاس (Class Declaration)

### `class HadethAdapter: RecyclerView.Adapter<HadethAdapter.Hadethviewholder>`

```kotlin
class HadethAdapter: RecyclerView.Adapter<HadethAdapter.Hadethviewholder>{
    
    var datalist:ArrayList<String>
    
    constructor(datalist: ArrayList<String>) : super() {
        this.datalist = datalist
    }
    
    class Hadethviewholder:RecyclerView.ViewHolder { /* ... */ }
    override fun onCreateViewHolder(...) { /* ... */ }
    override fun getItemCount(): Int { /* ... */ }
    override fun onBindViewHolder(...) { /* ... */ }
    
    var hadethnameonclicklistener:HadethOnClickListener?=null
    interface HadethOnClickListener { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يربط قائمة عناوين الأحاديث بـ RecyclerView ويتعامل مع النقر على الحديث.

---

## المتغيرات (Variables)

### `datalist`

```kotlin
var datalist:ArrayList<String>
```

**ما وظيفة هذا المتغير؟**

يحفظ قائمة عناوين الأحاديث (50 حديث).

**لماذا ليس nullable؟**

لأنه يتم تمريره عبر Constructor عند إنشاء Adapter، فهو مضمون الوجود.

---

## Constructor

### `constructor(datalist: ArrayList<String>)`

```kotlin
constructor(datalist: ArrayList<String>) : super() {
    this.datalist = datalist
}
```

**ما وظيفة هذا Constructor؟**

يستقبل قائمة العناوين ويحفظها.

---

## Inner Class: Hadethviewholder

### `class Hadethviewholder:RecyclerView.ViewHolder`

```kotlin
class Hadethviewholder:RecyclerView.ViewHolder{
    var hadethname:TextView?=null
    constructor(itemView: View) : super(itemView){
        hadethname=itemView.findViewById(R.id.texthadeth)
    }
}
```

**ما وظيفة هذا الكلاس؟**

يحفظ مرجع لـ TextView الذي يعرض عنوان الحديث.

**ما المتغيرات التي يحتويها؟**

`hadethname`: TextView لعنوان الحديث.

---

## الدوال (Functions)

### `onCreateViewHolder()`

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hadethviewholder {
    var view=LayoutInflater.from(parent.context).inflate(R.layout.hadethitem,parent,false)
    return Hadethviewholder(view)
}
```

**ما وظيفة هذه الدالة؟**

تُنشئ ViewHolder جديد عند الحاجة.

**ما اسم ملف الـ layout المستخدم؟**

`hadethitem.xml` - layout عنصر واحد (عنوان حديث واحد).

---

### `getItemCount()`

```kotlin
override fun getItemCount(): Int {
  return datalist.size
}
```

**ما وظيفة هذه الدالة؟**

تُرجع عدد الأحاديث في القائمة (50 حديث).

---

### `onBindViewHolder()`

```kotlin
override fun onBindViewHolder(holder: Hadethviewholder, position: Int) {
    var data= datalist?.get(position)
    holder.hadethname?.setText(data)
    
    if(hadethnameonclicklistener!=null){
        holder.hadethname?.setOnClickListener {
            if (data != null) {
                hadethnameonclicklistener?.hadethonclick(position,data)
            }
        }
    }
}
```

**ما وظيفة هذه الدالة؟**

تربط بيانات الحديث بالـ Views وتُعد listener للنقر.

---

#### ربط البيانات

```kotlin
var data= datalist?.get(position)
holder.hadethname?.setText(data)
```

**ما وظيفة هذه الأسطر؟**

1. تحصل على عنوان الحديث من القائمة
2. تعرضه في TextView

**لماذا ?. مع أن datalist ليست nullable؟**

خطأ في الكود، لا حاجة لـ safe call هنا.

---

#### Listener للنقر

```kotlin
if(hadethnameonclicklistener!=null){
    holder.hadethname?.setOnClickListener {
        if (data != null) {
            hadethnameonclicklistener?.hadethonclick(position,data)
        }
    }
}
```

**ما وظيفة هذا الكود؟**

إذا تم تعيين listener من الخارج، يُسجله على TextView.

**ماذا يحدث عند النقر؟**

يتم استدعاء callback يُمرر له:
- موضع الحديث
- عنوان الحديث

**لماذا نتحقق من data != null؟**

للتأكد من وجود بيانات قبل إرسالها للـ callback.

---

## Interface: HadethOnClickListener

### `interface HadethOnClickListener`

```kotlin
var hadethnameonclicklistener:HadethOnClickListener?=null

interface HadethOnClickListener{
    fun hadethonclick(pos:Int,hadethname:String)
}
```

**ما وظيفة هذه الواجهة؟**

تحدد عقد (contract) للتعامل مع نقرات المستخدم على الحديث.

**ما الفرق بينها وبين Suranameclicklistener في QranAdapter؟**

- هنا: معاملين فقط (موضع واسم)
- QranAdapter: 4 معاملات (موضع، اسم، زر تشغيل، progress bar)

---

## استخدام Adapter

```kotlin
// في Hadethfragment
hadethAdapter = HadethAdapter(datalist)
hadethAdapter.hadethnameonclicklistener = object : HadethAdapter.HadethOnClickListener {
    override fun hadethonclick(pos: Int, hadethname: String) {
        hadethdetailes(pos, hadethname)
    }
}
recyclerView.adapter = hadethAdapter
```

---

## مقارنة مع QranAdapter

**أوجه التشابه:**
- نفس البنية العامة
- استخدام Interface للـ click listeners
- استخدام ViewHolder pattern

**الاختلافات:**
- أبسط (لا يوجد زر تشغيل)
- listener واحد فقط (للنقر على العنوان)
- معاملات أقل في callback

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `hadethitem.xml`: layout كل عنصر في القائمة
- `Hadethfragment.kt`: يستخدم هذا Adapter لعرض قائمة الأحاديث

---

## الملخص النهائي

هذا Adapter يربط قائمة عناوين الأحاديث بـ RecyclerView. أبسط من QranAdapter لأنه يتعامل فقط مع النقر على العنوان (لا يوجد زر تشغيل). يستخدم Interface pattern لفصل منطق العرض عن منطق التفاعل.
