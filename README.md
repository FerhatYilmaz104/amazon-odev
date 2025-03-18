# Amazon Test Otomasyonu Framework'ü 🛍️

Bu proje, Amazon.com.tr web sitesi için Selenium WebDriver ve Java kullanılarak geliştirilmiş bir test otomasyonu framework'üdür. Page Object Model (POM) tasarım desenini kullanarak oluşturulmuştur.

## 🎯 Proje Açıklaması

Bu framework, Amazon.com.tr üzerinde temel alışveriş senaryolarını test etmek için tasarlanmıştır. Özellikle:
- Ürün arama
- Ürün detay sayfası kontrolü
- Sepete ürün ekleme
- Sepetten ürün silme
gibi temel işlemleri test eder.

## 🛠️ Kullanılan Teknolojiler

- Java 21
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- WebDriverManager 5.7.0
- Maven

## 📁 Proje Yapısı

```
src/
├── main/
│   └── java/
│       ├── base/
│       │   └── BaseTest.java       # Temel test sınıfı
│       └── pages/
│           ├── HomePage.java       # Ana sayfa işlemleri
│           ├── SearchResultsPage.java  # Arama sonuçları sayfası
│           ├── ProductDetailPage.java  # Ürün detay sayfası
│           └── CartPage.java       # Sepet sayfası
└── test/
    └── java/
        └── tests/
            └── AmazonTest.java     # Test senaryoları
```

## 🔍 Test Senaryoları

### 1. Alışveriş Akışı Testi
```java
@Test
public void testAmazonShoppingFlow() {
    // Ana sayfaya git ve yüklendiğini kontrol et
    HomePage homePage = new HomePage(driver);
    homePage.verifyHomePageLoaded();
    
    // Ürün ara
    homePage.searchProduct("Samsung");
    
    // Arama sonuçlarından 3. ürünü seç
    SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    searchResultsPage.verifySearchResultsLoaded();
    searchResultsPage.clickSecondSearchPageButton();
    searchResultsPage.verifySecondSearchPageLoaded();
    searchResultsPage.clickThirdProduct();
    
    // Ürün detay sayfasında sepete ekle
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    productDetailPage.verifyProductDetailPageLoaded();
    productDetailPage.getProductName();
    productDetailPage.addToCart();
    
    // Sepette ürünü kontrol et ve sil
    CartPage cartPage = new CartPage(driver);
    cartPage.verifyProductAddedToCart();
    cartPage.clickCartButton();
    cartPage.verifyCartPageLoaded();
    cartPage.checkProduct();
    cartPage.removeProductFromCart();
    cartPage.verifyCartEmpty();
    
    // Ana sayfaya dön
    homePage.navigateToHomePage();
    homePage.verifyHomePageLoaded();
}
```

## 🚀 Özellikler

### BaseTest Sınıfı
- WebDriver kurulumu ve yönetimi
- Cookie popup yönetimi
- Ortak bekleme metodları
- Tarayıcı ayarları

### HomePage Sınıfı
- Ana sayfa doğrulama
- Ürün arama
- Sayfa navigasyonu

### SearchResultsPage Sınıfı
- Arama sonuçları kontrolü
- Sayfa geçişleri
- Ürün seçimi

### ProductDetailPage Sınıfı
- Ürün detay sayfası kontrolü
- Ürün ismi doğrulama
- Sepete ekleme işlemleri

### CartPage Sınıfı
- Sepet sayfası kontrolü
- Ürün varlık kontrolü
- Ürün silme işlemleri
- Boş sepet kontrolü

## ⚙️ Kurulum

1. Projeyi klonlayın:
```bash
git clone [proje-url]
```

2. Proje dizinine gidin:
```bash
cd amazon-nobdd-selenium
```

3. Maven bağımlılıklarını yükleyin:
```bash
mvn clean install
```

## 🧪 Testleri Çalıştırma

Testleri çalıştırmak için:
```bash
mvn clean test
```

## 📝 Önemli Notlar

- Framework, Page Object Model (POM) tasarım desenini kullanır
- Her sayfa için ayrı bir Page Object sınıfı bulunur
- Explicit wait stratejisi kullanılarak elementler beklenir
- TestNG annotations kullanılarak test yönetimi sağlanır
- WebDriverManager ile driver yönetimi otomatikleştirilmiştir