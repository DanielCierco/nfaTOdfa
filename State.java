import java.util.List;
import java.util.ArrayList;

public class State {
    boolean isFinal;
    List<Transition> transitions;
    String name;

    public State(String name){
        this.name = name;
        this.isFinal = false;
        List<Transition> transitions = new ArrayList<Transitions>();
    }

    public State(String name, boolean isFinal){
        this.name = name;
        this.isFinal = isFinal;
        List<Transition> transitions = new ArrayList<Transitions>();
    }

    public void addTransition(Transition x){
        this.transitions.add(x);
    }
}