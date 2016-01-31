package in.ac.nita.advaitam;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    int[] colorArray;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        colorArray = mContext.getResources().getIntArray(R.array.rainbow);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        FeedItem feedItem = feedItemList.get(i);

        //Download image using picasso library
        //Picasso.with(mContext).load(feedItem.thumbnail)
        //        .into(customViewHolder.imageView);
        int randomStr = colorArray[new Random().nextInt(colorArray.length)];
        customViewHolder.imageView.setBackgroundDrawable(new ColorDrawable(randomStr));

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.title));
        customViewHolder.descView.setText(Html.fromHtml(feedItem.smallDesc));
        customViewHolder.thumbText.setText(feedItem.title.charAt(0)+"");
    }

    @Override
    public int getItemCount() {
        return ( feedItemList != null ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected ImageView imageView;
        protected TextView textView, descView, thumbText;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.thumbText = (TextView) view.findViewById(R.id.thumbText);
            this.descView = (TextView) view.findViewById(R.id.desc);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), DetailsActivity.class);
            FeedItem item = feedItemList.get(getLayoutPosition());
            i.putExtra("EVENT_TITLE",item.title);
            i.putExtra("IMG_URL",item.backdrop);
            i.putExtra("SMALL_DESC",item.desc);
            i.putExtra("CONTACT",item.contact);
            i.putExtra("LINK",item.link);

            v.getContext().startActivity(i);
        }
    }
}