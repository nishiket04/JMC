package com.nishiket.jmc.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nishiket.jmc.R;
import com.nishiket.jmc.model.UserData;
import com.nishiket.jmc.viewmodel.UserDataViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoFragment extends Fragment {
    String name,email,mobile,addhar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoFragment newInstance(String param1, String param2) {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
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
        return inflater.inflate(R.layout.fragment_personal_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView uname,mobileno,addharno,emailid,address_line1,address_line2,pincode;
        ImageView complate_img;
        ScrollView scrollView;
        complate_img = view.findViewById(R.id.complete_img);
        uname = view.findViewById(R.id.name);
        mobileno = view.findViewById(R.id.mobileno);
        addharno = view.findViewById(R.id.addharno);
        emailid = view.findViewById(R.id.email);
        address_line1 = view.findViewById(R.id.address_line1);
        address_line2 = view.findViewById(R.id.address_line2);
        pincode = view.findViewById(R.id.pincode);
        scrollView = view.findViewById(R.id.root);
        scrollView.setVisibility(View.GONE);
        view.getRootView().findViewById(R.id.load).setVisibility(View.VISIBLE);
        email = "nishiket04@gmail.com";
        UserDataViewModel userDataViewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(UserDataViewModel.class);
        userDataViewModel.getUserData(email);

        userDataViewModel.getUserDataMutableLiveData().observe(getViewLifecycleOwner(), new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                scrollView.setVisibility(View.VISIBLE);
                view.getRootView().findViewById(R.id.load).setVisibility(View.GONE);
                uname.setText(userData.getName());
                mobileno.setText(userData.getMobile());
                addharno.setText(userData.getAddhar());
                emailid.setText(userData.getEmail());
                address_line1.setText(userData.getAddressLine1());
                address_line2.setText(userData.getAddressLine2());
                pincode.setText(userData.getPincode());
            }
        });

    complate_img.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_contain,new ProfileFragment());
            ft.commit();
        }
    });


    }

}