package com.dabut.purcow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LinkAdapter extends ArrayAdapter<LinkServer> {

    private Context context;
    private int layoutResourceId;
    private List<LinkServer> data;

    public LinkAdapter(Context context, int layoutResourceId, List<LinkServer> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LinkAdapter.LinkserverHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new LinkAdapter.LinkserverHolder();
            holder.textView = row.findViewById(R.id.textView9);
            holder.ping = row.findViewById(R.id.textView8);

            row.setTag(holder);
        } else {
            holder = (LinkAdapter.LinkserverHolder) row.getTag();
        }

        LinkServer appInfo = data.get(position);
        holder.textView.setText(appInfo.name);

        holder.ping.setText(appInfo.ping);
        holder.ping.setTextColor(appInfo.color);


        return row;
    }

    static class LinkserverHolder {
        TextView textView;
        TextView ping;


    }

}
