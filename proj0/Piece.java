public class Piece{
	private boolean isFire, isKing, hasCaptured;
	public Board b;
	public int x;
	public int y;
	public String type;

	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire = isFire;
		this.b = b;
		this.x = x;
		this.y = y;
		this.type = type;
		this.isKing = false;
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
		Piece p = b.pieceAt(x, y);
		this.x = x;
		this.y = y;		

		if(this.isFire() && this.y == 7 || !this.isFire() && this.y == 0)
				this.isKing = true;

		if(p != null && !this.equals(p)){
			this.hasCaptured = true;
			if(this.isBomb()){
				for(int i = this.x -1 ; i <= this.x+1; i++){
					for(int j = this.y -1 ; j <= this.y+1; j++){
						Piece neighborP = b.pieceAt(i, j);
						if(neighborP != null && !neighborP.isShield()){
							b.remove(i, j);
						}
					}
				}
				b.remove(this.x, this.y);
			}
		}
	}
}
