package matheus.com.br.eaivai.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import matheus.com.br.eaivai.R;
import matheus.com.br.eaivai.activity.EventShowActivity;

/**
 * Created by matheus on 12/01/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter.DataObjectHolder> {

    private List<DataObject> mDataset;

    public MyRecyclerViewAdapter(List<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_row, parent, false);
        DataObjectHolder holder = new DataObjectHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getLabel1());
        holder.dateTime.setText(mDataset.get(position).getLabel2());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.info_text);
            dateTime = (TextView) itemView.findViewById(R.id.created_at);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EventShowActivity.class);
            v.getContext().startActivity(intent);

        }
    }

}
