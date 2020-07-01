import java.util.ArrayList;
import java.util.List;

/**
 * Automata
 */
public class Automata {
    List<State> states;
    State initial;
    String name;

    public Automata(State initial, String name){
        this.states = new ArrayList<State>();
        this.name = name;
    }

    public void addState(State x){
        this.states.add(x);
    }
}