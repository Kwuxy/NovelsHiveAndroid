package com.example.novelshiveandroid.presenters;

import java.util.ArrayList;

public interface ProfilePresenter {
    void getUserInfos(int userId);
    String convertDescription(ArrayList<Double> doubleData);
}
