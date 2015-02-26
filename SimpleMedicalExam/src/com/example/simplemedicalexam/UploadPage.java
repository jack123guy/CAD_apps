package com.example.simplemedicalexam;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UploadPage extends InputPage3 implements OnClickListener{
	
	protected static double Result_CAD = -1, Result_HF = -1, Result_GERD = -1;
	private Button Finish, Back;
	
	Thread Upload;
	HttpPost httppost;
	InputStream IS = null;
	SimpleDateFormat DateUpload;
	ArrayList<NameValuePair> UploadList = new ArrayList<NameValuePair>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_page);
		
		Finish = (Button) findViewById(R.id.ButtonFinish);
		Back = (Button) findViewById(R.id.ButtonCheck);
		
		Finish.setOnClickListener(this);
		Back.setOnClickListener(this);
		

		DateUpload = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", Locale.TAIWAN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_page, menu);
		return true;
	}
	
	public void onClick(View v)
	{
		if(v == Finish)
		{	
			FuzzyCompute();
			UploadData();
			
			Intent done = new Intent();
			done.setClass(UploadPage.this, OutputPage.class);
			startActivity(done);
			this.finish();
		}
		else if(v == Back)
		{
			check = true;
			Intent back = new Intent();
			back.setClass(UploadPage.this, InputPage3.class);
			startActivity(back);
			this.finish();
		}
	}
	
	private void FuzzyCompute()
	{
		double[] temp_CAD = new double[3];
		double[] temp_HF = new double[3];
		double[] temp_GERD = new double[3];
		
		//Compute CAD
		temp_CAD[0] = CAD.CAD_1(Value[16], Value[17], Value[8]);
		temp_CAD[1] = CAD.CAD_2(Value[6], Value[0], Value[14]);
		temp_CAD[2] = CAD.CAD_3(Value[7], Value[3], Value[4]);
		
		Result_CAD = CAD.CAD_Final(temp_CAD);
		
		if (CheckSmoke == true)
		{
			Result_CAD = Result_CAD * 1.1;
		}
		else ;
		if (CheckDM == true)
		{
			Result_CAD = Result_CAD * 1.1;
		}
		else ;
		if(CheckHL == true)
		{
			Result_CAD = Result_CAD * 1.1;
		}
		else ;
		if(CheckHTN == true)
		{
			Result_CAD = Result_CAD * 1.1;
		}
		else ;
		
		
		//Compute HF
		temp_HF[0] = HF.HF_1(Value[10], Value[9]);
		temp_HF[1] = HF.HF_2(Value[18], Value[7]);
		temp_HF[2] = HF.HF_3(Value[2], Value[19]);
		
		Result_HF = HF.HF_Final(temp_HF);
		
		//Compute GERD
		temp_GERD[0] = GERD.GERD_1(Value[5], Value[15], Value[16]);
		temp_GERD[1] = GERD.GERD_2(Value[6], Value[11], Value[18]);
		temp_GERD[2] = GERD.GERD_3(Value[1], Value[13], Value[12]);
		
		Result_GERD = GERD.GERD_Final(temp_GERD);
	}
	
	private void UploadData()
	{
		String stuffy, upstomach, tear, position, range, shoulder, breathe, presspain, duration, exercise;
		String lyingasthma, heartburn, nausea, syncope, coldsweat, lyingrelieve, rest, ntg, medicine, eatrelieve;
		String source, time, resultCAD, resultHF, resultGERD;
		
		String IP = "http://140.116.39.44/update93.php";
		httppost = new HttpPost(IP);
		
		time = DateUpload.format(new Date());
		
		source = "NoData";
		switch(PatientSource)
		{
			case 'I':
				source = "Inpatient";
				break;
			case 'O':
				source = "Outpatient";
				break;
			default :
				break;
		}
		
		stuffy = String.format("%f", Value[0]);
		upstomach = String.format("%f", Value[1]);
		tear = String.format("%f", Value[2]);
		position = String.format("%f", Value[3]);
		range = String.format("%f", Value[4]);
		shoulder = String.format("%f", Value[5]);
		presspain = String.format("%f", Value[6]);
		duration = String.format("%f", Value[7]);
		exercise = String.format("%f", Value[8]);
		breathe = String.format("%f", Value[9]);
		lyingasthma = String.format("%f", Value[10]);
		heartburn = String.format("%f", Value[11]);
		nausea = String.format("%f", Value[12]);
		syncope = String.format("%f", Value[13]);
		coldsweat = String.format("%f", Value[14]);
		lyingrelieve = String.format("%f", Value[15]);
		rest = String.format("%f", Value[16]);
		ntg = String.format("%f", Value[17]);
		medicine = String.format("%f", Value[18]);
		eatrelieve = String.format("%f", Value[19]);

		resultCAD = String.format("%.3f", Result_CAD);
		resultHF = String.format("%.3f", Result_HF);
		resultGERD = String.format("%.3f", Result_GERD);
		
		UploadList.add(new BasicNameValuePair("_Stuffypain", stuffy));
		UploadList.add(new BasicNameValuePair("_Upper_Stomachache", upstomach));
		UploadList.add(new BasicNameValuePair("_Tearpain", tear));
		UploadList.add(new BasicNameValuePair("_Position", position));
		UploadList.add(new BasicNameValuePair("_Range", range));
		UploadList.add(new BasicNameValuePair("_Shoulder", shoulder));
		UploadList.add(new BasicNameValuePair("_Chin", breathe));
		UploadList.add(new BasicNameValuePair("_Presspain", presspain));
		UploadList.add(new BasicNameValuePair("_Duration", duration));
		UploadList.add(new BasicNameValuePair("_Exercise_to_Intense", exercise));
		UploadList.add(new BasicNameValuePair("_Lying_Asthma", lyingasthma));
		UploadList.add(new BasicNameValuePair("_Heartburn", heartburn));
		UploadList.add(new BasicNameValuePair("_Nausea", nausea));
		UploadList.add(new BasicNameValuePair("__Vomit", syncope));
		UploadList.add(new BasicNameValuePair("_Cold_Sweats", coldsweat));
		UploadList.add(new BasicNameValuePair("_Lying_relieve", lyingrelieve));
		UploadList.add(new BasicNameValuePair("_Rest_to_Relieve", rest));
		UploadList.add(new BasicNameValuePair("_NTG_to_Relieve", ntg));
		UploadList.add(new BasicNameValuePair("_Stomach_Medicine", medicine));
		UploadList.add(new BasicNameValuePair("_Eat_to_relieve", eatrelieve));
				
		UploadList.add(new BasicNameValuePair("_PatiendID", PatientID));
		UploadList.add(new BasicNameValuePair("_Upload_Time", time));
		UploadList.add(new BasicNameValuePair("_Source", source));
		UploadList.add(new BasicNameValuePair("_ResultCAD", resultCAD));
		UploadList.add(new BasicNameValuePair("_ResultHF", resultHF));
		UploadList.add(new BasicNameValuePair("_ResultGERD", resultGERD));
		
		UploadList.add(new BasicNameValuePair("_Smoke", Smoke));
		UploadList.add(new BasicNameValuePair("_DM", DM));
		UploadList.add(new BasicNameValuePair("_HTN", HTN));
		UploadList.add(new BasicNameValuePair("_HL", HL));
		
		Upload = new Thread(SendData);
		Upload.start();
	}
	
	private Runnable SendData = new Runnable()
	{
        public void run()
        {
        	try
    		{
    			HttpClient httpclient = new DefaultHttpClient();
    			httppost.setEntity(new UrlEncodedFormEntity(UploadList,HTTP.UTF_8));
    			HttpResponse response = httpclient.execute(httppost);
    			HttpEntity entity = response.getEntity();
    			IS = entity.getContent();
    		}
    		catch(Exception e)
    		{
    			Log.e("log_tag", "Error in http connection"+e.toString());
    		}
        }
    };
}
