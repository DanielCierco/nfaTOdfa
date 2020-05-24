import java.util.List;

public class State {
    boolean isFinal;
    List<Transition> transitions;
    String name;

    public State(String name){
        this.name = name;
        this.isFinal = false;
        List<Transition> transitions = new List<Transitions>();
    }

    public State(String name, boolean isFinal){
        this.name = name;
        this.isFinal = isFinal;
        List<Transition> transitions = new List<Transitions>();
    }

    public void addTransition(Transition x){
        this.transitions.add(x);
    }
}