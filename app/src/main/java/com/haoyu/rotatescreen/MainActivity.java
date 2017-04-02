package com.haoyu.rotatescreen;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

public class MainActivity extends ListActivity
{
    private static final String TAG = "MainActivity";
    private ListAdapter mAdapter;
    private List<String> mDatas;
    private OtherRetainedFragment dataFragment;
    private MyAsyncTask mMyTask;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

        FragmentManager fm = getFragmentManager();
        dataFragment = (OtherRetainedFragment) fm.findFragmentByTag("data");

        if (dataFragment == null)
        {
            dataFragment = new OtherRetainedFragment();
            fm.beginTransaction().add(dataFragment, "data").commit();
        }
        mMyTask = dataFragment.getData();
        if (mMyTask != null)
        {
            mMyTask.setActivity(this);
        } else
        {
            mMyTask = new MyAsyncTask(this);
            dataFragment.setData(mMyTask);
            mMyTask.execute();
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle state)
    {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        mMyTask.setActivity(null);
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();

    }

    public void onTaskCompleted()
    {
        mDatas = mMyTask.getItems();
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mDatas);
        setListAdapter(mAdapter);
    }

}
