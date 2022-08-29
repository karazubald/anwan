package com.anwanxtd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class PabrikHuruf {
    private static FreeTypeFontGenerator fontGenerator;
    private static FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private static BitmapFont bitmapFont;

    private static void initFont(String namaFont, int ukuranFont, Color warnaFont){
        String lokasiFont = namaFont + ".ttf";
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+lokasiFont));
        fontParameter.size = ukuranFont;
        fontParameter.color = warnaFont;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
    }

    public static BitmapFont buatFont(String namaFont, int ukuranFont, Color warnaFont){
        initFont(namaFont, ukuranFont, warnaFont);
        return bitmapFont;
    }

    public static void dispose(){
        fontGenerator.dispose();
        bitmapFont.dispose();
    }
}
