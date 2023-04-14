package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Add_Kisti extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    AdapterSub6 getDataAdapter1;
    List<AddmemberModel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__kisti);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("কিস্তি গ্রহণ");
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
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new AdapterSub6(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =     firebaseFirestore.collection("SomitiMember")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Add_Kisti.this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
        //searching
        name=findViewById(R.id.serachname);
        name.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //fullsearch(query);

                //phoneSerach(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchAllUser(newText);

                //phoneSerach1(newText);
                return false;
            }
        });

    }

    private void searchAllUser(String newText) {
        firebaseFirestore.collection("SomitiMember")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("sovapoti");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                AddmemberModel add_customer=new AddmemberModel(documentSnapshot.getString("somitiname"),
                                        documentSnapshot.getString("sovapoti")
                                        , documentSnapshot.getString("signeture"),
                                        documentSnapshot.getString("pasword"),
                                        documentSnapshot.getString("refer"),
                                        //1
                                        documentSnapshot.getString("ocupa")
                                        , documentSnapshot.getString("s_kisti"),
                                        documentSnapshot.getString("b_date"),
                                        documentSnapshot.getString("natii")
                                        ,
                                        //2
                                        documentSnapshot.getString("votar")
                                        , documentSnapshot.getString("ppaddress"),
                                        documentSnapshot.getString("p_address"),
                                        documentSnapshot.getString("mother")
                                        ,
                                        documentSnapshot.getString("fatheren")
                                        , documentSnapshot.getString("fatherba"),
                                        documentSnapshot.getString("sovapoti_english"),
                                        documentSnapshot.getString("date")
                                        ,
                                        documentSnapshot.getString("relationname")
                                        , documentSnapshot.getString("relationship"),
                                        documentSnapshot.getString("relationshipplcae"),
                                        documentSnapshot.getString("repl1")
                                        //5
                                        ,
                                        documentSnapshot.getString("payment")
                                        , documentSnapshot.getString("id_number"),
                                        documentSnapshot.getString("image"),
                                        documentSnapshot.getString("email")
                                        ,
                                        documentSnapshot.getString("uuid")
                                        , documentSnapshot.getString("time")
                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new AdapterSub6(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }


    private void reciveData() {

        firebaseFirestore.collection("SomitiMember")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .orderBy("time", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        AddmemberModel get = ds.getDocument().toObject(AddmemberModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
        return true;
    }

    public void add_balance(View view) {
        startActivity(new Intent(getApplicationContext(),SimpleCalculator.class));
    }
}