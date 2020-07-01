import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n");
        String filename = read.nextLine();
        generateAutomata(filename);
 
    }

    public static void generateAutomata(String name){
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

            System.out.println(automata.toString());
            
            //while (line != null) {
                //line = readTxt.readLine(); // lê da segunda até a última line
            //}
            txt.close();
        } 
    
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
    }
}