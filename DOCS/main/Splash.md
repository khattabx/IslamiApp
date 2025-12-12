# Splash.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Activity الخاصة بشاشة البداية (Splash Screen) التي تظهر عند فتح التطبيق لمدة قصيرة قبل الانتقال إلى الشاشة الرئيسية.

---

## تعريف الكلاس (Class Declaration)

### `class Splash : AppCompatActivity()`

```kotlin
class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed( {
            var intent=Intent(this@Splash, Home::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}
```

**ما وظيفة هذا الكلاس؟**

يمثل شاشة البداية (Splash Screen) التي تظهر عند فتح التطبيق، وبعد فترة قصيرة ينتقل تلقائياً إلى الشاشة الرئيسية.

**ما معنى `: AppCompatActivity()`؟**

يعني أن هذا الكلاس يرث (inherit) من AppCompatActivity، وهو نوع من Activity يدعم ميزات الـ compatibility مع إصدارات أندرويد القديمة.

---

## الدوال (Functions)

### `onCreate()`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    Handler(Looper.getMainLooper()).postDelayed( {
        var intent=Intent(this@Splash, Home::class.java)
        startActivity(intent)
        finish()
    },2000)
}
```

**ما وظيفة هذه الدالة؟**

يتم استدعاؤها تلقائياً عند إنشاء الـ Activity لأول مرة، وتقوم بإعداد الشاشة والانتقال التلقائي للشاشة الرئيسية.

**ما معنى override؟**

تعني أننا نعيد كتابة (override) دالة موجودة في الكلاس الأب (AppCompatActivity).

**ما هي المعاملات (Parameters)؟**

`savedInstanceState: Bundle?` - يحتوي على حالة الـ Activity المحفوظة سابقاً (إن وجدت)، وهو nullable لأنه قد يكون null في أول مرة.

---

#### كود التنفيذ (Execution Code)

```kotlin
super.onCreate(savedInstanceState)
```

**ما وظيفة هذا السطر؟**

يستدعي دالة onCreate من الكلاس الأب (AppCompatActivity) لتنفيذ العمليات الأساسية المطلوبة.

**لماذا نكتبه أولاً؟**

لأنه يجب أن يتم تنفيذ الكود الأساسي من الكلاس الأب قبل إضافة الكود الخاص بنا.

---

```kotlin
setContentView(R.layout.activity_splash)
```

**ما وظيفة هذا السطر؟**

يربط هذه الـ Activity بملف الـ layout XML الخاص بها (activity_splash.xml)، فيتم عرض محتوى الشاشة.

**ما معنى R.layout.activity_splash؟**

- `R`: كلاس يحتوي على مراجع لجميع الموارد (resources) في التطبيق
- `layout`: مجلد الـ layouts
- `activity_splash`: اسم ملف الـ XML

---

```kotlin
Handler(Looper.getMainLooper()).postDelayed( {
    var intent=Intent(this@Splash, Home::class.java)
    startActivity(intent)
    finish()
},2000)
```

**ما وظيفة هذا الكود Block؟**

يقوم بتأخير تنفيذ كود معين لمدة 2000 milliseconds (2 ثانية)، ثم ينفذ الكود الموجود داخل الـ lambda.

**ما هو Handler؟**

كلاس يسمح بجدولة وتنفيذ كود في وقت لاحق أو على thread معين.

**ما معنى Looper.getMainLooper()؟**

يحصل على الـ Looper الخاص بالـ Main Thread (UI Thread) لضمان تنفيذ الكود على الـ thread الصحيح.

**ما معنى postDelayed؟**

دالة تؤخر تنفيذ كود معين لمدة محددة بالـ milliseconds.

**ما هي المعاملات؟**

1. Lambda function: الكود الذي سيتم تنفيذه
2. `2000`: الوقت بالـ milliseconds (2 ثانية)

---

##### داخل Lambda Function

```kotlin
var intent=Intent(this@Splash, Home::class.java)
```

**ما وظيفة هذا السطر؟**

ينشئ Intent للانتقال من شاشة Splash إلى شاشة Home.

**ما هو Intent؟**

كائن يستخدم للتنقل بين Activities أو إرسال بيانات بينهم.

**ما معنى this@Splash؟**

يشير إلى الـ context الخاص بـ Splash Activity (نستخدم @ لأننا داخل lambda).

**ما معنى Home::class.java؟**

يحدد الـ Activity الوجهة التي نريد الانتقال إليها (Home Activity).

---

```kotlin
startActivity(intent)
```

**ما وظيفة هذا السطر؟**

يبدأ الـ Activity الجديدة (Home) ويفتح شاشتها.

**ماذا يحدث عند استدعائها؟**

يتم إنشاء instance جديد من Home Activity وعرضه على الشاشة.

---

```kotlin
finish()
```

**ما وظيفة هذا السطر؟**

يغلق Splash Activity ويزيلها من الـ back stack.

**لماذا نستخدم finish() هنا؟**

لأننا لا نريد أن يرجع المستخدم لشاشة Splash عند الضغط على زر الرجوع، فنقوم بإغلاقها نهائياً.

**ماذا لو لم نستخدم finish()؟**

عند الضغط على زر الرجوع من Home، سيعود المستخدم إلى Splash مرة أخرى (وهذا غير مرغوب).

---

## سير العمل (Workflow)

1. يفتح المستخدم التطبيق
2. يتم إنشاء Splash Activity
3. تظهر شاشة Splash (activity_splash.xml)
4. يبدأ Handler بالعد التنازلي لمدة 2 ثانية
5. بعد 2 ثانية:
   - ينشئ Intent للانتقال إلى Home
   - يبدأ Home Activity
   - يغلق Splash Activity
6. يرى المستخدم الشاشة الرئيسية (Home)

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `activity_splash.xml`: ملف الـ layout الذي يحدد شكل شاشة Splash
- `Home.kt`: الـ Activity التي ينتقل إليها بعد Splash

---

## الملخص النهائي

هذا الملف يحتوي على شاشة البداية البسيطة التي تظهر لمدة ثانيتين عند فتح التطبيق، ثم تنتقل تلقائياً إلى الشاشة الرئيسية وتغلق نفسها لمنع العودة إليها.
