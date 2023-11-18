package com.nishiket.jmc.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishiket.jmc.R;
import com.nishiket.jmc.adapter.ComplainAdapter;
import com.nishiket.jmc.model.ComplainModel;
import com.nishiket.jmc.viewmodel.ComplainViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SolvedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SolvedFragment extends Fragment {
    private ComplainAdapter complainAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SolvedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SolvedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SolvedFragment newInstance(String param1, String param2) {
        SolvedFragment fragment = new SolvedFragment();
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
        return inflater.inflate(R.layout.fragment_solved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView r2;
        ComplainViewModel viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ComplainViewModel.class);

        r2=view.findViewById(R.id.r2);
        r2.setVisibility(View.GONE);
        view.getRootView().findViewById(R.id.load).setVisibility(View.VISIBLE);
        complainAdapter = new ComplainAdapter(getContext());
        r2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        r2.setAdapter(complainAdapter);
        viewModel.getSolvedData();
        viewModel.getActiveComplainListLiveData().observe(getViewLifecycleOwner(), new Observer<List<ComplainModel>>() {
            @Override
            public void onChanged(List<ComplainModel> complainModelList) {
                r2.setVisibility(View.VISIBLE);
                view.getRootView().findViewById(R.id.load).setVisibility(View.GONE);
                complainAdapter.setActiveComplainList(complainModelList);
                complainAdapter.notifyDataSetChanged();
            }
        });
    }
}