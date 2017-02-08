
public class Graph {
	
	public int G[][]; //adjacency matrix
	public int map[][];// maps i and j values of adjacency matrix to the link numbers
	public double p; //link reliability
	Graph(){
		p =0;
		G= new int[5][5];
		map = new int[10][2];
		int key = 0;
		
		//adjacency matrix and map is generated for a 5 node network with all nodes connected
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(i!=j)
				{
					if(i>j)
					{
						map[key][0]= i;
						map[key][1]= j;
						key++;
					}
					G[i][j]=1;
				}
				else
					G[i][j]=0;
			}
		}
	}
	
	//implements depth first search starting from node i and keeping track of the visited nodes
	public void dfs(int i, boolean visited[])
	{
		visited[i] = true;
		//System.out.println("Visited "+i);
		for(int j=0;j<5;j++){
			if(G[i][j]==1){
				if(!visited[j]){
					dfs(j,visited);
				}
			}
		}
		
	}
	
	//to check if the graph is connected using dfs
	public boolean isconnected(){
		boolean b = true;
		boolean visited[] = new boolean[5];
		for(int i=0;i<5;i++){
			visited[i] = false;
		}
		dfs(0, visited);		
		for(int i=0;i<5;i++){
			if(!visited[i]){b = false;}
		}
		return b;
	}
	
	//to print graph
	public void print(){
		for(int i=0;i<5;i++){
			for(int j=0; j<5; j++){
				System.out.print(G[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//returns reliability of given graph
	public double reliabilty() {
		double rel = 1;
		for(int i=0;i<5;i++){
			for(int j=0; j<5; j++){
				if(i>j){
					if (G[i][j]==0)
						rel = rel *(1-p);
					else
						rel = rel * p;
				}
			}
		}
		return rel;
	}
	
	
	
}
