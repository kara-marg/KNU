package lab2_1;

public class Field {
    private boolean[][] matrix;
    Field(int numberOfRow, int numberOfColumn){
        matrix = new boolean[numberOfRow][numberOfColumn];
    }
    public void stepIntoPoint(int x, int y){
        matrix[y][x] = true;
    }
    public void stepOutPoint(int x, int y){
        matrix[y][x]=false;
    }
    public int getHeight(){
        return matrix.length;
    }
    public int getWidth(){
        return matrix[0].length;
    }
    public boolean checkIfPointIsReserved(int x, int y){

        return matrix[y][x];
    }


}
