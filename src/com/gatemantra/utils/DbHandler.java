package com.gatemantra.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

	public static final int DbVersion=1;
	public static final String DataBaseName="QstnManager";
	public static final String TableName1="Questions";
	
	
	public static final String col1= "qid";
	public static final String col2="qstn";
	public static final String col3="ans";
	public static final String col4="ch1";
	public static final String col5="ch2";
	public static final String col6="ch3";
	public static final String col7="ch4";
	public static final String col8="expl";
	public static final String col9="sub";
	public DbHandler(Context context) {
		super(context, DataBaseName, null, DbVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String createTableQuery="CREATE TABLE "+ TableName1 +" ( "+col1+" INTEGER PRIMARY KEY , "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT, "+col5+" TEXT, "+col6+" TEXT, "+col7+" TEXT, "+col8+" TEXT, "+col9+" TEXT )";
		Log.d("dbget","create table: "+createTableQuery);
		db.execSQL(createTableQuery);
		//addQuestion(new Question(12, "questionTest2", "CHOICE2"));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS"+TableName1);
	}

	public void addQuestion(Question question)
	{
		SQLiteDatabase db= getWritableDatabase();
		Log.d("dbget","to insert: "+question.getQuestion());
		ContentValues cn= new ContentValues();
		cn.put(col1, question.getId());
		cn.put(col2, question.getQuestion());
		cn.put(col3, question.getAnswer());
		cn.put(col4, question.getChoice1());
		cn.put(col5, question.getChoice2());
		cn.put(col6, question.getChoice3());
		cn.put(col7, question.getChoice4());
		cn.put(col8, question.getExplanation());
		cn.put(col9, question.getSubject());
		db.insert(TableName1, null, cn);
		Log.d("dbget","insertd: "+question.getQuestion());
	}
	
	public Question getQuestion()
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1;
		
		Cursor cr= db.rawQuery(selQuery, null);
		Question q=null;
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
			q=new Question(Integer.parseInt(cr.getString(0)), cr.getString(1), cr.getString(2));
			q.setChoice1(cr.getString(3));
			q.setChoice2(cr.getString(4));
			q.setChoice3(cr.getString(5));
			q.setChoice4(cr.getString(6));
			Log.d("dbget","qstn: "+q.getQuestion());
		}
		return q;
	}
	public ArrayList<Question> getAllQuestions()
	{
		ArrayList<Question> qlist= new ArrayList<Question>();
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1;
		
		Cursor cr= db.rawQuery(selQuery, null);
//		 q=null;
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
		//	while(!cr.isLast())
			do{
				Question q=new Question(Integer.parseInt(cr.getString(0)), cr.getString(1), cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6));
				q.setExplanation(cr.getString(7));
				q.setSubject(cr.getString(8));
//				q.setChoice1(cr.getString(3));
//				q.setChoice2(cr.getString(4));
//				q.setChoice3(cr.getString(5));
//				q.setChoice4(cr.getString(6));
				qlist.add(q);
				Log.d("dbget","qstn: "+q.getSubject());
			}while(cr.moveToNext());
			
		}
		return qlist;
	}
	public ArrayList<Question> getQuestionsIn(String sub)
	{
		ArrayList<Question> qlist= new ArrayList<Question>();
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1+" where sub ='"+sub+"'";
		Log.d("dbget",selQuery);
		Cursor cr= db.rawQuery(selQuery, null);
//		 q=null;
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
		//	while(!cr.isLast())
			do{
				Question q=new Question(Integer.parseInt(cr.getString(0)), cr.getString(1), cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6));
				q.setExplanation(cr.getString(7));
				q.setSubject(cr.getString(8));
//				q.setChoice1(cr.getString(3));
//				q.setChoice2(cr.getString(4));
//				q.setChoice3(cr.getString(5));
//				q.setChoice4(cr.getString(6));
				qlist.add(q);
				Log.d("dbget","qstn: "+q.getSubject());
			}while(cr.moveToNext());
			
		}
		return qlist;
	}
	public int getMaxId()
	{
		ArrayList<Question> qlist= new ArrayList<Question>();
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT MAX(qid) FROM "+TableName1;
		int localmaxqid=0;
		Cursor cr= db.rawQuery(selQuery, null);
//		 q=null;
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
		//	while(!cr.isLast())
			do{
				if(cr.getString(0)!=null)
				{
					localmaxqid= Integer.parseInt(cr.getString(0));
				}
				
				Log.d("dbget","max qid: "+localmaxqid);
			}while(cr.moveToNext());
			
		}
		return localmaxqid;
	}
}
