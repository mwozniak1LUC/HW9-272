import java.util.*;

public class WeightedEdge implements Comparable<WeightedEdge>
{
     int v1;
   int v2;
   double weight;
    public WeightedEdge() {
    v1 =0;
    v2=0;
    weight=0;
        
    }
   
   public WeightedEdge (int i, int j, double w) {
       
       v1=i;
       v2=j;
       weight=w;
       
    }
    
    public double getWeight(){
       return weight; 
        
    }
    
    public int compareTo(WeightedEdge other){
        
        return (Double.valueOf(weight).compareTo(Double.valueOf(other.weight)));
        
    }
    
    public String toString(){
        
       return ("("+v1+","+v2+")"+weight); 
        
    }
    
}
