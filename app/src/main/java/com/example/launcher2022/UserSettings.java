package com.example.launcher2022;

import android.app.Application;

public class UserSettings extends Application {

    //Ajustes on guardar
    public static final String AJUSTES = "ajustes";
    public static final String NORMAL_THEME = "normalTheme";
    public static final String DARK_THEME = "darkTheme";
    public static final String CUSTOM_THEME = "customTheme";
    public static final boolean CHECKED = false;

    private String checked;
    private String numerote;
    private String customTheme;

    public String getNumerote() {
        return numerote;
    }

    public void setNumerote(String numerote) {
        this.numerote = numerote;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }

    public String getCustomTheme() {
        return customTheme;

    }
}
