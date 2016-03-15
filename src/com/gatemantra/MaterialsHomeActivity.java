package com.gatemantra;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MaterialsHomeActivity extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materialshome);
		
		findViewById(R.id.syllabus).setOnClickListener(this);
		findViewById(R.id.prepare).setOnClickListener(this);
		findViewById(R.id.samplematerial).setOnClickListener(this);
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
			case R.id.samplematerial:
			//	Toast.makeText(getApplicationContext(), "samplematerial", Toast.LENGTH_SHORT);
			myIntent = new Intent(MaterialsHomeActivity.this, HomeActivity.class);
				break;
			
			case R.id.syllabus:
				myIntent = new Intent(MaterialsHomeActivity.this, SyllabusActivity.class);
				//Toast.makeText(getApplicationContext(), "samplematerial", Toast.LENGTH_SHORT);
				break;
			case R.id.prepare:
				myIntent = new Intent(MaterialsHomeActivity.this, Material.class);
				break;
			default:
				myIntent = new Intent(MaterialsHomeActivity.this, HomeActivity.class);
		}
		 
       //  myIntent.putExtra("subject", sub); //Optional parameters
         startActivity(myIntent);
		
	}

	
	
}
