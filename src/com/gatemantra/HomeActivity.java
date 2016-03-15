package com.gatemantra;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gatemantra.utils.DbHandler;
import com.gatemantra.utils.Question;
import com.gatemantra.utils.ServiceHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	ProgressDialog pDialog;
	int dbMaxId;
	DbHandler dbHandler;
	private String getmaxqidurl="http://boser.byethost7.com/getQid.php";
	private String syncqstnurl="http://boser.byethost7.com/syncqstns.php";
	boolean gException;
	boolean jsonException;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		dbHandler= new DbHandler(getApplicationContext());
		getApplicationContext();
		gException=false;
		
//		Question qstn= new Question(11, "questionTest1", "CHOICE1");	
//		dbHandler.addQuestion(qstn);
//		dbHandler.addQuestion(new Question(12, "questionTest2", "CHOICE2"));
//		dbHandler.addQuestion(new Question(13, "questionTest3", "CHOICE3"));
//		dbHandler.addQuestion(new Question(14, "questionTest4", "CHOICE4"));
//		dbHandler.addQuestion(new Question(15, "questionTes5", "CHOICE1"));
		
		
	//	ArrayList<Question> questionList= dbHandler.getAllQuestions();
	//	Collections.shuffle(questionList);
//		log
		Log.d("nnnqlist","qlist size");
		
		findViewById(R.id.materials).setOnClickListener(this);
		findViewById(R.id.tests).setOnClickListener(this);
		findViewById(R.id.news).setOnClickListener(this);
		//findViewById(R.id.about).setOnClickListener(this);
		findViewById(R.id.sync).setOnClickListener(this);
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int elementId=view.getId();
		Intent myIntent;
		switch(elementId)
		{
			case R.id.materials:
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
				myIntent = new Intent(HomeActivity.this, MaterialsHomeActivity.class);
				startActivity(myIntent);
				break;
			case R.id.tests:
				myIntent = new Intent(HomeActivity.this, TestHomeActivity.class);
				startActivity(myIntent);
				
				break;
			case R.id.news:
				myIntent = new Intent(HomeActivity.this, NewsHomeActivity.class);
				startActivity(myIntent);
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
				break;
//			case R.id.about:
//				Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
//				break;
			case R.id.sync:
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
				int localMaxId=dbHandler.getMaxId();
				new SyncQuestns().execute(String.valueOf(localMaxId));
				break;
		}
	
		
	}

	
	public void checkLocaldb(){
		
		
	}

	
	
	/**************/
private class SyncQuestns extends AsyncTask<String, Void, Void> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(HomeActivity.this);
			pDialog.setMessage("Fetching data from server...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			//populateSpinner();
			// Log.d("post excpn", gException+"");
			if(jsonException)
			{
				Toast.makeText(getApplicationContext(), "App is uptodate.", Toast.LENGTH_LONG).show();
			}
			else if(gException)
			{
				Toast.makeText(getApplicationContext(), "Oops! Network/Server error has occured.", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Gate Mantra successfully synced with the server.", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected Void doInBackground(String... arg0) {
			
			String maxid = arg0[0];
			// Preparing post params
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("qstnid", maxid));

			ServiceHandler serviceClient = new ServiceHandler();

			String json = serviceClient.makeServiceCall(syncqstnurl,ServiceHandler.GET, params);
						
						// Log.e("Response: ", "> " + json);

						if (json != null) {
							try {
								JSONObject jsonObj = new JSONObject(json);
								if (jsonObj != null) {
									JSONArray questions = jsonObj
											.getJSONArray("qstns");						

									for (int i = 0; i < questions.length(); i++) {
										JSONObject jsonQstn = (JSONObject) questions.get(i);
										
									Question qstn2= new Question(jsonQstn.getInt("qid"), jsonQstn.getString("qstn"), jsonQstn.getString("ans"), jsonQstn.getString("ch1"), jsonQstn.getString("ch2"), jsonQstn.getString("ch3"), jsonQstn.getString("ch4"), jsonQstn.getString("expl"),jsonQstn.getString("sub"));
										dbHandler.addQuestion(qstn2);
										
										}
						}

							} catch (JSONException e) {
								// Log.e("JSON Data1", "err"+e.getMessage());
								jsonException=true;
							}
							catch (Exception e) {
								// Log.e("JSON Data2", "err"+e.getMessage());
								gException=true;
							}

						} else {
							gException=true;
							// Log.e("JSON Data3", "Didn't receive any data from server!");
							//Toast.makeText(mcontext, "Didn't receive any data from server!", Toast.LENGTH_SHORT).show();
							
						}

			
			return null;
		}

	}



	
	
	
	/**********************/
	
	
private class GetMaxId extends AsyncTask<Void, Void, Void> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(HomeActivity.this);
			pDialog.setMessage("Fetching data..");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandler jsonParser = new ServiceHandler();
			String json = jsonParser.makeServiceCall(getmaxqidurl, ServiceHandler.GET);

			// Log.e("Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj != null) {
					//	JSONArray categories = jsonObj
						//		.getJSONArray("categories");						

						//for (int i = 0; i < categories.length(); i++) {
							//JSONObject catObj = (JSONObject) categories.get(i);
							dbMaxId = jsonObj.getInt("maxid");
							// Log.d("nnn", "got maxid"+dbMaxId);

						//}
					}

				} catch (JSONException e) {
					// Log.d("nnn", "json exception"+e.getMessage());
				}

			} else {
				gException=true;
				// Log.e("JSON Data", "Didn't receive any data from server!");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			//populateSpinner();
			
			
			Toast.makeText(getApplicationContext(), "dbmaxid."+dbMaxId, Toast.LENGTH_SHORT).show();
		}
		
		

	}

	

}
