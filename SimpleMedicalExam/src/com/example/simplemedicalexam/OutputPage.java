package com.example.simplemedicalexam;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class OutputPage extends UploadPage implements OnClickListener{

	private Button Retry, Exit, Show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_output_page);
		
		Retry = (Button) findViewById(R.id.ButtonRetry);
		Exit = (Button) findViewById(R.id.ButtonExit);
		Show = (Button) findViewById(R.id.ButtonShow);
		
		Retry.setOnClickListener(this);
		Exit.setOnClickListener(this);
		Show.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.output_page, menu);
		return true;
	}
	
	public void onClick(View v)
	{
		if(v == Retry)
		{
			check = false;
			for(int i = 0 ; i < 20 ; i++)
			{
				ValueGet[i] = 0;
				Value[i] = 0.0;
			}
			PatientID = "";
			PatientSource = 'X';
			CheckChest = false;
			CheckPain = false;
			CheckNTG = false;
			CheckMedicine = false;
			CheckEat = false;
			CheckSmoke =false ;
			CheckDM =false ;
			CheckHTN =false ;
			CheckHL =false;
			
			Result_CAD = -1;
			Result_HF = -1;
			Result_GERD = -1;
			
			Intent retry = new Intent();
			retry.setClass(OutputPage.this, InputPage1.class);
			startActivity(retry);
			this.finish();
		}
		else if(v == Exit)
		{
			this.finish();
		    super.onDestroy();
		}
		else if(v == Show)
		{
			Builder ShowDialog = new AlertDialog.Builder(this);
			DialogInterface.OnClickListener OK = new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which) {}
			};
			ShowDialog.setTitle("檢測結果顯示");
			ShowDialog.setMessage("冠心症結果："+ String.format("%.3f", Result_CAD) +" %\n\n" + "胃食道逆流結果："+ String.format("%.3f", Result_GERD) +" %\n\n" + "心衰竭結果："+ String.format("%.3f", Result_HF) +" %");
			ShowDialog.setPositiveButton("確認", OK);
			ShowDialog.show();
		}
	}
}
