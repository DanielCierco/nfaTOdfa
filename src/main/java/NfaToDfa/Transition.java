package NfaToDfa;

/**
 * Transition\
 */
public class Transition {
    private State to;
    private char symbol;

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

    public void setTo(State to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "(" + to.getName() + ", " + this.symbol + ")";
    }
}