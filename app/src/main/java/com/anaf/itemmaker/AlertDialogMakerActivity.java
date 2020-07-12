package com.anaf.itemmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AlertDialogMakerActivity extends AppCompatActivity {

    AlertDialogMaker maker;
    AlertDialogMaker.CustomAlert makerCustomAlert;
    View view;

    EditText editText;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_maker);

        makerCustomAlert = new AlertDialogMaker.CustomAlert(AlertDialogMakerActivity.this);
        maker = new AlertDialogMaker(AlertDialogMakerActivity.this);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_alert, null, false);
        editText = view.findViewById(R.id.editTextCustomAlert);

        makerCustomAlert.customAlert(view, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }, "cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
