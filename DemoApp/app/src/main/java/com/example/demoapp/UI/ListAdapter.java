package com.example.demoapp.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.serviceapilibrary.model.Hero;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HeroViewHolder> {


        private List<Hero> heroList;
        private Context context;

        private static int currentPosition = 0;

        public ListAdapter(List<Hero> heroList, Context context) {
            this.heroList = heroList;
            this.context = context;
        }

        @Override
        public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes, parent, false);
            return new HeroViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final HeroViewHolder holder, final int position) {
            Hero hero = heroList.get(position);
            holder.textViewName.setText(hero.getName());
        }

        @Override
        public int getItemCount() {
            return heroList.size();
        }

        class HeroViewHolder extends RecyclerView.ViewHolder {
            TextView textViewName;


            HeroViewHolder(View itemView) {
                super(itemView);

                textViewName = itemView.findViewById(R.id.id_hero_tv);

            }
        }
}
