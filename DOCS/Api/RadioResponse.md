# RadioResponse.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على Data Classes التي تمثل هيكل البيانات (JSON Response) التي يتم استقبالها من API محطات الراديو الإسلامية.

---

## Data Classes

### `data class RadioResponse`

```kotlin
data class RadioResponse(
    @field:SerializedName("Radios")
    val radios: List<RadiosItem?>? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يمثل الاستجابة الرئيسية من API الراديو، ويحتوي على قائمة بجميع محطات الراديو المتاحة.

**ما معنى @field:SerializedName("Radios")؟**

يخبر Gson أن يربط حقل JSON باسم "Radios" مع المتغير radios في الكلاس (ملاحظة: الحرف الكبير R في JSON).

**ما نوع البيانات في المتغير radios؟**

`List<RadiosItem?>?` - قائمة nullable تحتوي على عناصر من نوع RadiosItem وكل عنصر قد يكون null أيضاً.

**لماذا نستخدم nullable (?) مرتين؟**

- الأول (`List<...>?`): القائمة نفسها قد تكون null إذا لم تُرجع البيانات
- الثاني (`RadiosItem?`): كل عنصر في القائمة قد يكون null

---

### `data class RadiosItem`

```kotlin
data class RadiosItem(
    @field:SerializedName("Id")
    val id: String? = null,

    @field:SerializedName("URL")
    val uRL: String? = null,

    @field:SerializedName("Name")
    val name: String? = null
)
```

**ما وظيفة هذا الـ Data Class؟**

يمثل محطة راديو واحدة بمعلوماتها الأساسية.

**ما البيانات التي يحتويها؟**

- `id`: معرّف فريد لمحطة الراديو
- `uRL`: رابط URL لبث الراديو المباشر
- `name`: اسم محطة الراديو

**لماذا استُخدمت أحرف كبيرة في SerializedName؟**

لأن API يُرجع البيانات بهذا الشكل (Id, URL, Name بأحرف كبيرة)، ويجب أن نطابق الأسماء تماماً.

**لماذا المتغير uRL وليس url؟**

لأن SerializedName هو "URL" بأحرف كبيرة، فتم استخدام uRL في الكود ليكون قريباً من الاسم الأصلي.

---

## مثال على الاستخدام

```kotlin
// عند استقبال البيانات من API
val radioResponse: RadioResponse = response.body()
val radioList = radioResponse.radios

// الوصول إلى محطة راديو معينة
val firstRadio = radioList?.get(0)
val radioName = firstRadio?.name
val radioUrl = firstRadio?.uRL
```

---

## الفرق بين RadioResponse و QuranResponse

**RadioResponse:**
- بنية بسيطة جداً (قائمة محطات فقط)
- كل محطة لها 3 حقول فقط

**QuranResponse:**
- بنية معقدة ومتداخلة
- تحتوي على معلومات تفصيلية عن كل سورة

---

## الملخص النهائي

هذا الملف يوفر نموذج بيانات بسيط لتمثيل استجابة API محطات الراديو الإسلامية. يحتوي على data class رئيسي (RadioResponse) يضم قائمة من محطات الراديو، وكل محطة ممثلة بـ data class آخر (RadiosItem) يحتوي على المعلومات الأساسية للمحطة.
