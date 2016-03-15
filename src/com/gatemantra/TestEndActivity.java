package com.gatemantra;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TestEndActivity extends Activity  {

	private String sub;
	private int testscore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testend);
		Bundle extras = getIntent().getExtras();
		if (extras != null) 
		{
		    sub = extras.getString("subject");
		    // Log.d("nnnn", "subject: "+sub);
		    testscore=extras.getInt("testscore");
		    // Log.d("onCreate", "sub"+getSubject(sub));
		    ((TextView)findViewById(R.id.testname)).setText(getSubject(sub));
		    
		    // Log.d("nnnn", "testscore: "+testscore);
		    ((TextView)findViewById(R.id.testscore)).setText("Score: "+testscore);
		    setComment(testscore);
		}	
	}
	
	public String getSubject(String subject)
	{
		// Log.d("getSubject", "sub"+subject);
		if(subject.equals("randomtest"))
		{
			return "Random Tests";
		}
		else if(subject.equals("engmath"))
		{
			return "Engineering Mathematics";
		}
		else if(subject.equals("digital"))
		{
			return "Digital Logic";
		}
		else if(subject.equals("comptrorganisation"))
		{
			return "Computer Organization and Architecture";
		}
		else if(subject.equals("datastructures"))
		{
			return "Data Structures";
		}
		else if(subject.equals("algorithms"))
		{
			return "Algorithms";
		}
		else if(subject.equals("toc"))
		{
			return "Theory of Computation";
		}
		else if(subject.equals("compiler"))
		{
			return "Compiler Design";
		}
		else if(subject.equals("os"))
		{
			return "Operating System";
		}
		else if(subject.equals("db"))
		{
			return "Databases";
		}
		else if(subject.equals("sweng"))
		{
			return "Software Engineering";
		}
		else if(subject.equals("netwrks"))
		{
			return "Computer Networks";
		}
		else if(subject.equals("web"))
		{
			return "Web technologies";
		}
		return "Unknown";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void setComment(int score) {
		// TODO Auto-generated method stub
		// Log.d("Comment", "Seting comment"+score);
		String testcomment="Good...!";
		TextView testCommentTextview=(TextView)findViewById(R.id.testcomment);
		testCommentTextview.setTextColor(Color.BLUE);
		if(score>=8)
		{
			testcomment="Excellent! Keep going...!";
			testCommentTextview.setTextColor(Color.GREEN);
		}
		else if(score<=5)
		{
			testcomment="Oops! Work hard...!";
			testCommentTextview.setTextColor(Color.RED);
		}

		testCommentTextview.setText(testcomment);
	}
	public void goHome(View v)
	{
		Intent myIntent = new Intent(TestEndActivity.this, TestHomeActivity.class);
       // myIntent.putExtra("subject", sub); //Optional parameters
		myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(myIntent);
		
	}
	public void tryAgain(View v)
	{
		Intent myIntent = new Intent(TestEndActivity.this, QstnActivity.class);
		myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		myIntent.putExtra("subject", sub); //Optional parameters
	    startActivity(myIntent);
	}
	
}
