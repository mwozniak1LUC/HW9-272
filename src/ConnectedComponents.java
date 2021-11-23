import java.util.*;
import java.io.*;
public class ConnectedComponents  {
    
    
    int[] findSet;
    ArrayList<MyLinkedList<Integer>> vertexSets;
   static int numVertex;
   static int numEdges;
    static ArrayList<Edge> edgeSet;
   static int totalChanges;
   int[] setArr;
   int[] height;
   int count;
    
    public ConnectedComponents(int n, int m){
        numVertex =n;
        numEdges=m;
        totalChanges=0;
        count=0;
       findSet = new int[n]; 
       setArr=new int[n];
       height=new int[n];
       vertexSets = new ArrayList<>(n);
       for (int i=0;i<n;i++) {
           findSet[i]=i;
           setArr[i]=i;
           
           vertexSets.add(new MyLinkedList<Integer>());
           vertexSets.get(i).addFirst(i);
        }
       // edgeSet = new ArrayList<>();
    }
    
    public int getComponentSize(int u){
       return vertexSets.get(u).size(); 
    }
    
    
    public int findComponent(int u) {
       return findSet[u]; 
    }
    
    public void changeComponent(MyLinkedList<Integer> my, int max){
        totalChanges+=my.size();
         if (!my.isEmpty()){
            Node<Integer> temp = my.getFirst();
            do {
            findSet[temp.getInfo()]=max;  
            temp = temp.getNext();  
        } while (temp!=null);
    }
        else {
               System.out.println("empty list ...");
            throw new NoSuchElementException();
        }
        
    }

    
    public void getComponents(){
        HashSet<Integer> comp = new HashSet<>();
        ArrayList<Integer> al = new ArrayList<>();
        //int count=0;
        for (int i: findSet)
          comp.add(i);
        System.out.println(comp.size());
      /*  for (Integer j:comp) {
          al.add(vertexSets.get(j).size());
         // System.out.println();
        }
        Collections.sort(al);
        System.out.println(al);*/
        
    }
    
    public void mergeComponents (int u, int v) {
        int p = findComponent(u);
        int q = findComponent(v);
        int min=0;
        int max=0;
        if (p!=q) {
            if (getComponentSize(p)>getComponentSize(q)){
              min =q; 
              max=p;
            }
              else {
                    min=p;
                    max=q;
                }
            // merge min with the max
            MyLinkedList<Integer> myl = vertexSets.get(min);
            vertexSets.get(max).appendList(myl);
            changeComponent(myl,max);
            
        }
    }
        
    }
    
