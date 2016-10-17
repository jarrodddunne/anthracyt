package com.example.anthracyt.helper;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.anthracyt.ToDoActivity;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by jarrod on 10/15/2016.
 */

public class BlobHandler {

    private static BlobHandler instance = new BlobHandler();
    protected BlobHandler(){}
    public static BlobHandler getInstance() {
        if(instance == null){
            instance = new BlobHandler();
        }
        return instance;
    }


    /**
     * Print out blobs on container
     */
    public void asyncPrintBlobsInContainer(final String containerString){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    CloudBlobContainer myContainer = getContainer(containerString);
                    File dir = new File(ToDoActivity.PHOTO_LOCATION);
                    File files[] = dir.listFiles();
                    for(File file : files){
                        isFileInContainer(file.toString(), myContainer);
                        uploadToContainer(file.toString(), myContainer);
                    }
                    uploadToContainer(ToDoActivity.PHOTO_LOCATION + "myimage.jpg", myContainer);
                    printBlobsInContainer(myContainer);
                    downloadBlobsFromContainer(myContainer);
                } catch (final Exception e) {
                    e.printStackTrace();//createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    /**
     * Create container
     */
    public CloudBlobContainer getContainer(String containerString){
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(ToDoActivity.storageConnectionString);

            // Create the blob client.
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            // Get a reference to a container.
            // The container name must be lower case
            CloudBlobContainer container = blobClient.getContainerReference(containerString);

            // Create the container if it does not exist.
            container.createIfNotExists();
            return container;
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Prints list of blobs in container
     */
    public void printBlobsInContainer(CloudBlobContainer container){
        try
        {
            // Loop over blobs within the container and output the URI to each of them.
            for (ListBlobItem blobItem : container.listBlobs()) {
                Log.i("Anthracyt", "BLOBS IN CONTAINER: " + blobItem.getUri().toString());
            }
        }
        catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    public void asyncSynchronizeDatabase(final String containerString){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    CloudBlobContainer myContainer = getContainer(containerString);
                    File dir = new File(ToDoActivity.PHOTO_LOCATION);
                    File files[] = dir.listFiles();
                    for(File file : files) {
                        if (!isFileInContainer(file.toString(), myContainer)) {
                            Log.i("Anthracyt", "UPLOADING FILE <" + file.toString() + ">");
                            uploadToContainer(file.toString(), myContainer);
                        }
                    }
                    for(ListBlobItem blobItem : myContainer.listBlobs()){
                        if(!isBlobInDirectory(dir.toString(), blobItem)){
                            Log.i("Anthracyt", "DOWNLOADING BLOB <" + ((CloudBlob) blobItem).getName() + ">");
                            downloadBlob(blobItem);
                        }
                    }
                    printBlobsInContainer(myContainer);
                } catch (final Exception e) {
                    e.printStackTrace();
                    //createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    /**
     * Checks if file is in container
     */
    public boolean isFileInContainer(String fileName, CloudBlobContainer container){
        for(ListBlobItem blobItem : container.listBlobs()){
            String[] blobNameSplit = blobItem.getUri().toString().split("/");
            String blobName = blobNameSplit[blobNameSplit.length-1];
            String[] fileNameSplit = fileName.split("/");
            String imageName = fileNameSplit[fileNameSplit.length-1];
            if(imageName.equals(blobName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if blob is in directory
     */
    public boolean isBlobInDirectory(String directoryString, ListBlobItem blobItem){
        File directory = new File(directoryString);
        File files[] = directory.listFiles();
        String[] blobNameSplit = blobItem.getUri().toString().split("/");
        String blobName = blobNameSplit[blobNameSplit.length-1];
        for(File file : files){
            String[] fileNameSplit = file.toString().split("/");
            String imageName = fileNameSplit[fileNameSplit.length-1];
            Log.i("Anthracyt", "IS " + imageName + " equal to " + blobName);
            if(imageName.equals(blobName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Downloads a blob locally
     */
    public void downloadBlobsFromContainer(CloudBlobContainer container){
        try
        {
            for (ListBlobItem blobItem : container.listBlobs()) {
                // If the item is a blob, not a virtual directory.
                if (blobItem instanceof CloudBlob) {
                    // Download the item and save it to a file with the same name.
                    CloudBlob blob = (CloudBlob) blobItem;
                    blob.download(new FileOutputStream(ToDoActivity.PHOTO_LOCATION + blob.getName()));
                }
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    /**
     * Downloads a single blob locally
     */
    public void downloadBlob(ListBlobItem blobItem){
        try
        {
            // If the item is a blob, not a virtual directory.
            if (blobItem instanceof CloudBlob) {
                CloudBlob blob = (CloudBlob) blobItem;
                blob.download(new FileOutputStream(ToDoActivity.PHOTO_LOCATION + blob.getName()));
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    /**
     * Upload to container
     */
    public void uploadToContainer(String localFile, CloudBlobContainer container){
        try
        {
            String[] fileNameSplit = localFile.toString().split("/");
            String imageName = fileNameSplit[fileNameSplit.length-1];
            CloudBlockBlob blob = container.getBlockBlobReference(imageName);
            File source = new File(localFile);
            blob.upload(new FileInputStream(source), source.length());
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    public AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

}
