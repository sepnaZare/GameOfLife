package GameOfLife;

public class Cell {
    private State currentState;
    private State nextState;
    private Cell[] neighbors;
    private int aliveNeighborsCount = 0;

    public Cell(State state)
    {
        this.currentState = state;
        this.nextState = state;
    }

    public void setNeighbors(Cell[] neighbors)
    {
        this.neighbors = neighbors;
    }

    public void setCurrentState(State state){
        this.currentState = state;
    }

    public void setNextState(State state){
        this.nextState = state;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void ruleOflife(){
        calculateAliveNeighborsCount();

        if(currentState == State.alive){
            if(isUnderPopulated() || isOverPopulated())
                this.nextState = State.dead;
        }
        else
        {
            if(isBorn())
                this.nextState = State.alive;
        }
    }

    public void updateCurrentState(){
        currentState = nextState;
    }

    private void calculateAliveNeighborsCount(){
        aliveNeighborsCount = 0;
        for(int index = 0; index < neighbors.length; index++)
            if(neighbors[index].currentState == State.alive)
                aliveNeighborsCount++;
    }

    private boolean isUnderPopulated(){
        return (aliveNeighborsCount < 2) ;
    }

    private boolean isOverPopulated(){
        return (aliveNeighborsCount > 3);
    }

    private boolean isBorn(){
        return (aliveNeighborsCount == 3);
    }
}
