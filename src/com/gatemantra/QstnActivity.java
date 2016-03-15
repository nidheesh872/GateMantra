package com.gatemantra;



import java.util.ArrayList;
import java.util.Collections;
import com.gatemantra.utils.DbHandler;
import com.gatemantra.utils.Question;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QstnActivity extends Activity implements AnimationListener, OnClickListener
{

	TextView qstnfield;
	TextView ans;
	TextView choice1;
	TextView choice2;
	TextView choice3;
	TextView choice4;
	//TextView AnsStatus;
	//Button checkButton;
	TableLayout tblout;
	ProgressDialog loading=null;
	
	Animation slideDwn;
	
	Question question=null;
	boolean attempt;
	boolean showans;
	boolean ansStatus;
	int qIndex;
	String sub ="";
	int testscore;
	
	ArrayList<Question> questionList;       
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		attempt=false;
		showans=false;
		qIndex=-1;
		testscore=0;
		qstnfield = (TextView) findViewById(R.id.TextView01);
		choice1 = (TextView) findViewById(R.id.TextView02);
		choice2 = (TextView) findViewById(R.id.TextView03);
		choice3 = (TextView) findViewById(R.id.TextView04);
		choice4 = (TextView) findViewById(R.id.TextView05);
		ans = (TextView) findViewById(R.id.TextView06);


		slideDwn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		
		
		slideDwn.setAnimationListener(this);
		choice1.setOnClickListener(this);
		choice2.setOnClickListener(this);
		choice3.setOnClickListener(this);
		choice4.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) 
		{
		    sub = extras.getString("subject");
		    // Log.d("nnnn", "subject: "+sub);
		}
		DbHandler dbHandler= new DbHandler(getApplicationContext());
		
		try
		{	        
			if(sub.equals("randomtest"))
				questionList= dbHandler.getAllQuestions();
			else
				questionList= dbHandler.getQuestionsIn(sub);
			
			if(questionList.size()<=0)	
			{
				Toast.makeText(getApplicationContext(), "No questions found", Toast.LENGTH_LONG).show();
				Intent myIntent = new Intent( QstnActivity.this,TestHomeActivity.class);
				myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(myIntent);
			}
			else
			{
				for (int i = 0; i < questionList.size(); i++) {
					// Log.d("qlist",questionList.get(i).getQuestion());
				}
				Collections.shuffle(questionList);
				// Log.d("nnnqlist","qlist size"+questionList.size());
				showNextQuestion();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(),"Exception: No questions found.", Toast.LENGTH_LONG).show();
			Intent myIntent = new Intent( QstnActivity.this,TestHomeActivity.class);
			myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(myIntent);
		}
		
		
}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onClick(View v) {
		
		int elementId=v.getId();
		switch(elementId)
		{
			case R.id.TextView02: 
			case R.id.TextView03: 
			case R.id.TextView04: 
			case R.id.TextView05: 
				//if(((TextView)findViewById(elementId)).getText().equals("ans"))
				TextView selectedElement=(TextView)findViewById(elementId);
				attempt=true;
				if(selectedElement.getText().toString().substring(2).equals(question.getAnswer()))
				{
				//	selectedElement.setBackgroundColor(getResources().getColor(R.color.green));
					selectedElement.setBackgroundResource(R.drawable.options_correct);
					//selectedElement.setHeight()
					ansStatus=true;
					testscore++;
				}
				else
				{
				//	selectedElement.setBackgroundColor(getResources().getColor(R.color.red));
					selectedElement.setBackgroundResource(R.drawable.options_incorrect);
					ansStatus=false;
				}
			//	Toast.makeText(getApplicationContext(), ((TextView)findViewById(v.getId())).getText().toString().substring(2), Toast.LENGTH_LONG).show();
				
			//case R.id.CheckButton:
				if(!showans)
				{
					showAnswer();
					
					showans=true;
				}
				
				break;
    	}
		
	}
	public void showAnswer()
	{
		String attemptStatus=null;
	//	AnsStatus.setVisibility(View.VISIBLE);
		if(ansStatus)
			attemptStatus="Correct...";
		else
			attemptStatus="Incorrect...";
		
		ans.setText(attemptStatus+"\nAns: "+question.getAnswer()+"\n"+"Explanation: \n"+question.getExplanation());
		//AnsStatus.setAnimation(slideDwn);
		ans.setVisibility(View.VISIBLE);
		ans.startAnimation(slideDwn);
		
	}
	public void showNextQuestion(View v)
	{
		
		((Button)findViewById(R.id.PrevQn)).setVisibility(View.VISIBLE);
		
		if(qIndex<=questionList.size())
		{
			question= questionList.get(++qIndex);
			
			// Log.d("nnndb", "nxt showing:"+question.getQuestion()+question.getChoice1()+question.getChoice2()+question.getChoice3()+question.getChoice4()+question.getAnswer()+question.getExplanation());
			qstnfield.setText("Q"+(qIndex+1)+". "+question.getQuestion());
			choice1.setText("a."+question.getChoice1());
			choice2.setText("b."+question.getChoice2());
			choice3.setText("c."+question.getChoice3());
			choice4.setText("d."+question.getChoice4());
			ans.setVisibility(View.GONE);
			resetQn();
			//qIndex++;
			// Log.d("nnndb", "nxt indx:"+qIndex);
			if(qIndex==(questionList.size()-1))
			{
			Button nxt=	(Button)findViewById(R.id.NextQn);
			nxt.setVisibility(View.GONE);
			}
		}
	}
	public void showNextQuestion()
	{
		if(qIndex<=questionList.size())
		{
			
			question= questionList.get(++qIndex);
			// Log.d("nnndb", "nxt showing:"+question.getQuestion()+question.getChoice1()+question.getChoice2()+question.getChoice3()+question.getChoice4()+question.getAnswer()+question.getExplanation());
			qstnfield.setText("Q1"+". "+question.getQuestion());
			choice1.setText("a."+question.getChoice1());
			choice2.setText("b."+question.getChoice2());
			choice3.setText("c."+question.getChoice3());
			choice4.setText("d."+question.getChoice4());
			//qIndex++;
			// Log.d("nnndb", "nxt indx:"+qIndex);
			if(qIndex==(questionList.size()-1))
			{
				((Button)findViewById(R.id.NextQn)).setVisibility(View.GONE);
		//	nxt.setClickable(false);
			}
		}
	}
	public void showPrevQuestion(View v)
	{
		((Button)findViewById(R.id.NextQn)).setVisibility(View.VISIBLE);
		
		if(qIndex>=1)
		{
			question= questionList.get(--qIndex);
			// Log.d("nnndb", "prev showing:"+question.getQuestion()+question.getChoice1()+question.getChoice2()+question.getChoice3()+question.getChoice4()+question.getAnswer()+question.getExplanation());
			qstnfield.setText("Q"+(qIndex+1)+". "+question.getQuestion());
			choice1.setText("a."+question.getChoice1());
			choice2.setText("b."+question.getChoice2());
			choice3.setText("c."+question.getChoice3());
			choice4.setText("d."+question.getChoice4());
			resetQn();
		}
		if(qIndex==0)
		{
				//((Button)findViewById(R.id.PrevQn)).setClickable(false);
				((Button)findViewById(R.id.PrevQn)).setVisibility(View.GONE);
			//nxt.setClickable(false);
		}
	
	}
	
	public void endTest(View v)
	{
		Intent myIntent = new Intent(QstnActivity.this, TestEndActivity.class);
	    myIntent.putExtra("subject", sub); //Optional parameters
	    myIntent.putExtra("testscore", testscore);
	    startActivity(myIntent);
	}
	
	public void resetQn()
	{
		//choice1.setBackground( getResources().getColor(R.color.choiceolor));
		choice1.setBackgroundResource(R.drawable.options);
		choice2.setBackgroundResource(R.drawable.options);
		choice3.setBackgroundResource(R.drawable.options);
		choice4.setBackgroundResource(R.drawable.options);
		showans=false;
		attempt=false;
	}
	
	
	
	

}
