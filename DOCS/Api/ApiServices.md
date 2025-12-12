# ApiServices.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحدد واجهة (Interface) تحتوي على دوال API التي يستخدمها التطبيق للتواصل مع الخوادم الخارجية باستخدام مكتبة Retrofit.

---

## تعريف الواجهة (Interface Declaration)

### `interface ApiServices`

```kotlin
interface ApiServices {

    @GET("radio_ar.json")
    fun get_radio(): Call<RadioResponse>

    @GET("quran")
    fun get_quran_audio(): Call<QuranResponse>
}
```

**ما هي وظيفة هذه الواجهة؟**

تحدد الواجهة العمليات (endpoints) التي يمكن استدعاؤها من الـ API، حيث تستخدم Retrofit لتحويل هذه الدوال إلى طلبات HTTP.

---

## الدوال (Functions)

### `get_radio()`

```kotlin
@GET("radio_ar.json")
fun get_radio(): Call<RadioResponse>
```

**ما وظيفة هذه الدالة؟**

تقوم بجلب بيانات محطات الراديو الإسلامية من الـ API.

**ما معنى الـ Annotation الموجود فوق الدالة (@GET)؟**

`@GET("radio_ar.json")` يخبر Retrofit أن هذه دالة GET request سيتم إرسالها إلى المسار "radio_ar.json" نسبة إلى الـ base URL.

**ما نوع البيانات المُرجعة (Return Type)؟**

`Call<RadioResponse>` - وهو كائن يمثل طلب API ويحتوي على الاستجابة من نوع RadioResponse بعد نجاح الطلب.

**كيف يتم استخدام هذه الدالة؟**

يتم استدعاؤها من خلال Retrofit instance، ثم نستخدم `enqueue()` للحصول على النتيجة بشكل asynchronous.

---

### `get_quran_audio()`

```kotlin
@GET("quran")
fun get_quran_audio(): Call<QuranResponse>
```

**ما وظيفة هذه الدالة؟**

تقوم بجلب بيانات سور القرآن الكريم وروابط الصوت الخاصة بها من الـ API.

**ما معنى الـ Annotation الموجود فوق الدالة (@GET)؟**

`@GET("quran")` يخبر Retrofit أن هذه دالة GET request سيتم إرسالها إلى المسار "quran" نسبة إلى الـ base URL.

**ما نوع البيانات المُرجعة (Return Type)؟**

`Call<QuranResponse>` - وهو كائن يمثل طلب API ويحتوي على الاستجابة من نوع QuranResponse بعد نجاح الطلب.

**لماذا نستخدم Interface بدلاً من Class؟**

لأن Retrofit يحتاج إلى interface ليقوم بإنشاء Implementation تلقائياً في وقت التشغيل (Runtime) لتنفيذ الطلبات.

---

## الملخص النهائي

هذا الملف يعمل كعقد (Contract) بين التطبيق والـ API، حيث يحدد جميع الطلبات التي يمكن إرسالها. Retrofit تستخدم هذه الواجهة لإنشاء الطلبات HTTP بشكل تلقائي.
