EvaExchange API
API'nin swagger linki : http://localhost:9080/evaexchangeapi/swagger-ui/index.html#/

DB diagramı aşağıdaki gibidir.
* <img width="439" alt="image" src="https://github.com/zaf012/eva-exchange-api/assets/99869968/c550cd59-8400-4dd6-bcde-2cd43540a97c">


* 2 tip User tanımlandı. ADMIN ve TRADER
* Öncelikle /api/users/get-user-id-by-params/{username}/{password} url uzantısından userId'i alalım. (username : USER1, password : USER1 -- kullanabilirsiniz.)
* İsteğe göre /api/users/create-user endpointinden yeni bir USER' da oluşturabilirsiniz.

* User oluşturduktan sonra, oluşan kullanıcı için bir Balance oluşturuluyor. Oluşturulan bu Balance'a para yükleme ve para çekme işlemleri yapabiliyorsunuz. Ekran görüntüsü eklenmiştir.
* <img width="1185" alt="image" src="https://github.com/zaf012/eva-exchange-api/assets/99869968/2c9c235b-7376-415f-985d-39d7ca68b681">

* /api/portfolio/get-all-user-shares/{userId} url'i üzerinden de user'ın yaptığı share işlemlerini görebiliyoruz.

* Aşağıdaki ekran görüntüsünde ise User'ın yaptığı BUY işleminin detayı ve yaptığı başarılı işlemin response'ı yer almaktadır.
<img width="1152" alt="image" src="https://github.com/zaf012/eva-exchange-api/assets/99869968/2fdcd324-cede-4fc3-a9cc-aa5fce0f2df9">

	
* Database credentials bilgileri application.properties dosyasinda mevcuttur.

* Uygulamayı docker üzerinde ayağa kaldırmak için :
  
- docker-compose klasöründeki infra.yml uzantısını çalıştıralım.
- docker build -t evaexchangeapi . --> build alalım.
- docker run -d -p 9080:9080 evaexchangeapi --> run edelim.
