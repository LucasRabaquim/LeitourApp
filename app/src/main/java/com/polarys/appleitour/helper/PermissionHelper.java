package com.polarys.appleitour.helper;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionHelper extends AppCompatActivity {
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private final static String CAMERA_PERMISSION = CAMERA;
    private final static String STORAGE_PERMISSION = READ_EXTERNAL_STORAGE;
    private ArrayList permissionsToRequest;
    private final ArrayList permissionsRejected = new ArrayList();
    private final ArrayList permissions = new ArrayList();
    private final Context context;

    public PermissionHelper(Context _context) {
        context = _context;
    }

    private boolean verifyImagePermission() {
        final boolean CAMERA = ContextCompat.checkSelfPermission(context, CAMERA_PERMISSION) != PackageManager.PERMISSION_DENIED;
        final boolean STORAGE = ContextCompat.checkSelfPermission(context, STORAGE_PERMISSION) != PackageManager.PERMISSION_DENIED;
        return CAMERA && STORAGE;
    }

    private void askImagePermission() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{CAMERA_PERMISSION}, CAMERA_PERMISSION_CODE);
        ActivityCompat.requestPermissions((Activity) context, new String[]{STORAGE_PERMISSION}, STORAGE_PERMISSION_CODE);
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

            else{

            }
        }
    }*/
}