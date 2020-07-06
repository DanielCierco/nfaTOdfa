import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:  ");
        String inputx = read.nextLine();
        Automata nfa = generateAutomata(inputx);
        Automata dfa = nfa.toDfa();
        System.out.println(dfa.toString());
        
        //System.out.println(dfa.getState("q1").transite('D'));
        //System.out.println(dfa.getStates().size());
        System.out.println("\n\nGostaria de testar o modelo deterministico? ");
        inputx = read.nextLine();
        teste(inputx, dfa);
        read.close();
    }

    public static Automata generateAutomata(String name){
        try {
            FileReader txt = new FileReader(name);
            BufferedReader readTxt = new BufferedReader(txt);
            String line = readTxt.readLine();
            StringBuilder temp = new StringBuilder();
            int index = 2;
            String[] splitedLine = line.split("=");
            splitedLine[1] = splitedLine[1].trim();
            Automata automata = new Automata(splitedLine[0]);
            for (int i = index; !(splitedLine[1].charAt(i-1) == '}'); i++) {
                if(splitedLine[1].charAt(i) == ',' || splitedLine[1].charAt(i) == '}'){
                    automata.addState(new State(temp.toString()));
                    temp = new StringBuilder();
                }
                if(!(splitedLine[1].charAt(i) == ',') && !(splitedLine[1].charAt(i) == '{') && !(splitedLine[1].charAt(i) == '(') && !(splitedLine[1].charAt(i) == ' ')){
                    temp.append(splitedLine[1].charAt(i));
                }
                index = i;
            }
            temp = new StringBuilder();
            for (int i = index+3; !(splitedLine[1].charAt(i-1) == '}'); i++) {
                if(splitedLine[1].charAt(i) == ',' || splitedLine[1].charAt(i) == '}'){
                    automata.addSymbol(temp.charAt(0));
                    temp = new StringBuilder();
                }
                if(!(splitedLine[1].charAt(i) == ',') && !(splitedLine[1].charAt(i) == '{') && !(splitedLine[1].charAt(i) == '(') && !(splitedLine[1].charAt(i) == ' ')){
                    temp.append(splitedLine[1].charAt(i));
                }
                index = i;
            }
            temp = new StringBuilder();
            
            for (int i = index+3; !(splitedLine[1].charAt(i-1) == ','); i++) {
                if(splitedLine[1].charAt(i) == ','){
                    automata.setInitial(automata.getState(temp.toString()));
                    temp = new StringBuilder();
                }
                if(!(splitedLine[1].charAt(i) == ',') && !(splitedLine[1].charAt(i) == '{') && !(splitedLine[1].charAt(i) == '(') && !(splitedLine[1].charAt(i) == ' ')){
                    temp.append(splitedLine[1].charAt(i));
                }
                index = i;
            }
            temp = new StringBuilder();
            for (int i = index+2; !(splitedLine[1].charAt(i-1) == '}'); i++) {
                if(splitedLine[1].charAt(i) == ',' || splitedLine[1].charAt(i) == '}'){
                    automata.getState(temp.toString()).setFinal();
                    temp = new StringBuilder();
                }
                if(!(splitedLine[1].charAt(i) == ',') && !(splitedLine[1].charAt(i) == '{') && !(splitedLine[1].charAt(i) == '(') && !(splitedLine[1].charAt(i) == ' ')){
                    temp.append(splitedLine[1].charAt(i));
                }
                index = i;
            }
            
            while (line != null) {
                line = readTxt.readLine();
                if(line!=null){
                    if(line.contains("(")){
                        splitedLine = line.split("=");
                        splitedLine[0] = splitedLine[0].substring(splitedLine[0].indexOf("(")+1, splitedLine[0].indexOf(")"));
                        String[] from = splitedLine[0].split(",");
                        automata.getState(from[0]).addTransition(new Transition(automata.getState(splitedLine[1].trim()), from[1].trim().charAt(0)));
                    }
                }
            }
            txt.close();
            return automata;
        } 


    
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
            return null;
        }

        
    }

    public static void teste(String resp, Automata toTest){
        Scanner read = new Scanner(System.in);
        System.out.println(toTest.toString());
        switch (resp) {
            case "s": case "S":
                System.out.println("\n Digite a palavra a ser testada: ");
                System.out.println(toTest.test(read.nextLine()));
                System.out.println("Gostaria de testar outra palavra? ");
                teste(read.nextLine(), toTest);
                break;
        
            case "n": case "N":
                System.out.println("Encerrando programa...");
                break;
            default:
                System.out.println("resposta inválida\n\n");
                teste(resp, toTest);
                break;
        }
        read.close();
    }
}