public class Piece{
	public boolean isFire;
	public Board b;
	public int x;
	public int y;
	public String type;
	public boolean isKing = false;
	public boolean hasCaptured = false;
	public boolean removed = false;

	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire = isFire;
		this.b = b;
		this.x = x;
		this.y = y;
		this.type = type;
		this.hasCaptured = false;
	}
	
	public boolean isFire(){
		return this.isFire;
	}
	
	public int side(){
		if(this.isFire){
			return 0;
		}
		else
			return 1;
	}
	
	public boolean isKing(){
		return this.isKing;
	}
	
	public boolean isBomb(){
		return this.type.equals("bomb");
	}
	
	public boolean isShield(){
		return this.type.equals("shield");
	}

	public boolean hasCaptured(){
		return this.hasCaptured;
	}
	
	public void doneCapturing(){
		this.hasCaptured = false;
	}
	
	public void move(int x,int y){
		
	}
}
