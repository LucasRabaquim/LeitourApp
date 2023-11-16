package com.polarys.appleitour.view.fragment;

import static androidx.databinding.DataBindingUtil.setContentView;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.helper.IntentHelper.POST_SHARED;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.view.adapter.PostAdapter;
import com.polarys.appleitour.viewmodel.SocialViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class PostFragment extends Fragment {

    private SocialViewModel viewModel;
    private Button btn_post,btn_delete;
    private EditText edit_post;
    private TextView txt_cancelar;

    private Post post;
    private boolean edit_mode = false;

    public PostFragment(){}

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
        viewModel = ViewModelProviders.of(this).get(SocialViewModel.class);
        btn_post = view.findViewById(R.id.btnPost);
        edit_post = view.findViewById(R.id.edit_post);
        btn_delete = view.findViewById(R.id.btnDeletePost);
        txt_cancelar = view.findViewById(R.id.txt_cancelar);
        SharedHelper sharedHelper = new SharedHelper(getContext());
        int userId = sharedHelper.GetId();
        String token = sharedHelper.GetToken();
        Bundle bundle = this.getArguments();

        if(bundle != null){
            edit_mode = true;
            post = (Post) bundle.get(POST_SHARED);
            edit_post.setText(post.getMessagePost());
            btn_delete.setVisibility(View.VISIBLE);
        }
        txt_cancelar.setOnClickListener(v-> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        btn_delete.setOnClickListener(v -> {new Post().DeletePost(post.getId(),ObjectToString(post),token);});
        btn_post.setOnClickListener(v->{
            String message = edit_post.getText().toString();
            post = new Post(userId, message);
            if(message.length() <= 5){
                Toast.makeText(getContext(),"Escreva um pouco mais",Toast.LENGTH_SHORT).show();
                return;
            }
            if(edit_mode){
                ApiResponse response = new Post().UpdatePost(post.getId(),ObjectToString(post),token);
                if(response.getCode() == 200 | response.getCode() == 201)
                    Toast.makeText(getContext(),"Post alterado",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),response.getBody(),Toast.LENGTH_SHORT).show();

            }
            else {
                ApiResponse response = new Post().PostPost(ObjectToString(post), token);
                if (response.getCode() == 200 | response.getCode() == 201)
                    Toast.makeText(getContext(), "Post Criado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), response.getBody(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}