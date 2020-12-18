# migros-project
Projemizde H2SQL kullanılmıştır. Programın JUnit testleri mevcuttur. H2'ya default olan giriş değerleri ile giriş yapabilirsiniz.<br>
url: jdbc: h2:mem:testdb <br>
user name: sa <br>
password: <br>
**cour** ve  **str** olmak üzere 2 adet tablomuz bulunmaktadır. **str** için bir DBInitializer mevcuttur. Programı çalıştırmanız yeterlidir. <br>
- courier
  * courier id
  * time
  * identity number
  * name
  * town
  * latitude
  * longitude
  * distance
  * last store
  * create date
  * update date
  
- store
  * store id
  * store name
  * town
  * latitude
  * longitude
  * create date
  * update date

## API
- http://localhost:9091/courier/create <br>
Aşağıdaki gibi bir örnek request ile istenilen logic işlemlerini yapar ve geriye kurye bilgilerini ve duruma göre logic'e takılan mesajları gösterir. <br>
**Api Detayı ve Örnek Request:**
```json
POST 
Content-Type: application/json

{
  "name": "Can",
  "identityNumber": 123456788,
  "town": "Caddebostan",
  "latitude": 40.9923307,
  "longitude": 29.1244229,
  "distance": 29
}
```
- http://localhost:9091/courier/{courierId} <br>
Kullanıcıya seçili kuryenin toplam yapmış olduğu mesafeyi döndürür. <br>
**Api Detayı:**
```json
GET 
Content-Type: application/json
```  
