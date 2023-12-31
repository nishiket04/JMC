package com.nishiket.jmc.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nishiket.jmc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyComplainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyComplainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyComplainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyComplainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyComplainFragment newInstance(String param1, String param2) {
        MyComplainFragment fragment = new MyComplainFragment();
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
        return inflater.inflate(R.layout.fragment_my_complain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout l1 = view.findViewById(R.id.l1);
        LinearLayout l2 = view.findViewById(R.id.l2);
        LinearLayout l3 = view.findViewById(R.id.l3);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout constraintLayout = getActivity().findViewById(R.id.parent);
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_contain, new MyComplainActiveFragment());
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
                transaction.replace(R.id.fragment_contain, new MyComplainSolvedFragment());
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
                transaction.replace(R.id.fragment_contain, new MyComplainDelayedFragment());
                transaction.addToBackStack(null);  // If you want to add the transaction to the back stack
                transaction.commit();
            }
        });

    }
}