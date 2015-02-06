package com.dianzhong.acitivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dianzhong.acitivity.DownloadImage.CallBack;
import com.dianzhong.imagecache.SimpleImageLoader;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
    

    private ListView mListView;
    Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();
        try {
            InputStream in = getResources().getAssets().open("image.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = reader.readLine())!= null){
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(new MyAdapter(list));
//        new Thread(){
//            @Override
//            public void run() {
//                final DownloadImage mDownloadImage = new DownloadImage();
//                mDownloadImage.callback(new CallBack() {
//
//                    @Override
//                    public void onSuccess() {
//                        mBitmap =  mDownloadImage.getImage("image");
//                        mHandler.sendEmptyMessage(1);
//                    }
//
//                    @Override
//                    public void onFaild() {
//                        Log.d("download", "œ¬‘ÿ ß∞‹");
//                    }
//                });
//               mDownloadImage.download("http://www.gwxdn.com/uploads/allimg/120228/46-12022Q50Q3.jpg", "image");
//            }
//        }.start();
        
//        SimpleImageLoader.showImage("http://www.gwxdn.com/uploads/allimg/120228/46-12022Q50Q3.jpg", mImageView);
    }

//    private Handler mHandler = new Handler(){
//        public void handleMessage(android.os.Message msg) {
//            mImageView.setImageBitmap(mBitmap);
//        };
//    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class MyAdapter extends BaseAdapter{

        private List<String> paths;
        /** 
         * <p>Title: </p> 
         * <p>Description: </p>  
         */
        public MyAdapter(List<String> paths) {
           this.paths = paths;
        }
        
        /* (non-Javadoc)
         * <p>Title: getCount</p> 
         * <p>Description: </p> 
         * @return 
         * @see android.widget.Adapter#getCount() 
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return paths.size();
        }

        /* (non-Javadoc)
         * <p>Title: getItem</p> 
         * <p>Description: </p> 
         * @param position
         * @return 
         * @see android.widget.Adapter#getItem(int) 
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return paths.get(position);
        }

        /* (non-Javadoc)
         * <p>Title: getItemId</p> 
         * <p>Description: </p> 
         * @param position
         * @return 
         * @see android.widget.Adapter#getItemId(int) 
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        /* (non-Javadoc)
         * <p>Title: getView</p> 
         * <p>Description: </p> 
         * @param position
         * @param convertView
         * @param parent
         * @return 
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) 
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(  
                        R.layout.item, null);  
                viewHolder = new ViewHolder();
                viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView1);
                convertView.setTag(viewHolder); 
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            SimpleImageLoader.showImage(paths.get(position), viewHolder.img);
//            viewHolder.img.setImageResource(R.drawable.ic_launcher);
            return  convertView;
        }
        
    }
    
    static class ViewHolder {
        public ImageView img;
        public boolean isComMsg = true;
    }
}
