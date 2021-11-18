# Kontribusi Kode (Github dan Gitlab)
1. Silakan mengunduh proyek dari Gitlab atau Github.
- via Github
```
git clone https://github.com/karazubald/anwan.git
```
- via Gitlab
```
git clone https://gitlab.com/karazubald/anwan.git
```
2. Masuk ke direktori anwan dan buka terminal atau command line di direktori anwan.

3. Buat cabang(<i>branch</i>) baru di terminal atau command line dengan format berikut.
```
git checkout -b kontribusi_namaDepanAnda_namaBelakangAnda
```
misalnya nama Anda Kara Zubald, maka di terminal atau command line diketik sebagai berikut.
```
git checkout -b kontribusi_Kara_Zubald
```
4. Lakukan modifikasi sesuai keinginan Anda (misal menambahkan fitur atau <i>file</i> baru, mengubah struktur <i>folder</i>, memperbaiki <i>bug</i> di kode, dsb).
5. Catat modifikasi yang Anda lakukan dengan git add dan commit sesuai format berikut.
```
git add .
git commit -m "rincian modifikasi yang Anda lakukan"
```
contoh:
- Mengubah kode di dalam Layar_0.java, maka di terminal atau command line ditulis sebagai berikut.
```
git add .
git commit -m "menambahkan tombol tidak setuju, menambahkan metode keluar dari aplikasi"
```
- Mengubah struktur <i>folder</i>, maka di terminal atau command line ditulis sebagai berikut.
```
git add .
git commit -m "Memindahkan file fxml ke folder baru bernama FXML"
```
- Memperbaiki <i> bug </i> dalam kode, maka di terminal atau command line ditulis sebagai berikut.
```
git add .
git commit -m "Memperbaiki bug: aplikasi tidak dapat memuat file di Layar_1.fxml"
```
6. Apabila Anda sudah selesai melakukan modifikasi, silakan unggah hasil kontribusi Anda menggunakan git push dengan format berikut.
```
git push -f origin kontribusi_namaDepanAnda_namaBelakangAnda
```
misalnya nama Anda Kara Zubald, maka di terminal atau command line diketik sebagai berikut.
```
git push -f origin kontribusi_Kara_Zubald
```
