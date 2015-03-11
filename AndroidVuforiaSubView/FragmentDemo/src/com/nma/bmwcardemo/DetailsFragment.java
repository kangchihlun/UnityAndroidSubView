package com.nma.bmwcardemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qualcomm.QCARUnityPlayer.QCARPlayerSharedActivity;
import com.unity3d.player.UnityPlayer;

public class DetailsFragment extends Fragment 
{
	private UnityPlayer mUnityPlayer;
	private QCARPlayerSharedActivity mQCARShared;
	private View v;
	public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }
	
	public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.fragment_tmp, container, false);
		//TextView text = (TextView)v.findViewById(R.id.text_view);
		//text.setText("Page" + getShownIndex());
		
		
		// Unity Code
		mUnityPlayer = new UnityPlayer(getActivity());
		final int mode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
		 
		this.mQCARShared = new QCARPlayerSharedActivity();
		this.mQCARShared.onCreate(getActivity(), mode, new QCARPlayerSharedActivity.IUnityInitializer() {
		    @Override
		    public void InitializeUnity() 
		    {
		        mUnityPlayer.init(mode, false);
		        
		        FrameLayout container = (FrameLayout)v.findViewById(R.id.frameLayout2);
		        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		        container.addView(mUnityPlayer, 0, lp);
		 		
		        
		        mUnityPlayer.windowFocusChanged(true);
		        mUnityPlayer.resume();
		    }
		});
		return v;
	}

}
