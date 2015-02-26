package com.example.simplemedicalexam;

public class GERD
{
	//First Level of GERD - 1
	public static double GERD_1(double shoulder, double lyingrelieve, double rest)
	{
		double MF_Shoulder[][] = {{0.0, 0.0, 1.5, 2.5},
				                  {1.5, 2.5, 4.5, 5.5},
				                  {4.5, 5.5, 7.5, 8.5},
				                  {7.5, 8.5, 10.0, 10.0}};
		double MF_LyingRelieve[][] = {{0.0, 0.0, 1.5, 2.5},
				                      {1.5, 2.5, 4.5, 5.5},
				                      {4.5, 5.5, 7.5, 8.5},
				                      {7.5, 8.5, 10.0, 10.0}};
		double MF_Rest[][] = {{0.0, 0.0, 1.5, 2.5},
				              {1.5, 2.5, 4.5, 5.5},
				              {4.5, 5.5, 7.5, 8.5},
				              {7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
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
					level = i*(-1.92) + j*(-1.92) + k*(-1.83);
					count[0] = FUZZY.Height(MF_Shoulder[i], shoulder);
					count[1] = FUZZY.Height(MF_LyingRelieve[j], lyingrelieve);
					count[2] = FUZZY.Height(MF_Rest[k], rest);
					
					if(level <= -12.7575)
				    {
					    temp[0][A] = FUZZY.MIN(count, 3);
						A++;
				    }
				    else if(level <= -8.505)
				    {
				    	temp[1][B] = FUZZY.MIN(count, 3);
				    	B++;
				    }
				    else if(level <= -4.2525)
				    {
				    	temp[2][C] = FUZZY.MIN(count, 3);
				    	C++;
				    }
				    else if(level <= 0)
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
	
	//First Level of GERD - 2
	public static double GERD_2(double chin, double heartburn, double medicine)
	{
		double MF_Chin[][] = {{0.0, 0.0, 1.5, 2.5},
				              {1.5, 2.5, 4.5, 5.5},
				              {4.5, 5.5, 7.5, 8.5},
				              {7.5, 8.5, 10.0, 10.0}};
		double MF_HeartBurn[][] = {{0.0, 0.0, 1.5, 2.5},
				                   {1.5, 2.5, 4.5, 5.5},
				                   {4.5, 5.5, 7.5, 8.5},
				                   {7.5, 8.5, 10.0, 10.0}};
		double MF_Medicine[][] = {{0.0, 0.0, 1.5, 2.5},
				                  {1.5, 2.5, 4.5, 5.5},
				                  {4.5, 5.5, 7.5, 8.5},
				                  {7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
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
					level = i*(-1.67) + j*1.50 + k*1.42;
					count[0] = FUZZY.Height(MF_Chin[i], chin);
					count[1] = FUZZY.Height(MF_HeartBurn[j], heartburn);
					count[2] = FUZZY.Height(MF_Medicine[k], medicine);
					
					if(level <= -1.5675)
				    {
					    temp[0][A] = FUZZY.MIN(count, 3);
						A++;
				    }
				    else if(level <= 1.875)
				    {
				    	temp[1][B] = FUZZY.MIN(count, 3);
				    	B++;
				    }
				    else if(level <= 5.3175)
				    {
				    	temp[2][C] = FUZZY.MIN(count, 3);
				    	C++;
				    }
				    else if(level <= 8.76)//最大權重值
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
	
	//First Level of GERD - 3
	public static double GERD_3(double upstomach, double vomit, double nausea)
	{
		double MF_UpStomach[][] = {{0.0, 0.0, 1.5, 2.5},
				                   {1.5, 2.5, 4.5, 5.5},
				                   {4.5, 5.5, 7.5, 8.5},
				                   {7.5, 8.5, 10.0, 10.0}};
		double MF_Vomit[][] = {{0.0, 0.0, 1.5, 2.5},
				               {1.5, 2.5, 4.5, 5.5},
				               {4.5, 5.5, 7.5, 8.5},
				               {7.5, 8.5, 10.0, 10.0}};
		double MF_Nausea[][] = {{0.0, 0.0, 1.5, 2.5},
				                {1.5, 2.5, 4.5, 5.5},
				                {4.5, 5.5, 7.5, 8.5},
				                {7.5, 8.5, 10.0, 10.0}};
		double MF_Output[][] = {{0.0, 0.0, 1.5, 2.5},
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
					level = i*1.08 + j*1.08 + k;
					count[0] = FUZZY.Height(MF_UpStomach[i], upstomach);
					count[1] = FUZZY.Height(MF_Vomit[j], vomit);
					count[2] = FUZZY.Height(MF_Nausea[k], nausea);
					
					if(level <= 2.37)
				    {
					    temp[0][A] = FUZZY.MIN(count, 3);
						A++;
				    }
				    else if(level <= 4.74)
				    {
				    	temp[1][B] = FUZZY.MIN(count, 3);
				    	B++;
				    }
				    else if(level <= 7.11)
				    {
				    	temp[2][C] = FUZZY.MIN(count, 3);
				    	C++;
				    }
				    else if(level <= 9.48)
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
	
	//Second Level of GERD
	public static double GERD_Final(double height[])
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
					level = x*1.89 + y*1.52 + z*1.05;
					count[0] = FUZZY.Height(input_1[x], height[0]);
					count[1] = FUZZY.Height(input_2[y], height[1]);
					count[2] = FUZZY.Height(input_3[z], height[2]);
					
					if(level <= 1.6725)
					{
						temp[0][A] = FUZZY.MIN(count, 3);
						A++;
					}
					else if(level <= 3.345)
					{
						temp[1][B] = FUZZY.MIN(count, 3);
						B++;
					}
					else if(level <= 5.0175)
					{
						temp[2][C] = FUZZY.MIN(count, 3);
						C++;
					}
					else if(level <= 6.69)
					{
						temp[3][D] = FUZZY.MIN(count, 3);
						D++;
					}
					else if(level <= 8.3625)
					{
						temp[4][E] = FUZZY.MIN(count, 3);
						E++;
					}
					else if(level <= 10.035)
					{
						temp[5][F] = FUZZY.MIN(count, 3);
						F++;
					}
					else if(level <= 11.7075)
					{
						temp[6][G] = FUZZY.MIN(count, 3);
						G++;
					}
					else if(level <= 13.38)
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
