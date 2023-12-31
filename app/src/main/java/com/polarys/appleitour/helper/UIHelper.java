package com.polarys.appleitour.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.model.ApiResponse;

import java.net.ContentHandler;

public class UIHelper extends AppCompatActivity {
    private Context context;
    private View view;
    private final int MARGIN_CORRECTION = 8;
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

    public AlertDialog.Builder createTextDialog(int title,int length){
        return createTextDialog(stringFromResource(title),length);
    }
    public AlertDialog.Builder createTextDialog(String title,int length){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton(stringFromResource(R.string.string_dialog_option_cancel), (dialog, which) -> {
            dialog.cancel();
        });
        edittext = new EditText(context);
        edittext.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
        builder.setView(edittext);
        return builder;
    }
    public AlertDialog.Builder createTextDialog(int title,int length,String text){
        return createTextDialog(stringFromResource(title),length,text);
    }
    public AlertDialog.Builder createTextDialog(String title,int length,String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton(stringFromResource(R.string.string_dialog_option_cancel), (dialog, which) -> {
            dialog.cancel();
        });
        edittext = new EditText(context);
        edittext.setFilters(new InputFilter[] { new InputFilter.LengthFilter(length) });
        edittext.setText(text);
        builder.setView(edittext);
        return builder;
    }
    public String getText(){
        try {
            return edittext.getText().toString();
        }
        catch (Exception e){
            return "";
        }
    }
    public void showSnackBar(String message){
        if(view != null){
           // view.setY(20);
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            View _view = snackbar.getView();
            try {
                LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)_view.getLayoutParams();
                params.setMargins(params.leftMargin,params.topMargin,params.rightMargin,params.bottomMargin *MARGIN_CORRECTION);
                _view.setLayoutParams(params);
                snackbar.show();

            }catch (Exception e){
                try{
                    FrameLayout.LayoutParams params=(FrameLayout.LayoutParams)_view.getLayoutParams();
                    params.setMargins(params.leftMargin,params.topMargin,params.rightMargin,params.bottomMargin *MARGIN_CORRECTION);
                    _view.setLayoutParams(params);
                    snackbar.show();
                }
                catch (Exception e1){
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
                }
            }

        }
    }
    public void showSnackBar(int message){
        showSnackBar(stringFromResource(message));
    }
    private String stringFromResource(int message){
        return context.getResources().getString(message);
    }
}