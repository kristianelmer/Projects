package tycoon.model;

import java.util.ArrayList;

public class Grid {
    int rows;
    int cols;
    char standard;
    ArrayList<ArrayList<GridCell>> grid;
    public Grid(int rows, int cols, char standard){
        this.rows = rows;
        this.cols = cols;
        this.standard = standard;
        grid = new ArrayList<>();
        for (int row = 0; row < rows; row++){
            grid.add(new ArrayList<>());
            for (int col = 0; col < cols; col++){
                grid.get(row).add(new GridCell(col, row, standard));
            }
        }
    }


    public void set(GridCell cell){
        grid.get(cell.y()).set(cell.x(), cell);
    }
    public char get(int col, int row){
        return grid.get(row).get(col).symbol();
    }   
    public void printShape(){
        System.out.println(grid.get(0).size() + ", " + grid.size());
    }
}
