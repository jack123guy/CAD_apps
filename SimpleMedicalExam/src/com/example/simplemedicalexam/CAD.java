package com.example.simplemedicalexam;

public class CAD
{
	//First Level of CAD - 1
	public static double CAD_1(double rest, double ntg, double exercise)
	{
		double[][] MF_Rest = {{0.0, 0.0, 1.5, 2.5},
				              {1.5, 2.5, 4.5, 5.5},
				              {4.5, 5.5, 7.5, 8.5},
				              {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_NTG = {{0.0, 0.0, 1.5, 2.5},
				             {1.5, 2.5, 4.5, 5.5},
				             {4.5, 5.5, 7.5, 8.5},
				             {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Exercise = {{0.0, 0.0, 1.5, 2.5},
				                  {1.5, 2.5, 4.5, 5.5},
				                  {4.5, 5.5, 7.5, 8.5},
				                  {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Output = {{0.0, 0.0, 1.5, 2.5},
				                {1.5, 2.5, 4.5, 5.5},
				                {4.5, 5.5, 7.5, 8.5},
				                {7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][60];
		double[] count = new double[3];
		double[] o_height = new double[4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				for(int k = 0 ; k < 4 ; k++)
				{
					level = i*1.83 + j*1.67 + k*1.83;//Max:(1.83+1.83+1.67)*3 =15.99 min = 0 
					//取得病人填寫的值
					count[0] = FUZZY.Height(MF_Rest[i], rest);
					count[1] = FUZZY.Height(MF_NTG[j], ntg);
					count[2] = FUZZY.Height(MF_Exercise[k], exercise);
					
					if(level <= 3.9975)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= 7.995)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= 11.9925)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= 15.99)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
				}
			}
		}
		//Level 1 的 output高度
		o_height[0] = FUZZY.MAX(temp[0], A);
		o_height[1] = FUZZY.MAX(temp[1], B);
		o_height[2] = FUZZY.MAX(temp[2], C);
		o_height[3] = FUZZY.MAX(temp[3], D);
		
		result = FUZZY.CENTER_4(MF_Output, o_height);
		
		return result;
	}
	
	//First Level of CAD - 2
	public static double CAD_2(double press, double stuffy, double coldsweat)
	{
		double[][] MF_Press = {{0.0 ,0.0, 1.5, 2.5},
				               {1.5, 2.5, 4.5, 5.5},
				               {4.5, 5.5, 7.5, 8.5},
				               {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Stuffy = {{0.0, 0.0, 1.5, 2.5},
				                {1.5, 2.5, 4.5, 5.5},
				                {4.5, 5.5, 7.5, 8.5},
				                {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_ColdSweat = {{0.0, 0.0, 1.5, 2.5},
				                   {1.5, 2.5, 4.5, 5.5},
				                   {4.5, 5.5, 7.5, 8.5},
				                   {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Output = {{0.0, 0.0, 1.5, 2.5},
				                {1.5, 2.5, 4.5, 5.5},
				                {4.5, 5.5, 7.5, 8.5},
				                {7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][60];
		double[] count = new double[3];
		double[] o_height = new double[4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				for(int k = 0 ; k < 4 ; k++)
				{
					level = i*(-1.58) + j*1.5 + k*1.33;//Max = (1.5+1.33)*3 = 8.49  min = (-1.58*3) = -4.74
					count[0] = FUZZY.Height(MF_Press[i], press);
					count[1] = FUZZY.Height(MF_Stuffy[j], stuffy);
					count[2] = FUZZY.Height(MF_ColdSweat[k], coldsweat);
					
					if(level <= -1.4325)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= 1.875)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= 5.1825)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= 8.49)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
				}
			}
		}
		
		o_height[0] = FUZZY.MAX(temp[0], A);
		o_height[1] = FUZZY.MAX(temp[1], B);
		o_height[2] = FUZZY.MAX(temp[2], C);
		o_height[3] = FUZZY.MAX(temp[3], D);
		
		result = FUZZY.CENTER_4(MF_Output, o_height);
		
		return result;
	}
		
	//First Level of CAD - 3
	public static double CAD_3(double duration, double position, double range)
	{
		double[][] MF_Duration = {{0.0, 0.0, 1.5, 2.5},
								  {1.5, 2.5, 4.5, 5.5},
								  {4.5, 5.5, 7.5, 8.5},
								  {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Position = {{0.0, 0.0, 1.5, 2.5},
								  {1.5, 2.5, 4.5, 5.5},
								  {4.5, 5.5, 7.5, 8.5},
								  {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Range = {{0.0, 0.0, 1.5, 2.5},
							   {1.5, 2.5, 4.5, 5.5},
							   {4.5, 5.5, 7.5, 8.5},
							   {7.5, 8.5, 10.0, 10.0}};
		double[][] MF_Output = {{0.0, 0.0, 1.5, 2.5},
                				{1.5, 2.5, 4.5, 5.5},
                				{4.5, 5.5, 7.5, 8.5},
                				{7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][60];
		double[] count = new double[3];
		double[] o_height = new double[4];
		
		//Change the value of duration
		double t = 0;
		if(duration <= 5) t = duration * 2;
		else t = 20 - duration*2;
		
		//Change the value of position
		double p;
		p = 10 - position;
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				for(int k = 0 ; k < 4 ; k++)
				{
					level = i*1.22 - j + k*0.375;
					count[0] = FUZZY.Height(MF_Duration[i], t);
					count[1] = FUZZY.Height(MF_Position[j], p);
					count[2] = FUZZY.Height(MF_Range[k], range);
					
					if(level <= -1.05375)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= 0.8925)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= 2.83875)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= 4.785)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
				}
			}
		}
		
		o_height[0] = FUZZY.MAX(temp[0], A);
		o_height[1] = FUZZY.MAX(temp[1], B);
		o_height[2] = FUZZY.MAX(temp[2], C);
		o_height[3] = FUZZY.MAX(temp[3], D);
		
		result = FUZZY.CENTER_4(MF_Output, o_height);
		
		return result;
	}
	
	//Second Level of CAD
	public static double CAD_Final(double height[])
	{
		double input_1[][] = {{0.0, 0.0, 1.5, 2.5},
	              			  {1.5, 2.5, 4.5, 5.5},
	              			  {4.5, 5.5, 7.5, 8.5},
	              			  {7.5, 8.5, 10.0, 10.0}};
		double input_2[][] = input_1;
		double input_3[][] = input_1;
		double output[][] = {{0.0, 0.0, 1.429, 12.86},
							 {1.429, 12.86, 15.71, 27.14},
							 {15.71, 27.14, 30.0, 41.43},
							 {30.0, 41.43, 44.29, 55.71},
							 {44.29, 55.71, 58.57, 70.0},
							 {58.57, 70.0, 72.86, 84.29},
							 {72.86, 84.29, 87.14, 98.57},
							 {87.14, 98.57, 100.0, 100.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0, E = 0, F = 0, G = 0, H = 0;
		double temp[][] = new double[8][70];
		double count[] = new double[3];
		double o_height[] = new double[8];
		
		for(int x = 0 ; x < 4 ; x++)
		{
			for(int y = 0 ; y < 4 ; y++)
			{
				for(int z = 0 ; z < 4 ; z++)
				{
					level = x*1.72 + y*1.47 + z*0.865;
					count[0] = FUZZY.Height(input_1[x], height[0]);
					count[1] = FUZZY.Height(input_2[y], height[1]);
					count[2] = FUZZY.Height(input_3[z], height[2]);
					
					if(level <= 1.520625)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= 3.04125)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= 4.561875)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= 6.0825)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
					else if(level <= 7.603125)
					{
						temp[4][E] = FUZZY.MIN(count, 3);
						E++;
					}
					else if(level <= 9.12375)
					{
						temp[5][F] = FUZZY.MIN(count, 3);
						F++;
					}
					else if(level <= 10.644375)
					{
						temp[6][G] = FUZZY.MIN(count, 3);
						G++;
					}
					else if(level <= 12.165)
					{
						temp[7][H] = FUZZY.MIN(count, 3);
						H++;
					}
				}
			}
		}
		
		o_height[0] = FUZZY.MAX(temp[0], A);
		o_height[1] = FUZZY.MAX(temp[1], B);
		o_height[2] = FUZZY.MAX(temp[2], C);
		o_height[3] = FUZZY.MAX(temp[3], D);
		o_height[4] = FUZZY.MAX(temp[4], E);
		o_height[5] = FUZZY.MAX(temp[5], F);
		o_height[6] = FUZZY.MAX(temp[6], G);
		o_height[7] = FUZZY.MAX(temp[7], H);
		
		result = FUZZY.CENTER_8(output, o_height);
		
		return result;
	}
}
