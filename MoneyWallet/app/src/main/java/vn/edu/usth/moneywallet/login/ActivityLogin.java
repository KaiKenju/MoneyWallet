package vn.edu.usth.moneywallet.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import vn.edu.usth.moneywallet.MainActivity;
import vn.edu.usth.moneywallet.R;
import vn.edu.usth.moneywallet.fragment.FragmentHome;


public class ActivityLogin extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    ImageView btn_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        btn_google = findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);


        } catch (ApiException e){

        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // redirect to userdetail activity
            String email = account.getEmail();
            String name = account.getDisplayName();
            String picture = String.valueOf(account.getPhotoUrl());

            // Create a new FragmentHome instance and set arguments
            FragmentHome fragmentHome = new FragmentHome();
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("name", name);
            bundle.putString("picture", picture);
            fragmentHome.setArguments(bundle);

            // Replace the current fragment with FragmentHome
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_GV, fragmentHome)  // Replace 'fragment_container' with your actual fragment container ID
                    .commit();
        }
    }



}