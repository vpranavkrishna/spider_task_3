package com.delta_inductions.spider_task_3.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.delta_inductions.spider_task_3.MainActivity;
import com.delta_inductions.spider_task_3.Model.Superhero;
import com.delta_inductions.spider_task_3.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder> implements Filterable {
    private ArrayList<Superhero> SuperheroList;
    private ArrayList<Superhero> SuperherofullList;
    private Context mContext;
    private Onitemclicklistener listener;

    public SuperheroAdapter(Context context, ArrayList<Superhero> superheroesList) {
        mContext = context;
        SuperheroList = superheroesList;
        SuperherofullList = new ArrayList<>(superheroesList);;
    }
    @NonNull
    @Override
    public SuperheroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.cardlayout,parent,false);
        return new SuperheroViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull SuperheroViewHolder holder, int position) {
        Superhero currenthero = SuperheroList.get(position);
        holder.breedname.setText(currenthero.getName());
        Picasso.get().load(currenthero.getImage().getURl()).fit().centerInside().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.breedname.setText("Sorry error has occured");
            }
        });
    }
    @Override
    public int getItemCount() {
        return SuperheroList.size();
    }
    @Override
    public Filter getFilter() {
        return SuperheroListFilter;
    }
    private Filter SuperheroListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Superhero> Superherofilterlist = new ArrayList<>();
            if(constraint==null||constraint.length() == 0 )
            {
                Superherofilterlist.addAll(SuperherofullList);
            }
            else
            {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Superhero item : SuperherofullList)
                {
                    if(item.getName().toLowerCase().contains(filterpattern) || String.valueOf(item.getId()).equals(filterpattern))
                    {
                        Superherofilterlist.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = Superherofilterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            SuperheroList.clear();
            SuperheroList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class SuperheroViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView breedname;
        private ProgressBar progressBar;
        public SuperheroViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.superheroimage);
            breedname = itemView.findViewById(R.id.superheroname);
            progressBar = itemView.findViewById(R.id.progressbar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                            listener.itemclickList(position,imageView);
                    }
                }
            });
        }
    }

    public void setOnitemclicklistenerList( Onitemclicklistener onitemclicklistener)
    {
        listener = onitemclicklistener;
    }
}
