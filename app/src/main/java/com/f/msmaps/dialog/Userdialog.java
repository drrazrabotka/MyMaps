package com.f.msmaps.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.f.msmaps.R;

import androidx.appcompat.app.AppCompatActivity;

public class Userdialog {
    private Context context;

    public Userdialog(Context context) {
        this.context = context;
    }

    public void createDialog(String text) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setNegativeButton(context.getString(R.string.dialog_btn), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        final AlertDialog builder = dialog.create();
        LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_user, null);
        builder.setView(dialogLayout);
        builder.show();


        TextView textView = builder.findViewById(R.id.textViewDialog);
        textView.setText(text);

    }
}
