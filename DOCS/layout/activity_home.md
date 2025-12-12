# activity_home.xml

## نظرة عامة على الملف (File Overview)
هذا الملف يحدد تصميم الشاشة الرئيسية للتطبيق التي تحتوي على Bottom Navigation للتنقل بين الأقسام المختلفة.

---

## البنية الأساسية (Root Element)

### `ConstraintLayout`

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/default_bg">
```

**ما وظيفة هذا العنصر؟**

الحاوية الرئيسية للشاشة مع خلفية افتراضية.

---

## العناصر الرئيسية (Main Components)

### 1. FrameLayout (homefragment)

```xml
<FrameLayout
    android:id="@+id/homefragment"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toTopOf="@id/buttonnavigation"/>
```

**ما وظيفة هذا العنصر؟**

حاوية لعرض الـ Fragments المختلفة (القرآن، الراديو، الأحاديث، السبحة).

**ما معنى android:layout_height="0dp"؟**

الارتفاع يتم حسابه بناءً على constraints (من أعلى الشاشة إلى أعلى Bottom Navigation).

**كيف يتم استخدامه في الكود؟**

```kotlin
supportFragmentManager.beginTransaction()
    .replace(R.id.homefragment, fragment)
    .commit()
```

---

### 2. BottomNavigationView (buttonnavigation)

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/buttonnavigation"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:itemIconSize="40dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:menu="@menu/homenavigation"
    android:background="@color/brown"
    app:itemTextColor="@color/buttonnavigationcolor"
    app:itemIconTint="@color/buttonnavigationcolor"/>
```

**ما وظيفة هذا العنصر؟**

شريط التنقل السفلي الذي يحتوي على 4 أيقونات للأقسام المختلفة.

**ما معنى app:itemIconSize="40dp"؟**

حجم الأيقونات 40dp.

**ما معنى app:menu="@menu/homenavigation"؟**

القائمة تأتي من ملف XML في مجلد menu.

**ما معنى app:itemTextColor و app:itemIconTint؟**

يحددان لون النص والأيقونات (من ملف colors).

**ما الأقسام الأربعة؟**

1. القرآن (quran)
2. الراديو (radio)
3. الأحاديث (hadeth)
4. السبحة (sebha)

---

## الربط مع ملفات Kotlin

**الملف المرتبط:**
- `Home.kt`: الـ Activity الرئيسية

**كيف يتم الربط؟**

```kotlin
setContentView(R.layout.activity_home)
bottomnavigation = findViewById(R.id.buttonnavigation)
```

---

## الملخص النهائي

هذا layout يقسم الشاشة الرئيسية إلى جزئين:
1. FrameLayout علوي: لعرض Fragment النشط
2. BottomNavigationView سفلي: للتنقل بين الأقسام

التصميم بسيط وفعال، يسمح بالتبديل السلس بين الأقسام المختلفة.
