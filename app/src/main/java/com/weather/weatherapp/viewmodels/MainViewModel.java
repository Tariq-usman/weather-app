package com.weather.weatherapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> data = new MutableLiveData<>();

    public MutableLiveData<Boolean> getData() {
        return data;
    }

    public void updateData(boolean newData) {
        data.setValue(newData);
    }
}
