package NfaToDfa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class State {
    private boolean isFinal;
    private List<Transition> transitions;
    private String name;

    public State(String name){
        this.name = name;
        this.isFinal = false;
        this.transitions = new ArrayList<Transition>();
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
        this.transitions = new ArrayList<Transition>();
    }

    public void addTransition(Transition t){
        this.transitions.add(t);
    }

    public void removeTransitions(char c){
        this.transitions.removeIf(p -> p.getSymbol() == c);
    }

    public String getName(){
        return this.name;
    }

    public void setFinal(){
        this.isFinal = true;
    }

    public List<State> getTransition(char c){
        List<State> states = new ArrayList<>();
        for(Transition t: this.transitions){
            if(t.getSymbol() == c){
                states.add(t.getTo());
            }
        }
        return states;
    }

    public List<Transition> getTransitions(){
        return this.transitions;
    }

    public boolean isFinal(){
        return this.isFinal;
    }

    public String getAllTransitions(){
        String resp = "";
        if(this.transitions!=null){
            for (Transition t : this.transitions) {
                resp = resp.concat("("+ t.getTo().getName()  + ", " + t.getSymbol() + "), ");
            }
            return "Transicoes de " + this.getName() + " : " +resp.substring(0, resp.lastIndexOf(","));
        }
        return "nada";
    }

    public State transite(char c){
        for (Transition transition : this.transitions) {
            //System.out.println("simbolo: "+ c + " Simbolo dessa transição: "+ transition.getSymbol());
            if(transition.getSymbol() == c){
                //System.out.println(transition.getTo());
                return transition.getTo();
            }
        }
        return null;
    }

    public void addAll(List<Transition> list){
        for (Transition t : list) {
            this.transitions.add(t);
        }
    }

    public boolean hasTransition(){
        return (this.transitions == null);
    }

    @Override
    public String toString(){
        List<String> aux = this.transitions.stream().map(p -> p.toString()).collect(Collectors.toList());
        return "\nNome do Estado: " + this.name + "\nTransicoes: " + aux.toString() + "\n";
    }


}