# XML Layout Files - Complete Documentation

## Activity Layouts

### activity_sura_detailes.xml

**الغرض:** تصميم صفحة تفاصيل السورة (عرض الآيات)

**العناصر الرئيسية:**
1. **CoordinatorLayout** - الحاوية الرئيسية
2. **AppBarLayout** - شريط العنوان العلوي
   - **MaterialToolbar** - شريط الأدوات
   - **TextView (suradetailes)** - عنوان السورة
3. **include layout="content_sura_detailes"** - يتضمن محتوى السورة (RecyclerView للآيات)

**الربط مع Kotlin:**
- `SuraDetailes.kt`
- `suratitle = findViewById(R.id.suradetailes)`

---

### activity_hadeth_detailes.xml

**الغرض:** تصميم صفحة تفاصيل الحديث (عرض محتوى الحديث)

**العناصر الرئيسية:**
1. **CoordinatorLayout** - الحاوية الرئيسية
2. **AppBarLayout** - شريط العنوان العلوي
   - **MaterialToolbar** - شريط الأدوات
   - **TextView (hadethdetailes)** - عنوان الحديث
3. **include layout="content_hadeth_detailes"** - يتضمن محتوى الحديث (RecyclerView للنص)

**الربط مع Kotlin:**
- `HadethDetailes.kt`
- `hadethname = findViewById(R.id.hadethdetailes)`

**ملاحظة:** تصميم مشابه جداً لـ activity_sura_detailes.xml

---

## Fragment Layouts

### quranfragment.xml

**الغرض:** تصميم fragment قائمة سور القرآن

**العناصر الرئيسية:**
1. **ConstraintLayout** - الحاوية الرئيسية
2. **ImageView (quranlogo)** - شعار القرآن في الأعلى
3. **View (viewquran1)** - خط فاصل
4. **TextView (chaptername)** - عنوان "اسم السوره"
5. **View (viewquran2)** - خط فاصل آخر
6. **RecyclerView (quranrecycler)** - قائمة السور

**الربط مع Kotlin:**
- `QuranFragment.kt`
- `recyclerview = requireView().findViewById(R.id.quranrecycler)`

---

### ahadethfragment.xml

**الغرض:** تصميم fragment قائمة الأحاديث

**العناصر الرئيسية:**
1. **ConstraintLayout** - الحاوية الرئيسية
2. **ImageView (hadethlogo)** - شعار الأحاديث
3. **View (viewhadeth1)** - خط فاصل
4. **TextView (hadethname)** - عنوان "الاحاديث"
5. **View (viewhadeth2)** - خط فاصل
6. **RecyclerView (hadethrecyclerview)** - قائمة الأحاديث

**الربط مع Kotlin:**
- `Hadethfragment.kt`
- `recyclerView = requireView().findViewById(R.id.hadethrecyclerview)`

---

### radiofragment.xml

**الغرض:** تصميم fragment الراديو الإسلامي

**العناصر الرئيسية:**
1. **ConstraintLayout** - الحاوية الرئيسية
2. **ImageView (radioimage)** - صورة الراديو
3. **TextView (ezaa)** - اسم المحطة "أذاعة القرآن الكريم"
4. **ImageButton (prev)** - زر السابق
5. **ImageButton (play)** - زر التشغيل/الإيقاف
6. **ProgressBar (progress)** - شريط التحميل (يظهر مكان زر play)
7. **ImageButton (next)** - زر التالي

**الربط مع Kotlin:**
- `Radiofragment.kt`
- `radio_text = requireView().findViewById(R.id.ezaa)`
- `buttonStop = requireView().findViewById(R.id.play)`
- `buttonnext = requireView().findViewById(R.id.next)`
- `buttonprev = requireView().findViewById(R.id.prev)`
- `progressBar = requireView().findViewById(R.id.progress)`

---

### tasbehfragment.xml

**الغرض:** تصميم fragment السبحة الإلكترونية

**العناصر الرئيسية:**
1. **ConstraintLayout** - الحاوية الرئيسية
2. **ImageView (sebha)** - صورة السبحة (قابلة للنقر والدوران)
3. **ImageView** - رأس السبحة (ثابت)
4. **TextView (textoftasbeh)** - نص "عدد التسبيحات"
5. **Button (numberoftasbeh)** - عداد التسبيحات (0-33)
6. **Button (doaa)** - الذكر الحالي "سبحان الله"

