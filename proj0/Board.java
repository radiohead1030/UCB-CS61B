public class Board{
	private static int width = 8;
	private static int height = 8;
	private int pieceNumber = 24;
	public Piece[]	pieces = new Piece[pieceNumber];

	//private boolean fireTurn;
	public boolean fireTurn;
	//private boolean waterTurn;
	public boolean waterTurn;
	//private Piece selectPiece;
	public Piece selectPiece;
	private boolean moveSelectPiece;

	public static void main(String args[]){
		Board b = new Board(false);
		b.setFireTurn(true);
		int x, y;

		while(true){
			if (StdDrawPlus.mousePressed()) {
				x = (int)StdDrawPlus.mouseX();
                		y = (int)StdDrawPlus.mouseY();
				if(b.canSelect(x, y)){
					b.select(x, y);
				}
            		}

			if(StdDrawPlus.isSpacePressed()){
				if(b.canEndTurn()){
					b.endTurn();
				}
			}
            		StdDrawPlus.show(100);
		}
	}

	public Board(boolean shouldBeEmpty){
        	StdDrawPlus.setXscale(0, width);
		StdDrawPlus.setYscale(0, height);
		
		fireTurn = true;
		waterTurn = false;
		selectPiece = null;
		moveSelectPiece = false;
		
		int pieceIndex = 0;
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				if((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, 0.5);
				if(!shouldBeEmpty){
					if(j == 0 && (i % 2 == 0)) {
						pieces[pieceIndex] = new Piece(true, this, i, j, "pawn");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					else if(j == 1 && (i % 2 != 0)) {
						pieces[pieceIndex] = new Piece(true, this, i, j, "shield");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						}
					else if(j == 2 && (i % 2 == 0)){
						pieces[pieceIndex] = new Piece(true, this, i, j, "bomb");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						}
					else if(j == height - 1 && (i % 2 != 0)){
						pieces[pieceIndex] = new Piece(false, this, i, j, "pawn");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					else if(j == height - 2 && (i % 2 == 0)){
						pieces[pieceIndex] = new Piece(false, this, i, j, "shield");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						}	
					else if(j == height - 3 && (i % 2 != 0)) {
						pieces[pieceIndex] = new Piece(false, this, i, j, "bomb");
						pieceIndex++;
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
						}
					}
				}
			}
		}

	public Piece pieceAt(int x, int y){
		if(isOutOfBounds(x,y)){
			return null;
		}
		for(int i = 0; i < pieceNumber; i++){
			if(pieces[i] != null){
				if(x == pieces[i].x && y == pieces[i].y){
					if(pieces[i].removed == false){
						return pieces[i];
					}
				else continue;	
				}
			}
			else continue;
		}
		return null;
	}
	
	public Piece remove(int x, int y){
		if(isOutOfBounds(x,y)){
			System.out.println("(x, y) is out of bounds for remove method!!!");
			return null;
		}
		Piece p = this.pieceAt(x, y);
		
		if(p != null){
			if((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			else StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
		}

		return p;
	}
	
	public void place(Piece p, int x, int y){
		if(!isOutOfBounds(x,y) && p != null){
			this.remove(x,y);
			this.drawPiece(p, x, y);
			p.x = x;
			p.y = y;
		}
	}
	
	public boolean canSelect(int x, int y){
		if(isOutOfBounds(x,y)){
			return false;
		}
		Piece p = this.pieceAt(x, y);

		if(p != null){
			if (selectPiece != null && moveSelectPiece == true){
				if(selectPiece.hasCaptured()){
					return this.validMove(selectPiece.x, selectPiece.y, x, y);		
				}
				return false;
			}
			else if (selectPiece != null && moveSelectPiece == false){
				if(selectPiece.isFire() == p.isFire()){
					return true;
				}	
				return this.validMove(selectPiece.x, selectPiece.y, x, y);		
			}
			else{
				if(fireTurn && p.isFire()){
					return true;
				}
				else if(waterTurn && !p.isFire()){
					return true;
				}
				else return false;
			}
		}
		else{
			if(selectPiece == null){
				return false;
			}
			else{
				if(!moveSelectPiece){
					return this.validMove(selectPiece.x, selectPiece.y, x, y);		
				}
				return false;
			}
		}
	}

	public void select(int x, int y){
		Piece p = this.pieceAt(x, y);

		if(p == null){
			if(selectPiece != null){
			 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			 	StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
			 	this.remove(selectPiece.x, selectPiece.y);	
			 	this.place(selectPiece, x, y);	
				moveSelectPiece = true;
			}
		}
		else{
			if(selectPiece == null || selectPiece != null && selectPiece.isFire() == p.isFire()){
				if(selectPiece != null){
			 		this.place(selectPiece, selectPiece.x, selectPiece.y);	
				}
			 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			 	StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
				this.drawPiece(p, x, y);
				selectPiece = p;
			}
			else{
			 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			 	StdDrawPlus.filledSquare(x + .5, y + .5, 0.5);
			 	this.remove(selectPiece.x, selectPiece.y);	
				this.drawPiece(selectPiece, x, y);
				p.removed = true;
				selectPiece.x = x;
				selectPiece.y = y;
				moveSelectPiece = true;
				selectPiece.hasCaptured = true;
			}
		}	
	}

	public boolean canEndTurn(){
		if(selectPiece != null && moveSelectPiece == true){
			return true;
		}
		else return false;
	}

	public void endTurn(){
		this.place(selectPiece, selectPiece.x, selectPiece.y);
		selectPiece.doneCapturing();
		selectPiece = null;
		moveSelectPiece = false;
		this.switchTurn();
	}

	private void switchTurn(){
		if(fireTurn){
			fireTurn = false;
			waterTurn = true;
		}
		else if(waterTurn){
			fireTurn = true;
			waterTurn = false;
		}
		else{
			fireTurn = false;
			waterTurn = false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if(isOutOfBounds(xi,yi) || isOutOfBounds(xf,yf) || Math.abs(xf - xi) != Math.abs(yf - yi)){
			return false;
		}
		Piece p = this.pieceAt(xi, yi);
		Piece endP = this.pieceAt(xf, yf);

		if(p == null){
			return false;
		}
		else if(!p.isKing() && ((yf < yi) && p.isFire() || (yf > yi) && !p.isFire())){
				return false;
		}
		else{
			if(endP == null){
				return true;
			}
			else{
				if(p.isFire() == endP.isFire()){
					return false;
				}
				else return true;
			}
		}	
	}

	private static boolean isOutOfBounds(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height){
			return true;
		}
		else return false;
	}

	private void setFireTurn(boolean setTurn){
		fireTurn = setTurn;
	}
	
	private void setWaterTurn(boolean setTurn){
		waterTurn = setTurn;
	}

	private void drawPiece(Piece p, int x, int y){
		if(!isOutOfBounds(x, y) && p != null){
			switch(p.type){
				case "pawn":{
						    if(p.isFire()){
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
						    }
						    else{
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
						    }
						    break;
				}
				case "shield":{
						    if(p.isFire()){
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
						    }
						    else{
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
						    }
						    break;
				}
				case "bomb":{
						    if(p.isFire()){
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
						    }
						    else{
							    if(p.isKing()) StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
							    else StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
						    }
						    break;
				}
				default: {
						 System.out.println("unknown type: "+ p.type);
						 break;
				}
			}
		}
	}
}
