# Tasbehfragment.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Fragment للتسبيح الإلكتروني (السبحة الإلكترونية) حيث يمكن للمستخدم عد التسبيحات بالنقر على صورة السبحة.

---

## تعريف الكلاس (Class Declaration)

### `class Tasbehfragment:Fragment()`

```kotlin
class Tasbehfragment:Fragment() {
    lateinit var buttonnumbertasbeh: Button
    lateinit var buttonTasbeh: Button
    lateinit var sebha: ImageView
    val Doaa = arrayListOf(
        "سبحان الله",
        "الحمدالله",
        "لا اله الا الله",
        "الله أكبر",
        "استغفر الله العظيم",
        "اللهم صل وسلم علي سيدنا محمد"
    )
    
    override fun onCreateView(...) { /* ... */ }
    override fun onViewCreated(...) { /* ... */ }
}
```

**ما وظيفة هذا الكلاس؟**

يوفر سبحة إلكترونية للتسبيح، حيث ينقر المستخدم على صورة السبحة لزيادة العدد، ويتغير الذكر تلقائياً بعد 34 نقرة.

---

## المتغيرات (Variables)

### `buttonnumbertasbeh`

```kotlin
lateinit var buttonnumbertasbeh: Button
```

**ما وظيفة هذا المتغير؟**

يعرض عدد التسبيحات الحالي (من 0 إلى 33).

**لماذا Button وليس TextView؟**

على الأرجح خطأ في التصميم، كان يجب استخدام TextView لأنه للعرض فقط.

---

### `buttonTasbeh`

```kotlin
lateinit var buttonTasbeh: Button
```

**ما وظيفة هذا المتغير؟**

يعرض الذكر الحالي (مثل "سبحان الله"، "الحمدلله"، إلخ).

---

### `sebha`

```kotlin
lateinit var sebha: ImageView
```

**ما وظيفة هذا المتغير؟**

صورة السبحة التي ينقر عليها المستخدم، وتدور عند كل نقرة.

---

### `Doaa`

```kotlin
val Doaa = arrayListOf(
    "سبحان الله",
    "الحمدالله",
    "لا اله الا الله",
    "الله أكبر",
    "استغفر الله العظيم",
    "اللهم صل وسلم علي سيدنا محمد"
)
```

**ما وظيفة هذا المتغير؟**

يحتوي على قائمة الأذكار التي يتم التنقل بينها.

**كم عدد الأذكار؟**

6 أذكار.

**لماذا اسم المتغير Doaa بحرف كبير؟**

خطأ في التسمية، يجب أن تبدأ المتغيرات بحرف صغير (doaa أو doaaList).

---

## الدوال (Functions)

### `onCreateView()`

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.tasbehfragment, container, false)
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى لإنشاء وإرجاع الـ View الخاص بالـ Fragment.

---

