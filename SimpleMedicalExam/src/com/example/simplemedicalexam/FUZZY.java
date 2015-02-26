package com.example.simplemedicalexam;

public class FUZZY
{
	public static double Height(double member[], double x)
	{
	   double H;
	   
	   if(x == -1) return 1; //如果不影響 高度傳1 (因為mandani取較小值)
	   
	   if(x > member[0] && x < member[1]) H = (x-member[0]) / (member[1]-member[0]); //梯形左邊最低&最高點之間 算高度
	   
	   else if(x >= member[1] && x <= member[2]) H = 1; //梯形最高兩點 高度=1
	   
	   else if(x > member[2] && x < member[3]) H = (x-member[3]) / (member[2]-member[3]); //梯形右邊最低&最高點之間 算高度
	   
	   else H = 0;
	   
	   return H;
	}
	   
	public static double MAX(double height[], int size) //抓每個M.F的最小值
	{
	   int i;
	   double max;
		  
	   max = height[0];
		  
	   for(i = 1 ; i < size ; i++)
	   {
		  if(max < height[i]) max = height[i];
	      else ;
	   }
		  
	   return max;
	}
	   
	public static double MIN(double height[], int size)
	{
	   int i;
	   double min;
		  
	   min = height[0];
		  
	   for(i = 1 ; i < size ; i++)
	   {
		  if(min > height[i]) min = height[i];
	      else ;
	   }
		  
	   return min;
	}
	   
	private static double Intersection(double a, double b, double c, double d)//內插法
	{
	   double y, x;
	   
	   x = b*(c-d) - d*(a-b);
	   x = x / (c-d-a+b);
       y = (x-b) / (a-b);
	   
	   return y;
	}
	   
	public static double CENTER_3(double M[][], double H[]) //three output membership functions
	{
	   double IS_h, center;
	   double a1, a2, x1, x2, h, c1, c2; //use for temp
	   double[] area = new double[5];
	   double[] temp = new double[5];
		
	   IS_h = Intersection(M[0][2], M[0][3], M[1][1], M[1][0]);
		
	   //first area (add)
	   x1 = M[0][0];
	   x2 = H[0]*(M[0][2]-M[0][3]) + M[0][3];
	   a1 = (x2-x1) * H[0];
	   a2 = 0.5 * (M[0][3]-x2) * H[0];
	   area[0] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = x2 + 0.3333333333*(M[0][3]-x2);
	   if(area[0] == 0) temp[0] = 0;
	   else temp[0] = (a1*c1 + a2*c2) / area[0];
	
	   //second area (add)
	   x1 = H[1]*(M[1][1]-M[1][0]) + M[1][0];
	   x2 = H[1]*(M[1][2]-M[1][3]) + M[1][3];
	   area[1] = 0.5 * H[1]*((x2-x1)+(M[1][3]-M[1][0]));
	   if(area[1] == 0) temp[1] = 0;
	   else temp[1] = 0.5 * (M[1][0]+M[1][3]);
	   
	   //third area (add)
	   x1 = H[2]*(M[2][1]-M[2][0]) + M[2][0];
	   x2 = M[2][3];
	   a1 = (x2-x1) * H[2];
	   a2 = 0.5 * (x1-M[2][0]) * H[2];
	   area[2] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = M[2][0] + 0.6666666666*(x1-M[2][0]);
	   if(area[2] == 0) temp[2] = 0;
	   else temp[2] = (a1*c1 + a2*c2) / area[2];
	    
	   //first intersection (sub)
	   if(H[0] >= H[1]) h = H[1];
	   else h = H[0];
	   if(h >= IS_h)
	   {
	      area[3] = 0.5 * IS_h * (M[0][3]-M[1][0]) * (-1);
		  temp[3] = 0.5 * (M[0][3]+M[1][0]);
	   }
	   else
	   {
	      x1 = h*(M[1][1]-M[1][0]) + M[1][0];
		  x2 = h*(M[0][2]-M[0][3]) + M[0][3];
		  area[3] = 0.5 * h * ((x2-x1)+(M[0][3]-M[1][0])) * (-1);
		  if(area[3] == 0) temp[3] = 0;
		  else temp[3] = 0.5 * (M[0][3]+M[1][0]);
	   }
	    
	   //second intersection (sub)
	   if(H[1] >= H[2]) h = H[2];
	   else h = H[1];
	   if(h >= IS_h)
	   {
	      area[4] = 0.5 * IS_h * (M[1][3]-M[2][0]) * (-1);
		  temp[4] = 0.5 * (M[1][3]+M[2][0]);
	   }
	   else
	   {
	      x1 = h*(M[2][1]-M[2][0]) + M[2][0];
		  x2 = h*(M[1][2]-M[1][3]) + M[1][3];
		  area[4] = 0.5 * h * ((x2-x1)+(M[1][3]-M[2][0])) * (-1);
		  if(area[4] == 0) temp[4] = 0;
		  else temp[4] = 0.5 * (M[1][3]+M[2][0]);
	   }
	    
	   //calculate the center
	   int i;
	   double up, down;
	   up = 0;
	   down = 0;
	   for(i = 0 ; i < 5 ; i++)
	   {
	      up += area[i]*temp[i];
	      down += area[i];
	   }
	   if(down == 0) center = 5;
	   else center = up / down;
	    
	   return center;
	}
	
