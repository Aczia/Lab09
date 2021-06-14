public class Runner {
    public static void main (String args []){
        CalcEngine calcEngine = new CalcEngine();
        UserInterfaceString userInterface = new UserInterfaceString(calcEngine);
        userInterface.setVisible(true);
    }
}
