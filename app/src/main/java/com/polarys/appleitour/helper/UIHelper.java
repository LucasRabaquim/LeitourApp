package com.polarys.appleitour.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.model.ApiResponse;

import java.net.ContentHandler;

public class UIHelper extends AppCompatActivity {
    private Context context;
    private View view;
    EditText edittext;
    public UIHelper(Context _context){context = _context;}
    public UIHelper(Context _context,View _view){context = _context;view = _view;}
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
    public AlertDialog.Builder createDialog(int title,int message,int no){
        return createDialog(stringFromResource(title),stringFromResource(message),stringFromResource(no));
    }

    public AlertDialog.Builder createTextDialog(int title){
        return createTextDialog(stringFromResource(title));
    }
    public AlertDialog.Builder createTextDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton(stringFromResource(R.string.string_dialog_option_cancel), (dialog, which) -> {
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
            return "TESTE " +e;
        }
    }
    public void showSnackBar(String message){
        if(view != null)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
    public void showSnackBar(int message){
        showSnackBar(stringFromResource(message));
    }
    private String stringFromResource(int message){
        return context.getResources().getString(message);
    }
}