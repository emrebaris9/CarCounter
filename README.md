# CarCounter
Car Counter in Java
##2.2 OpenCV Kurulumu
	Libraries kısmında add library penceresini açarak openCV kütüphanemizi ekliyoruz. Ardından OpenCV dosyasının içindeki java dosyasının içindeki jar dosyasının hedef adresini seçip, kütüphanesini ekliyoruz. 
System.load( )’ı ana metoda ekleyerek, kütüphanedeki java için dll dosyalarının hedef adresini çekip projeye ekliyouz. Çevre değişkenleri ile uğraşmamak için bu metod kullanılıyor.

##2.3.  Kullanılan OpenCV Paketleri
######2.3.1. Core
	OpenCV ilkel veri türleri için şablon “özellik” sınıfı. İlkel bir OpenCV veri türü, unsigned char, bool, signed char, unsigned short, signed short, int, float, double değerlerinin tümünün aynı tipte olduğu bu tiplerden birinin değerlerinin biridir. Böyle bir ilkel veri tipinin tek bir örneğini saklayabilen evrensel bir OpenCV yapısı Vec (Kısa sayısal vektörler için şablon sınıfı) dir.

######Size
	Bir resmin veya dikdörtgenin boyutunu belirlemek için şablon sınıfı. Genişlik ve yükseklik  içerir.

######Rect
	2D dikdörtgenler için şablon sınıfı: Sol üst köşenin koordinatları ve Dikdörtgen genişliği ve yüksekliği içerir. OpenCV genellikle dikdörtgenin sol ve üst sınırlarının dahil olduğunu, sağ ve alt sınırların olmadığını varsayar.
                 
######Scalar
	Vec’den türetilmiş 4 elementli bir vektör için şablon sınıfı. Vec Kısa sayısal vektörler için şablon sınıfıydı. Türetilmiştir , ve sadece, tipik bir 4-elemanı vektörler olarak kullanılabilir. Ayrıca, bunlara dönüştürülebilirler . OpenCV'de piksel değerlerini geçmek için yaygın olarak kullanılır.

######Point
	X ve y koordinatları ile belirtilen 2B nokta için şablon sınıfı. Nokta koordinatlarını belirtilen türe dönüştürmek için bir döküm operatörü bulunmaktadır. Kayan noktalı koordinatlardan tamsayı koordinatlarına dönüşüm yuvarlama ile yapılır. Genellikle bu dönüşüm işlemi her bir koordinat için ayrı ayrı  kullanır.

##2.3.2 Mat 
      	N boyutlu bir yoğun sayısal tek kanallı veya çok kanallı diziyi temsil eder. Gerçek veya karmaşık değerli vektörlerin ve matrislerin, gri tonlamalı veya renkli görüntülerin, vektör alanlarının, nokta bulutlarının, tensörlerin, histogramların (çok yüksek boyutlu histogramların daha iyi saklanabileceği durumlarda SparseMat) saklanması için kullanılabilir. M dizisinin veri yerleşimi M.step [ ] dizisi tarafından tanımlanır.
    İsteğe bağlı karmaşık ifadelerde birleştirilebilen uygulanan matris işlemlerinin bir listesi A ve B matrisi (Mat),  s harfi skaler(Scalar), alpha değişkeni gerçek değerli bir skaler (dobule) olmak üzere :
⦁	Toplama, çıkarma, olumsuzlama: A+B, A-B, A+s, A-s,s+A, s-A, -A
⦁	Ölçekleme: A*alpha
⦁	Her eleman için çarpma ve bölme: A.mul(B),  A/B,  alpha/A
⦁	Matris çarpımı: A*B
⦁	Aktarım: A.t( ) ( AT anlamına gelir )
⦁	Karşılaştırma:  Karşılaştırma sonucu, elemanları 255'e  veya 0'a ayarlanmış olan 8 bitlik tek kanal maskesidir.A cmpop B, A cmpop alpha,alpha cmpop Acmpop
⦁	Eleman-asgari ve maksimum: min(A, B), min(A, alpha),max(A, B), max(A, alpha)
⦁	Eleman-mutlak mutlak değeri: abs(A)
⦁	Çapraz çarpım, noktasal çarpım: A.cross (B) A.dot (B)

