# BuildRetrofit.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على كلاس مسؤول عن بناء وإنشاء instance من Retrofit للتواصل مع الـ APIs. يستخدم نمط Singleton لضمان وجود نسخة واحدة فقط من Retrofit.

---

## تعريف الكلاس (Class Declaration)

### `class BuildRetrofit`

```kotlin
class BuildRetrofit {
    companion object {
        private var retrofit_object: Retrofit? = null

        private fun getInstanceRetrofit(url: String): Retrofit {
            retrofit_object = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit_object!!
        }

        fun get_api(url: String): ApiServices {
            return getInstanceRetrofit(url).create(ApiServices::class.java)
        }
    }
}
```

**ما هي وظيفة هذا الكلاس؟**

يقوم ببناء وتكوين Retrofit object الذي يُستخدم لإجراء الطلبات إلى الـ APIs.

---

## المتغيرات (Variables)

### `retrofit_object`

```kotlin
private var retrofit_object: Retrofit? = null
```

**ما وظيفة هذا المتغير؟**

يحفظ instance واحد من Retrofit object ليتم إعادة استخدامه.

**لماذا هو من نوع nullable (?)؟**

لأنه في البداية لا يوجد object تم إنشاؤه، فيكون null حتى يتم إنشاءه لأول مرة.

**لماذا هو private؟**

لمنع الوصول إليه مباشرة من خارج الكلاس، والوصول إليه فقط من خلال الدوال المخصصة.

---

## الدوال (Functions)

### `getInstanceRetrofit()`

```kotlin
private fun getInstanceRetrofit(url: String): Retrofit {
    retrofit_object = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit_object!!
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإنشاء وبناء Retrofit instance باستخدام Retrofit Builder.

**ما هي المعاملات (Parameters) التي تستقبلها؟**

`url: String` - الرابط الأساسي (Base URL) للـ API الذي نريد الاتصال به.

**ما الذي يحدث داخل الدالة؟**

1. `Retrofit.Builder()` - ينشئ builder لبناء Retrofit object
2. `.baseUrl(url)` - يحدد الرابط الأساسي للـ API
3. `.addConverterFactory(GsonConverterFactory.create())` - يضيف محول Gson لتحويل JSON إلى Kotlin objects
4. `.build()` - يبني الـ Retrofit object النهائي

**لماذا نستخدم !! بعد retrofit_object؟**

للإشارة إلى أن المتغير ليس null في هذه اللحظة (non-null assertion)، لأننا أنشأناه للتو في السطر السابق.

**لماذا الدالة private؟**

لأنها دالة مساعدة داخلية، والوصول إلى Retrofit يتم من خلال `get_api()` فقط.

---

### `get_api()`

```kotlin
fun get_api(url: String): ApiServices {
    return getInstanceRetrofit(url).create(ApiServices::class.java)
}
```

**ما وظيفة هذه الدالة؟**

تقوم بإنشاء وإرجاع implementation من واجهة ApiServices جاهزة للاستخدام.

**ما هي المعاملات (Parameters) التي تستقبلها؟**

`url: String` - الرابط الأساسي (Base URL) للـ API.

**ما نوع البيانات المُرجعة (Return Type)؟**

`ApiServices` - واجهة تحتوي على جميع دوال الـ API.

**كيف تعمل الدالة؟**

1. تستدعي `getInstanceRetrofit(url)` لإنشاء Retrofit object
2. تستخدم `.create(ApiServices::class.java)` لإنشاء implementation من واجهة ApiServices
3. ترجع هذا الـ implementation جاهزاً للاستخدام

**ما معنى `ApiServices::class.java`؟**

هذا يمرر class type من ApiServices interface إلى Retrofit ليستطيع إنشاء implementation منه.

---

## Companion Object

**ما هو companion object؟**

هو كائن مرتبط بالكلاس نفسه وليس بـ instances من الكلاس. يشبه static members في Java.

**لماذا نستخدم companion object هنا؟**

لنتمكن من استدعاء الدوال مباشرة من الكلاس دون إنشاء instance: `BuildRetrofit.get_api(url)`

---

## مثال على الاستخدام

```kotlin
// استخدام في الكود
val apiService = BuildRetrofit.get_api(Constant.radio_base_url)
apiService.get_radio().enqueue(callback)
```

---

## الملخص النهائي

هذا الملف يوفر طريقة مركزية وسهلة لإنشاء Retrofit instances المستخدمة للاتصال بالـ APIs. يستخدم نمط Builder pattern لبناء Retrofit مع التكوينات المطلوبة (Base URL و Gson Converter).
