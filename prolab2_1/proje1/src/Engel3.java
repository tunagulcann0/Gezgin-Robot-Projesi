import java.util.*;
public class Engel3 {
    Random random=new Random();
    public int engelsec(){
        int x= random.nextInt(0,5);
        return x;
    }
    public void engelturu4(int matris[][],int x,int y){
        matris[x][y]=8;
        matris[x][y+1]=8;
        matris[x][y+2]=0;
        matris[x+1][y]=8;
        matris[x+1][y+1]=8;
        matris[x+1][y+2]=0;
        matris[x+2][y]=8;
        matris[x+2][y+1]=8;
        matris[x+2][y+2]=0;
    }
    public void engelturu5(int matris[][],int x,int y){
        matris[x][y]=0;
        matris[x][y+1]=8;
        matris[x][y+2]=8;
        matris[x+1][y]=0;
        matris[x+1][y+1]=8;
        matris[x+1][y+2]=8;
        matris[x+2][y]=0;
        matris[x+2][y+1]=8;
        matris[x+2][y+2]=8;
    }
    public void engelturu6(int matris[][],int x,int y){
        matris[x][y]=0;
        matris[x][y+1]=0;
        matris[x][y+2]=0;
        matris[x+1][y]=8;
        matris[x+1][y+1]=8;
        matris[x+1][y+2]=8;
        matris[x+2][y]=8;
        matris[x+2][y+1]=8;
        matris[x+2][y+2]=8;
    }
    public void engelturu7(int matris[][],int x,int y){
        matris[x][y]=8;
        matris[x][y+1]=8;
        matris[x][y+2]=8;
        matris[x+1][y]=8;
        matris[x+1][y+1]=8;
        matris[x+1][y+2]=8;
        matris[x+2][y]=0;
        matris[x+2][y+1]=0;
        matris[x+2][y+2]=0;
    }
}
