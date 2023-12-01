package com.polarys.appleitour.view.fragment;

import static androidx.databinding.DataBindingUtil.setContentView;

import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.viewmodel.PostViewModel;

import java.util.Objects;

public class PostFragment extends Fragment {

    private PostViewModel viewModel;
    private Button btn_post;
    private EditText edit_post;
    private TextView txt_cancelar;
    private Post post;
    private String buttonText = "";
    private boolean edit_mode = false;
    public PostFragment(){}
    public PostFragment(String _buttonText){buttonText = _buttonText;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_post,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        btn_post = view.findViewById(R.id.btnSend);
        btn_post.setText( (buttonText.isEmpty()) ? "Postar" : buttonText);
        edit_post = view.findViewById(R.id.edit_post);
        txt_cancelar = view.findViewById(R.id.txt_cancelar);
        txt_cancelar.setOnClickListener(v -> requireActivity().onBackPressed());
        SharedHelper sharedHelper = new SharedHelper(getContext());
        int userId = sharedHelper.GetId();
        String token = sharedHelper.GetToken();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            edit_mode = true;
            post = (Post) bundle.get(POST_SHARED);
            edit_post.setText(post.GetMessagePost());
        }
        txt_cancelar.setOnClickListener(v-> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        btn_post.setOnClickListener(v->{
            String message = edit_post.getText().toString();
            if(message.length() <= 5){
                Toast.makeText(getContext(),"Escreva um pouco mais",Toast.LENGTH_SHORT).show();
                return;
            }
            String postResult;
            if(edit_mode){
                post.SetMessagePost(message);
                Log.d("Editando", ApiUtil.ObjectToString(post));
                postResult = viewModel.UpdatePost(post,token);
            }
            else {
                post = new Post(userId, message);
                Log.d("Criando", ApiUtil.ObjectToString(post));
                postResult = viewModel.CreatePost(post,token);
            }
            Toast.makeText(getContext(), postResult, Toast.LENGTH_SHORT).show();
        });
    }
}