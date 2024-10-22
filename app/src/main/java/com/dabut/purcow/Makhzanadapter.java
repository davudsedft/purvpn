package com.dabut.purcow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Makhzanadapter extends ArrayAdapter<Makhzan> {

    private Context context;
    private int layoutResourceId;
    private List<Makhzan> data;
    private MyCustomAdapter.OnItemClickListener clickListener;
    private MyCustomAdapter.OnItemLongClickListener pingtest;

    public Makhzanadapter(Context context, int layoutResourceId, List<Makhzan> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface  pingtest{
        void onItemClick(int position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Makhzanadapter.Makhzanholder makhzanholder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            makhzanholder = new Makhzanadapter.Makhzanholder();
            makhzanholder.name = row.findViewById(R.id.textView5);

            row.setTag(makhzanholder);
        } else {
            makhzanholder = (Makhzanadapter.Makhzanholder) row.getTag();
        }

        Makhzan makhzan = data.get(position);
        makhzanholder.name.setText(makhzan.name);



        return row;
    }
    static class Makhzanholder {
        TextView name;


    }




}
