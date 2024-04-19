import java.util.*;

public class Engel2 {
    Random random = new Random();
    public int engelsec(){
        int x=random.nextInt(0,5);
        return x;
    }
    public void engelturu5(int matris[][],int x,int y){
        matris[x][y]=7;
        matris[x+1][y]=0;
        matris[x][y+1]=7;
        matris[x+1][y+1]=0;
    }
    public void engelturu6(int matris[][],int x,int y){
        matris[x][y]=0;
        matris[x+1][y]=7;
        matris[x][y+1]=0;
        matris[x+1][y+1]=7;
    }

    public void engelturu7(int matris[][], int x, int y) {
        matris[x][y]=0;
        matris[x+1][y]=0;
        matris[x][y+1]=7;
        matris[x+1][y+1]=7;
    }
    public void engelturu8(int matris[][],int x,int y){
        matris[x][y]=7;
        matris[x+1][y]=7;
        matris[x][y+1]=0;
        matris[x+1][y+1]=0;
    }
}
