package news;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.chromium.chrome.R;

import java.util.List;

import news.model.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Article> data;
    public NewsAdapter(List<Article> data){
        this.data = data;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        Article article = data.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDesc.setText(article.getDescription());
        Glide.with(holder.ivImage.getContext()).load(article.getUrlToImage()).into(holder.ivImage);
        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setPackage("org.chromium.chrome");
            try {
                holder.itemView.getContext().startActivity(browserIntent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed and open Kindle Browser
                browserIntent.setPackage("com.amazon.cloud9");
                holder.itemView.getContext().startActivity(browserIntent);
            }

        });
    }
    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDesc;
        private ImageView ivImage;

        public ViewHolder(View view) {
            super(view);
            this.tvTitle = view.findViewById(R.id.tvTitle);
            this.tvDesc = view.findViewById(R.id.tvDescription);
            this.ivImage = view.findViewById(R.id.ivImage);
        }

    }
}