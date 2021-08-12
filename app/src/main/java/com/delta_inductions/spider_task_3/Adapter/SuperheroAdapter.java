package com.delta_inductions.spider_task_3.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.delta_inductions.spider_task_3.Model.Superhero;
import com.delta_inductions.spider_task_3.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder> {
    private ArrayList<Superhero> SuperheroList;
    private Context mContext;
    private Onitemclicklistener listener;

    public SuperheroAdapter(Context context, ArrayList<Superhero> superheroesList) {
        mContext = context;
        SuperheroList = superheroesList;
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

    public class SuperheroViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView breedname;
        private ProgressBar progressBar;
        private ImageButton star;
        public SuperheroViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.superheroimage);
            breedname = itemView.findViewById(R.id.superheroname);
            progressBar = itemView.findViewById(R.id.progressbar);
//            star = itemView.findViewById(R.id.star_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                            listener.itemclickList(position);
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
