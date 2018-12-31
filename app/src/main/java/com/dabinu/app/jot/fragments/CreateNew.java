package com.dabinu.app.jot.fragments;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.dabinu.app.jot.R;
import com.dabinu.app.jot.models.NoteModel;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateNew extends Fragment {


    EditText note;
    Menu menu;

    public CreateNew(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_create_new, container, false);

        init(view);

        return view;
    }


    public void init(View view){
        note = view.findViewById(R.id.note);

        note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveTheFile();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.for_create_new, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.status:
                //nothing
                return true;

            case R.id.archive:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new AllNotesFragment());
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


    public void saveTheFile(){


        Random random = new Random();
        int code = random.nextInt(9000);

        try{
            FileOutputStream fos = getActivity().openFileOutput(Integer.toString(code), Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new NoteModel("hgv", "jgv", "jg")); //todo: modify this
            oos.close();
            fos.close();

            MenuItem somText = menu.findItem(R.id.status);
            somText.setTitle("SAVED");

        }

        catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}