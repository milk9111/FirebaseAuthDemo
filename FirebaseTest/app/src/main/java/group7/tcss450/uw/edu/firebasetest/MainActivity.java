package group7.tcss450.uw.edu.firebasetest;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener {

    private static final String AT_SYMBOL = "@";
    private static final String DOT_SYMBOL = ".";

    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("FIREBASE", "Signed in user id: " + user.getUid());
                } else {
                    Log.d("FIREBASE", "User signed out");
                }
            }
        };

        if (savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new RegisterFragment())
                        .commit();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void createAccount (String email, String password) {
        if (isValidEmail(email) && isValidPassword(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("FIREBASE", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            } /*else {
                                Toast.makeText(MainActivity.this, R.string.auth_passed,
                                        Toast.LENGTH_SHORT).show();
                            }*/
                        }
                    });


            /*mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("FIREBASE", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("FIREBASE", "signInWithEmail:failed", task.getException());
                                Toast.makeText(MainActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });*/
        }
    }


    public void signIn (String email, String password) {
        if (isValidEmail(email) && isValidPassword(password)) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("FIREBASE", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("FIREBASE", "signInWithEmail:failed", task.getException());
                                Toast.makeText(MainActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        private boolean isValidEmail(String email) {
            boolean isValid = false;

            if (email.contains(AT_SYMBOL)) {
                int delimiter = email.indexOf(AT_SYMBOL);
                String suffix = email.substring(delimiter);
                if (suffix.contains(DOT_SYMBOL)) {
                    String prefix = email.substring(0, delimiter);
                    if (!prefix.equals("")) {
                        isValid = true;
                    }
                }
            }

            return isValid;
        }


        private boolean isValidPassword(String password) {
            boolean isValid = false;

            if (password.length() > 6) {
                isValid = true;
            }

            return isValid;
        }
    }





    @Override
    public void onRegisterInteraction(String email, String password) {
        Log.d("FIREBASE", "Inside onRegisterInteraction, about to create account");
        createAccount(email, password);

        InfoFragment infoFragment = new InfoFragment();
        Bundle args = new Bundle();

        infoFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, infoFragment)
                .addToBackStack(null);

        transaction.commit();
    }


    @Override
    public void onSigninInteraction(String email, String password) {

    }
}

















