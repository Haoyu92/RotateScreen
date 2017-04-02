package com.haoyu.rotatescreen;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by haoyu on 4/1/17.
 */

public class OtherRetainedFragment extends Fragment
{

    private MyAsyncTask data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(MyAsyncTask data)
    {
        this.data = data;
    }

    public MyAsyncTask getData()
    {
        return data;
    }


}
