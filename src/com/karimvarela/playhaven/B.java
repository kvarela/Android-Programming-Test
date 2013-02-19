package com.karimvarela.playhaven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class B extends Base {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        mButton1.setText(R.string.a);
        mButton2.setText(R.string.c);
        mButton3.setText(R.string.d);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			mIntent = new Intent(this, A.class);			
			break;
		case R.id.button2:
			mIntent = new Intent(this, C.class);			
			break;
		case R.id.button3:
			mIntent = new Intent(this, D.class);			
			break;

		default:
			break;
		}
		
		startActivity(mIntent);
	}
}
