package com.example.novelshiveandroid.presenters;

import com.example.novelshiveandroid.models.User;

public interface SettingsPresenter {
    void getUserSettings(int userId);
    void changeSettings(User user, Integer font_size, String font_family, String theme);
}
