package com.anwanxtd.logic;

import java.util.HashMap;

public class PenyimpanData {
    static String[] dataWawancara = new String[5];
    private static Integer nomorDataWawancara = 0;
    static HashMap<Integer, String[]> dataMentah = new HashMap<>();
    static HashMap<Integer, String[]> dataFiltrasi = new HashMap<>();

    public enum tipeData {
        DATA_MENTAH,
        DATA_FILTRASI;
    }
}
