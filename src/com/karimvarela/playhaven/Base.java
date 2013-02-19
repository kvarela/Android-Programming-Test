package com.karimvarela.playhaven;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public abstract class Base extends Activity implements OnClickListener {

	// kv: Buttons
	protected Button mButton1;
	protected Button mButton2;
	protected Button mButton3;

	protected TextView mTextViewTime;

	protected Intent mIntent;

	private static long mTimeTotal = 0;
	private long mTimeOnResume = 0;
	private long mTimeOnPause = 0;

	private final int REFRESH_RATE = 100;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout);

		mButton1 = (Button) findViewById(R.id.button1);
		mButton2 = (Button) findViewById(R.id.button2);
		mButton3 = (Button) findViewById(R.id.button3);

		mTextViewTime = (TextView) findViewById(R.id.textView_time);

		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
		mButton3.setOnClickListener(this);
	}

	/** Runnable to handle timing of current Activity **/
	private Runnable startTimer = new Runnable() {
		public void run() {
			mTextViewTime
					.setText(getString(R.string.time)
							+ (mTimeTotal + System.currentTimeMillis() - mTimeOnResume));
			mHandler.postDelayed(this, REFRESH_RATE);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		mTimeOnResume = System.currentTimeMillis();

		mHandler.removeCallbacks(startTimer);
		mHandler.postDelayed(startTimer, 0);
	}

	@Override
	protected void onPause() {
		super.onPause();

		mTimeOnPause = System.currentTimeMillis();

		mTimeTotal += mTimeOnPause - mTimeOnResume;

		mHandler.removeCallbacks(startTimer);
	}

	@Override
	public abstract void onClick(View v);
}
