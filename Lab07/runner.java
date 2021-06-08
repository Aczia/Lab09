public class runner {
    public static void main (String args []){
        CalcEngine calcEngine = new CalcEngine();
        UserInterface userInterface = new UserInterface(calcEngine);

        userInterface.setVisible(true);
    }
}
