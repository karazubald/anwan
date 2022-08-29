package com.anwanxtd.logic;

public enum Tema {
    AZURE, ORANGE, GREEN, DARK;

    public String temaAplikasi() {
        return this.toString().toLowerCase();
    }
}
