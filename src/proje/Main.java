package proje;

public class Main {

    public static void main(String[] args) {
        Main program = new Main();
        program.startProgram();
    }

    public void startProgram(){
        FileTools tool = new FileTools();
        tool.readFile();
        GraphicalUI gui = new GraphicalUI();
        gui.setUpGui();
    }
}