##2.3.3 Imgproc
Resize 
Bir görüntüyü yeniden boyutlandırır.
resize( src , dst , dsize , double fx = 0, double fy = 0, int enterpolasyon = INTER_CUBIC ),
 Parametreler:  src : giriş görüntüsü, dst : çıktı görüntüsü, dsize: çıktı 
 
######CvtColor

Bir görüntüyü bir renk uzayından diğer birine dönüştürür. RGB renk uzayından bir dönüşme durumunda, kanalların sırası açıkça belirtilmelidir (RGB veya BGR). OpenCV'deki varsayılan renk formatının genellikle RGB olarak adlandırır.
	RGB alanındaki dönüşümler, alfa kanalının eklenmesi / kaldırılması, kanal sırasının tersine çevrilmesi, 16 bit RGB rengine dönüşümünün yanı sıra aşağıdakilerden yararlanarak gri tonlamalıya dönüştürülmesi:


######Threshold
	Her dizi öğesine sabit düzey eşiği uygular. En basit segmentasyon yöntemidir. Fonksiyon, tek kanallı bir diziye sabit seviye eşikleme uygular. Bu işlev genellikle gri tonlamalı bir görüntüden iki seviyeli (ikili) bir görüntü elde etmek veya bir gürültüyü kaldırmak, yani pikselleri çok küçük veya çok büyük değerlerle filtrelemek için kullanılır. İşlev tarafından desteklenen çeşitli eşik türleri vardır. Özetle görüntünün siyah beyaz tanımlanmasıdır.