**الربط مع Kotlin:**
- `Tasbehfragment.kt`
- `buttonnumbertasbeh = view.findViewById(R.id.numberoftasbeh)`
- `sebha = view.findViewById(R.id.sebha)`
- `buttonTasbeh = view.findViewById(R.id.doaa)`

---

## RecyclerView Item Layouts

### quranitem.xml

**الغرض:** تصميم عنصر واحد في قائمة السور

**العناصر:**
1. **TextView (suraname)** - اسم السورة
2. **ImageView (quran_play)** - زر تشغيل التلاوة
3. **ProgressBar (quran_progress)** - شريط التحميل
4. **View** - خط فاصل

**الربط مع Adapter:**
- `QranAdapter.kt`
- يتم inflate في `onCreateViewHolder()`

---

### ayatquranitem.xml

**الغرض:** تصميم عنصر واحد (آية) في صفحة تفاصيل السورة

**العناصر:**
1. **TextView (ayatquran)** - نص الآية
2. **View** - خط فاصل

**الربط مع Adapter:**
- `AyatquranAdapter.kt`
- Layout بسيط جداً لعرض آية واحدة

---

### hadethitem.xml

**الغرض:** تصميم عنصر واحد في قائمة الأحاديث

**العناصر:**
1. **TextView (texthadeth)** - عنوان الحديث "الحديث رقم X"
2. **View (viewhadeth)** - خط فاصل

**الربط مع Adapter:**
- `HadethAdapter.kt`
- Layout بسيط لعرض عنوان الحديث فقط

---

### hadethweittenitem.xml

**الغرض:** تصميم عنصر واحد (سطر) في صفحة تفاصيل الحديث

**العناصر:**
1. **TextView (hadethwritten)** - سطر من نص الحديث

**الربط مع Adapter:**
- `HadethWrittenAdapter.kt`
- أبسط layout في التطبيق (TextView فقط)

---

## خصائص XML المشتركة

### ConstraintLayout Attributes

**app:layout_constraintTop_toTopOf** - يربط الجزء العلوي بعنصر آخر
**app:layout_constraintBottom_toBottomOf** - يربط الجزء السفلي بعنصر آخر
**app:layout_constraintStart_toStartOf** - يربط البداية (اليسار) بعنصر آخر
**app:layout_constraintEnd_toEndOf** - يربط النهاية (اليمين) بعنصر آخر

### Common Attributes

**android:layout_width="match_parent"** - عرض كامل
**android:layout_width="wrap_content"** - عرض حسب المحتوى
**android:layout_width="0dp"** - عرض محسوب من constraints

**android:textSize="30dp"** - حجم الخط
**android:textColor="@color/black"** - لون الخط
**android:padding="10dp"** - مسافة داخلية
**android:layout_margin="20dp"** - مسافة خارجية

---

## الألوان المستخدمة

- `@color/brown` - البني (للخطوط الفاصلة والـ navigation)
- `@color/brownlight` - البني الفاتح
- `@color/black` - الأسود (للنصوص)
- `@color/colorTransparent` - شفاف

---

## الخلفيات المستخدمة

- `@drawable/default_bg` - الخلفية الافتراضية
- `@drawable/splash` - خلفية شاشة البداية

---

## الأيقونات المستخدمة

- `@drawable/qur2an_logo` - شعار القرآن
- `@drawable/hadeth_logo` - شعار الأحاديث
- `@drawable/radio_image` - صورة الراديو
- `@drawable/sebha_logo` - صورة السبحة
- `@drawable/head_sebha_logo` - رأس السبحة
- `@drawable/ic_play_radio` - أيقونة تشغيل
- `@drawable/ic_pause_radio` - أيقونة إيقاف
- `@drawable/ic_next_radio` - أيقونة التالي
- `@drawable/ic_previce_radio` - أيقونة السابق

---

## الملخص النهائي

جميع ملفات الـ XML في التطبيق تتبع نمط تصميم متسق:
1. استخدام ConstraintLayout كحاوية رئيسية
2. خلفية موحدة للتطبيق
3. شعارات وأيقونات في الأعلى
4. خطوط فاصلة بنية اللون
5. RecyclerView لعرض القوائم
6. نصوص كبيرة وواضحة للقراءة

التصميم بسيط وعملي، يركز على سهولة الاستخدام والوضوح.
