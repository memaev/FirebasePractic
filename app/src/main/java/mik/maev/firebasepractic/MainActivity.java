package mik.maev.firebasepractic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

import mik.maev.firebasepractic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        binding.register.setOnClickListener(v -> {
            if (binding.email.getText().toString().isEmpty() || binding.password.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }else {
                mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString()).
                        addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                String uid = mAuth.getCurrentUser().getUid();
                                ref.child("Users").child(uid).setValue(binding.email.getText().toString());
//                                String uid = mAuth.getCurrentUser().getUid();
                            }else{

                            }
                        });
            }
        });

    }
}