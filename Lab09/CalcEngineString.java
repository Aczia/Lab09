import java.util.Stack;

public class CalcEngineString extends CalcEngine {
	
	public CalcEngineString () {
		super();
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

                }
            }
        }
        return  stack.pop();
    }

	public static int getPriority(char c) {
		if(c == '+' || c == '-') {
			return 1;
		} else {
			return 2;
		}
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
}
