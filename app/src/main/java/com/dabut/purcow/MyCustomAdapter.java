package com.dabut.purcow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    private final ArrayList<LinkServer> data; // آرایه‌ای از مقادیر
    private OnItemClickListener clickListener;
    private OnItemClickListener pingtest;
    private OnItemClickListener imglisener;

    private OnItemLongClickListener onItemlongClickListener;
    CopyOnWriteArrayList<LinkServer> datalist = new CopyOnWriteArrayList<>();
    private ArrayList<LinkServer> items;

    public void updateList(ArrayList<LinkServer> newList) {
        // Calculate the difference between the old and new lists
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(data, newList));

        // Update the list with the new data
        data.clear();
        data.addAll(newList);

        // Dispatch the updates to the adapter
        diffResult.dispatchUpdatesTo(this);
    }

    public MyCustomAdapter(ArrayList<LinkServer> data) {
        this.data = data;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener {
        void onItemClick(int position);
    }

    public void imglisenner(OnItemClickListener listener) {
        this.imglisener = listener;
    }
    public void pinlisenr(OnItemClickListener  listener) {
        this.pingtest = listener;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public void setOnItemClickListener2(OnItemLongClickListener  listener) {
        this.onItemlongClickListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.server_row2, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LinkServer appInfo = data.get(position);

        holder.textView.setText(appInfo.name);
        holder.textView2.setText(appInfo.ping);
        holder.textView2.setTextColor(appInfo.color);
       // holder.textView2.setText(appInfo.color);
        holder.itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onItemClick(position);

            }
        });

      holder.itemView.setOnLongClickListener(view -> {

          if (onItemlongClickListener != null) {
              onItemlongClickListener.onItemClick(position);

          }
          return  true;
      });

      holder.img.setOnClickListener(view ->{
          if (imglisener != null) {
              imglisener.onItemClick(position);

          }
      });
      holder.textView2.setOnClickListener(view ->{
          if (pingtest != null) {
              pingtest.onItemClick(position);

          }
      });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView,textView2;


        MyViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView9);
            textView2 = itemView.findViewById(R.id.textView8);

        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView,textView2;
        private final ImageView img;
        public MyViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView9);
            textView2 = (TextView) view.findViewById(R.id.textView8);
            img = (ImageView) view.findViewById(R.id.imageView8);


        }

        public TextView getTextView() {
            return textView;
        }
    }








    static class MyDiffCallback extends DiffUtil.Callback {
        private final ArrayList<LinkServer> oldList;
        private final ArrayList<LinkServer> newList;

        public MyDiffCallback(ArrayList<LinkServer> oldList, ArrayList<LinkServer> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // Compare item IDs or unique identifiers
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            // Compare item contents (e.g., fields of an object)
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }

}
