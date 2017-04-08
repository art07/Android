package ua.art.myapppackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FragHome extends Fragment {

    public FragHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        RecyclerView fragmentHomeRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_home_recycler_view);
        fragmentHomeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        DataStorage dataStorage = DataStorage.getDataStorage();
        fragmentHomeRecyclerView.setAdapter(new MyAdapter(dataStorage.getAppArrayList()));
        return view;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        MyHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private final List<MyApp> appArrayList;

        MyAdapter(List<MyApp> appArrayList) {
            this.appArrayList = appArrayList;
        }

        @Override
        public int getItemCount() {
            return appArrayList.size();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.card_view, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder myHolder, int position) {
            final int elementPosition = position;
            MyApp myApp = appArrayList.get(elementPosition);
            ((ImageView) myHolder.itemView.findViewById(R.id.app_image)).setImageResource(myApp.getImageId());
            ((TextView) myHolder.itemView.findViewById(R.id.app_title)).setText(myApp.getAppName());

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment;
                    if (elementPosition == 0) fragment = new FragCalculator();
                    else fragment = new FragConverter();

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, fragment, "current_fragment")
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }
            });
        }
    }
}
