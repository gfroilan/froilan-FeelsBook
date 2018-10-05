package com.example.gillien.feelsbook3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mEmotionData;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> emotionData, Context mContext) {
        mEmotionData = emotionData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_viewhistory, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG, "onBindViewHolder: called.");
        viewHolder.emotionData.setText(mEmotionData.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick:clicked on: "+mEmotionData.get(i));

                PopupMenu popupMenu = new PopupMenu(mContext, viewHolder.itemView);
                popupMenu.inflate(R.menu.activity_context_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch(menuItem.getItemId()) {
                            case R.id.menu_delete:
                                Toast.makeText(mContext, "Deleting Entry..." + i, Toast.LENGTH_LONG).show();
                            break;
                            case R.id.menu_edit:
                                Toast.makeText(mContext, "Editing Entry...", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
     
    }

    @Override
    public int getItemCount() {
        return mEmotionData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView emotionData;

        public ViewHolder(View v) {
            
            super(v);

            emotionData = v.findViewById(R.id.emotion_data);
            Log.d(TAG, "cardview: called.");

        }
    }
}
    