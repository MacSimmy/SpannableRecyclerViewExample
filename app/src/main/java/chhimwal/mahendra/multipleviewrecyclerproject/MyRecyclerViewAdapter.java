package chhimwal.mahendra.multipleviewrecyclerproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mahendra.chhimwal on 12/10/2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mRViewDataList;


    public MyRecyclerViewAdapter(Context context, List<String> rViewDataList) {
        this.mContext = context;
        this.mRViewDataList = rViewDataList;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindDataWithViewHolder(mRViewDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRViewDataList != null ? mRViewDataList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cvItemView;
        private String mDataItem=null;

        public ViewHolder(View itemView) {
            super(itemView);
            cvItemView=(CardView)itemView.findViewById(R.id.cv_item_view);
            textView = (TextView) itemView.findViewById(R.id.tvItemName);
            cvItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  // One should handle onclick of event here based on the dataItem i.e. mDataItem in this case.
                  // something like that..
                /* Intent intent = new Intent(mContext,ResultActivity.class);
                 intent.putExtra("MY_DATA",mDataItem);   //If you want to pass data.
                 intent.putExtra("CLICKED_ITEM_POSTION",getAdapterPosition()); // If one want to get selected item position
                 startActivity(intent);*/
                 Toast.makeText(mContext,"You clicked item number "+ViewHolder.this.getAdapterPosition(),Toast.LENTH_SHORT).show();
                }
            });
        }
        
        //This is clean method to bind data with viewHolder. Do all dirty things on View based on dataItem.
        //Must be called from onBindViewHolder(),with dataItem. In our case dataItem is String object.
        public void bindDataWithViewHolder(String dataItem){
            this.mDataItem=dataItem;
            textView.setText(mDataItem);
        }
    }
}
