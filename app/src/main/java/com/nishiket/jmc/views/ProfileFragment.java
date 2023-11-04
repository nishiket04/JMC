package com.nishiket.jmc.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nishiket.jmc.R;
import com.nishiket.jmc.login;
import com.nishiket.jmc.viewmodel.AuthViewModel;
import com.nishiket.jmc.viewmodel.UserDataViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    String name,mobile,addhar,email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent toH = new Intent(getActivity(), login.class);
        LinearLayout l1 = view.findViewById(R.id.l1);
        LinearLayout l2 = view.findViewById(R.id.l2);
        LinearLayout l3 = view.findViewById(R.id.l3);
        TextView profile_user_name;
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // firebase firestore instance
        CollectionReference refrence= db.collection("userData"); // now collection refrence here our collection refrence is userData

        email = "nishi04@gmail.com";
        profile_user_name = view.findViewById(R.id.profile_user_name);

       AuthViewModel viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
       refrence.document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if (task.isSuccessful()){
                   DocumentSnapshot documentSnapshot= task.getResult();
                   name = documentSnapshot.getString("name");
                   profile_user_name.setText(name);
               }
           }
       });
       l1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ConstraintLayout constraintLayout = getActivity().findViewById(R.id.parent);
               FragmentManager fragmentManager = getParentFragmentManager();

               FragmentTransaction transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.fragment_contain, new PersonalInfoFragment());
               transaction.addToBackStack(null);  // If you want to add the transaction to the back stack
               transaction.commit();
           }
       });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.parent);
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_contain, new MyComplainFragment());
                transaction.addToBackStack(null);  // If you want to add the transaction to the back stack
                transaction.commit();
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.parent);
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_contain, new ChangePasswordFragment());
                transaction.addToBackStack(null);  // If you want to add the transaction to the back stack
                transaction.commit();
            }
        });

        TextView logout;
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signOut();
                startActivity(toH);
                getActivity().finish();
            }
        });
    }
}