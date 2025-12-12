# activity_splash.xml

## نظرة عامة على الملف (File Overview)
هذا الملف يحدد تصميم شاشة البداية (Splash Screen) التي تظهر عند فتح التطبيق.

---

## البنية الأساسية (Root Element)

### `ConstraintLayout`

```xml
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".islamiproject.main.Splash"
    android:background="@drawable/splash">
</androidx.constraintlayout.widget.ConstraintLayout>
```

**ما وظيفة هذا العنصر؟**

الحاوية الرئيسية للشاشة التي تستخدم نظام ConstraintLayout لترتيب العناصر.

**ما معنى android:layout_width="match_parent"؟**

العرض يساوي عرض الشاشة بالكامل.

**ما معنى android:layout_height="match_parent"؟**

الارتفاع يساوي ارتفاع الشاشة بالكامل.

**ما معنى tools:context=".islamiproject.main.Splash"؟**

يحدد الـ Activity المرتبطة بهذا الـ layout (للمعاينة في Android Studio فقط).

**ما معنى android:background="@drawable/splash"؟**

يضع صورة خلفية من مجلد drawable اسمها splash.

---

## محتوى الشاشة

**هل يوجد محتوى داخل الـ layout؟**

لا، الـ ConstraintLayout فارغ. الشاشة تعتمد فقط على الصورة الخلفية.

**لماذا لا توجد عناصر؟**

لأن شاشة البداية عادة تكون بسيطة وتعرض شعار التطبيق فقط، والشعار موجود في الصورة الخلفية.

---

## الربط مع ملفات Kotlin

**الملف المرتبط:**
- `Splash.kt`: الـ Activity التي تستخدم هذا الـ layout

**كيف يتم الربط؟**

```kotlin
setContentView(R.layout.activity_splash)
```

---

## الملخص النهائي

هذا layout بسيط جداً لشاشة البداية، يحتوي فقط على صورة خلفية من drawable. لا توجد عناصر تفاعلية، فقط شعار التطبيق يظهر لثانيتين ثم ينتقل للشاشة الرئيسية.
