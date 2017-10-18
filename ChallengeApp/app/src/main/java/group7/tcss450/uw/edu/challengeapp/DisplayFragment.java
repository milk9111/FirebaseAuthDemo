package group7.tcss450.uw.edu.challengeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class DisplayFragment extends Fragment {

    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getArguments() != null) {
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            updateDisplay(email);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }


    public void updateDisplay (String email) {
        TextView userName = (TextView) getActivity().findViewById(R.id.email_display);
        userName.setText(email);
    }

}
