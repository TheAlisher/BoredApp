[![License](https://img.shields.io/badge/License-Apache-brightgreen.svg)](https://opensource.org/licenses/Apache-2.0)
[![Bored](https://img.shields.io/badge/Bored-API-blue.svg)](https://www.boredapi.com/)
# Bored App
Это приложение поможет найти занятие когда вам скучно! Есть поля где вы можете выбирать тип деятельности, доступность занятий и другие.

## Почему был создан этот проект?
Цель этого проекта была узнать как работать с **API** и **Retrofit**. Благодоря проекту узнал как работать как уже и перечислил c **API**, **Retrofit**, повторил работу c **Room**, можно сказать более углубленно был изучен паттерн **Singleton**, научился делать **Light / Dark MODE**, ещё узнал как работать с анимациями самого android, и поработал с **SplashScreen**'ом, сделал **BottomSheet**, **PopupMenu**.

# Dependencies

### [Material Design](https://github.com/material-components/material-components-android)
Я думаю любой более или менее крупный проект должен использовать Material Design для придания можно сказать эстетичности для вашего приложения, конечно это громкое заявление но все таки.
Совет: Если у вас достаточно масштабный проект используйте версии библиотек без окончаний alpha, beta или еще как потому что они думаю более стабильны и без багов например в том же **1.3.0.-alpha01** был замечен баг с **password toggle**, поменялись обозначения если глазик зачеркнут то пароль показывается если не зачеркнут пароль не показывается конечно это можно исправить задав свою иконку но все таки не приятный осадочек остался :)
```java
    implementation 'com.google.android.material:material:1.1.0'
    implementation 'com.google.android.material:material:1.3.0-alpha01'
```

### [DotsIndicator](https://github.com/tommybuonomo/dotsindicator) 
Был использован для **OnBoard**'а в этом приложении, здесь могу сказать одно 
> Всё гениальное просто
```java
    implementation 'com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2'
```

### [Retrofit](https://github.com/square/retrofit)
Так как основная тема этого приложение работа с **API** и взаимодействие с ним, я использовал библиотеку упрощающая взаимодействие с **API**'шками
```java
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

### [LottieFiles](https://github.com/LottieFiles/lottie-android)
Конечно же была использована для очень ярких анимаций в приложении
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
    
     Copyright 2020

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
