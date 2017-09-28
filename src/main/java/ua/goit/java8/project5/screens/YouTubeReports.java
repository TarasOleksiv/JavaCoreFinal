package ua.goit.java8.project5.screens;

import ua.goit.java8.project5.extra.SettingsSet;

/**
 * Created by t.oleksiv on 28/09/2017.
 */

// Клас створений для того щоб розділити графічну частину (YouTubeAnalyticsScreen) і аналітичну (YouTubeReports)
// в цей клас закидаєм методи для виконання дій з екрану YouTubeAnalyticsScreen
// Результати запитів з методів цього класу виводитимуться на екран YouTubeAnalyticsScreen

public class YouTubeReports {
    private SettingsSet settingsSet;

    public YouTubeReports(SettingsSet settingsSet){
        this.settingsSet = settingsSet;
    }
}
