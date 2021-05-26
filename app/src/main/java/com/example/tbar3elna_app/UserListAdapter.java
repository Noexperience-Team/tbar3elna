package com.example.tbar3elna_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private Activity context;
    private List<User> userList;
    public UserListAdapter(@NonNull Activity context, @NonNull List<User> userList) {
        super((Context) context,R.layout.listview_layout,userList);
        this.context=context;
        this.userList=userList;

    }
    public View getView(int position,  View convertView, ViewGroup parent) {




        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_layout,null,true);





        TextView tnom=(TextView) listViewItem.findViewById(R.id.textView_name);
        TextView local=(TextView) listViewItem.findViewById(R.id.textView_local);
        TextView group=(TextView) listViewItem.findViewById(R.id.textView_groupe);
        User pa= userList.get(position);

        tnom.setText(pa.getNom_et_prénom());
        local.setText(pa.getLocalisation());
        group.setText(pa.getGroupe_sanguin());

        return listViewItem;
    }
}
