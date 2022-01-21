package GameOfLife;

import java.awt.*;

public class GameOfLife {

    private Cell[][] currentBoard;
    private int boardSize = 50;
    private boolean isActive = false;

    public GameOfLife(int[][] seed){

        //Board Definition
        currentBoard = new Cell[boardSize][boardSize];
        for(int x = 0; x < currentBoard.length; x++)
            for(int y = 0; y < currentBoard[0].length; y++)
                currentBoard[x][y] = new Cell(State.dead);

        //update alive cells
        for(int x = 0; x < seed.length; x++){
            currentBoard[seed[x][0]][seed[x][1]].setCurrentState(State.alive);
            currentBoard[seed[x][0]][seed[x][1]].setNextState(State.alive);
        }

        setNeighborsOfCells();
    }


    private void setNeighborsOfCells(){
        for(int x = 1; x < currentBoard.length - 1; x++)
            for(int y = 1; y < currentBoard[0].length - 1; y++){
                int index = 0;
                Cell[] neighbors = new Cell[8];
                for(int i = -1; i <= 1; i++)
                    for(int j =  -1; j <= 1; j++) {
                        if (i == 0 && j == 0)
                            continue;
                        neighbors[index++] = currentBoard[x + i][y + j];
                    }
                currentBoard[x][y].setNeighbors(neighbors);
            }
    }

    public void tick(){
        if(isActive == false)
            return;

        for(int x = 1; x < currentBoard.length - 1; x++)
            for(int y = 1; y < currentBoard[0].length - 1; y++)
                currentBoard[x][y].ruleOflife();


        for(int x = 1; x < currentBoard.length - 1; x++)
            for(int y = 1; y < currentBoard[0].length - 1; y++)
                currentBoard[x][y].updateCurrentState();

    }

    public void stop(){
        isActive = false;
    }

    public void start(){
        isActive = true;
    }

    public void draw(Graphics g){
        for ( int x = 0; x < currentBoard.length; x += 1 )
            for ( int y = 0; y < currentBoard[0].length; y += 1 )
                if(currentBoard[x][y].getCurrentState() == State.alive) {
                    g.setColor(new Color(102, 0 , 153));
                    g.fillRect(50 + (x * 15), 50 +(y * 15), 15, 15);
                }
    }

}
