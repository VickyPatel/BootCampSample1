package vickypatel.ca.bootcampsample1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vickypatel.ca.bootcampsample1.MainActivity;
import vickypatel.ca.bootcampsample1.R;
import vickypatel.ca.bootcampsample1.pojos.User;

/**
 * Created by VickyPatel on 2016-06-13.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<User> comments;
    private Context context;

    public CustomAdapter(Context context, ArrayList<User> comments) {
        this.context = context;
        this.comments = comments;
        System.out.println(comments.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userId.setText(String.valueOf(comments.get(position).getId()));
        holder.name.setText(comments.get(position).getName());
        holder.email.setText(comments.get(position).getEmail());
        holder.comments.setText(comments.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView userId, name, email, comments;

        public ViewHolder(View itemView) {
            super(itemView);
            userId = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            comments = (TextView) itemView.findViewById(R.id.comments);
        }
    }

}
