import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Transition\
 */
public class Transition {
    private State to;
    private char symbol;
    private boolean isVoid;

    public Transition(State to, char symbol, boolean isVoid){
        this.to = to;
        this.symbol = symbol;
        this.isVoid = isVoid;
    }

    public Transition(State from, State to, char symbol){
        this.to = to;
        this.symbol = symbol;
        this.isVoid = false;
    }
    
    public boolean isTransition(Char symbol){
        return this.symbol==symbol? true : false;
    }

    public State getTo(){
        return this.to;
    }
}