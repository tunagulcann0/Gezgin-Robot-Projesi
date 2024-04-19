import java.util.List;

public class DepthFirst2 {

    public static  boolean isValidSpot(int[][] matris, int x, int y,int boyut){
        if (x>=0 && x<boyut && y>=0 && y<boyut){
            return matris[x][y] == 0;
        }
        return false;
    }

    public static boolean traverse(int[][] matris,int x,int y,int boyut){


        if (isValidSpot(matris,x,y,boyut)){
            if (boyut - 1 == x && y == boyut-1){
                return true;
            }

            matris[x][y] = 5;
            boolean returnValue = traverse(matris, x, y + 1, boyut);
            if (!returnValue) {
                returnValue = traverse(matris, x - 1, y, boyut);
            }
            if (!returnValue){
                returnValue = traverse(matris,x+1,y,boyut);
            }
            if (!returnValue){
                returnValue = traverse(matris,x,y-1,boyut);
            }
            if (!returnValue){
                matris[x][y]=6;
            }
            return returnValue;
        }

        return false;
    }
}
