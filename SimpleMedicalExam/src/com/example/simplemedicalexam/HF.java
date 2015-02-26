package com.example.simplemedicalexam;

public class HF
{
	//First Level of HF - 1
	public static double HF_1(double lyingasthma, double exercise)
	{
		double MF_LyingAsthma[][] = {{0.0, 0.0, 1.5, 2.5},
								     {1.5, 2.5, 4.5, 5.5},
								     {4.5, 5.5, 7.5, 8.5},
								     {7.5, 8.5, 10.0, 10.0}};
		double MF_Exercise[][] = {{0.0, 0.0, 1.5, 2.5},
                       		      {1.5, 2.5, 4.5, 5.5},
                       			  {4.5, 5.5, 7.5, 8.5},
                       			  {7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
                				{1.5, 2.5, 4.5, 5.5},
                				{4.5, 5.5, 7.5, 8.5},
                				{7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][16];
		double[] count = new double[2];
		double[] o_height = new double[4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				level = i*1.5 + j*1.67;
				count[0] = FUZZY.Height(MF_LyingAsthma[i], lyingasthma);
				count[1] = FUZZY.Height(MF_Exercise[j], exercise);
				
				if(level <= 2.3775)
				 {
					temp[0][A] = FUZZY.MIN(count, 2);
					A++;
				 }
				 else if(level <= 4.775)
				 {
					 temp[1][B] = FUZZY.MIN(count, 2);
					 B++;
				 } 
				 else if(level <= 7.1325)
				 {
					 temp[2][C] = FUZZY.MIN(count, 2);
					 C++;
				 }
				 else if(level <= 9.51)
				 {
					 temp[3][D] = FUZZY.MIN(count, 2);
					 D++;
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
	
	//First Level of HF - 2
	public static double HF_2(double medicine, double press)
	{
		double MF_Medicine[][] = {{0.0, 0.0, 1.5, 2.5},
                				  {1.5, 2.5, 4.5, 5.5},
                				  {4.5, 5.5, 7.5, 8.5},
                				  {7.5, 8.5, 10.0, 10.0}};
		double MF_Press[][] = {{0.0, 0.0, 1.5, 2.5},
                			   {1.5, 2.5, 4.5, 5.5},
                			   {4.5, 5.5, 7.5, 8.5},
                			   {7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
								{1.5, 2.5, 4.5, 5.5},
								{4.5, 5.5, 7.5, 8.5},
								{7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][16];
		double[] count = new double[2];
		double[] o_height = new double[4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				level = i*(-1.75) + j*(-1.92);
				count[0] = FUZZY.Height(MF_Medicine[i], medicine);
				count[1] = FUZZY.Height(MF_Press[j], press);
				
				if(level <= -2.7525)
				{
					temp[0][A] = FUZZY.MIN(count, 2);
					A++;
				}
				else if(level <= -5.505)
				{
					temp[1][B] = FUZZY.MIN(count, 2);
					B++;
				}
				else if(level <= -8.2575)
				{
					temp[2][C] = FUZZY.MIN(count, 2);
					C++;
				}
				else if(level <= -11.01)
				{
					temp[3][D] = FUZZY.MIN(count, 2);
					D++;
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
	
	//First Level of HF - 3
	public static double HF_3(double tear, double eatrelieve)
	{
		double MF_Tear[][] = {{0.0, 0.0, 1.5, 2.5},
                			  {1.5, 2.5, 4.5, 5.5},
                			  {4.5, 5.5, 7.5, 8.5},
                			  {7.5, 8.5, 10.0, 10.0}};
		double MF_EatRelieve[][] = {{0.0, 0.0, 1.5, 2.5},
                					{1.5, 2.5, 4.5, 5.5},
                					{4.5, 5.5, 7.5, 8.5},
                					{7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
								{1.5, 2.5, 4.5, 5.5},
								{4.5, 5.5, 7.5, 8.5},
								{7.5, 8.5, 10.0, 10.0}};
		
		double result = -1, level = 0;
		int A = 0, B = 0, C = 0, D = 0;
		double[][] temp = new double[4][16];
		double[] count = new double[2];
		double[] o_height = new double[4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				level =  i*(-2) + j*(-2);
				count[0] = FUZZY.Height(MF_Tear[i], tear);
				count[1] = FUZZY.Height(MF_EatRelieve[j], eatrelieve);
				
				if(level <= -3)
				{
					temp[0][A] = FUZZY.MIN(count, 2);
					A++;
				}
				else if(level <= -6)
				{
					temp[1][B] = FUZZY.MIN(count, 2);
					B++;
				}
				else if(level <= -9)
				{
					temp[2][C] = FUZZY.MIN(count, 2);
					C++;
				}
				else if(level <= -12)
				{
					temp[3][D] = FUZZY.MIN(count, 2);
					D++;
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
	
	//Second Level of HF
	public static double HF_Final(double height[])
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
					level = x*1.585 + y*(-1.835) + z*(-2);
					count[0] = FUZZY.Height(input_1[x], height[0]);
					count[1] = FUZZY.Height(input_2[y], height[1]);
					count[2] = FUZZY.Height(input_3[z], height[2]);
					
					if(level <= -0.051875)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= -0.10375)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= -0.155625)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= -0.2075)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
					else if(level <= -0.259375)
					{
						temp[4][E] = FUZZY.MIN(count, 3);
						E++;
					}
					else if(level <= -0.31125)
					{
						temp[5][F] = FUZZY.MIN(count, 3);
						F++;
					}
					else if(level <= -0.363125)
					{
						temp[6][G] = FUZZY.MIN(count, 3);
						G++;
					}
					else if(level <= -0.415)
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
