import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import java.util.Stack;

public class UserInterfaceString extends UserInterface {
	CalcEngine calcES;
	private JTextField display2;
	
	public UserInterfaceString(CalcEngine engine) {
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
	public static double evaluates(String s){

		//create a stack
		Stack<Integer> stack=new Stack<>();

		// Scan all characters one by one
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);

			// If the scanned character is an operand (number here),
			// push it to the stack.
			if(Character.isDigit(c))
				stack.push((int) c - '0');

			else
			{
				int val1 = stack.pop();
				int val2 = stack.pop();

				switch(c)
				{
					case '+':
						stack.push(val2+val1);
						break;

					case '-':
						stack.push(val2- val1);
						break;

					case '/':
						stack.push(val2/val1);
						break;

					case '*':
						stack.push(val2*val1);
						break;

					case '^':
						stack.push((int) Math.pow(val2, val1));
				}
			}
		}
		return  stack.pop();
	}
	public static String infixToPostfix(String s) {
		Stack<Character> st = new Stack<Character>();
		String postfix = "";
		char ch[] = s.toCharArray();

		for(char c: ch) {
			if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')') {
				postfix = postfix + c;
			} else if (c == '(') {
				st.push(c);
			} else if (c == ')') {
				while(!st.isEmpty()) {
					char t = st.pop();
					if(t != '(') {
						postfix = postfix + t;
					} else {
						break;
					}
				}
			} else if(c == '+' ||c == '-' ||c == '*' ||c == '/') {
				if(st.isEmpty()) {
					st.push(c);
				} else {
					while(!st.isEmpty()) {
						char t = st.pop();
						if(t == '(') {
							st.push(t);
							break;
						} else if(t == '+' || t == '-' || t == '*' || t == '/') {
							if(getPriority(t) <  getPriority(c)) {
								st.push(t);
								break;
							} else {
								postfix = postfix + t;
							}
						}
					}
					st.push(c);
				}
			}
		}
		while(!st.isEmpty()) {
			postfix = postfix + st.pop();
		}
		return postfix;
	}

	public static int getPriority(char c) {
		if(c == '+' || c == '-') {
			return 1;
		} else {
			return 2;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		calc.clear();
		if(command.equals("=")) {
			String userInput = display.getText();
			String postFix = infixToPostfix(userInput);
			/*for(char c: userInput.toCharArray()){
		        if(c == '0' ||
		                c == '1' ||
		                c == '2' ||
		                c == '3' ||
		                c == '4' ||
		                c == '5' ||
		                c == '6' ||
		                c == '7' ||
		                c == '8' ||
		                c == '9') {
		                 int number = Character.getNumericValue(c);
		                 calc.numberPressed(number);
		             }
		             else if(c == '+') {
		                 calc.plus();
		             }
		             else if(c == '-') {
		                 calc.minus();
		             }
		             else if(c == '*') {
		                 calc.multiplicate();
		             }
		             else if(c == '/') {
		                 calc.divide();
		             }*/

			display2.setText(Double.toString(evaluates(postFix)));
			}

        }
	}