### `onViewCreated()`

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    buttonnumbertasbeh = view.findViewById(R.id.numberoftasbeh)
    sebha = view.findViewById(R.id.sebha)
    buttonTasbeh = view.findViewById(R.id.doaa)
    var num = 0
    var i=1
    sebha.setOnClickListener {
        val clk_rotate = AnimationUtils.loadAnimation(view.context, R.anim.sebha_rotat)
        
        // assigning that animation to
        // the image and start animation
        sebha.startAnimation(clk_rotate)
        num++
        buttonnumbertasbeh.setText(num.toString())
        if (num == 34) {
            num = 0
            buttonnumbertasbeh.setText("0")
            buttonTasbeh.setText(Doaa[i])
            i++
            if(i==6)i=0
        }
    }
}
```

**ما وظيفة هذه الدالة؟**

تُستدعى بعد إنشاء الـ View، وتقوم بتهيئة المكونات وإعداد منطق التسبيح.

---

#### تهيئة Views

```kotlin
buttonnumbertasbeh = view.findViewById(R.id.numberoftasbeh)
sebha = view.findViewById(R.id.sebha)
buttonTasbeh = view.findViewById(R.id.doaa)
```

**ما وظيفة هذه الأسطر؟**

تحصل على مراجع للـ Views من الـ layout.

---

#### المتغيرات المحلية

```kotlin
var num = 0
var i=1
```

**ما وظيفة هذه المتغيرات؟**

- `num`: عدد التسبيحات الحالي (من 0 إلى 33)
- `i`: فهرس الذكر الحالي في قائمة Doaa

**لماذا i يبدأ من 1 وليس 0؟**

لأن الذكر الأول ("سبحان الله") يُعرض افتراضياً، فنبدأ من الذكر الثاني.

**ما المشكلة في هذه المتغيرات؟**

هي محلية في onViewCreated، لذا عند إعادة إنشاء View يتم إعادة تعيينها لـ 0 و 1.

---

#### Listener للنقر على السبحة

```kotlin
sebha.setOnClickListener {
    val clk_rotate = AnimationUtils.loadAnimation(view.context, R.anim.sebha_rotat)
    sebha.startAnimation(clk_rotate)
    num++
    buttonnumbertasbeh.setText(num.toString())
    if (num == 34) {
        num = 0
        buttonnumbertasbeh.setText("0")
        buttonTasbeh.setText(Doaa[i])
        i++
        if(i==6)i=0
    }
}
```

**ما وظيفة هذا الكود؟**

يتعامل مع نقرات المستخدم على صورة السبحة.

---

##### تحميل وتشغيل Animation

```kotlin
val clk_rotate = AnimationUtils.loadAnimation(view.context, R.anim.sebha_rotat)
sebha.startAnimation(clk_rotate)
```

**ما وظيفة هذه الأسطر؟**

1. يحمّل animation دوران من ملف XML
2. يطبقه على صورة السبحة

**لماذا نُدور الصورة؟**

لمحاكاة السبحة الحقيقية التي تدور عند استخدامها.

**ما اسم ملف Animation؟**

`sebha_rotat` (على الأرجح sebha_rotate، خطأ إملائي في الاسم).

---

##### زيادة العدد

```kotlin
num++
buttonnumbertasbeh.setText(num.toString())
```

**ما وظيفة هذه الأسطر؟**

1. يزيد عدد التسبيحات بـ 1
2. يعرض العدد الجديد

---

##### التحقق من الوصول لـ 34

```kotlin
if (num == 34) {
    num = 0
    buttonnumbertasbeh.setText("0")
    buttonTasbeh.setText(Doaa[i])
    i++
    if(i==6)i=0
}
```

**ما وظيفة هذا الكود؟**

عند الوصول لـ 34 تسبيحة:
1. يعيد العدد لـ 0
2. يعرض 0
3. ينتقل للذكر التالي
4. يزيد فهرس الذكر
5. إذا وصل لآخر ذكر (6)، يرجع للبداية (0)

**لماذا 34 وليس 33؟**

لأننا نزيد num أولاً ثم نتحقق، فعند الوصول لـ 34 يعني أن المستخدم سبّح 33 مرة (التسبيحة 34 هي إشارة التغيير).

**لماذا نرجع لـ 0 عند i==6؟**

لأن القائمة بها 6 أذكار (من index 0 إلى 5)، فعند الوصول لـ 6 نرجع للبداية.

---

## آلية عمل السبحة

1. المستخدم ينقر على صورة السبحة
2. تدور الصورة (animation)
3. يزيد العدد بـ 1 ويُعرض
4. عند 34 نقرة:
   - يُعاد العدد لـ 0
   - يتغير الذكر للتالي
   - بعد آخر ذكر، يرجع للأول

---

## دورة الأذكار

1. سبحان الله (33 مرة)
2. الحمدلله (33 مرة)
3. لا إله إلا الله (33 مرة)
4. الله أكبر (33 مرة)
5. استغفر الله العظيم (33 مرة)
6. اللهم صل وسلم على سيدنا محمد (33 مرة)
7. يعود لـ "سبحان الله" وهكذا...

---

## الربط مع الملفات الأخرى

**الملفات المرتبطة:**
- `tasbehfragment.xml`: layout هذا الـ Fragment
- `Home.kt`: الـ Activity التي تحتوي على هذا Fragment
- `res/anim/sebha_rotat.xml`: ملف animation دوران السبحة
- `res/drawable/`: صورة السبحة

---

## الملخص النهائي

هذا Fragment يوفر سبحة إلكترونية بسيطة. ينقر المستخدم على صورة السبحة (التي تدور)، ويزيد العدد حتى 33، ثم يتغير الذكر تلقائياً. دورة من 6 أذكار مختلفة، وتعود للبداية بعد الانتهاء. لا يحتاج اتصال بالإنترنت، ويعمل محلياً بالكامل.
