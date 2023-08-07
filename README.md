## Görev İzleme

Bu projede görevlerin kolayca detaylı bir şekilde takip edilebilmesi amaçlanmıştır. Giriş yapma ve yetkilendirme ile projedeki kısıtlamalar sağlanmıştır. Görev izleme backend projesinde CSRF token ile güvenlik sağlanmıştır.CSRF Token, sunucu taraflı bir uygulamanın CSRF savunmasız kaynaklarını korumak için oluşturduğu gizli, benzersiz ve öngörülemez bir değerdir.

3 farklı rol bulunmaktadır.
+ Admin
+ Pilot
+ Copilot
###### Admin uygulama üzerinde her yere erişebilir. Pilot ve copilota ek olarak kullanıcı ekleme/silme/güncelleme/listeleme ve rol ekleme/silme/güncelleme/listeleme işlemlerini de yapabilir. Pilot, copilot ve admin görev ekleme/silme/güncelleme/listeleme , ilaç ekleme/silme/güncelleme/listeleme ,adres ekleme/silme/güncelleme/listeleme işlemlerini yapabilmektedir.

## Mission Following

In this project, it is aimed that the tasks can be easily followed in detail. With login and authorization, the permissions on the project are met. The task following backend project is secured with CSRF token. CSRF Token is a secret, unique and unpredictable value created by a server-side application to protect CSRF vulnerable resources.

There are 3 different roles.
+ Admin
+ Pilot
+ Copilot
###### Admin can access anywhere on the application. In addition to pilot and copilot, admin can add/delete/update/list users and add/delete/update/list roles. Pilot, copilot and admin can add / delete / update / list tasks, add / delete / update / list medcines , add / delete / update / list addresses.
