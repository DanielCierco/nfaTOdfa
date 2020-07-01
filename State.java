import java.util.List;
import java.util.ArrayList;

public class State {
    private boolean isFinal;
    private List<Transition> transitions;
    private String name;

    public State(String name){
        this.name = name;
        this.isFinal = false;
        List<Transition> transitions = new ArrayList<Transition>();
    }

    public State(String name, List<Transition> transitions){
        this.name = name;
        this.transitions = transitions;
        this.isFinal = false;
    }

    public State(String name, List<Transition> transitions, boolean isFinal){
        this.name = name;
        this.transitions = transitions;
        this.isFinal = isFinal;
    }

    public State(String name, boolean isFinal){
        this.name = name;
        this.isFinal = isFinal;
        List<Transition> transitions = new ArrayList<Transition>();
    }

    public void addTransition(Transition t){
        if(!this.transitions.contains(t)){
            this.transitions.add(t);
        }
    }

    public String getName(){
        return this.name;
    }

    public void setFinal(){
        this.isFinal = true;
    }

    public State getTransition(Character c){
        for(Transition t: this.transitions){
            if(t.getSymbol() == c){
                t.getTo();
            }
        }
        return null;
    }

    public boolean isFinal(){
        return this.isFinal;
    }
}