package com.anwanxtd.penataAset;

import com.anwanxtd.logic.Tema;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class KotakTulisan {
    private String aset_id;
    private String tulisan;
    private TextureAtlas atlas;
    private TextureRegionDrawable gambar_up, gambar_down, gambar_cek;
    private TextButton tombol;
    private TextButton.TextButtonStyle desainTombol;

    /**
     * Instansiasi kotak tulisan
     * @param tulisan satu atau lebih kata yang akan dituliskan saat kotak tulisan dimunculkan pertama kali
     * @param tema nama Tema yang saat ini diterapkan di aplikasi
     * @param namaAset nama atau jenis kotak tulisan ini
     * @param huruf nama huruf yang sedang dipakai
     * @return tombol kotak tulisan
     */
    public TextButton KotakTulisan(String tulisan, Tema tema, String namaAset, BitmapFont huruf){
        aset_id = namaAset;
        this.tulisan = tulisan;
        atlas = new TextureAtlas(Gdx.files.internal(tema.temaAplikasi()+".atlas"));
        gambar_up = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        gambar_down = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        gambar_cek = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        desainTombol = new TextButton.TextButtonStyle(gambar_up, gambar_down, gambar_cek, huruf);

        tombol = new TextButton(tulisan, desainTombol);
        return tombol;
    }

    public void ubahWarna(TextButton tombol, Color warnaHuruf){
        desainTombol.fontColor = warnaHuruf;
        tombol.setStyle(desainTombol);
    }

    public void ubahTema(TextButton tombol, Tema tema){
        atlas = new TextureAtlas(Gdx.files.internal(tema.temaAplikasi()+".atlas"));
        gambar_up = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        gambar_down = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        gambar_cek = new TextureRegionDrawable(atlas.findRegion(tema.temaAplikasi() + aset_id));
        desainTombol = new TextButton.TextButtonStyle(gambar_up, gambar_down, gambar_cek, tombol.getStyle().font);
        tombol.setStyle(desainTombol);
    }

    public String getTulisan() {
        return tulisan;
    }

    public String getAset_id() {
        return aset_id;
    }

    public void setTulisan(String tulisan) {
        this.tulisan = tulisan;
    }
}
