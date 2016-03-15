package com.gatemantra;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class TestHomeActivity extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testhome);
		
		findViewById(R.id.algorithms).setOnClickListener(this);
		findViewById(R.id.compiler).setOnClickListener(this);
		findViewById(R.id.comptrorganisation).setOnClickListener(this);
		findViewById(R.id.datastructures).setOnClickListener(this);
		findViewById(R.id.db).setOnClickListener(this);
		findViewById(R.id.digital).setOnClickListener(this);
		findViewById(R.id.engmath).setOnClickListener(this);
		findViewById(R.id.netwrks).setOnClickListener(this);
		findViewById(R.id.os).setOnClickListener(this);
		findViewById(R.id.randomtest).setOnClickListener(this);
		findViewById(R.id.sweng).setOnClickListener(this);
		findViewById(R.id.toc).setOnClickListener(this);
		findViewById(R.id.web).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String sub="randomtest";
		int elementId=v.getId();
		switch(elementId)
		{
			case R.id.algorithms:
				sub="algorithms";
				break;
			case R.id.compiler:
				sub="compiler";
				break;
			case R.id.comptrorganisation:
				sub="comptrorganisation";
				break;
			case R.id.datastructures:
				sub="datastructures";
				break;
			case R.id.db:
				sub="db";
				break;
			case R.id.digital:
				sub="digital";
				break;
			case R.id.engmath:
				sub="engmath";
				break;
			case R.id.netwrks:
				sub="netwrks";
				break;
			case R.id.os:
				sub="os";
				break;
			case R.id.randomtest:
				sub="randomtest";
				break;
				
				
			case R.id.sweng:
				sub="sweng";
				break;
			case R.id.toc:
				sub="toc";
				break;
			case R.id.web:
				sub="web";
				break;
				
		}
		 Intent myIntent = new Intent(TestHomeActivity.this, QstnActivity.class);
         myIntent.putExtra("subject", sub); //Optional parameters
         startActivity(myIntent);
		
	}

	
	
}
