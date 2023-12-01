package com.polarys.appleitour.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.model.ApiResponse;

import java.net.ContentHandler;

public class UIHelper extends AppCompatActivity {
    private Context context;
    EditText edittext;
    public UIHelper(Context _context){context = _context;}
    public AlertDialog.Builder createDialog(String title,String message,String no){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
          builder.setNegativeButton(no, (dialog, which) -> {
            dialog.cancel();
        });
        return builder;
    }

    public AlertDialog.Builder createTextDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.cancel();
        });
        edittext = new EditText(context);
        builder.setView(edittext);
        return builder;
    }
    public String getText(){
        try {
            return edittext.getText().toString();
        }
        catch (Exception e){
            return "TESTE";
        }
    }
}