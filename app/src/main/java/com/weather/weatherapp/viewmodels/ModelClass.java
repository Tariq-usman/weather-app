package com.weather.weatherapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModelClass extends ViewModel {

    private MutableLiveData<Integer> liveData = new MutableLiveData<>();

    public void setLiveData(int data) {
        liveData.setValue(data);
    }

    public MutableLiveData<Integer> getLiveData() {
        return liveData;
    }
}
