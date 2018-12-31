package com.dabinu.app.jot.fragments;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dabinu.app.jot.R;
import com.dabinu.app.jot.adapters.NoteListAdapter;
import com.dabinu.app.jot.models.NoteModel;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class AllNotesFragment extends Fragment {

    RecyclerView recyclerView;
    TextView noNotes;
    FloatingActionButton fab;

    public AllNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_all_notes, container, false);

        init(view);
        checkData();

        return view;
    }


    public void init(View view){
        recyclerView = view.findViewById(R.id.allNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        noNotes = view.findViewById(R.id.noNotes);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new CreateNew());
                fragmentTransaction.commit();
            }
        });
    }

    void checkData(){
        File[] listof = getActivity().getFilesDir().listFiles();

        final ArrayList<NoteModel> noteModels = new ArrayList<>();

        for(File i: listof){
            try{
                NoteModel noteModel = (NoteModel) (new ObjectInputStream(getActivity().openFileInput(i.getName())).readObject());
                noteModels.add(noteModel);

            }

            catch(Exception e){

            }
        }

        if(noteModels.isEmpty()){
            noNotes.setVisibility(View.VISIBLE);
        }

        else{
            NoteListAdapter adapter = new NoteListAdapter(getActivity().getApplicationContext(), noteModels);
            adapter.setOnItemClickListener(new NoteListAdapter.ClickListener(){
                @Override
                public void onItemClick(int position, View v){
                    //load in create new
                }

                @Override
                public void onItemLongClick(int position, View v){
                    //delete
                }
            });

            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.for_all_notes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case R.id.add:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new CreateNew());
                fragmentTransaction.commit();
                return true;

            case R.id.settings:
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.container, new SettingsFragment());
                fragmentTransaction1.commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}