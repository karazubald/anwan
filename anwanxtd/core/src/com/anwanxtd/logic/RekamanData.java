package com.anwanxtd.logic;

import com.anwanxtd.penataAset.KotakTulisan;

public class RekamanData {
    static Integer indeks;
    static boolean[] isiKotakTulisan = {false,false,false,false,false,false,false};

    private static void identifikasiKotakTulisan(KotakTulisan kotakTulisan){
        if(kotakTulisan.getAset_id().toLowerCase().contains("nomor"))
            isiKotakTulisan[0] = true;
            indeks = Integer.valueOf(kotakTulisan.getTulisan());
        if(kotakTulisan.getAset_id().toLowerCase().contains("pertanyaan")) isiKotakTulisan[1] = true;
        if(kotakTulisan.getAset_id().toLowerCase().contains("jawaban"))
            isiKotakTulisan[2] = true;
        if(kotakTulisan.getAset_id().toLowerCase().contains("ideutama"))
            isiKotakTulisan[3] = true;
        if(kotakTulisan.getAset_id().toLowerCase().contains("koding"))
            isiKotakTulisan[4] = true;
        if(kotakTulisan.getAset_id().toLowerCase().contains("tema"))
            isiKotakTulisan[5] = true;
        if(kotakTulisan.getAset_id().toLowerCase().contains("impresi"))
            isiKotakTulisan[6] = true;
    }

    public static void rekam(KotakTulisan kotakTulisan){
        identifikasiKotakTulisan(kotakTulisan);
        for(int iterasi = 1; iterasi <= 6; iterasi++){
            if(isiKotakTulisan[iterasi] == true)
                PenyimpanData.dataWawancara[iterasi-1] = kotakTulisan.getTulisan();
        }
        PenyimpanData.dataMentah.put(indeks, PenyimpanData.dataWawancara);
    }
}
