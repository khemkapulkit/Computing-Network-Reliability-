/*
 * ATN PROJECT 3
 * Title - Computing Network Reliability using Exact Calculation by Exhaustive Enumeration
 * Author - Pulkit Khemka
 * */


import java.util.*;


public class Project3 {
	
	
	//This function returns reliability fot a given value of p and k 
	public static double findReliability(double p, int k)
	{
		//list of k distinct random numbers between 0 and 1024 are generated
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int j = 0; j < k; j++)
		{
		   Random rand = new Random();
		   int r = rand.nextInt(1024);
		   while(list.contains(r)){
			   r = rand.nextInt(1024);
		   }
		   list.add(r);
		}
		
		
		double R =0;
		for(int i=0;i<1024;i++)
		{
			//All possible permutations are generated using all 10 digit binary numbers
			Graph Graph= new Graph();
			Graph.p = p;
			String s = String.format("%10s", Integer.toBinaryString(i)).replace(" ", "0");
			for(int j=0;j<10;j++){
				if (s.charAt(j)=='1'){
					Graph.G[Graph.map[j][0]][Graph.map[j][1]]= 0;
					Graph.G[Graph.map[j][1]][Graph.map[j][0]]= 0;
				}
			}
			
			//if i is in the list then reverse state else do not reverse state
			if(list.contains(i)){
				if(!Graph.isconnected()){
					//compute reliability by adding reliability of different states
					R = R +Graph.reliabilty();
				}
			}
			else{				
				if(Graph.isconnected()){
					R = R +Graph.reliabilty();
				}
			}
		}
		
		return R;
	}
	
	
	public static void main(String[] args) {
		int k=0; //k is the number of states reversed
		double p; //p is the reliability of each link
		
		//finding variation of probability with p with k =0
		for ( p = 0;p<1.04;p = p+0.04)
		{
			System.out.println("Reliability for p = "+String.format("%.2g", p)+" is "+findReliability(p, k));
		}
		System.out.println();
		
		//finding variation of probability with k with p =0.9
		p =0.9;
		for(k=0; k<=30;k++){
			double rel = 0;
			for(int j=0;j<100;j++){
				rel = rel +findReliability(p, k);
			}
			rel = rel/100;
			System.out.println("Reliability for k = "+k+" is "+rel);
			
		}
		
	}
}
