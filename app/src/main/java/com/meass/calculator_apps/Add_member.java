package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class Add_member extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("নতুন সদস্য");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);

        //edittext
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        fatherba=findViewById(R.id.fatherba);
        fatheren=findViewById(R.id.fatheren);
        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address);
        ppaddress=findViewById(R.id.ppaddress);
        votar=findViewById(R.id.votar);
        natii=findViewById(R.id.natii);
        b_date=findViewById(R.id.b_date);
        s_kisti=findViewById(R.id.s_kisti);
        ocupa=findViewById(R.id.ocupa);
        relagi=findViewById(R.id.relagi);
        refer=findViewById(R.id.refer);
        pasword=findViewById(R.id.pasword);
        signeture=findViewById(R.id.signeture);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        s_name=findViewById(R.id.s_name);
        relation=findViewById(R.id.relation);
        s_paddress=findViewById(R.id.s_paddress);
        s_ppaer=findViewById(R.id.s_ppaer);
        datee=findViewById(R.id.datee);
        mysigne=findViewById(R.id.mysigne);
        porichoy=findViewById(R.id.porichoy);
        userimage=findViewById(R.id.userimage);
        //edit
        namefirst=findViewById(R.id.namefirst);
        namesecond=findViewById(R.id.namesecond);
        onomoshonsig=findViewById(R.id.onomoshonsig);
        date2=findViewById(R.id.date2);
        idNumber=findViewById(R.id.idNumber);
        takakka=findViewById(R.id.takakka);
        grohonsa=findViewById(R.id.grohonsa);
        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 21);
            }
        });
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        ZoneId z = ZoneId.of( "Asia/Dhaka" );
        LocalDate today = LocalDate.now( z );
        datee.setText(""+today);
        date2.setText(""+today);
        final int min = 111;
        final int max = 999;
        final int min1 = 3000;
        final int max1 = 500000;
        final int random = new Random().nextInt((max - min) + 1) + min;
        idNumber.setText(""+random);
        namefirst.addTextChangedListener(textWatcher);
        somitiname.addTextChangedListener(somitiwatcher);

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(somitiname.getText().toString())||TextUtils.isEmpty(sovapoti.getText().toString())||TextUtils.isEmpty(signeture.getText().toString())||TextUtils.isEmpty(pasword.getText().toString())||TextUtils.isEmpty(refer.getText().toString())||
                        TextUtils.isEmpty(ocupa.getText().toString())||TextUtils.isEmpty(s_kisti.getText().toString())||TextUtils.isEmpty(b_date.getText().toString())||TextUtils.isEmpty(natii.getText().toString())||TextUtils.isEmpty(votar.getText().toString())||
                        TextUtils.isEmpty(ppaddress.getText().toString())||TextUtils.isEmpty(p_address.getText().toString())||TextUtils.isEmpty(mother.getText().toString())||TextUtils.isEmpty(fatheren.getText().toString())||TextUtils.isEmpty(fatherba.getText().toString())||
                        TextUtils.isEmpty(sovapoti_english.getText().toString())||TextUtils.isEmpty(s_name.getText().toString())||TextUtils.isEmpty(relagi.getText().toString())||TextUtils.isEmpty(porichoy.getText().toString())||TextUtils.isEmpty(mysigne.getText().toString())||
                        TextUtils.isEmpty(datee.getText().toString())||TextUtils.isEmpty(s_ppaer.getText().toString())||TextUtils.isEmpty(s_paddress.getText().toString())||TextUtils.isEmpty(relation.getText().toString())||TextUtils.isEmpty(namefirst.getText().toString())||
                        TextUtils.isEmpty(namesecond.getText().toString())||TextUtils.isEmpty(onomoshonsig.getText().toString())||TextUtils.isEmpty(date2.getText().toString())||TextUtils.isEmpty(idNumber.getText().toString())||TextUtils.isEmpty(takakka.getText().toString())||TextUtils.isEmpty(grohonsa.getText().toString())) {

                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    if (flag==1) {
                        Toasty.warning(getApplicationContext(),"ব্যবহারকারীর ছবি নির্বাচন করুন",Toasty.LENGTH_SHORT,true).show();

                        return;

                    }
                    else {
                        firebaseFirestore.collection("SomitiMember")
                                .document(firebaseAuth.getCurrentUser().getEmail())
                                .collection("List")
                                .document(sovapoti_english.getText().toString().toLowerCase())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {
                                                Toasty.error(getApplicationContext(),"এই সদস্যটি ইতিমধ্যে বিদ্যমান। সদস্য নামের জন্য অন্য একটি নাম দিন",Toasty.LENGTH_SHORT,true).show();
                                                return;
                                            }
                                            else {
                                                uploadImage();
                                            }
                                        }
                                    }
                                });

                    }
                }

            }
        });




    }
    TextWatcher somitiwatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
