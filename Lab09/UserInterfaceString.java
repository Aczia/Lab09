import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserInterfaceString extends UserInterface {
	CalcEngineString calcES;
	private JTextField display2;
	
	public UserInterfaceString(CalcEngineString engine) {
		super(engine);
		calcES = engine;
	}
	
	public void makeFrame() {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        frame.setMinimumSize(new Dimension(500,150));
        
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        addButton(buttonPanel, "=");
        buttonPanel.setMinimumSize(new Dimension(500,100));
        buttonPanel.setBorder(new EmptyBorder( 0, 0, 100, 0));
        contentPane.add(buttonPanel, BorderLayout.CENTER); 
        
        display2 = new JTextField();
        contentPane.add(display2, BorderLayout.SOUTH);

        frame.pack();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		calc.clear();
		if(command.equals("=")) {
			String userInput = display.getText();
			String postFix = CalcEngineString.infixToPostfix(userInput);
			double result = CalcEngineString.evaluates(postFix);
//			for(char c: userInput.toCharArray()){
//		        if(c == '0' ||
//		                c == '1' ||
//		                c == '2' ||
//		                c == '3' ||
//		                c == '4' ||
//		                c == '5' ||
//		                c == '6' ||
//		                c == '7' ||
//		                c == '8' ||
//		                c == '9') {
//		                 int number = Character.getNumericValue(c);
//		                 calc.numberPressed(number);
//		             }
//		             else if(c == '+') {
//		                 calc.plus();
//		             }
//		             else if(c == '-') {
//		                 calc.minus();
//		             }
//		             else if(c == '*') {
//		                 calc.multiplicate();
//		             }
//		             else if(c == '/') {
//		                 calc.divide();
//		             }
//			}
//			calc.calculateResult();
//			display2.setText("" + calc.getDisplayValue());
			display2.setText("" + result);
        }
	}
}
