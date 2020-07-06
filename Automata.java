import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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

    public String getName(){
        return this.name;
    }

    public void setInitial(State i){
        this.initial = i;
    }

    public List<State> getStates() {
        return states;
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
            System.out.println("estado atual: " + actual.getName() + "    simbolo atual: " + c);
            if(!containsSymbol(c)){
                return false;
            }
            actual = actual.transite(c);
            if(actual == null){
                return false;
            }
        }
        return actual.isFinal();
    }

    public Automata toDfa(){
        State actual = this.initial;
        Automata newAutomata = new Automata(this.getName()+" Deterministic");
        newAutomata.addState(actual);
        newAutomata.setInitial(actual);
        Stack<State> s = new Stack<State>();
        s.push(actual);
        while (!s.isEmpty()){
            actual = s.pop();
            for (char c : this.alphabet) {
                List<State> transicoes = actual.getTransition(c);
                //System.out.println("Estado atual: " + actual.getName() + " Simbolo atual: " + c + " Quantidade de transições nesse simbolo: " + transicoes.size());
                if(transicoes.size()>0){
                    if(!newAutomata.containsSymbol(c)){
                        newAutomata.addSymbol(c);
                    }
                    String newName = new String();
                    for (State state : transicoes) {
                        newName = newName.concat(state.getName());
                    }
                    if(newAutomata.getState(newName) == null && newName.length()>0){
                        newAutomata.addState(new State(newName.trim()));
                        s.push(newAutomata.getState(newName));
                        for(State state: transicoes){
                            newAutomata.getState(newName).addAll(state.getTransitions());
                        }
                    }
                    
                    newAutomata.getState(actual.getName()).removeTransitions(c);
                    newAutomata.getState(actual.getName()).addTransition(new Transition(newAutomata.getState(newName), c));
                    
                }
            }
        }


        for (State state : this.states){
            if(state.isFinal()){
                for (State aux2 : newAutomata.getStates()) {
                    if(aux2.getName().contains(state.getName())){
                        aux2.setFinal();
                    }
                }
            }
        }

        ajusta(newAutomata);

        return newAutomata;
    }

    
    private void ajusta(Automata automato){
        for (State s : automato.getStates()) {
            if(s.hasTransition()){
                for (Transition t : s.getTransitions()) {
                    System.out.println("aqui n deu erro " + t.toString());
                    t.setTo(automato.getState(t.getTo().getName()));
                }
            }
        }
    }


    public String toString() {
        String aux = "";
        for (State s : states) {
            if(s.isFinal()){
                aux = aux.concat(s.getName()+", ");
            }
        }
       List<String> estados = this.states.stream().map(p -> p.toString()).collect(Collectors.toList());
        return this.name + ":\nEstados: " + estados.toString() + "\n\nEstado inicial: " + this.initial.getName()+ "\n\nEstados finais: " + aux  + "\n\nAlfabeto: " + alphabet.toString();
    }


}