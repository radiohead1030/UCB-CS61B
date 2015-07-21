public class CalculatorUI{ 
	public static void main(String[] args){ 
		int a;
		int b;
		int result;
		int historyNum;
		String op;
		String starter;
		String equation;
		Calculator cal = new Calculator();

		while(true){
			System.out.print ("> ");
			starter = StdIn.readString();
			if(starter.equals("quit"))
				break;
			else if(starter.equals("history")){
				historyNum = StdIn.readInt();
				cal.printHistory(historyNum);	
			}
			else if(starter.equals("dump")){
				cal.printAllHistory();	
			}
			else if(starter.equals("clear")){
				cal.clearHistory();	
			}
			else if(starter.equals("undo")){
				cal.undoEquation();	
			}
			else if(starter.equals("sum")){
				cal.cumulativeSum();	
				System.out.println (cal.cumulativeSum());
			}
			else{
				a = Integer.parseInt(starter);
				op = StdIn.readString();
				b = StdIn.readInt();
				if (op.equals("+")){
					result = cal.add(a, b);
					equation = a + " " + op + " " + b;
					System.out.println (result);
					cal.saveEquation(equation, result);
				}
				else if(op.equals("*")){
					result = cal.multiply(a, b);
					equation = a + " " + op + " " + b;
					System.out.println (result);
					cal.saveEquation(equation, result);
				}
				else
					System.out.println ("Invalid expression!");
			}
		}
	}
}
