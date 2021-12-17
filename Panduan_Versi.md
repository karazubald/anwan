# Versi
Versi aplikasi mengikuti format X.Y.Z-A
- X mengindikasikan pengubahan yang mempengaruhi interaksi antarkode dalam program seperti pengubahan kelas, antarmuka, metode, versi atau jenis pustaka sistem (library), dan lain sebagainya. X juga mencakup pengubahan lisensi. Apabila X berubah, maka Y dan Z diatur menjadi 0.
- Y mengindikasikan pengubahan pada tampilan, penambahan/pengubahan fitur, dan hal lainnya yang bersifat mencolok. Apabila Y berubah, maka Z diatur menjadi 0.
- Z mengindikasikan perbaikan dan pengubahan pada program yang tidak tampak dengan jelas pada saat program dijalankan seperti perbaikan bug, refaktorisasi kode, dan pengubahan dokumentasi. Tidak ada batasan nomor pada nilai Z.
- A sebagai penanda kompatibilitas platform dari aplikasi.

contoh:
![Gambar Alur Pengembangan](https://github.com/karazubald/anwan/blob/main/img/alur-pengembangan-contoh.png)
```
Alur pengembangan pertama
0.0.0-Desktop (Rilis pertama yang ditujukan untuk platform: Desktop)
OpenJDK 8

Alur pengembangan kedua
0.2.21-Desktop (Penambahan 2 fitur, 16 perbaikan bug, dan 5 dokumentasi)
OpenJDK 8

Alur pengembangan ketiga
1.0.0-Desktop (Pengubahan kode program dan tampilan program karena adanya peningkatan pustaka sistem)
OpenJDK 9

Alur pengembangan keempat, terjadi percabangan di repo
2.0.0-WebApp (Pengubahan bahasa pemrograman dan tampilan program sehingga menyesuaikan dengan target platform: Web, lanjutan dari rilis pengembangan kedua)
Java -> JavaScript, SQLite -> MySQL

2.2.2-Desktop (Pengubahan pustaka sistem dan tampilan program disertai dengan perbaikan 2 bug, lanjutan dari rilis di pengembangan ketiga)
OpenJDK 10

2.2.0-Android (Pengubahan bahasa pemrograman dari Java menjadi Kotlin dan penyesuaian tampilan program sehingga menyesuaikan dengan target platform: Android, lanjutan dari rilis di pengembangan ketiga)
Java -> Kotlin

```

Aturan ini dibuat berdasarkan [Panduan Pembuatan Versi oleh Eclipse](https://wiki.eclipse.org/Version_Numbering)
