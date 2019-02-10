package quadtree;

public class QuadTree 
{
   
    public Node point;
    public Node point2; 
    public static int bolge=0,i=1;
    public static double prex,prey;
    public static double minx, miny, maxx, maxy;
    public static int [] dizix=new int[1000];
    public static int [] diziy=new int[1000];
    
    
    public void insert(double x, double y, double minx, double miny, double maxx, double maxy) {
    //Ekleme   
        if (point == null) 
        {
           if(bolge==0){
            this.minx=minx;
            this.miny=miny;
            this.maxx=maxx;
            this.maxy=maxy;
        }    
        point = new Node(x, y, this.minx, this.miny, this.maxx, this.maxy);         
        } 
        else 
        {
            insert(point, x, y, minx, miny, maxx, maxy);
        }
    }
   
    public void insert(Node point, double x, double y ,double minx, double miny, double maxx, double maxy) {
    //Ekleme    
        if (x <= point.x && y <= point.y)  
        {
           
            if (point.SW == null) 
            {
                point.SW = new QuadTree();
                       
                  this.minx=point.minx;
                  this.miny=point.miny; 
                  this.maxx=this.prex;
                  this.maxy=this.prey;
                 
            }
            
            point.SW.insert(x, y, minx, miny, maxx, maxy);
           
        }
        else if (x <= point.x && y > point.y) 
        {

            if (point.NW == null)
            {
                point.NW = new QuadTree();
                 
                  this.minx=point.minx;
                  this.miny=this.prey;
                  this.maxx=this.prex;      
                  this.maxy=point.maxy;
                
            }
           
            point.NW.insert(x, y, minx, miny, maxx, maxy);
        }
        else if (x > point.x && y <= point.y)  
        {

            if (point.SE == null) 
            {
                point.SE = new QuadTree();
                   
                this.minx=this.prex;
                this.miny=point.miny;
                this.maxx=point.maxx;
                this.maxy=this.prey;
            }
            point.SE.insert(x, y, minx, miny, maxx, maxy);
        }
        else if (x > point.x && y > point.y) 
        {
            if (point.NE == null) 
            {
                
                point.NE = new QuadTree();
                
                this.minx=this.prex;
                this.maxx=point.maxx;
                this.miny=this.prey;
                this.maxy=point.maxy;
                
            }
            point.NE.insert(x, y, minx, miny, maxx, maxy);
        }
    }
    
    public void findzone(double x, double y) {
        //Mouse'a basıldığında hangi bölgede olduğunu bulması için farklı bir point
        if (point2 == null) 
        {     
            point2 = new Node(x, y);
        }
        else
        { 
            findzone(point2, x, y);
        }
    }
    public void findzone(Node point2, double x, double y) {
        
        //Mouse'a basıldığında hangi bölgede olduğunu bulması için farklı bir point
        if (x <= point2.x && y <= point2.y)  
        {
            this.bolge=2;          
            if (point2.SW == null) 
            {
                point2.SW = new QuadTree();  
                this.prex=point2.x;
                this.prey=point2.y;
            }
            
            point2.SW.findzone(x, y);
           
        }
        else if (x <= point2.x && y > point2.y)
        {
            this.bolge=1;
            if (point2.NW == null) {
                point2.NW = new QuadTree();
                this.prex=point2.x;
                this.prey=point2.y;  
            }
           
            point2.NW.findzone(x, y);
        }
        else if (x > point2.x && y <= point2.y) 
        {
            this.bolge=4;
            if (point2.SE == null) {
                point2.SE = new QuadTree();
                this.prex=point2.x;
                this.prey=point2.y;
            }
            point2.SE.findzone(x, y);
        }
        else if (x > point2.x && y > point2.y) {
            this.bolge=3;
            if (point2.NE == null) {
                
                point2.NE = new QuadTree();
                this.prex=point2.x;
                this.prey=point2.y;  
            }
            point2.NE.findzone(x, y);
        }
    }
    public void ara(double x1, double x2, double y1, double y2)
    {
     //Dairenin içindeki noktaları arayan metod   
     if(point==null)
     {
      System.out.println("No intersections.");
     } 
     else
     {   
         if(x1<point.x && point.x<x2 && y1<point.y && point.y<y2)
         {
          System.out.println(" Point number "+i+":(x,y)= "+point.x+","+point.y);
          
          if(point.x!=0 && point.y!=0)
          {
              dizix[i]=(int)point.x;//Kesişen xleri diziye atma
              diziy[i]=(int)point.y;//Kesişen yleri diziye atma  
          } 
          i++;
          ara(point, x1,x2,y1,y2);           
         }  
         else
         {             
            ara(point, x1,x2,y1,y2);
         } 
      
     } 
     
  
    }
    public void ara(Node point, double x1, double x2,double y1,double y2)
    {    
     //Dairenin içindeki noktaları arayan metod 
          if(point.SE==null){
               //Nokta Bitti 
          }
          else
               point.SE.ara(x1, x2, y1, y2);
      
          if(point.NE==null)
          {
               //Nokta Bitti  
          }
          else
               point.NE.ara(x1, x2, y1, y2);
     
          if(point.SW==null)
          {
               //Nokta Bitti;
          }
          else
               point.SW.ara(x1, x2, y1, y2);
     
          if(point.NW==null)
          {
               //Nokta Bitti;
          }
          else
               point.NW.ara(x1, x2, y1, y2);
    }

    public void temizle()
    {       
        //Nodeları silme
         if(point!=null){  
         if(point.SW==null && point.SE==null && point.NW==null && point.NE==null)
         {
             point=null;   
             System.out.println("Cleaned.");   
         }
         else
         {       
             temizle(point);  
         }
         }
         
    }
 
    public void temizle(Node point)
    {
         //Nodeları silme       
         if(point.SW!=null)
         {
             point.SW.temizle();   
         }
         if(point.NW!=null)
         {
             point.NW.temizle();
         }
         if(point.SE!=null)
         {
             point.SE.temizle();
         }
         if(point.NE!=null)
         {
             point.NE.temizle();
         }            
           
    }
}
