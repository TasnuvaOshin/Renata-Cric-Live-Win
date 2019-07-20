package cricketworldcup.icccricketworldcup;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        storageReference  = FirebaseStorage.getInstance().getReference().child("Folder");
       databaseReference = FirebaseDatabase.getInstance().getReference().child("image");
    }

    public void PickImage(View view) {

        Intent i  = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);






        if (requestCode == 1 && resultCode == RESULT_OK) {

            final Uri uri = data.getData();
            //this is for image file name
            final StorageReference filepath = storageReference.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("link", String.valueOf(uri));
                            databaseReference.push().setValue(hashMap);
                            Toast.makeText(MainActivity.this, "Done"+uri, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }







    }
}
