import java.util.Stack;

public class CalcEngineString extends CalcEngine {
	
	public CalcEngineString () {
		super();

	}
	public int evaluate(String expression) {
		String[] words = expression.split(" ");
		Stack<String> stack = new Stack<>();
		for (String w : words) {
			if (w.equals("+")) {
				String y = stack.pop();
				String x = stack.pop();
				int num = Integer.valueOf(x) + Integer.valueOf(y);
				stack.push(String.valueOf(num));
			} else if (w.equals("-")) {
				String y = stack.pop();
				String x = stack.pop();
				int num = Integer.valueOf(x) - Integer.valueOf(y);
				stack.push(String.valueOf(num));
			} else if (w.equals("*")) {
				String y = stack.pop();
				String x = stack.pop();
				int num = Integer.valueOf(x) * Integer.valueOf(y);
				stack.push(String.valueOf(num));
			} else if (w.equals("/")) {
				String y = stack.pop();
				String x = stack.pop();
				int num = Integer.valueOf(x) / Integer.valueOf(y);
				stack.push(String.valueOf(num));
			} else {
				stack.push(w);
			}
		}
		return Integer.valueOf(stack.pop());
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
