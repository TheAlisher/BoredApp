[![License](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)
# Bored App
Это приложение поможет найти занятие когда вам скучно! Есть поля где вы можете выбирать тип деятельности, доступность занятий и другие.

## Почему был создан этот проект?
Это учебный проект из курсов **GeekTech**! Благодаря которому я узнал как работать с **Retrofit**, **Room**, освоил анимацию на android и многое многое другое

# Dependencies

### Material Design
Я думаю любой более или менее крупный проект должен использовать Material Design для придания можно сказать эстетичности для вашего приложения, конечно это громкое заявление но все таки.
Совет: Если у вас достаточно масштабный проект используйте версии библиотек без окончаний alpha, beta или еще как потому что они думаю более стабильны и без багов например в том же **1.3.0.-alpha01** был замечен баг с **password toggle**, поменялись обозначения если глазик зачеркнут то пароль показывается если не зачеркнут пароль не показывается конечно это можно исправить задав свою иконку но все таки не приятный осадочек остался :)
###### [github](https://github.com/material-components/material-components-android)
```java
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'com.google.android.material:material:1.3.0-alpha01'
```

### DotsIndicator
Был использован для OnBoard'а в этом приложении, здесь могу сказать одно 
> Всё гениальное просто
###### [github](https://github.com/tommybuonomo/dotsindicator) 
```java
    implementation 'com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2'
```

### Retrofit
Вообще основная тема этого приложение работа с API и взаимодействие с ним и для этого конечно же была использована эта упрощающая работу библиотека
###### [github](https://github.com/square/retrofit)
```java
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

### LottieFiles
Конечно же была использована для очень ярких анимаций в приложении
###### [github](https://github.com/LottieFiles/lottie-android)
```java
  implementation "com.airbnb.android:lottie:3.4.1"
```

### Room
Нужно было реализовать сохранение данных в избранное и оно было реализовано через эту Базу Данных
```java
    def room_version = "2.2.5"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
```

# License

### На русском
    Лицензия MIT

    Copyright (c) 2020 Alisher

    Данная лицензия разрешает лицам, получившим копию данного программного обеспечения 
    и сопутствующей документации (в дальнейшем именуемыми «Программное обеспечение»), 
    безвозмездно использовать Программное обеспечение без ограничений, включая неограниченное 
    право на использование, копирование, изменение, слияние, публикацию, распространение, 
    сублицензирование и/или продажу копий Программного обеспечения, а также лицам, 
    которым предоставляется данное Программное обеспечение, при соблюдении следующих условий:

    Указанное выше уведомление об авторском праве и данные условия должны быть 
    включены во все копии или значимые части данного Программного обеспечения.

    ДАННОЕ ПРОГРАММНОЕ ОБЕСПЕЧЕНИЕ ПРЕДОСТАВЛЯЕТСЯ «КАК ЕСТЬ», БЕЗ КАКИХ-ЛИБО ГАРАНТИЙ, 
    ЯВНО ВЫРАЖЕННЫХ ИЛИ ПОДРАЗУМЕВАЕМЫХ, ВКЛЮЧАЯ ГАРАНТИИ ТОВАРНОЙ ПРИГОДНОСТИ,
    СООТВЕТСТВИЯ ПО ЕГО КОНКРЕТНОМУ НАЗНАЧЕНИЮ И ОТСУТСТВИЯ НАРУШЕНИЙ, НО НЕ ОГРАНИЧИВАЯСЬ ИМИ.
    НИ В КАКОМ СЛУЧАЕ АВТОРЫ ИЛИ ПРАВООБЛАДАТЕЛИ НЕ НЕСУТ ОТВЕТСТВЕННОСТИ ПО КАКИМ-ЛИБО ИСКАМ, 
    ЗА УЩЕРБ ИЛИ ПО ИНЫМ ТРЕБОВАНИЯМ, В ТОМ ЧИСЛЕ, ПРИ ДЕЙСТВИИ КОНТРАКТА, ДЕЛИКТЕ ИЛИ ИНОЙ СИТУАЦИИ,
    ВОЗНИКШИМ ИЗ-ЗА ИСПОЛЬЗОВАНИЯ ПРОГРАММНОГО ОБЕСПЕЧЕНИЯ ИЛИ ИНЫХ ДЕЙСТВИЙ С ПРОГРАММНЫМ ОБЕСПЕЧЕНИЕМ.
    
### In english
    MIT License
    
    Copyright (c) 2020 Alisher

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
