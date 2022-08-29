package com.anwanxtd.logic;

import java.util.HashMap;

public class MuatData {
    private static HashMap<Integer, String[]> dataWawancara;
    public static HashMap<Integer, String[]> data(PenyimpanData.tipeData tipeData){
        dataWawancara = null;
        switch (tipeData){
            case DATA_MENTAH:
                dataWawancara = PenyimpanData.dataMentah;
                break;
            case DATA_FILTRASI:
                dataWawancara = PenyimpanData.dataFiltrasi;
                break;
        }
        return dataWawancara;
    }
}
