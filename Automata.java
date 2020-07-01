import java.util.ArrayList;
import java.util.List;

/**
 * Automata
 */
public class Automata {
    private List<State> states;
    private List<Character> alphabet;
    private State initial;
    private String name;

    public Automata(String name){
        this.states = new ArrayList<State>();
        this.alphabet = new ArrayList<Character>();
        this.name = name;
        this.initial = null;
    }

    public void setInitial(State i){
        this.initial = i;
    }

    public void addState(State s){
        this.states.add(s);
    }

    public void addSymbol(Character c){
        this.alphabet.add(c);
    }

    public State getState(String name) {
        for (State e : states) {
            if (e.getName().equals(name))
                return e;
        }
        return null;
    }

    public boolean containsSymbol(char s){
        for (Character symbol : alphabet){
            if(s == symbol){
                return true;
            }
        }
        return false;
    }

    public boolean test(String input){
        State actual = this.initial;
        for(char c: input.toCharArray()){
            if(!containsSymbol(c)){
                return false;
            }
            actual = actual.getTransition(c);
            if(actual == null){
                return false;
            }
        }
        return actual.isFinal();
    }

    public String toString() {
        String aux = "";
        for (State s : states) {
            if(s.isFinal()){
                aux = aux.concat(s.getName()+", ");
            }
        }
        return this.name + ":\nEstados: " + this.states.toString() + "\n\nEstado inicial: " + this.initial.getName()+ "\n\nEstados finais: " + aux.substring(0, aux.lastIndexOf(","))  + "\n\nAlfabeto: " + alphabet.toString();
    }
}