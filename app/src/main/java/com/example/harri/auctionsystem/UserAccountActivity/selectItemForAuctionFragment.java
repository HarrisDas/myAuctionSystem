package com.example.harri.auctionsystem.UserAccountActivity;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.harri.auctionsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class selectItemForAuctionFragment extends Fragment {

    Date tempDate ;
    private static final int REQUEST_CODE_GALLERY = 2;
    ImageView mThumbnail;
    Button dateButton;
    EditText mItemName;
    EditText mItemBid;

int REQUEST_CODE_DATE_PICKER=100;
Date mInitial_Date;
    Date mFinal_Date;
String uuid;
    ProgressDialog progressDialog;
String year_x,month_x,day_x;
    private Calendar calendar= Calendar.getInstance();
    Button submit_Button;
    EditText mItemCategory;
String stringItemName,stringItemBid, stringItemCategory;

    Uri myImageUri;
FirebaseStorage mStorage;
    FirebaseAuth mAuth;
    StorageReference mStorageReference,imageRef;
    FirebaseDatabase mDatabase;
    UploadTask mUploadTask;

    Spinner initial_hour_spinner;
    Spinner initial_min_spinner;
    Spinner end_hour_spinner;
Spinner end_min_spinner;
    int end_min;

    int initial_hour;
  int initial_min;
    int end_hour;
    ArrayAdapter<CharSequence> initial_hour_adapter;
    ArrayAdapter<CharSequence> initial_min_adapter;


    public selectItemForAuctionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_select_item_for_auction, container, false);



       // mInitial_Date=new Date( format.applyPattern("yyyy MM dd HH:mm:ss"));
        //mInitial_Date= format.;
attachWidgets(rootView);
 attachingComponents();

        set_initial_time(rootView);
        set_end_time(rootView);



        dateButton =(Button)rootView.findViewById(R.id.start_Date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date today =new Date();
                long h=today.getTime();

               // System.out.println("yaha agya");
                DatePickerDialog datePicker=  new DatePickerDialog(getActivity(),DailogListner,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(h);

                datePicker.show();

    //            System.out.println("chala gya");
            }
        });
        return rootView;
    }
private  DatePickerDialog.OnDateSetListener DailogListner= new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

mInitial_Date= new Date();
mInitial_Date.setYear(year-1900);
        mInitial_Date.setMonth(month);
        mInitial_Date.setDate(dayOfMonth);
        SimpleDateFormat format= new SimpleDateFormat("yyyy MM dd HH:mm:ss");

