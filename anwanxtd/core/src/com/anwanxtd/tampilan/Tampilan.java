package com.anwanxtd.tampilan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Tampilan extends Viewport {
    private float lebarMin, tinggiMin;
    private float lebarMax, tinggiMax;
    private float LEBAR, TINGGI, aspekRasio, lebarVirtual, tinggiVirtual, aspekRasioVirtual;

    public Tampilan(float lebarMin, float tinggiMin){
        this(lebarMin, tinggiMin, 0, 0, new OrthographicCamera());
    }

    public Tampilan(float lebarMin, float tinggiMin, Camera kamera){
        this(lebarMin, tinggiMin, 0, 0, kamera);
    }

    public Tampilan(float lebarMin, float tinggiMin, float lebarMax, float tinggiMax){
        this(lebarMin, tinggiMin, lebarMax, tinggiMax, new OrthographicCamera());
    }

    public Tampilan (float lebarMin, float tinggiMin, float lebarMax, float tinggiMax, Camera kamera) {
        this.lebarMin = lebarMin;
        this.tinggiMin = tinggiMin;
        this.lebarMax = lebarMax;
        this.tinggiMax = tinggiMax;
        setCamera(kamera);
    }

    /**
     * Override terhadap metode update dari Viewport biasa.
     * Meyesuaikan tampilan agar sesuai dengan aspek rasio tertentu.
     * Apabila layar tidak sesuai dengan aspek rasio yang telah ditentukan, maka layar
     * akan dibuat melebar atau meninggi sehingga tidak ada bayang hitam di layar.
     *
     * Aspek rasio yang saat ini digunakan didasarkan pada lebarVirtual dan tinggiVirtual.
     * @<code>
     *     lebarVirtual = 9;
     *     tinggiVirtual = 16;
     *     aspekRasioVirtual = lebarVirtual/tinggiVirtual;
     * </code>
     * @param lebarLayar lebar layar dalam satuan pixel
     * @param tinggiLayar tinggi layar dalam satuan pixel
     * @param kameraTengah boolean untuk mengaktifkan fokus kamera ke tengah layar
     */
    @Override
    public void update(int lebarLayar, int tinggiLayar, boolean kameraTengah){
        // Menyesuaikan ukuran minimal ke layar
        float lebar = lebarMin;
        float tinggi = tinggiMin;
        LEBAR = Gdx.graphics.getWidth();
        TINGGI = Gdx.graphics.getHeight();
        aspekRasio = LEBAR/TINGGI;
        lebarVirtual = 9;
        tinggiVirtual = 16;
        aspekRasioVirtual = lebarVirtual/tinggiVirtual;
        Vector2 skala = Scaling.fit.apply(lebar, tinggi, lebarLayar, tinggiLayar);
        // Meregangkan tampilan
        int lebarTampilan = Math.round(skala.x);
        int tinggiTampilan = Math.round(skala.y);

        if (lebarTampilan < lebarLayar) {
            float layarTampilan = tinggiTampilan / tinggi;
            float toWorldSpace = tinggi / tinggiTampilan;
            float pengisiRuang = (lebarLayar - lebarTampilan) * toWorldSpace;
            if (lebarMax > 0) pengisiRuang = Math.min(pengisiRuang, lebarMax - lebarMin);
            lebar += pengisiRuang;
            lebarTampilan += Math.round(pengisiRuang * layarTampilan);
        } else if (tinggiTampilan < tinggiLayar) {
            float layarTampilan = lebarTampilan / lebar;
            float toWorldSpace = lebar / lebarTampilan;
            float pengisiRuang = (tinggiLayar - tinggiTampilan ) * toWorldSpace;
            if (tinggiMax > 0) pengisiRuang = Math.min(pengisiRuang, tinggiMax - tinggiMin);
            tinggi += pengisiRuang;
            tinggiTampilan += Math.round(pengisiRuang * layarTampilan);
        }
        setWorldSize(lebar, tinggi);
        setScreenBounds((lebarLayar - lebarTampilan) / 2, (tinggiLayar - tinggiTampilan) / 2, lebarTampilan, tinggiTampilan);

        if( aspekRasioVirtual < aspekRasio)
            apply(false);
        else
            apply(kameraTengah);
    }

    public float getLebarMin () {
        return lebarMin;
    }

    public void setLebarMin (float lebarMin) {
        this.lebarMin = lebarMin;
    }

    public float getTinggiMin () {
        return tinggiMin;
    }

    public void setTinggiMin (float tinggiMin) {
        this.tinggiMin = tinggiMin;
    }

    public float getLebarMax () {
        return lebarMax;
    }

    public void setLebarMax (float lebarMax) {
        this.lebarMax = lebarMax;
    }

    public float getTinggiMax () {
        return tinggiMax;
    }

    public void setTinggiMax (float tinggiMax) {
        this.tinggiMax = tinggiMax;
    }
}
