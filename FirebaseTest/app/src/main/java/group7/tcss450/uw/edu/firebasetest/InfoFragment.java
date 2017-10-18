package group7.tcss450.uw.edu.firebasetest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class InfoFragment extends Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        /*if (getArguments() != null) {
            String email = getArguments().getString(MainActivity.EMAIL_KEY);
            String password = getArguments().getString(MainActivity.PASSWORD_KEY);
            showCredentials(email, password);
        }*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            Log.w("FIREBASE", "User email: " + user.getEmail());
            Log.w("FIREBASE", "User display name: " + user.getDisplayName());
            showCredentials(user.getEmail(), user.getDisplayName());
        } else {
            Log.w("FIREBASE", "User null, sign in didn't work");
        }
    }


    private void showCredentials (String email, String displayName) {
        ((TextView) getActivity().findViewById(R.id.show_email)).setText(email);
        ((TextView) getActivity().findViewById(R.id.show_password)).setText(displayName);
    }
}
