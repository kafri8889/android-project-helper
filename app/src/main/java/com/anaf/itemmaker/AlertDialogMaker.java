package com.anaf.itemmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;

public class AlertDialogMaker extends ContextWrapper {
    private Context context;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    public AlertDialogMaker(Context base) {
        super(base);

        this.context = base;
        builder = new AlertDialog.Builder(context);
    }

    public void setIcon(int resId) {
        builder.setIcon(resId);
    }

    public void setIcon(Drawable drawable) {
        builder.setIcon(drawable);
    }

    public void createAlertWithNeutralButton(String title, String message, boolean cancelable, String textPositive, DialogInterface.OnClickListener listenerPositive, String textNeutral, DialogInterface.OnClickListener listenerNeutral, String textNegative, DialogInterface.OnClickListener listenerNegative) {
        builder.setCancelable(cancelable).setTitle(title).setMessage(message);
        builder.setPositiveButton(textPositive, listenerPositive);
        builder.setNeutralButton(textNeutral, listenerNeutral);
        builder.setNegativeButton(textNegative, listenerNegative);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void createAlert(String title, String message, boolean cancelable, String textPositive, DialogInterface.OnClickListener listenerPositive, String textNegative, DialogInterface.OnClickListener listenerNegative) {
        builder.setCancelable(cancelable).setTitle(title).setMessage(message);
        builder.setPositiveButton(textPositive, listenerPositive);
        builder.setNegativeButton(textNegative, listenerNegative);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public static class CustomAlert extends ContextWrapper {
        private Context context;
        private View view;
        private AlertDialog.Builder builder;
        private AlertDialog alertDialog;

        public CustomAlert(Context base) {
            super(base);

            this.context = base;
            builder = new AlertDialog.Builder(context);
        }

        public void setIcon(int resId) {
            builder.setIcon(resId);
        }

        public void setIcon(Drawable drawable) {
            builder.setIcon(drawable);
        }

        public void customAlert(View v, String textPositive, DialogInterface.OnClickListener listenerPositive, String textNegative, DialogInterface.OnClickListener listenerNegative) {
            view = v;

            builder.setCancelable(false);
            builder.setView(view);
            builder.setPositiveButton(textPositive, listenerPositive);
            builder.setNegativeButton(textNegative, listenerNegative);

            alertDialog = builder.create();
            alertDialog.show();
        }

        public void customAlert(String title, String message, View v, String textPositive, DialogInterface.OnClickListener listenerPositive, String textNegative, DialogInterface.OnClickListener listenerNegative) {
            view = v;

            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setView(view);
            builder.setPositiveButton(textPositive, listenerPositive);
            builder.setNegativeButton(textNegative, listenerNegative);

            alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
