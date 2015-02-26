package com.example.simplemedicalexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class InputPage3 extends InputPage2 implements OnClickListener, OnSeekBarChangeListener {

	protected static boolean CheckNTG = false, CheckMedicine = false, CheckEat = false;
	private TextView[] ShowNum = new TextView[10];
	private SeekBar[] Bar = new SeekBar[10];
	private Button Done, Back;
	private CheckBox BoxNTG, BoxMedicine, BoxEat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_page3);
		
		findview();
		
		Done.setOnClickListener(this);
		Back.setOnClickListener(this);
		
		BoxNTG.setOnCheckedChangeListener(this);
		BoxMedicine.setOnCheckedChangeListener(this);
		BoxEat.setOnCheckedChangeListener(this);
		
		for(int i = 0 ; i < 10 ; i++)
		{
			Bar[i].setOnSeekBarChangeListener(this);
		}
		
		if(check) holdvalue();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_page3, menu);
		return true;
	}
	
	private void findview()
	{
		Done = (Button) findViewById(R.id.ButtonDone);
		Back = (Button) findViewById(R.id.ButtonBack);
		
		BoxNTG = (CheckBox) findViewById(R.id.BoxNTG);
		BoxMedicine = (CheckBox) findViewById(R.id.BoxMedicine);
		BoxEat = (CheckBox) findViewById(R.id.BoxEat);
		
		ShowNum[0] = (TextView) findViewById(R.id.NumLyingAsthma);
		ShowNum[1] = (TextView) findViewById(R.id.NumHeartBurn);
		ShowNum[2] = (TextView) findViewById(R.id.NumNausea);
		ShowNum[3] = (TextView) findViewById(R.id.NumSyncope);
		ShowNum[4] = (TextView) findViewById(R.id.NumColdSweat);
		ShowNum[5] = (TextView) findViewById(R.id.NumlyingRelieve);
		ShowNum[6] = (TextView) findViewById(R.id.NumRest);
		ShowNum[7] = (TextView) findViewById(R.id.NumNTG);
		ShowNum[8] = (TextView) findViewById(R.id.NumMedicine);
		ShowNum[9] = (TextView) findViewById(R.id.NumEatRelieve);
		
		
		Bar[0] = (SeekBar)findViewById(R.id.BarLyingAsthma);
		Bar[1] = (SeekBar)findViewById(R.id.BarHeartBurn);
		Bar[2] = (SeekBar)findViewById(R.id.BarNausea);
		Bar[3] = (SeekBar)findViewById(R.id.BarSyncope);
		Bar[4] = (SeekBar)findViewById(R.id.BarColdSweat);
		Bar[5] = (SeekBar)findViewById(R.id.BarlyingRelieve);
		Bar[6] = (SeekBar)findViewById(R.id.BarRest);
		Bar[7] = (SeekBar)findViewById(R.id.BarNTG);
		Bar[8] = (SeekBar)findViewById(R.id.BarMedicine);
		Bar[9] = (SeekBar)findViewById(R.id.BarEatRelieve);
	}
	
	private void holdvalue()
	{
		for(int i = 0 ; i < 10 ; i++)
		{
			Bar[i].setProgress(ValueGet[i+10]);
		}
	}
	
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
	{
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i++)
		{
			if(arg0 == Bar[i])
			{
				ValueGet[i+10] = arg1;
				Value[i+10] = arg1 * 0.1;
				ShowNum[i].setText(String.format("%.1f",Value[i+10]));
			}
		}
	}
	
	public void onCheckedChanged(CompoundButton box, boolean checked)
	{
		// TODO Auto-generated method stub
		if(box == BoxNTG)
		{
			if(checked)
			{
				CheckNTG = true;
				Bar[7].setProgress(0);
				Bar[7].setEnabled(false);
			}
			else
			{
				CheckNTG = false;
				Bar[7].setEnabled(true);
			}
		}
		else if(box == BoxMedicine)
		{
			if(checked)
			{
				CheckMedicine = true;
				Bar[8].setProgress(0);
				Bar[8].setEnabled(false);
			}
			else
			{
				CheckMedicine = false;
				Bar[8].setEnabled(true);
			}
		}
		else if(box == BoxEat)
		{
			if(checked)
			{
				CheckEat = true;
				Bar[9].setProgress(0);
				Bar[9].setEnabled(false);
			}
			else
			{
				CheckEat = false;
				Bar[9].setEnabled(true);
			}
		}
		else ;
	}
	
	public void onClick(View v)
	{
		if(v == Done)
		{	
			if(CheckNTG) Value[17] = -1;
			if(CheckMedicine) Value[18] = -1;
			if(CheckEat) Value[19] = -1;
			
			Intent done = new Intent();
			done.setClass(InputPage3.this, UploadPage.class);
			startActivity(done);
			this.finish();
		}
		else if(v == Back)
		{
			check = true;
			Intent back = new Intent();
			back.setClass(InputPage3.this, InputPage2.class);
			startActivity(back);
			this.finish();
		}
	}
}