check1=s.toString();
if (TextUtils.isEmpty(check1)) {
}
else {
   /// Toast.makeText(Add_member.this, ""+check1, Toast.LENGTH_SHORT).show();
    firebaseFirestore.collection("SomitiList")
            .document(firebaseAuth.getCurrentUser().getEmail())
            .collection("List")
            .document(check1.toString().toLowerCase())
            .get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            cirLoginButton.setEnabled(true);
                            somitiname.setError(" সমিতির নাম সঠিক");
                           // Toasty.success(getApplicationContext(),,Toasty.LENGTH_SHORT,true).show();
                            return;
                        }
                        else
                        {
                            cirLoginButton.setEnabled(true);
                            somitiname.setError(" সমিতির নাম সঠিক নয়");
                            //Toasty.info(getApplicationContext()," সমিতির নাম সঠিক ",Toasty.LENGTH_SHORT,true).show();
                            return;
                        }
                    }
                }
            });

}
        }
    };
    String check1;
    String check;
    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
check=s.toString();
if (TextUtils.isEmpty(check)) {

}
else {
    namesecond.setText(check+" কে " +
            "আত্র সমিতির সদস্যপদ দেওয়ার জন্য আনুমতি " +
            "প্রদান করা হল।");
}
        }
    };
    EditText namefirst,namesecond,onomoshonsig,date2,idNumber,takakka,grohonsa;
    FirebaseUser firebaseUser;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 21 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                // Toast.makeText(this, ""+PICK_IMAGE_REQUEST, Toast.LENGTH_SHORT).show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), second);
                userimage.setImageBitmap(bitmap);
                flag=2;

                // nid.setImageBitmap(bitmap1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    int flag=1;
    private Spinner spinnerTextSize,spinnerTextSize1,spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    EditText Email_Log4;

    //
    TextView changeProfilePhoto;
    ImageButton image_button;
    ImageView imageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath,second,vechileimage,vechilelicesse;//Firebase

    Button floatingActionButton;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int CAMERA_REQUEST = 1888;
    Button generate_btn;
    //doctor
    private static final int READCODE = 1;
    private static final int WRITECODE = 2;

    private Uri mainImageUri = null;
    ImageView userimage;
    EditText s_name,relagi,porichoy,mysigne,datee,s_ppaer,s_paddress,relation;
    Button cirLoginButton;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    EditText somitiname,sovapoti,signeture,pasword,refer,ocupa,s_kisti,
            b_date,natii,votar,ppaddress,p_address,mother,fatheren,
            fatherba,sovapoti_english;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("আপলোড করা হচ্ছে...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            StorageReference ref = storageReference.child("ProfileImage/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());
                            final Uri downloadUri=uriTask.getResult();



                            if (uriTask.isSuccessful()) {


                                ZoneId z = ZoneId.of( "America/Montreal" );
                                LocalDate today = LocalDate.now( z );
                                LocalDate oneMonthLater = today.plusMonths( 1 );
                                Long tsLong = System.currentTimeMillis()/1000;
                                String  ts = tsLong.toString();
                                AddmemberModel addSomitimodel=new AddmemberModel(somitiname.getText().toString(),sovapoti.getText().toString(),signeture.getText().toString(),pasword.getText().toString(),refer.getText().toString(),ocupa.getText().toString(),s_kisti.getText().toString(),
                                        b_date.getText().toString(),natii.getText().toString(),votar.getText().toString(),ppaddress.getText().toString(),p_address.getText().toString(),mother.getText().toString(),fatheren.getText().toString(),
                                        fatherba.getText().toString(),sovapoti_english.getText().toString(),""+today,s_name.getText().toString(),relation.getText().toString(),s_paddress.getText().toString(),s_ppaer.getText().toString(),takakka.getText().toString(),idNumber.getText().toString(),downloadUri.toString(),firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid(),ts);
                                firebaseFirestore.collection("SomitiMember")
                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                        .collection("List")
                                        .document(sovapoti_english.getText().toString().toLowerCase())
                                        .set(addSomitimodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                                   startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));

                                                }
                                            }
                                        });


                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "ব্যর্থ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Add_member.this, "ব্যর্থ "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("আপলোড করা হয়েছে "+(int)progress+"%");
                        }
                    });
        }
        else {
            ZoneId z = ZoneId.of( "America/Montreal" );
            LocalDate today = LocalDate.now( z );
            String image="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
            LocalDate oneMonthLater = today.plusMonths( 1 );
            Long tsLong = System.currentTimeMillis()/1000;
            String  ts = tsLong.toString();
            AddmemberModel addSomitimodel=new AddmemberModel(somitiname.getText().toString(),sovapoti.getText().toString(),signeture.getText().toString(),pasword.getText().toString(),refer.getText().toString(),ocupa.getText().toString(),s_kisti.getText().toString(),
                    b_date.getText().toString(),natii.getText().toString(),votar.getText().toString(),ppaddress.getText().toString(),p_address.getText().toString(),mother.getText().toString(),fatheren.getText().toString(),
                    fatherba.getText().toString(),sovapoti_english.getText().toString(),""+today,s_name.getText().toString(),relation.getText().toString(),s_paddress.getText().toString(),s_ppaer.getText().toString(),takakka.getText().toString(),idNumber.getText().toString(),image.toString(),firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid(),ts);
            firebaseFirestore.collection("SomitiMember")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .collection("List")
                    .document(sovapoti_english.getText().toString().toLowerCase())
                    .set(addSomitimodel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));

                            }
                        }
                    });
        }
    }
}