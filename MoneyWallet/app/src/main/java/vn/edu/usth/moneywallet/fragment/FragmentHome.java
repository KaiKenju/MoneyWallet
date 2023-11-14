package vn.edu.usth.moneywallet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vn.edu.usth.moneywallet.R;


public class FragmentHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Retrieve data from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            String email = bundle.getString("email");
            String name = bundle.getString("name");
            String pictureUrl = bundle.getString("picture");

            // Use the data in your fragment as needed
            ImageView ivProfile = view.findViewById(R.id.iv_profile);
            Glide.with(this)
                    .load(pictureUrl)
                    .circleCrop()
                    .into(ivProfile);

            TextView tvEmail = view.findViewById(R.id.tv_email);
            tvEmail.setText("Email " + email);

            TextView tvName = view.findViewById(R.id.tv_name);  // Corrected the TextView ID
            tvName.setText("Name " + name);
        }

        return view;
    }
}