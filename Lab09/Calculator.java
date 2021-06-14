/**
 * The main class of a simple calculator. Create one of these and you'll
 * get the calculator on screen.
 */
public class Calculator
{
    private CalcEngineString engine;
    private UserInterfaceString gui;

    /**
     * Create a new calculator and show it.
     */
    public Calculator()
    {
//        engine = new CalcEngineString();
//        gui = new UserInterfaceString(engine);
    }
    
    public void createCalcString() {
    	engine = new CalcEngineString();
        gui = new UserInterfaceString(engine);
        show();
    }

    /**
     * In case the window was closed, show it again.
     */
    public void show()
    {
        gui.setVisible(true);
    }
}
