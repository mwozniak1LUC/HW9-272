import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimalSpanningTreeFinder {

    static PriorityQueue<WeightedEdge> queue;
    static ArrayList<WeightedEdge> edgeSet;
    static int weight;
    static int numVertex;
    static int numEdges;
    int[] findSet;
    int[] height;

    public MinimalSpanningTreeFinder() {
        readInEdges();
        numVertex = 50515;
        numEdges = 819306;
        queue = new PriorityQueue<>(edgeSet);
        findSet = new int[numVertex];
        height = new int[numVertex];
        for(int i = 0; i < numVertex; i++){
            findSet[i] = i;
            height[i] = 1;
        }
    }

    public static Graph readInEdges() {
        int maxV=0;
        edgeSet = new ArrayList<>();

        try{
            Scanner v =new Scanner(new File("artist_edges.txt"));
            Scanner weights = new Scanner(new File("Weights.txt"));
            String v1v2;
            String weight;
            while (v.hasNextLine()) {
                v1v2 = v.nextLine();
                weight = weights.nextLine();
                if (v1v2.isEmpty()) continue;
                String[] line= v1v2.split("\t");
                int v1=  Integer.parseInt(line[0]);
                int v2=  Integer.parseInt(line[1]);
                int p= Math.max(v1,v2);
                if (p>maxV) maxV=p;
                edgeSet.add(new WeightedEdge(v1, v2, Double.parseDouble(weight)));
            }
        }
        catch (FileNotFoundException e) {
        }
        Graph myGraph = new Graph(maxV+1);
        return myGraph;
    }

    public int find(int x){
        int r = x;
        while(findSet[r] != r)
            r = findSet[r];
        int i = x;
        while(i != r){
            int j = findSet[i];
            findSet[i] = r;
            i = j;
        }
        return r;
    }

    public void merge(int a, int b){
        if (height[a] == height[b]){
            findSet[b] = a;
            height[a]++;
        } else {
            if (height[a] > height[b]) {
                findSet[b] = a;
            } else {
                findSet[a] = b;
            }
        }
    }


    public void compression(){
        for (WeightedEdge edge: queue){
                int v1 = find(edge.v1);
                int v2 = find(edge.v2);
                if( v1 != v2 ){
                    merge(v1,v2);
                    numEdges++;
                    weight += edge.weight;
                    edgeSet.add(edge);
                }
            }
    }

    public int setUnion() {
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        ConnectedComponents cc = new ConnectedComponents(numVertex, 0);
        while (edges.size() < numVertex - 1) {
            WeightedEdge current = queue.poll();
            if (cc.findSet[current.v1] != cc.findSet[current.v2]) {
                edges.add(current);
                cc.mergeComponents(current.v1, current.v2);
            }
        }
        int cWeight = 0;
        for (WeightedEdge edge: queue) {
            cWeight += edge.getWeight();
        }
        return cWeight;
    }

    public static void main (String[] args){
        MinimalSpanningTreeFinder mst1 = new MinimalSpanningTreeFinder();
        MinimalSpanningTreeFinder mst2 = new MinimalSpanningTreeFinder();
        mst1.compression();
        System.out.println("Homework 9 (MST weight using compression and setUnion)");
        System.out.println("------------------------------------------------------");
        System.out.println(mst1);
        System.out.println("------------------------------------------------------");
        System.out.println("COMPRESSION weight: " + mst1.weight);
        mst2.setUnion();
        System.out.println("SET-UNION weight: " + mst2.weight);

    }
}
