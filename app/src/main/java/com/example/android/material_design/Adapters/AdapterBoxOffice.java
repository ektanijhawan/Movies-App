package com.example.android.material_design.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.android.material_design.Model.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

import java.util.ArrayList;


/**
 * Created by sunny on 3/10/2016.
 */
public class AdapterBoxOffice extends RecyclerView.Adapter<AdapterBoxOffice.ViewHolderBoxOffice>  {


    public VolleySingleton volleySingleton;
    public ImageLoader imageLoader;
    public ArrayList<Movie> listMovies = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public Context context;
    public ClickListener clickListener;


    public AdapterBoxOffice(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
    }

    public void setMovieList(ArrayList<Movie> listMovies) {

        this.listMovies = listMovies;
        notifyItemChanged(0, listMovies.size());
    }

    @Override
    public ViewHolderBoxOffice onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.diaplay_movies_box_office, parent, false);
        ViewHolderBoxOffice viewHolder = new ViewHolderBoxOffice(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderBoxOffice holder, int position) {

        Movie currentMovie = listMovies.get(position);

       // holder.movieTitle.setText(currentMovie.getTitle());
        String url = currentMovie.getUrlSelf();
        if (url != null) {

            imageLoader.get(url, new ImageLoader.ImageListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_not_available);

                    holder.movieImage.setImageBitmap(bitmap);
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.movieImage.setImageBitmap(response.getBitmap());
                }
            });

        }


    }


    public void setClickListener(ClickListener clickListener){

        this.clickListener=clickListener;
    }
    @Override
    public int getItemCount() {
        return listMovies.size();
    }



     class ViewHolderBoxOffice extends RecyclerView.ViewHolder implements View.OnClickListener {


         public ImageView movieImage;
        // public TextView movieTitle;


         public ViewHolderBoxOffice(View itemView) {
             super(itemView);
             // context = itemView.getContext();
             itemView.setOnClickListener(this);
             movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            // movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
         }


         public String getTitle(String title){

             return title;
         }
         @Override
         public void onClick(View v) {
             //  Intent intent = new Intent(context, DeatilBoxOfficeActivity.class);

             // context.startActivity(intent);
           //  context.startActivity(new Intent(context, DeatilBoxOfficeActivity.class));
 String title;
             if(clickListener!=null){

                 clickListener.itemClicked(v,getPosition());
             }

         }
     }

         public interface ClickListener{

            public void itemClicked(View view,int position);





   }
}


