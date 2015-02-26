package com.example.simplemedicalexam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class InputPage2 extends InputPage1 implements OnClickListener, OnSeekBarChangeListener, CheckBox.OnCheckedChangeListener{

	protected static int[] ValueGet = new int[20];
	protected static double[] Value = new double[20];
	protected static boolean CheckChest = false, CheckPain = false, CheckSmoke =false , CheckDM =false , CheckHTN =false , CheckHL =false; //摰���oolean 颲典��閮���
	
	private TextView[] ShowNum = new TextView[10];
	private TextView ShowID;
	protected static String Smoke = "N"; //protected:���� �隞亙�隞�.java雿輻 static:霈�澆�隞�銝����身��
	protected static String DM = "N";
	protected static String HTN = "N";
	protected static String HL = "N";
	//private CheckBox[] ShowCheck = new CheckBox[4];
	private CheckBox ShowCheck0;
	private CheckBox ShowCheck1;
	private CheckBox ShowCheck2;
	private CheckBox ShowCheck3;
	//0:smoke 1:DM 2:HTN 3:HL 摰���heckBox��
	private SeekBar[] Bar = new SeekBar[10];
	private CheckBox BoxChest, BoxPain;
	private Button Next, Back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_page2);
		findview();
				
		
		ShowID.setText( PatientID + " 您好，請依自身感受填寫以下問題");
		Next.setOnClickListener(this);
		Back.setOnClickListener(this);
		for(int i = 0 ; i < 10 ; i++)
		{
			Bar[i].setOnSeekBarChangeListener(this);
		}
		
		/*for(int i =0 ; i<4;i++)
		{
			ShowCheck[i].setOnCheckedChangeListener(this); 
		}*/
		

		BoxChest.setOnCheckedChangeListener(this);
		BoxPain.setOnCheckedChangeListener(this);
		ShowCheck0.setOnCheckedChangeListener(this);
		ShowCheck1.setOnCheckedChangeListener(this);
		ShowCheck2.setOnCheckedChangeListener(this);
		ShowCheck3.setOnCheckedChangeListener(this);
		if(check) holdvalue();
		

	}
	


	







	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_page2, menu);
		return true;
	}
	

	private void findview()
	{
		Next = (Button) findViewById(R.id.ButtonNext);
		Back = (Button) findViewById(R.id.ButtonHome);
		ShowID = (TextView) findViewById(R.id.TextShowID);
		
		BoxChest = (CheckBox) findViewById(R.id.BoxChest);
		BoxPain = (CheckBox) findViewById(R.id.BoxPain);
		/*ShowCheck[0] = (CheckBox)findViewById(R.id.chkSmoke);
		ShowCheck[1] = (CheckBox)findViewById(R.id.chkDM);
		ShowCheck[2] = (CheckBox)findViewById(R.id.chkHTN);
		ShowCheck[3] = (CheckBox)findViewById(R.id.chkHL);*/
		
		ShowCheck0 = (CheckBox)findViewById(R.id.chkSmoke);
		ShowCheck1 = (CheckBox)findViewById(R.id.chkDM);
		ShowCheck2 = (CheckBox)findViewById(R.id.chkHTN);
		ShowCheck3 = (CheckBox)findViewById(R.id.chkHL);
		
		ShowNum[0] = (TextView) findViewById(R.id.NumStuffy);
		ShowNum[1] = (TextView) findViewById(R.id.NumUpStomach);
		ShowNum[2] = (TextView) findViewById(R.id.NumTear);
		ShowNum[3] = (TextView) findViewById(R.id.NumPosition);
		ShowNum[4] = (TextView) findViewById(R.id.NumRange);
		ShowNum[5] = (TextView) findViewById(R.id.NumShoulder);
		ShowNum[6] = (TextView) findViewById(R.id.NumPressPain);
		ShowNum[7] = (TextView) findViewById(R.id.NumDuration);
		ShowNum[8] = (TextView) findViewById(R.id.NumExercise);
		ShowNum[9] = (TextView) findViewById(R.id.NumBreathe);
		
		Bar[0] = (SeekBar)findViewById(R.id.BarStuffy);
		Bar[1] = (SeekBar)findViewById(R.id.BarUpStomach);
		Bar[2] = (SeekBar)findViewById(R.id.BarTear);
		Bar[3] = (SeekBar)findViewById(R.id.BarPosition);
		Bar[4] = (SeekBar)findViewById(R.id.BarRange);
		Bar[5] = (SeekBar)findViewById(R.id.BarShoulder);
		Bar[6] = (SeekBar)findViewById(R.id.BarPressPain);
		Bar[7] = (SeekBar)findViewById(R.id.BarDuration);
		Bar[8] = (SeekBar)findViewById(R.id.BarExercise);
		Bar[9] = (SeekBar)findViewById(R.id.BarBreathe);
		
		
	}
	
	private void holdvalue()
	{
		for(int i = 0 ; i < 10 ; i++)
		{
			Bar[i].setProgress(ValueGet[i]);
		}
		ShowID.setText(PatientID + "請依自己的感覺填寫以下問卷");
		
		BoxChest.setChecked(CheckChest); //敺�boolean �boolean�true ������
		BoxPain.setChecked(CheckPain);
		ShowCheck0.setChecked(CheckSmoke);
		ShowCheck1.setChecked(CheckDM);
		ShowCheck2.setChecked(CheckHTN);
		ShowCheck3.setChecked(CheckHL);
	}
	
	@Override
	public void onCheckedChanged(CompoundButton box, boolean checked)
	{
		// TODO Auto-generated method stub
		if(box == BoxChest)
		{
			if(checked)
			{
				CheckChest = true;
				for(int i = 0 ; i < 5 ; i++)
				{
					Bar[i].setProgress(0);
					Bar[i].setEnabled(false);
				}
			}
			else
			{
				CheckChest = false;
				for(int i = 0 ; i < 5 ; i++) Bar[i].setEnabled(true);
			}
		}
		else if(box == BoxPain)
		{
			if(checked)
			{
				CheckPain = true;
				for(int i = 5 ; i < 8 ; i++)
				{
					Bar[i].setProgress(0);
					Bar[i].setEnabled(false);
				}
			}
			else
			{
				CheckPain = false;
				for(int i = 5 ; i < 8 ; i++) Bar[i].setEnabled(true);
			}
		}
		else ;
		
		if( box == ShowCheck0)
		{
			if(checked)
			{
				CheckSmoke = true;
				Smoke =Smoke.replace("N","Y");
				//Toast.makeText(this,Smoke,Toast.LENGTH_SHORT).show();
			}
			else
			{	
				CheckSmoke = false;
				Smoke =Smoke.replace("Y","N");
				//Toast.makeText(this,Smoke,Toast.LENGTH_SHORT).show();
			}
		}
		
		if( box == ShowCheck1)
		{
			if(checked)
			{
				CheckDM = true;
				DM = DM.replace("N","Y");
				//Toast.makeText(this,DM,Toast.LENGTH_SHORT).show();
			}
			else
			{
				CheckDM = false;
				DM =DM.replace("Y","N");
				//Toast.makeText(this,DM,Toast.LENGTH_SHORT).show();
		}
		}
			
		if( box == ShowCheck2)
		{
			if(checked)
			{
				CheckHTN = true;
				HTN = HTN.replace("N","Y");
				//Toast.makeText(this,HTN,Toast.LENGTH_SHORT).show();
			}
			else
			{
				CheckHTN = false;
				HTN = HTN.replace("Y","N");
				//Toast.makeText(this,HTN,Toast.LENGTH_SHORT).show();
			}
			}
		if( box == ShowCheck3)
		{
			if(checked)
			{
				CheckHL = true;
				HL = HL.replace("N","Y");
				//Toast.makeText(this,HL,Toast.LENGTH_SHORT).show();
			}
			else
			{
				CheckHL = false;
				HL = HL.replace("Y","N");
				//Toast.makeText(this,HL,Toast.LENGTH_SHORT).show();
			}
			}
	}
	
	
	private void DisplayToast(String string) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
	{
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i++)
		{
			if(arg0 == Bar[i])
			{
				ValueGet[i] = arg1;
				Value[i] = arg1 * 0.1;
				ShowNum[i].setText(String.format("%.1f",Value[i]));
			}
		}
	}
	
	public void onStartTrackingTouch(SeekBar bar) {}
	
	public void onStopTrackingTouch(SeekBar bar) {}
	
	public void onClick(View v)
	{
		if(v == Next)
		{
			if(CheckChest)
			{
				Value[3] = -1;
				Value[4] = -1;
			}
			if(CheckPain) Value[7] = -1;
			
			Intent next = new Intent();
			next.setClass(InputPage2.this, InputPage3.class);
			startActivity(next);
			this.finish();
		}
		else if(v == Back)
		{
			check = true;
			Intent back = new Intent();
			back.setClass(InputPage2.this, InputPage1.class);
			startActivity(back);
			this.finish();
		}
	}
}