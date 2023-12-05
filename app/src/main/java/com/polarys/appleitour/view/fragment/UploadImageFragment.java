package com.polarys.appleitour.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.HomeActivity;
import com.polarys.appleitour.viewmodel.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadImageFragment extends Fragment {

    private Button btn_skip, btn_image;
    private ImageView img_user;
    private UserViewModel viewModel;
    private UIHelper uiHelper;
    private Bitmap bitmap;
    ActivityResultLauncher<Intent> getImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                        img_user.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.d("TAG", e.toString());
                        bitmap = null;
                    }
                    if (bitmap != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] imageBytes = baos.toByteArray();

                        String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP | Base64.URL_SAFE);
                      //  byte[] decode = Base64.decode(imageBytes);
                        //String encodedImage = new BigInteger(1, imageBytes).toString(2);
                        boolean success = viewModel.updatePhoto(new SharedHelper(getContext()).GetToken(), encodedImage);
                        int message = (success) ? R.string.string_photo_success : R.string.string_photo_fail;
                        uiHelper.showSnackBar(message);
                    }
                }
            }
    );
    private User user;

    public UploadImageFragment() {
    }

    public UploadImageFragment(User _user) {
        user = _user;
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
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
        return rotatedImg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_image, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_image = view.findViewById(R.id.btn_send_image);
        btn_skip = view.findViewById(R.id.btn_skip_image);
        viewModel = new UserViewModel();
        img_user = view.findViewById(R.id.img_user);
        View rootView = getActivity().getWindow().getDecorView().getRootView();
        uiHelper = new UIHelper(getContext(),rootView);
        btn_skip.setOnClickListener(v -> {
            getActivity().finish();
            IntentHelper intentHelper = new IntentHelper(getActivity(), IntentHelper.USER_SHARED);
            intentHelper.nextActivity(HomeActivity.class);
        });

        btn_image.setOnClickListener(v -> getImage());
    }

    public void getImage() {
        Intent intentGalery = new Intent(Intent.ACTION_PICK);
        intentGalery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getImage.launch(intentGalery);
    }

}
