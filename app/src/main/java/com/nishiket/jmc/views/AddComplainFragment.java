package com.nishiket.jmc.views;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nishiket.jmc.R;
import com.nishiket.jmc.user.MainActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class AddComplainFragment extends Fragment {
    String branchName,SubjectTitle,categoryName,details;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE_REQUEST = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddComplainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddComplainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddComplainFragment newInstance(String param1, String param2) {
        AddComplainFragment fragment = new AddComplainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_complain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatButton add_comaplain_submit;
        Spinner branchNo,category;
        EditText subject,complain_add_details;
        AppCompatButton BselectImage;

        BselectImage = view.findViewById(R.id.BselectImage);
        add_comaplain_submit = view.findViewById(R.id.add_complain_submit);
        category = view.findViewById(R.id.prio);

        String[] courses = {"Select Item","Normal","Advanced"};
        ArrayAdapter<String> ad = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(ad);

        branchNo = view.findViewById(R.id.branchNo);
        String[] data = { "Select Item","Water Works", "Slum","Light eSMART CITY AREA","civil","Health","Solid Waste Branch","Estate","Town Planning","UGD","House Tax","UCD","ICDS","Garden","Project-Planning","Light EESL NAGAR SIM AREA","Security","FLOOD CONTROL"};
        ArrayAdapter<String> ad1 = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item, data);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchNo.setAdapter(ad1);

        branchNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                branchName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SubjectTitle = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        BselectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        add_comaplain_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.parent);
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_contain, new AddComplainDoneFragment());
                transaction.addToBackStack(null);  // If you want to add the transaction to the back stack
                transaction.commit();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference().child("images"); // Change "images" to your desired folder name

            UploadTask uploadTask = storageRef.child(selectedImageUri.getLastPathSegment()).putFile(selectedImageUri);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Handle successful upload, e.g., get the download URL
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUri) {
                            String downloadUrl = downloadUri.toString();
                            Log.d("imageURL", "onSuccess: "+downloadUrl);
                            // Now you can use the download URL for the uploaded image
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle the failure
                }
            });

            StorageReference imageRef = storageRef.child(selectedImageUri.getLastPathSegment());
            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String downloadUri = uri.toString();
                    Log.d("imagePath", "onSuccess: "+downloadUri);
                }
            });


        }
    }
}