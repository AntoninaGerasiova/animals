package net.gerasiov.antonina.animals;

/**
 * Created by tony on 15.12.15.
 */
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.util.Log;

import static android.widget.ImageView.*;

public class ImageAdapter extends BaseAdapter {
    private static final int WIDTH_PORTRAIT_COEFFICIENT = 2;
    private static final int WIDTH_LANSCAPE_COEFFICIENT = 4;
    private static final double HEIGHT_PORTRAIT_COEFFICIENT = 4.6;
    private static final double HEIGHT_LANSCAPE_COEFFICIENT = 2.45;
    private static int mHeight;// height of every picture
    private static int mWidth;// width of every picture
    private Context mContext;
    private List<Integer> mThumbIds;


    // Store the list of image IDs
    public ImageAdapter(Context c, List<Integer> ids) {
        mContext = c;
        this.mThumbIds = ids;
        //get mHeight and  mHeight
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int width = size.x;
        mHeight = (height > width)?(int)(height/HEIGHT_PORTRAIT_COEFFICIENT):(int)(height/HEIGHT_LANSCAPE_COEFFICIENT);
        mWidth = (height > width)?(width/WIDTH_PORTRAIT_COEFFICIENT):(width/WIDTH_LANSCAPE_COEFFICIENT);
    }

    // Return the number of items in the Adapter
    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    // Return the data item at position
    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    // Will get called to provide the ID that
    // is passed to OnItemClickListener.onItemClick()
    @Override
    public long getItemId(int position) {
        return mThumbIds.get(position);
    }

    // Return an ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = (ImageView) convertView;

        // if convertView's not recycled, initialize some attributes
        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(mWidth, mHeight));
            imageView.setScaleType(ScaleType.FIT_XY);
        }

        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
    }
}