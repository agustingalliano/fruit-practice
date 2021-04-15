package galliano.android.fruitworld.adaptaters;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

import galliano.android.fruitworld.R;
import galliano.android.fruitworld.models.Fruit;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;

    private List<Fruit> fruits;

    public MyAdapter (Context context, int layout, List<Fruit>fruits){
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fruits.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);
            holder = new ViewHolder();
            holder.fruitTextView = (TextView) convertView.findViewById(R.id.textViewFruit);
            holder.cityTextView = (TextView) convertView.findViewById(R.id.textNewCity);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        String currentFruit = fruits.get(position).getName();
        String currentCity = fruits.get(position).getOrigin();
        int currentIcon = fruits.get(position).getIcon();
        holder.image.setImageResource(currentIcon);
        holder.fruitTextView.setText(currentFruit);
        holder.cityTextView.setText(currentCity);
        return convertView;
    }
    static class ViewHolder{
        private TextView fruitTextView;
        private TextView cityTextView;
        private ImageView image;
    }
}
