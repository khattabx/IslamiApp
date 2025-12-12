# Constant.kt

## نظرة عامة على الملف (File Overview)
هذا الملف يحتوي على كائن ثابت (object) يحفظ الروابط الأساسية (Base URLs) للـ APIs المستخدمة في التطبيق.

---

## تعريف الكائن (Object Declaration)

### `object Constant`

```kotlin
object Constant {
    var radio_base_url = "http://www.mp3quran.net/api/radio/"
    var quran_base_url = "https://quran-endpoint.vercel.app/"
}
```

**ما هي وظيفة هذا الكائن؟**

هذا الكائن يحتوي على متغيرات ثابتة لحفظ الروابط الأساسية للخدمات التي يستخدمها التطبيق.

**ما الذي يحتويه الكائن؟**

يحتوي على متغيرين:
1. `radio_base_url`: رابط API للراديو الإسلامي من موقع mp3quran.net
2. `quran_base_url`: رابط API للقرآن الكريم من خدمة quran-endpoint

**لماذا نستخدم object بدلاً من class؟**

لأننا نريد نسخة واحدة فقط من هذا الكائن في كل التطبيق (Singleton Pattern)، ويمكن الوصول إليه مباشرة دون إنشاء instance منه.

**كيف نستخدم هذه الثوابت في التطبيق؟**

يمكن استخدامها مباشرة عن طريق: `Constant.radio_base_url` أو `Constant.quran_base_url`

---

## الملخص النهائي

هذا الملف يعمل كمكان مركزي لتخزين الروابط الأساسية للـ APIs، مما يسهل تعديلها أو صيانتها في مكان واحد بدلاً من البحث عنها في كل ملفات التطبيق.
