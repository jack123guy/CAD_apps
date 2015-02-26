package com.example.simplemedicalexam;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InputPage1 extends Activity implements OnClickListener{

	protected static boolean check = false; // Check whether user choose back(true) or retry(false)
	
	private EditText GetPatientID;
	private Button Start;
	private RadioGroup PatientType;
	
	protected static String PatientID = "";
	protected static char PatientSource = 'X';
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_page1);
		
		GetPatientID = (EditText) findViewById(R.id.EDtextID);
		
		Start = (Button) findViewById(R.id.ButtonStart);
		Start.setOnClickListener(this);
		
		PatientType = (RadioGroup) findViewById(R.id.RadioPatientType);
		
		if(check)
		{
			GetPatientID.setText(PatientID);
			
			switch(PatientSource)
			{
				case 'I':
					PatientType.check(R.id.RadioInpatient);
					break;
				case 'O':
					PatientType.check(R.id.RadioOutpatient);
					break;
				default :
					break;
			}
		}
		else PatientType.clearCheck();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_page1, menu);
		return true;
	}
	
	public void onClick(View v)
	{
		if(v == Start)
		{
			PatientID = GetPatientID.getText().toString();
			
			if(PatientID.length() != 8)
			{
				Toast.makeText(this,"病歷號碼錯誤！",Toast.LENGTH_SHORT).show();
				Toast.makeText(this,"請再次輸入正確的病歷號碼！",Toast.LENGTH_SHORT).show();
			}
			else if(PatientSource == 'X')
			{
				Toast.makeText(this,"請選擇患者類型！",Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent start = new Intent();
				start.setClass(InputPage1.this, InputPage2.class);
				startActivity(start);
				this.finish();
			}
		}
	}
	
	public void RadioChange(View v)
	{
		int patienttype = v.getId();
		
		if(((RadioButton) v).isChecked())
		{
			switch(patienttype)
			{
				case R.id.RadioInpatient:
					PatientSource = 'I';
					break;
				case R.id.RadioOutpatient:
					PatientSource = 'O';
					break;
				default:
					break;
			}
		}
	}
}