Toast.makeText(getActivity(),mInitial_Date.toString(),Toast.LENGTH_LONG).show();
        System.out.println(mInitial_Date.toString());
    }
};




    private void set_initial_time(View rootView) {
        initial_hour_spinner= (Spinner)rootView.findViewById(R.id.initial_hour);
        initial_hour_adapter= ArrayAdapter.createFromResource(getActivity(),R.array.Hours,android.R.layout.simple_spinner_item);
        initial_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initial_hour_spinner.setAdapter(initial_hour_adapter);
        initial_hour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                initial_hour= Integer.parseInt( parent.getItemAtPosition(position).toString());
                Toast.makeText(getActivity(), initial_hour+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initial_min_spinner= (Spinner)rootView.findViewById(R.id.initial_min);
        initial_min_adapter=ArrayAdapter.createFromResource(getActivity(),R.array.Minutes,android.R.layout.simple_spinner_item);
        initial_min_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initial_min_spinner.setAdapter(initial_min_adapter);
        initial_min_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initial_min= Integer.parseInt( parent.getItemAtPosition(position).toString());
                Toast.makeText(getActivity(), initial_min+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void set_end_time(View rootView) {

        end_min_spinner=(Spinner)rootView.findViewById(R.id.end_min);
        initial_min_adapter=ArrayAdapter.createFromResource(getActivity(),R.array.Minutes,android.R.layout.simple_spinner_item);
        initial_min_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        end_min_spinner.setAdapter(initial_min_adapter);
        end_min_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                end_min=Integer.parseInt(parent.getItemAtPosition(position).toString());
                Toast.makeText(getActivity(), end_min+"Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        end_hour_spinner = (Spinner)rootView.findViewById(R.id.end_hour);
        initial_hour_adapter=ArrayAdapter.createFromResource(getActivity(),R.array.End_hours,android.R.layout.simple_spinner_item);


        //  end_min_spinner = (Spinner)findViewById(R.id.spinner_end_min);
        initial_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        end_hour_spinner.setAdapter(initial_hour_adapter);
        end_hour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                end_hour=Integer.parseInt(  parent.getItemAtPosition(position).toString());
                Toast.makeText(getActivity(), end_hour+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void attachingComponents() {
        mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_CODE_GALLERY);
            }
        });
        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringItemBid=mItemBid.getText().toString();
                stringItemCategory=mItemCategory.getText().toString();
                stringItemName=mItemName.getText().toString();

                if(TextUtils.isEmpty(stringItemBid)&&TextUtils.isEmpty(stringItemCategory)&&TextUtils.isEmpty(stringItemName)){


                    mItemBid.setError("Enter Bid");
                    mItemCategory.setError("Enter Category");
                    mItemName.setError("Enter Name");
                }else{

                    if(TextUtils.isEmpty(stringItemBid)){    mItemBid.setError("Enter Bid");}
                    if(TextUtils.isEmpty(stringItemName)){     mItemName.setError("Enter Name");}
                    if(TextUtils.isEmpty(stringItemCategory)){    mItemCategory.setError("Enter Category");
                    }



                }


                mInitial_Date.setHours(initial_hour);
                mInitial_Date.setMinutes(initial_min);
             //   mFinal_Date= mInitial_Date;
                mFinal_Date= new Date(mInitial_Date.getTime());

                mFinal_Date.setHours(initial_hour+end_hour);
mFinal_Date.setMinutes(initial_min+end_min);

                progressDialog= new ProgressDialog(getActivity());
                progressDialog.setMessage("uploading item For Auction");
                progressDialog.show();
                mAuth=FirebaseAuth.getInstance();
uuid=mAuth.getCurrentUser().getUid().toString();


                mDatabase=FirebaseDatabase.getInstance();
                String key =mDatabase.getReference().push().getKey();
                Item myiTem= new Item(stringItemName,stringItemCategory,stringItemBid,myImageUri.toString(),uuid,String.valueOf( mInitial_Date.getTime()),String.valueOf(mFinal_Date.getTime()),key, "False");


                mDatabase.getReference().child("Auction").child(key).setValue(myiTem).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
if(!task.isSuccessful()){


    progressDialog.dismiss();
    Toast.makeText(getActivity(), "Error while Uploading Data",
            Toast.LENGTH_SHORT).show();




}else{
    progressDialog.dismiss();
    getActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.user_fragment_container ,new UserAuctionerFragment())
            .commit();



    Toast.makeText(getActivity(), "Data Uploaded for Auction",
            Toast.LENGTH_SHORT).show();

}
                    }
                });

    }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==REQUEST_CODE_GALLERY&&resultCode==RESULT_OK){

        Uri uri=data.getData();
        if(data!=null){

            mStorage=FirebaseStorage.getInstance();
            mStorageReference=mStorage.getReference();
          imageRef=  mStorageReference.child(uri.getLastPathSegment());

            progressDialog= new ProgressDialog(getActivity());
            progressDialog.setMax(100);
            progressDialog.setMessage("Uploading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            progressDialog.setCancelable(false);


            mUploadTask=imageRef.putFile(uri);
            mUploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    //sets and increments value of progressbar
                    progressDialog.incrementProgressBy((int) progress);

                }
            });

            mUploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getActivity(),"Error in uploading!",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(getActivity(),"Upload successful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    myImageUri=taskSnapshot.getDownloadUrl();
                    Glide.with(getActivity()).load(data.getData()).into(mThumbnail);

                }
            });


















        }
    }

    }



    public void attachWidgets(View v) {
        mThumbnail=(ImageView) v.findViewById(R.id.thumbnail);
        mItemName=(EditText)v.findViewById(R.id.item_name);
        mItemBid=(EditText)v.findViewById(R.id.entered_bid);
        mItemCategory=(EditText)v.findViewById(R.id.Enter_Category);
        submit_Button=(Button)v.findViewById(R.id.upload_item_data);
    }


}
