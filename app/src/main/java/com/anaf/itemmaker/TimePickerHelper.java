package com.anaf.itemmaker;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

public class TimePickerHelper extends ContextWrapper {
    View view;
    TimePicker timePicker;

    public TimePickerHelper(Context base) {
        super(base);
    }

    public void findViewById(int id) {
        timePicker = view.findViewById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public int getHour() {
        return timePicker.getHour();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public int getMinute() {
        return timePicker.getMinute();
    }

    public int getCurrentHour() {
        return timePicker.getCurrentHour();
    }

    public int getCurrentMinute() {
        return timePicker.getCurrentMinute();
    }

    public void setIs24Hour() {
        timePicker.setIs24HourView(true);
    }

    public void setCurrentHour(int hour) {
        timePicker.setCurrentHour(hour);
    }

    public void setCurrentMinute(int minute) {
        timePicker.setCurrentMinute(minute);
    }
}
