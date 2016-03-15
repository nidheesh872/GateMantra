package com.gatemantra;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class NewsHomeActivity extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newshome);
		
		findViewById(R.id.importantdates).setOnClickListener(this);
		findViewById(R.id.samplenews).setOnClickListener(this);
		
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
		Intent myIntent;
		int elementId=v.getId();
		switch(elementId)
		{
			case R.id.samplenews:
				 myIntent = new Intent(NewsHomeActivity.this, HomeActivity.class);
			break;
			
			case R.id.importantdates:
				 myIntent = new Intent(NewsHomeActivity.this, NewsActivity.class);
			break;
			
			default:
				 myIntent = new Intent(NewsHomeActivity.this, HomeActivity.class);
			break;
			
		}
		// Intent myIntent = new Intent(NewsHomeActivity.this, HomeActivity.class);
       //  myIntent.putExtra("subject", sub); //Optional parameters
         startActivity(myIntent);
		
	}

	
	
}
