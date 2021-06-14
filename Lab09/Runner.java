public class Runner {
    public static void main (String args []){
        CalcEngineString calcEngine = new CalcEngineString();
        UserInterfaceString userInterface = new UserInterfaceString(calcEngine);
        userInterface.setVisible(true);
    }
}
