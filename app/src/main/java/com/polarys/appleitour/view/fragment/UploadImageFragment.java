package com.polarys.appleitour.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.ImageHelper;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.view.customview.ImageCircleCustomView;
import com.polarys.appleitour.viewmodel.UserViewModel;

import java.io.File;
import java.io.IOException;

public class UploadImageFragment extends Fragment {

    private Button btn_skip,btn_image;
    private ImageView img_user;
    UserViewModel viewModel;
    Bitmap bitmap;
    private User user;
    public UploadImageFragment(){}
    public UploadImageFragment(User _user){user = _user;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_user_image,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_image = view.findViewById(R.id.btn_send_image);
        btn_skip = view.findViewById(R.id.btn_skip_image);
        viewModel = new UserViewModel();
        img_user = view.findViewById(R.id.img_user);
        btn_skip.setOnClickListener(v -> {
            getActivity().finish();
            IntentHelper intentHelper = new IntentHelper(getActivity(),IntentHelper.USER_SHARED);
            intentHelper.nextActivity(PlaceholderActivity.class);
        });

        btn_image.setOnClickListener(v -> {
           getImage();
        });



    }
    public void getImage(){
        Intent intentGalery = new Intent(Intent.ACTION_PICK);
        intentGalery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getImage.launch(intentGalery);
    }

    ActivityResultLauncher<Intent> getImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);

                  //      bitmap = rotateImageIfRequired(bitmap,imageUri);
                        img_user.setImageBitmap(bitmap);
                        user.SetProfilePhoto(bitmap);

                    } catch (IOException e) {
                        Log.d("TAG", e.toString());
                        bitmap = null;
                    }
                    if(bitmap != null){
                        boolean success = viewModel.update(new SharedHelper(getContext()).GetToken(),user);
                        Log.d("TAG", "Sucesso: "+success);
                    }
                }
            }
    );

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(new File(selectedImage.toString()));
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

}