	public static double CENTER_4(double M[][], double H[]) //three output membership functions
	{
	   double IS_h, center;
	   double a1, a2, x1, x2, h, c1, c2; //use for temp
	   double[] area = new double[7];
	   double[] temp = new double[7];
		
	   IS_h = Intersection(M[0][2], M[0][3], M[1][1], M[1][0]);
		
	   //first area (add)
	   x1 = M[0][0];
	   x2 = H[0]*(M[0][2]-M[0][3]) + M[0][3];
	   a1 = (x2-x1) * H[0];
	   a2 = 0.5 * (M[0][3]-x2) * H[0];
	   area[0] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = x2 + 0.3333333333*(M[0][3]-x2);
	   if(area[0] == 0) temp[0] = 0;
	   else temp[0] = (a1*c1 + a2*c2) / area[0];
	
	   //second area (add)
	   x1 = H[1]*(M[1][1]-M[1][0]) + M[1][0];
	   x2 = H[1]*(M[1][2]-M[1][3]) + M[1][3];
	   area[1] = 0.5 * H[1]*((x2-x1)+(M[1][3]-M[1][0]));
	   if(area[1] == 0) temp[1] = 0;
	   else temp[1] = 0.5 * (M[1][0]+M[1][3]);
	   
	   //third area (add)
	   x1 = H[2]*(M[2][1]-M[2][0]) + M[2][0];
	   x2 = H[2]*(M[2][2]-M[2][3]) + M[2][3];
	   area[2] = 0.5 * H[2]*((x2-x1)+(M[2][3]-M[2][0]));
	   if(area[2] == 0) temp[2] = 0;
	   else temp[2] = 0.5 * (M[2][0]+M[2][3]);
	   
	   //fourth area (add)
	   x1 = H[3]*(M[3][1]-M[3][0]) + M[3][0];
	   x2 = M[3][3];
	   a1 = (x2-x1) * H[3];
	   a2 = 0.5 * (x1-M[3][0]) * H[3];
	   area[3] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = M[3][0] + 0.6666666666*(x1-M[3][0]);
	   if(area[3] == 0) temp[3] = 0;
	   else temp[3] = (a1*c1 + a2*c2) / area[3];
	    
	   //first intersection (sub)
	   if(H[0] >= H[1]) h = H[1];
	   else h = H[0];
	   if(h >= IS_h)
	   {
	      area[4] = 0.5 * IS_h * (M[0][3]-M[1][0]) * (-1);
		  temp[4] = 0.5 * (M[0][3]+M[1][0]);
	   }
	   else
	   {
	      x1 = h*(M[1][1]-M[1][0]) + M[1][0];
		  x2 = h*(M[0][2]-M[0][3]) + M[0][3];
		  area[4] = 0.5 * h * ((x2-x1)+(M[0][3]-M[1][0])) * (-1);
		  if(area[4] == 0) temp[4] = 0;
		  else temp[4] = 0.5 * (M[0][3]+M[1][0]);
	   }
	    
	   //second intersection (sub)
	   if(H[1] >= H[2]) h = H[2];
	   else h = H[1];
	   if(h >= IS_h)
	   {
	      area[5] = 0.5 * IS_h * (M[1][3]-M[2][0]) * (-1);
		  temp[5] = 0.5 * (M[1][3]+M[2][0]);
	   }
	   else
	   {
	      x1 = h*(M[2][1]-M[2][0]) + M[2][0];
		  x2 = h*(M[1][2]-M[1][3]) + M[1][3];
		  area[5] = 0.5 * h * ((x2-x1)+(M[1][3]-M[2][0])) * (-1);
		  if(area[5] == 0) temp[5] = 0;
		  else temp[5] = 0.5 * (M[1][3]+M[2][0]);
	   }
	   
	   //third intersection (sub)
	   if(H[2] >= H[3]) h = H[3];
	   else h = H[2];
	   if(h >= IS_h)
	   {
	      area[6] = 0.5 * IS_h * (M[2][3]-M[3][0]) * (-1);
		  temp[6] = 0.5 * (M[2][3]+M[3][0]);
	   }
	   else
	   {
	      x1 = h*(M[3][1]-M[3][0]) + M[3][0];
		  x2 = h*(M[2][2]-M[2][3]) + M[2][3];
		  area[6] = 0.5 * h * ((x2-x1)+(M[2][3]-M[3][0])) * (-1);
		  if(area[6] == 0) temp[6] = 0;
		  else temp[6] = 0.5 * (M[2][3]+M[3][0]);
	   }
	    
	   //calculate the center
	   int i;
	   double up, down;
	   up = 0;
	   down = 0;
	   for(i = 0 ; i < 7 ; i++)
	   {
	      up += area[i]*temp[i];
	      down += area[i];
	   }
	   if(down == 0) center = 5;
	   else center = up / down;
	    
	   return center;
	}
	
