package chhimwal.mahendra.multipleviewrecyclerproject;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahendra.chhimwal on 12/10/2015.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mDataList;


    public ViewPagerAdapter(Context context, List<String> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if (mDataList != null) {
            if (mDataList.size() % 4 == 0) {
                return mDataList.size() / 4;
            } else {
                return mDataList.size() / 4 + 1;
            }
        }
        return 0;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.item_view_pager, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 4 == 0 || position % 4 == 3) {         //Set full card items for positions 0and 3 i.e.first and last and half for item second and third.
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        List<String> rViewDataList = makeDataForRecyclerView(position);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(mContext, rViewDataList);
        //set Adapter to recycler View
        recyclerView.setAdapter(myRecyclerViewAdapter);
        container.addView(view);
        return view;
    }


    private List<String> makeDataForRecyclerView(int startPosition) {
        List<String> listOf4Item = new ArrayList<String>();
        for (int i = startPosition * 4; i < Math.min((4 * startPosition) + 4, mDataList.size()); i++) {
            listOf4Item.add(mDataList.get(i));
        }
        return listOf4Item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}
