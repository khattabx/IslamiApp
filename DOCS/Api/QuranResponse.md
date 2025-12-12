# QuranResponse.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Data Classes التي تمثل هيكل البيانات (JSON Response) التي يتم استقبالها من API القرآن الكريم. تستخدم Gson annotations لربط أسماء الحقول.

---

## Data Classes

### `data class QuranResponse`

```kotlin
data class QuranResponse(
    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يمثل الاستجابة الرئيسية من API القرآن، ويحتوي على قائمة بسور القرآن، رسالة، وحالة الطلب.

**ما معنى @field:SerializedName؟**

يخبر Gson أن يربط حقل JSON باسم معين مع المتغير في الكلاس. مثلاً "data" في JSON سيتم ربطه بـ data في الكلاس.

**لماذا كل المتغيرات nullable (?)؟**

لأن البيانات قادمة من API خارجي وقد لا تكون موجودة دائماً، فنحتاج للتعامل مع حالة null.

---

### `data class DataItem`

```kotlin
data class DataItem(
    @field:SerializedName("number")
    val number: Int? = null,

    @field:SerializedName("sequence")
    val sequence: Int? = null,

    @field:SerializedName("ayahCount")
    val ayahCount: Int? = null,

    @field:SerializedName("tafsir")
    val tafsir: Tafsir? = null,

    @field:SerializedName("asma")
    val asma: Asma? = null,

    @field:SerializedName("preBismillah")
    val preBismillah: Any? = null,

    @field:SerializedName("type")
    val type: Type? = null,

    @field:SerializedName("recitation")
    val recitation: Recitation? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يمثل معلومات سورة واحدة من القرآن الكريم.

**ما البيانات التي يحتويها؟**

- `number`: رقم السورة
- `sequence`: ترتيب نزول السورة
- `ayahCount`: عدد الآيات في السورة
- `tafsir`: التفسير
- `asma`: أسماء السورة بلغات مختلفة
- `preBismillah`: بيانات البسملة (إن وجدت)
- `type`: نوع السورة (مكية أو مدنية)
- `recitation`: رابط تلاوة السورة

---

### `data class Recitation`

```kotlin
data class Recitation(
    @field:SerializedName("full")
    val full: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على رابط الصوت الكامل لتلاوة السورة.

**ما البيانات التي يحتويها؟**

- `full`: رابط URL للملف الصوتي الكامل للسورة

---

### `data class Asma`

```kotlin
data class Asma(
    @field:SerializedName("ar")
    val ar: Ar? = null,

    @field:SerializedName("translation")
    val translation: Translation? = null,

    @field:SerializedName("en")
    val en: En? = null,

    @field:SerializedName("id")
    val id: Id? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على أسماء السورة بلغات مختلفة (عربي، إنجليزي، إندونيسي) مع الترجمات.

**ما البيانات التي يحتويها؟**

- `ar`: اسم السورة بالعربي (قصير وطويل)
- `translation`: ترجمة معنى اسم السورة
- `en`: اسم السورة بالإنجليزي
- `id`: اسم السورة بالإندونيسي

---

### `data class Ar`

```kotlin
data class Ar(
    @field:SerializedName("short")
    val jsonMemberShort: String? = null,

    @field:SerializedName("long")
    val jsonMemberLong: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على الاسم العربي للسورة بصيغتين (قصيرة وطويلة).

**لماذا استخدم jsonMemberShort و jsonMemberLong؟**

لأن "short" و "long" كلمات محجوزة في بعض السياقات، فتم إضافة بادئة jsonMember للوضوح.

---

### `data class En`

```kotlin
data class En(
    @field:SerializedName("short")
    val jsonMemberShort: String? = null,

    @field:SerializedName("long")
    val jsonMemberLong: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على الاسم الإنجليزي للسورة بصيغتين (قصيرة وطويلة).

---

### `data class Id`

```kotlin
data class Id(
    @field:SerializedName("short")
    val jsonMemberShort: String? = null,

    @field:SerializedName("long")
    val jsonMemberLong: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على الاسم الإندونيسي للسورة بصيغتين (قصيرة وطويلة).

---

### `data class Translation`

```kotlin
data class Translation(
    @field:SerializedName("en")
    val en: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على ترجمة معنى اسم السورة باللغات المختلفة.

**ما البيانات التي يحتويها؟**

- `en`: الترجمة الإنجليزية
- `id`: الترجمة الإندونيسية

---

### `data class Type`

```kotlin
data class Type(
    @field:SerializedName("ar")
    val ar: String? = null,

    @field:SerializedName("en")
    val en: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحدد نوع السورة (مكية أو مدنية) بلغات مختلفة.

**ما البيانات التي يحتويها؟**

- `ar`: النوع بالعربي (مكية/مدنية)
- `en`: النوع بالإنجليزي (Meccan/Medinan)
- `id`: النوع بالإندونيسي

---

### `data class Tafsir`

```kotlin
data class Tafsir(
    @field:SerializedName("en")
    val en: Any? = null,

    @field:SerializedName("id")
    val id: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على معلومات التفسير للسورة.

**لماذا en من نوع Any؟**

لأن القيمة قد تكون من أنواع مختلفة (String أو Object) حسب استجابة API، فاستخدمنا Any للمرونة.

---

### `data class PreBismillah`

```kotlin
data class PreBismillah(
    @field:SerializedName("translation")
    val translation: Translation? = null,

    @field:SerializedName("text")
    val text: Text? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على بيانات البسملة التي تسبق السورة (إن وجدت).

---

### `data class Text`

```kotlin
data class Text(
    @field:SerializedName("ar")
    val ar: String? = null,

    @field:SerializedName("read")
    val read: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يحتوي على نص بالعربي وطريقة قراءته.

---

## الملخص النهائي

هذا الملف يحتوي على جميع Data Classes التي تمثل هيكل البيانات الكامل للاستجابة من API القرآن الكريم. كل data class يمثل جزء من الـ JSON response، ويتم استخدام Gson لتحويل JSON تلقائياً إلى هذه الـ objects. هذا النمط يسمى "Model" أو "DTO (Data Transfer Object)".