Blur 
	Normalleştirilmiş kutu filtresini kullanarak bir görüntüyü bulanıklaştırır. Bu filtre en basit olandır. Her çıktı pikseli, çekirdek komşuların ortalamasıdır (hepsi eşit ağırlığa katkıda bulunur.
 blur( Giriş dizisi src , Çıktı dizisi dst , Boyut ksize (bulanık çekirdek boyutu) ) ; olmak üzere :  İşlev çekirdeği kullanarak görüntüyü pürüzsüzleştirir.

######FindContours
	Bir ikili (binary) görüntüdeki konturları bulur.
findContours(çıktı, konturlar, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_ APPROX_SIMPLE);
konturlar: Tespit edilen konturlardır
heirarchy: Görüntü topolojisi hakkında bilgi içeren isteğe bağlı çıkış vektörüdür. 
CV_RETR_EXTERNAL:  sadece aşırı dış konturları alır.
CV_CHAIN_APPROX_SIMPLE yatay, dikey ve diyagonal segmentleri sıkıştırır ve sadece uç noktalarını bırakır. Örneğin, yukarı doğru bir dikdörtgen kontur 4 nokta ile kodlanır.

##Çizim Fonksiyonları 
	Çizim fonksiyonları, rasgele derinlik matrisleri veya görüntüleri ile çalışır. Şekillerin sınırları antialiasing ile oluşturulabilir (şimdilik sadece 8 bit görüntüler için uygulanır). Tüm işlevler, renkli görüntüler, gri tonlamalı görüntüler ve parlaklık için RGB değerini kullanan parametre rengini içerir . 
######Line 
	İki noktayı birleştiren bir çizgi parçası çizer. Parametre olarak görüntü, çizgi segmentinin ilk noktası, çizgi segmentinin ikinci noktası, çizgi kalınlığı ve rengi.

######Rectangle
	Basit, kalin veya sağa doğru dolu bir dikdörtgen çizer. Parametre olarak  görüntü, dikdörtgenin 1. köşe noktası , 1.köşe noktasının karşısındaki dikdörtgenin köşe noktası, çizilen dikdörtgenin alternatif belirtimi, dikdörtgen rengi veya parlaklığı (gri tonlamalı görüntü), dikdörtgeni oluşturan çizgilerin kalınlığı, CV_FILLED gibi negatif değerler, işlevin dolu bir dikdörtgen çizmesi gerektiği anlamına gelir.

######Circle
	Bir daire çizer. Parametre olarak kaynak görüntü, dairenin merkezi, yarıçapı, renk, dairenin ana hattının kalınlığı (negatifse içi dolu bir daire çizer.).

######PutText
	Bir metin dizesi çizer. Parametre olarak görüntü, çizilecek girdi metni, görüntüdeki metin dizesinin sol alt köşesi, fontu, boyutu, renkli, kalınlığı
BoundingRect
Fonksiyon, belirtilen 2 boyutlu nokta seti için minimum sağ-up sınırlama dikdörtgeni hesaplar ve döndürür.

##2.3.4 Arka Plan Çıkartma
BackgroundSubtractorMOG2
Ön plan algılama olarak da bilinen arka plan çıkarma, bir görüntüden ön alanı kaldırmak için kullanılan bir tekniktir. Statik bir kameradan bir video akışında hareket algılama için yaygın olarak kullanılır. İki arkaplan çıkarma yöntemi, çerçeve farklılığı ve Gauss (MOG) modellerinin karışımıdır. Çerçeve farklılığı, mevcut video karesi ile bir “referans görüntü” arasındaki farklılıkları bularak hareketli nesneleri tanımlamasının arkaplan çıkarma arkasındaki temel gerekçeyi temsil eder. Herhangi bir arka plan çıkarma yönteminin çalışması için, ön plan ve arka plan arasında bazı karşıtlıkların olması gerekir. Çerçeve farklılığı, cv2.absdiff işlevi kullanılarak OpenCV'de yapılabilir. Bunun çıktısı, beyaz piksellerin, geçerli zaman ile referans görüntünün alındığı zaman arasında değişen çerçevenin pikselleri olduğu ikili bir maskedir. İzlenen bir nesnenin koordinatlarını elde etmek için, konturlar nesneyi temsil eden beyaz “leke” ye takılabilir ve daha sonra bloğun merkezinin koordinatları kontur alanının merkezini hesaplayarak belirlenebilir. Bloğun etrafında tam bir kontur elde etmek için, herhangi bir arka plan renk deliğini doldurmak için bir morfolojik kapatma işlemi yapılmalıdır . Diğer gürültü filtreleme teknikleri de gerektiğinde kullanılmalıdır.
 OpenCV'nin varsayılan kurulumunda uygulanan en basit MOG modeli BackgroundSubtractorMOG2'dir. Bu yöntemde, bir pikselin arka planın bir parçası olarak sınıflandırılmadan önce değişmemesi gereken süre, bir parametre olarak ayarlanır. İkili bir maske OpenCV BackgroundSubtractorMOG2 yönteminin son çıktısıdır. Yakalanan sahnede meydana gelen değişikliklere bağlı olarak, bu maske cv2.absdiff işlevi kullanılarak üretilen ile aynı olmayabilir veya olmayabilir.

######Absdiff 
 	İki dizi arasındaki veya bir dizi ve bir skaler arasındaki eleman başına mutlak farkını hesaplar. Aynı boyut ve türe sahip olduklarında iki dizi arasındaki mutlak fark:

##2.3.5 Video 
Video Capture
	Video dosyalarından, görüntü dizilerinden veya kameralardan video yakalama için sınıf. Sınıf, kameralardan video çekmek veya video dosyalarını ve görüntü dizilerini okumak için C ++ API sağlar. Esasen, video manipülasyonu için gerekli tüm işlevler VideoCapture sınıfına entegre edilmiştir. 
Bir video birbirini izleyen resimlerden oluşur ve bunlar literatürde çerçeveler olarak adlandırılır. Temel olarak sürekli görüntülerin bir koleksiyonudur Bir video dosyasının kare hızı, iki çerçeve arasında ne kadar süre geçtiğiyle belirlenir. Videodan kareleri çıkarmak için önce bu videoyu giriş akışına eklememiz ve sonra gerektiğinde bunları almamız gerekir.
VideoCapture capture = new VideoCapture("C:\\Users\\Emre\\Masaüstü\\Otoyol.mp4");
	Kamera dizini video dosyası adıyla değiştirilir. Çerçeveyi görüntülerken, cv2.waitKey( ) için uygun zamanı kullanılmalı . Çok azsa, video çok hızlı olur ve çok yüksekse, video yavaş olacaktır. Normal durumlarda 25 milisaniye tamam olur.