	public static double CENTER_8(double M[][], double H[]) //three output membership functions
	{
	   double center;
	   double a1, a2, x1, x2, h, c1, c2; //use for temp
	   double[] area = new double[15];
	   double[] temp = new double[15];
	   double[] IS = new double[7];
	   int i;
		
	   //first area (add)
	   x1 = M[0][0];
	   x2 = H[0]*(M[0][2]-M[0][3]) + M[0][3];
	   a1 = (x2-x1) * H[0];
	   a2 = 0.5 * (M[0][3]-x2) * H[0];
	   area[0] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = x2 + 0.3333333333*(M[0][3]-x2);
	   if(area[0] == 0) temp[0] = 0;
	   else temp[0] = (a1*c1 + a2*c2) / area[0];
	
	   //second area (add)
	   x1 = H[1]*(M[1][1]-M[1][0]) + M[1][0];
	   x2 = H[1]*(M[1][2]-M[1][3]) + M[1][3];
	   area[1] = 0.5 * H[1]*((x2-x1)+(M[1][3]-M[1][0]));
	   if(area[1] == 0) temp[1] = 0;
	   else temp[1] = 0.5 * (M[1][0]+M[1][3]);
	   
	   //third area (add)
	   x1 = H[2]*(M[2][1]-M[2][0]) + M[2][0];
	   x2 = H[2]*(M[2][2]-M[2][3]) + M[2][3];
	   area[2] = 0.5 * H[2]*((x2-x1)+(M[2][3]-M[2][0]));
	   if(area[2] == 0) temp[2] = 0;
	   else temp[2] = 0.5 * (M[2][0]+M[2][3]);
	   
	   //fourth area (add)
	   x1 = H[3]*(M[3][1]-M[3][0]) + M[3][0];
	   x2 = H[3]*(M[3][2]-M[3][3]) + M[3][3];
	   area[3] = 0.5 * H[3]*((x2-x1)+(M[3][3]-M[3][0]));
	   if(area[3] == 0) temp[3] = 0;
	   else temp[3] = 0.5 * (M[3][0]+M[3][3]);
	   
	   //fifth area (add)
	   x1 = H[4]*(M[4][1]-M[4][0]) + M[4][0];
	   x2 = H[4]*(M[4][2]-M[4][3]) + M[4][3];
	   area[4] = 0.5 * H[4]*((x2-x1)+(M[4][3]-M[4][0]));
	   if(area[4] == 0) temp[4] = 0;
	   else temp[4] = 0.5 * (M[4][0]+M[4][3]);
	   
	   //sixth area (add)
	   x1 = H[5]*(M[5][1]-M[5][0]) + M[5][0];
	   x2 = H[5]*(M[5][2]-M[5][3]) + M[5][3];
	   area[5] = 0.5 * H[5]*((x2-x1)+(M[5][3]-M[5][0]));
	   if(area[5] == 0) temp[5] = 0;
	   else temp[5] = 0.5 * (M[5][0]+M[5][3]);
	   
	   //seventh area (add)
	   x1 = H[6]*(M[6][1]-M[6][0]) + M[6][0];
	   x2 = H[6]*(M[6][2]-M[6][3]) + M[6][3];
	   area[6] = 0.5 * H[6]*((x2-x1)+(M[6][3]-M[6][0]));
	   if(area[6] == 0) temp[6] = 0;
	   else temp[6] = 0.5 * (M[6][0]+M[6][3]);
	   
	   //eighth area (add)
	   x1 = H[7]*(M[7][1]-M[7][0]) + M[7][0];
	   x2 = M[7][3];
	   a1 = (x2-x1) * H[7];
	   a2 = 0.5 * (x1-M[7][0]) * H[7];
	   area[7] = a1 + a2;
	   c1 = 0.5 * (x1+x2);
	   c2 = M[7][0] + 0.6666666666*(x1-M[7][0]);
	   if(area[7] == 0) temp[7] = 0;
	   else temp[7] = (a1*c1 + a2*c2) / area[7];
	    
	   //calculate the height of inersection
	   for(i = 0 ; i < 7 ; i++)
	   {
	      IS[i] = Intersection(M[i][2], M[i][3], M[i+1][1], M[i+1][0]);
	   }
	    
	   //first intersection (sub)
	   if(H[0] >= H[1]) h = H[1];
	   else h = H[0];
	   if(h >= IS[0])
	   {
	      area[8] = 0.5 * IS[0] * (M[0][3]-M[1][0]) * (-1);
		  temp[8] = 0.5 * (M[0][3]+M[1][0]);
	   }
	   else
	   {
	      x1 = h*(M[1][1]-M[1][0]) + M[1][0];
		  x2 = h*(M[0][2]-M[0][3]) + M[0][3];
		  area[8] = 0.5 * h * ((x2-x1)+(M[0][3]-M[1][0])) * (-1);
		  if(area[8] == 0) temp[8] = 0;
		  else temp[8] = 0.5 * (M[0][3]+M[1][0]);
	   }
	   
	   //second intersection (sub)
	   if(H[1] >= H[2]) h = H[2];
	   else h = H[1];
	   if(h >= IS[1])
	   {
	      area[9] = 0.5 * IS[1] * (M[1][3]-M[2][0]) * (-1);
		  temp[9] = 0.5 * (M[1][3]+M[2][0]);
	   }
	   else
	   {
	      x1 = h*(M[2][1]-M[2][0]) + M[2][0];
		  x2 = h*(M[1][2]-M[1][3]) + M[1][3];
		  area[9] = 0.5 * h * ((x2-x1)+(M[1][3]-M[2][0])) * (-1);
		  if(area[9] == 0) temp[9] = 0;
		  else temp[9] = 0.5 * (M[1][3]+M[2][0]);
	   }
	   
	   //third intersection (sub)
	   if(H[2] >= H[3]) h = H[3];
	   else h = H[2];
	   if(h >= IS[2])
	   {
	      area[10] = 0.5 * IS[2] * (M[2][3]-M[3][0]) * (-1);
		  temp[10] = 0.5 * (M[2][3]+M[3][0]);
	   }
	   else
	   {
	      x1 = h*(M[3][1]-M[3][0]) + M[3][0];
		  x2 = h*(M[2][2]-M[2][3]) + M[2][3];
		  area[10] = 0.5 * h * ((x2-x1)+(M[2][3]-M[3][0])) * (-1);
		  if(area[10] == 0) temp[10] = 0;
		  else temp[10] = 0.5 * (M[2][3]+M[3][0]);
	   }
	   
	   //fourth intersection (sub)
	   if(H[3] >= H[4]) h = H[4];
	   else h = H[4];
	   if(h >= IS[3])
	   {
	      area[11] = 0.5 * IS[3] * (M[3][3]-M[4][0]) * (-1);
		  temp[11] = 0.5 * (M[3][3]+M[4][0]);
	   }
	   else
	   {
	      x1 = h*(M[4][1]-M[4][0]) + M[4][0];
		  x2 = h*(M[3][2]-M[3][3]) + M[3][3];
		  area[11] = 0.5 * h * ((x2-x1)+(M[3][3]-M[4][0])) * (-1);
		  if(area[11] == 0) temp[11] = 0;
		  else temp[11] = 0.5 * (M[3][3]+M[4][0]);
	   }
	   
	   //fifth intersection (sub)
	   if(H[4] >= H[5]) h = H[5];
	   else h = H[5];
	   if(h >= IS[4])
	   {
	      area[12] = 0.5 * IS[4] * (M[4][3]-M[5][0]) * (-1);
		  temp[12] = 0.5 * (M[4][3]+M[5][0]);
	   }
	   else
	   {
	      x1 = h*(M[5][1]-M[5][0]) + M[5][0];
		  x2 = h*(M[4][2]-M[4][3]) + M[4][3];
		  area[12] = 0.5 * h * ((x2-x1)+(M[4][3]-M[5][0])) * (-1);
		  if(area[12] == 0) temp[12] = 0;
		  else temp[12] = 0.5 * (M[4][3]+M[5][0]);
	   }
	   
	   //sixth intersection (sub)
	   if(H[5] >= H[6]) h = H[6];
	   else h = H[6];
	   if(h >= IS[5])
	   {
	      area[13] = 0.5 * IS[5] * (M[5][3]-M[6][0]) * (-1);
		  temp[13] = 0.5 * (M[5][3]+M[6][0]);
	   }
	   else
	   {
	      x1 = h*(M[6][1]-M[6][0]) + M[6][0];
		  x2 = h*(M[5][2]-M[5][3]) + M[5][3];
		  area[13] = 0.5 * h * ((x2-x1)+(M[5][3]-M[6][0])) * (-1);
		  if(area[13] == 0) temp[13] = 0;
		  else temp[13] = 0.5 * (M[5][3]+M[6][0]);
	   }
	   
	   //seventh intersection (sub)
	   if(H[6] >= H[7]) h = H[7];
	   else h = H[7];
	   if(h >= IS[6])
	   {
	      area[14] = 0.5 * IS[6] * (M[6][3]-M[7][0]) * (-1);
		  temp[14] = 0.5 * (M[6][3]+M[7][0]);
	   }
	   else
	   {
	      x1 = h*(M[7][1]-M[7][0]) + M[7][0];
		  x2 = h*(M[6][2]-M[6][3]) + M[6][3];
		  area[14] = 0.5 * h * ((x2-x1)+(M[6][3]-M[7][0])) * (-1);
		  if(area[14] == 0) temp[14] = 0;
		  else temp[14] = 0.5 * (M[6][3]+M[7][0]);
	   }
	    
	   //calculate the center
	   double up, down;
	   up = 0;
	   down = 0;
	   for(i = 0 ; i < 15 ; i++)
	   {
	      up += area[i]*temp[i];
	      down += area[i];
	   }
	   if(down == 0) center = 5;
	   else center = up / down;
	    
	   return center;
	}
}
