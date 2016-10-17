package com.example.anthracyt;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.shapes.PathShape;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.anthracyt.helper.BlobHandler;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.*;

// Include the following imports to use blob APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

// File Imports for blob communication
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;


public class ToDoActivity extends FragmentActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static final String PHOTO_LOCATION = "/storage/emulated/0/anthracyt/";

    // Define the connection-string with your values
    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=http;" +
                    "AccountName=ENTER ACCOUNT NAME HERE;" +
                    "AccountKey=INSERT KEYS HERE";

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<ToDoItem> mToDoTable;

    private ToDoItemAdapter mAdapter;

    /**
     * EditText containing the "New To Do" text
     */
    private EditText mTextNewToDo;

    /**
     * Progress spinner to use for table operations
     */
    private ProgressBar mProgressBar;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    ArrayList<Pair<String,String>> imagesTexts;

    int pos;

    ImageView image;
    TextView topText;
    TextView bottomText;
    Button leftButton;
    Button rightButton;

    /**
     * Initializes the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("Anthracyt","HELLO WORLD!");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.swiping);

        setContentView(R.layout.image_caption);

        imagesTexts = new ArrayList();

        fillFiles(PHOTO_LOCATION);

        TextView rightButton = (TextView)findViewById(R.id.right);

        image = (ImageView) findViewById(R.id.imageView);

        topText = (TextView) findViewById(R.id.textViewTop);
        bottomText = (TextView) findViewById(R.id.textViewBottom);

        Typeface font = Typeface.createFromAsset(getAssets(),"impact.ttf");
        topText.setTypeface(font);
        bottomText.setTypeface(font);

        pos = 0;

        //BlobHandler.getInstance().asyncSynchronizeDatabase("test");


        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        //BlobHandler.getInstance().asyncSynchronizeDatabase("test");
        //dispatchTakePictureIntent();
    }

    private void updateImage(){
        Log.i("Anthracyt", "ENTER UPDATE");
        Pair imageText = imagesTexts.get(pos);
        String imageFileString = (String) imageText.getKey();
        Bitmap bMap = BitmapFactory.decodeFile(imageFileString);
        Log.i("Anthracyt", "ENTER UPDATE");
        image.setImageBitmap(bMap);
        String s = "";
        BufferedReader br = null;
        String text = (String) imageText.getValue();
        Log.i("Anthracyt", "Text " + text);
        try {
            try {
                br = new BufferedReader(new FileReader(text));
                Log.i("Anthracyt", "ENTER UPDATE");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
                Log.i("Anthracyt", "ENTER UPDATE");
                String line = "";
                while ((line = br.readLine()) != null) {
                    s += line;
                    //Log.i("Anthracyt", "ENTER UPDATE" + s);
                }
                Log.i("Anthracyt", "ENTER UPDATE");
                br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] a = s.split("!");
        if(a.length > 1) {
            if (s.length() > 0)
                Log.i("Anthracyt", "ENTER UPDATE");
            String text1 = s.split("!")[0];
            String text2 = s.split("!")[1];
            Log.i("Anthracyt", text1 + "\t" + text2);
            topText.setText(text1);
            bottomText.setText(text2);
        }
        Log.i("Anthracyt", "EXIT UPDATE");
    }

    public void altLeft(View view){
        Log.i("Anthracyt", "ENTER LEFT");
        if(imagesTexts.size() > 0){
            pos = (pos - 1 + imagesTexts.size()) % imagesTexts.size();
            updateImage();
        }
        Log.i("Anthracyt", "EXIT LEFT");
    }

    public void altRight(View view){
        Log.i("Anthracyt", "ENTER RIGHT");
        if(imagesTexts.size() > 0) {
            pos = (pos + 1) % imagesTexts.size();
            updateImage();
        }
        Log.i("Anthracyt", "EXIT RIGHT");
    }

    private String getImageBase(String filename){
        Log.i("Anthracyt","TESTING IMAGE BASE STRING: " + filename);
        //String[] split = filename.split("/");
        //String name = split[split.length-1];
        String name = filename.substring(0,filename.length()-4);
        name = name.substring(2);
        return name;
    }

    private void fillFiles(String fileDirectoryString){
        File fileDirectory = new File(fileDirectoryString);
        Log.i("Anthracyt", "READING FROM FILES FOR IMAGES AND TEXTS: " + fileDirectoryString);
        if (fileDirectory.isDirectory()) {
            Log.i("Anthracyt", "READING FROM FILES FOR IMAGES AND TEXTS: " + fileDirectoryString);
            File[] listFile = fileDirectory.listFiles();
            int len = 0;
            if(listFile != null) {
                Log.i("Anthracyt", "READING FROM FILES FOR IMAGES AND TEXTS: " + fileDirectoryString);
                for (File f : listFile) {
                    String name = f.getName();
                    if (name.substring(name.length() - 4).equals(".jpg")) {
                        String baseName = getImageBase(name);
                        for (File s : listFile) {
                            String txtname = s.getName();
                            if(baseName.equals(getImageBase(txtname)) && txtname.substring(txtname.length()-4).equals(".txt")){
                                Pair p = new Pair(f.getAbsolutePath(),s.getAbsolutePath());
                                imagesTexts.add(p);
                            }
                        }
                    }
                }
                for(Pair p : imagesTexts){
                    Log.i("Anthracyt", p.getKey() + "\t" + p.getValue());
                }
            }
        }
    }

    public void image(MenuItem item){
        Log.i("Anthracyt", "IMAGE WAS CALLED");
    }

    /**
     * Initializes the activity menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        return true;
    }

    /**
     * Select an option from the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            Log.i("Anthracyt","MENU INFLATED");
        }
        Intent intent = new Intent(this, RecognizeActivity.class);
        startActivity(intent);
        return true;
    }

    /**
     * Mark an item as completed
     *
     * @param item
     *            The item to mark
     */
    public void checkItem(final ToDoItem item) {
        if (mClient == null) {
            return;
        }

        // Set the item as completed and update it in the table
        item.setComplete(true);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    checkItemInTable(item);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (item.isComplete()) {
                                mAdapter.remove(item);
                            }
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();//createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        BlobHandler.getInstance().runAsyncTask(task);

    }

    /**
     * Mark an item as completed in the Mobile Service Table
     *
     * @param item
     *            The item to mark
     */
    public void checkItemInTable(ToDoItem item) throws ExecutionException, InterruptedException {
        mToDoTable.update(item).get();
    }

    /**
     * Add a new item
     *
     * @param view
     *            The view that originated the call
     */
    public void addItem(View view) {
        Intent intent = new Intent(this, RecognizeActivity.class);
        startActivity(intent);
    }

    public void addImage(View view) {
        Intent intent = new Intent(this, RecognizeActivity.class);
        startActivity(intent);
    }

    /**
     * Add an item to the Mobile Service Table
     *
     * @param item
     *            The item to Add
     */
    public ToDoItem addItemInTable(ToDoItem item) throws ExecutionException, InterruptedException {
        ToDoItem entity = mToDoTable.insert(item).get();
        return entity;
    }

    /**
     * Refresh the list with the items in the Table
     */
    private void refreshItemsFromTable() {

        // Get the items that weren't marked as completed and add them in the
        // adapter

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<ToDoItem> results = refreshItemsFromMobileServiceTable();

                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.clear();

                            for (ToDoItem item : results) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    e.printStackTrace();//createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        BlobHandler.getInstance().runAsyncTask(task);
    }

    /**
     * Refresh the list with the items in the Mobile Service Table
     */
    private List<ToDoItem> refreshItemsFromMobileServiceTable() throws ExecutionException, InterruptedException {
        return mToDoTable.where().field("complete").
                eq(val(false)).execute().get();
    }

    private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MoviesFragment();
                case 1:
                    return new SongsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public static class MoviesFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);
            return rootView;
        }
    }

    public static class SongsFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            return rootView;
        }
    }

    public class Pair<Key,Value> {
        private Key key;
        private Value value;

        public Pair(Key key, Value value){
            this.key = key;
            this.value = value;
        }

        public Key getKey(){ return this.key; }
        public Value getValue(){ return this.value; }

        public void setKey(Key key){ this.key = key; }
        public void setValue(Value value){ this.value = value; }
    }
}