import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Transition\
 */
public class Transition {
    private State to;
    private char symbol;

    public Transition(State to, char symbol, boolean isVoid){
        this.to = to;
        this.symbol = symbol;
    }

    public Transition(State to, char symbol){
        this.to = to;
        this.symbol = symbol;
    }
    
    public boolean isTransition(Character symbol){
        return this.symbol==symbol? true : false;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public State getTo(){
        return this.to;
    }
}