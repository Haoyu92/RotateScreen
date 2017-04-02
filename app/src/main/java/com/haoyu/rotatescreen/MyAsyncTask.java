package com.haoyu.rotatescreen;

/**
 * Created by haoyu on 4/1/17.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Void, Void, Void>
{
    private MainActivity activity;

    private boolean isCompleted;

    private List<String> items;
    private ProgressDialog mLoadingDialog;

    public MyAsyncTask(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute()
    {
        mLoadingDialog = new ProgressDialog(activity);
        mLoadingDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        items = loadingData();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused)
    {
        isCompleted = true;
        notifyActivityTaskCompleted();
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    public List<String> getItems()
    {
        return items;
    }

    private List<String> loadingData()
    {
        try
        {
            Thread.sleep(7000);
        }catch (InterruptedException e) {

        }
        return new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
    }

    public void setActivity(MainActivity activity)
    {
        if (activity == null)
        {
            mLoadingDialog.dismiss();
        }
        this.activity = activity;
        if (activity != null && !isCompleted)
        {
            mLoadingDialog = new ProgressDialog(activity);
            mLoadingDialog.show();
        }
        if (isCompleted)
        {
            notifyActivityTaskCompleted();
        }
    }

    private void notifyActivityTaskCompleted()
    {
        if (null != activity)
        {
            activity.onTaskCompleted();
        }
    }

}
