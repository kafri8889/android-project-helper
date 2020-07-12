package com.anaf.itemmaker;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class HardwareHelper extends ContextWrapper {
    private BluetoothAdapter adapter;
    private WifiManager manager;
    private Intent intentCamera;

    private boolean wifiOrBluetooth;

    public HardwareHelper(Context base, Activity a, boolean wifiTrue_or_bluetoothFalse) {
        super(base);
        this.wifiOrBluetooth = wifiTrue_or_bluetoothFalse;
        ActivityCompat.requestPermissions(a, new String[] {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.CHANGE_WIFI_STATE}, 1);
        manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public int requestCode() {
        return 927;
    }

    public boolean isNull() {
        if (wifiOrBluetooth) {
            return manager == null;
        }

        else {
            return adapter == null;
        }
    }

    public boolean notNullBluetooth() {
        if (wifiOrBluetooth) {
            return manager != null;
        }

        else {
            return adapter != null;
        }
    }

    public void checkSupportHardware() {
        if (wifiOrBluetooth) {
            if (manager == null) {
                Toast.makeText(this, "this Device not Supported Wi-Fi", Toast.LENGTH_SHORT).show();
            }

            else {
                Log.e("WIFI", "this Device Supported Wifi");
            }
        }

        else {
            if (adapter == null) {
                Toast.makeText(this, "this Device not Supported Bluetooth", Toast.LENGTH_SHORT).show();
            }

            else {
                Log.e("BLUETOOTH", "this Device Supported Bluetooth");
            }
        }

    }

    public void enableAndDisableHardware() {
        if (wifiOrBluetooth) {
            if (manager.isWifiEnabled()) {
                manager.setWifiEnabled(false);
            }

            else {
                manager.setWifiEnabled(true);
            }
        }

        else {
            if (adapter.isEnabled()) {
                adapter.disable();
            }

            else {
                adapter.enable();
            }
        }
    }

    public Intent launchIntentCameraVideo() {
        try {
            Intent i = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
            intentCamera = i;
        } catch (Exception e) {
            Toast.makeText(this, "you must startActivityOnRresult(helper.launcIntentCameraVideo(), helper.requestCode());", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return intentCamera;
    }

}
