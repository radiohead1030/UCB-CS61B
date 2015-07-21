import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public EquationList equationHistory = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
	    int op1;
	    int op2;
	    int sum = 0;
	    int carry = 0;
	    int result = 0;
	    for (int i = 0; i < 32; i++){
		op1 = getBit(x, i);
		op2 = getBit(y, i);
		sum = op1 ^ op2 ^ carry;
		carry = op1 & op2 | (op1 ^ op2) & carry;
		if (sum == 1)
			result = setBit(result, i);
	    }
	    return result;
    }

    private int getBit(int x, int i){
	if(i >= 32)
		return -1;
	else{
		return ((x>>i) & 1);
	}
    }
    
    private int setBit(int x, int i){
	if(i >= 32)
		return -1;
	else{
		return (x | (1<<i));
	}
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
	    int result = 0;
	    int partialY = 0;
	    if (x == 0 | y ==0)
		    return 0;
	    else{
		for(int i = 0; i < 32; i++){
			partialY = getBit(y, i);
			if (partialY == 1){
				result = this.add(result, x<<i);		
			}
		}
		return result;
	    }
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
	    if ( this.equationHistory == null){
		    this.equationHistory = new EquationList(equation, result, null);
	    }
	    else {
		    EquationList equationLatest = new EquationList(equation, result, this.equationHistory);
		    this.equationHistory = equationLatest;
	    }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	    EquationList equationP = this.equationHistory;
	    int n = 0;
	    while (equationP != null){
		    n += 1;
		    equationP = equationP.next;
	    }
	    this.printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
	    EquationList equationP = this.equationHistory;
	    while (equationP != null && n >= 1){
		    System.out.println (equationP.equation + " = " + equationP.result);
		    equationP = equationP.next;
		    n = n - 1;
	    }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
	if (this.equationHistory != null){
		this.equationHistory = this.equationHistory.next;
	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
	this.equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
	    EquationList equationP = this.equationHistory;
	    int sum = 0;

	    if (equationP == null){
		    return 0;
	    }
	    else{
		    while (equationP != null){
			    sum = this.add(equationP.result, sum);
			    equationP = equationP.next;
		    }
		    return sum;
	    }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
	    EquationList equationP = this.equationHistory;
	    int product = 1;

	    if (equationP == null){
		    return 1;
	    }
	    else{
		    while (equationP != null){
			    product = this.multiply(equationP.result, product);
			    equationP = equationP.next;
		    }
		    return product;
	    }
    }
}
