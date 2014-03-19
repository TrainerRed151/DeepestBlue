//Brian Pomerantz

import java.util.*;

public class Board {
	private Piece[][] b;
	private BrianReader reader;
	
	//Constructor for creating clones
	public Board(Piece[][] bd) {
		b = new Piece[8][8];
		
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				if (bd[r][c] instanceof Pawn) {
					b[r][c] = new Pawn(bd[r][c].getSide(), r, c);
				}
				
				else if (bd[r][c] instanceof Knight) {
					b[r][c] = new Knight(bd[r][c].getSide(), r, c);
				}
				
				else if (bd[r][c] instanceof Bishop) {
					b[r][c] = new Bishop(bd[r][c].getSide(), r, c);
				}
				
				else if (bd[r][c] instanceof Rook) {
					b[r][c] = new Rook(bd[r][c].getSide(), r, c);
				}
				
				else if (bd[r][c] instanceof Queen) {
					b[r][c] = new Queen(bd[r][c].getSide(), r, c);
				}
				
				else if (bd[r][c] instanceof King) {
					b[r][c] = new King(bd[r][c].getSide(), r, c, ((King) bd[r][c]).getCKS(), ((King) bd[r][c]).getCQS());
				}
				
				else {
					b[r][c] = new Null(r, c);
				}
			}
		}
	}
	
	//New game constructor
	public Board() {
		reader = new BrianReader();
		b = new Piece[8][8];
		
		b[0][0] = new Rook(true, (byte) 0, (byte) 0);
		b[0][1] = new Knight(true, (byte) 0, (byte) 1);
		b[0][2] = new Bishop(true, (byte) 0, (byte) 2);
		b[0][3] = new Queen(true, (byte) 0, (byte) 3);
		b[0][4] = new King(true, (byte) 0, (byte) 4);
		b[0][5] = new Bishop(true, (byte) 0, (byte) 5);
		b[0][6] = new Knight(true, (byte) 0, (byte) 6);
		b[0][7] = new Rook(true, (byte) 0, (byte) 7);
		
		for (byte i = 0; i < b[1].length; i++) {
			b[1][i] = new Pawn(true, (byte) 1, i);
		}
		
		b[7][0] = new Rook(false, (byte) 7, (byte) 0);
		b[7][1] = new Knight(false, (byte) 7, (byte) 1);
		b[7][2] = new Bishop(false, (byte) 7, (byte) 2);
		b[7][3] = new Queen(false, (byte) 7, (byte) 3);
		b[7][4] = new King(false, (byte) 7, (byte) 4);
		b[7][5] = new Bishop(false, (byte) 7, (byte) 5);
		b[7][6] = new Knight(false, (byte) 7, (byte) 6);
		b[7][7] = new Rook(false, (byte) 7, (byte) 7);
		
		for (byte i = 0; i < b[6].length; i++) {
			b[6][i] = new Pawn(false, (byte) 6, i);
		}
		
		for (byte r = 2; r <= 5; r++) {
			for (byte c = 0; c <= 7; c++) {
				b[r][c] = new Null(r, c);
			}
		}
	}
	
	//Displays graphical representaion of board
	public void display() {
		System.out.println("   ---------------------------------------");
		
		for (byte r = (byte) (b.length - 1); r >= 0; r--) {
			System.out.print(r + 1);
			
			for (byte c = 0; c < b[r].length; c++) {
				System.out.print(" | " + b[r][c]);
			}
			
			System.out.println(" |");
			System.out.println("   ---------------------------------------");
		}
		
		System.out.println("    a    b    c    d    e    f    g    h");
	}
	
	//Inputs: Move as string, e.g., Nb1-c3 (side used for castling only)
	//Output: Updated board
	public Board move(String s, boolean si) {		
		
		//Queen-side castling
		if (s.length() >= 5 && s.substring(0, 5).equals("O-O-O")) {
			
			Piece tempKing;
			Piece tempRook;
			Piece tempNull1;
			Piece tempNull2;
			
			if (si) {
				((King) b[0][4]).castle();
				tempKing = b[0][4];
				tempRook = b[0][0];
				tempNull1 = b[0][2];
				tempNull2 = b[0][3];
				
				b[0][2] = tempKing;
				b[0][3] = tempRook;
				b[0][2].setPosition((byte) 0, (byte) 2);
				b[0][3].setPosition((byte) 0, (byte) 3);
				
				b[0][0] = tempNull1;
				b[0][4] = tempNull2;
				b[0][0].setPosition((byte) 0, (byte) 0);
				b[0][4].setPosition((byte) 0, (byte) 4);
			}
			
			else {
				((King) b[7][4]).castle();
				tempKing = b[7][4];
				tempRook = b[7][0];
				tempNull1 = b[7][2];
				tempNull2 = b[7][3];
				
				b[7][2] = tempKing;
				b[7][3] = tempRook;
				b[7][2].setPosition((byte) 7, (byte) 2);
				b[7][3].setPosition((byte) 7, (byte) 3);
				
				b[7][0] = tempNull1;
				b[7][4] = tempNull2;
				b[7][0].setPosition((byte) 7, (byte) 0);
				b[7][4].setPosition((byte) 7, (byte) 4);
			}
			
			return this;
		}
		
		//King-side castling
		if (s.substring(0, 3).equals("O-O")) {
			Piece tempKing;
			Piece tempRook;
			Piece tempNull1;
			Piece tempNull2;
			
			if (si) {
				((King) b[0][4]).castle();
				
				tempKing = b[0][4];
				tempRook = b[0][7];
				tempNull1 = b[0][5];
				tempNull2 = b[0][6];
				
				b[0][6] = tempKing;
				b[0][5] = tempRook;
				b[0][6].setPosition((byte) 0, (byte) 6);
				b[0][5].setPosition((byte) 0, (byte) 5);
				
				b[0][7] = tempNull1;
				b[0][4] = tempNull2;
				b[0][7].setPosition((byte) 0, (byte) 7);
				b[0][4].setPosition((byte) 0, (byte) 4);
			}
			
			else {
				((King) b[7][4]).castle();
				
				tempKing = b[7][4];
				tempRook = b[7][7];
				tempNull1 = b[7][5];
				tempNull2 = b[7][6];
				
				b[7][6] = tempKing;
				b[7][5] = tempRook;
				b[7][6].setPosition((byte) 7, (byte) 6);
				b[7][5].setPosition((byte) 7, (byte) 5);
				
				b[7][7] = tempNull1;
				b[7][4] = tempNull2;
				b[7][7].setPosition((byte) 7, (byte) 7);
				b[7][4].setPosition((byte) 7, (byte) 4);
			}
			
			return this;
		}
		
		byte fR, fC, tR, tC;
		
		fR = (byte) (Byte.parseByte(s.substring(2, 3)) - 1);
		tR = (byte) (Byte.parseByte(s.substring(5, 6)) - 1);
		
		if (s.charAt(1) == 'a') {
			fC = 0;
		}
		
		else if (s.charAt(1) == 'b') {
			fC = 1;
		}
		
		else if (s.charAt(1) == 'c') {
			fC = 2;
		}
		
		else if (s.charAt(1) == 'd') {
			fC = 3;
		}
		
		else if (s.charAt(1) == 'e') {
			fC = 4;
		}
		
		else if (s.charAt(1) == 'f') {
			fC = 5;
		}
		
		else if (s.charAt(1) == 'g') {
			fC = 6;
		}
		
		else if (s.charAt(1) == 'h') {
			fC = 7;
		}
		
		else {
			System.out.println("Invalid Entry");
			return this;
		}
		
		if (s.charAt(4) == 'a') {
			tC = 0;
		}
		
		else if (s.charAt(4) == 'b') {
			tC = 1;
		}
		
		else if (s.charAt(4) == 'c') {
			tC = 2;
		}
		
		else if (s.charAt(4) == 'd') {
			tC = 3;
		}
		
		else if (s.charAt(4) == 'e') {
			tC = 4;
		}
		
		else if (s.charAt(4) == 'f') {
			tC = 5;
		}
		
		else if (s.charAt(4) == 'g') {
			tC = 6;
		}
		
		else if (s.charAt(4) == 'h') {
			tC = 7;
		}
		
		else {
			System.out.println("Invalid Entry");
			reader.pause();
			return this;
		}
		
		if (b[fR][fC] instanceof King) {
			((King) b[fR][fC]).cantC(0);
		}
		
		if (b[fR][fC] instanceof Rook) {
			if (si) {
				if (fR == 0 && fC == 0) {
					getKing(si).cantC(-1);
				}
				
				else if (fR == 0 && fC == 7) {
					getKing(si).cantC(1);
				}
			}
			
			else {
				if (fR == 7 && fC == 0) {
					getKing(si).cantC(-1);
				}
				
				else if (fR == 7 && fC == 7) {
					getKing(si).cantC(1);
				}
			}
		}
		
		b[tR][tC] = b[fR][fC];
		b[tR][tC].setPosition(tR, tC);
		b[fR][fC] = new Null(fR, fC);
		
		if (s.length() >= 8 && s.charAt(6) == '=') {
			char p = s.charAt(7);
			
			if (p == 'N') {
				b[tR][tC] = new Knight(si, tR, tC);
			}
		
			else if (p == 'B') {
				b[tR][tC] = new Bishop(si, tR, tC);
			}
			
			else if (p == 'R') {
				b[tR][tC] = new Rook(si, tR, tC);
			}
			
			else if (p == 'Q') {
				b[tR][tC] = new Queen(si, tR, tC);
			}
		}

		return this;
	}
	
	//Input: Side
	//Output: Arraylist of all legal moves for side
	public ArrayList<String> legalMoves(boolean side) {
		ArrayList<String> moveList = new ArrayList<String>();
		char fTemp = ' ', tTemp = ' ';
		String tempMove;
		update();
		
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				if (b[r][c].getSide() == side && !(b[r][c] instanceof Null)) {
					if (c == 0) {
						fTemp = 'a';
					}
					
					else if (c == 1) {
						fTemp = 'b';
					}
					
					else if (c == 2) {
						fTemp = 'c';
					}
					
					else if (c == 3) {
						fTemp = 'd';
					}
					
					else if (c == 4) {
						fTemp = 'e';
					}
					
					else if (c == 5) {
						fTemp = 'f';
					}
					
					else if (c == 6) {
						fTemp = 'g';
					}
					
					else if (c == 7) {
						fTemp = 'h';
					}
					
					if (b[r][c] instanceof King) {
						if (side) {
							if (b[0][5] instanceof Null && b[0][6] instanceof Null && ((King) b[r][c]).getCKS()) {
								moveList.add("O-O");
							}
							
							if (b[0][1] instanceof Null && b[0][2] instanceof Null && ((King) b[r][c]).getCQS()) {
								moveList.add("O-O-O");
							}
						}
						
						else {
							if (b[7][5] instanceof Null && b[7][6] instanceof Null && ((King) b[r][c]).getCKS()) {
								moveList.add("O-O");
							}
							
							if (b[7][1] instanceof Null && b[7][2] instanceof Null && ((King) b[r][c]).getCQS()) {
								moveList.add("O-O-O");
							}
						}
					}
					
					for (Piece p : b[r][c].getMoves()) {
						if (p.getFile() == 0) {
							tTemp = 'a';
						}
						
						else if (p.getFile() == 1) {
							tTemp = 'b';
						}
						
						else if (p.getFile() == 2) {
							tTemp = 'c';
						}
						
						else if (p.getFile() == 3) {
							tTemp = 'd';
						}
						
						else if (p.getFile() == 4) {
							tTemp = 'e';
						}
						
						else if (p.getFile() == 5) {
							tTemp = 'f';
						}
						
						else if (p.getFile() == 6) {
							tTemp = 'g';
						}
						
						else if (p.getFile() == 7) {
							tTemp = 'h';
						}
						
						tempMove = b[r][c].getSymbol() + "" + fTemp + "" + (b[r][c].getRank()+1) + '-' + tTemp + (p.getRank()+1);
						Board nBD = new Board(getBoard());
						nBD.move(tempMove, side);
						nBD.update();
						
						if (!(nBD.check(!side))) {
							//Promotion
							if (side && p.getRank() == 7 && b[r][c] instanceof Pawn) {
								moveList.add(tempMove + "=Q");
								moveList.add(tempMove + "=R");
								moveList.add(tempMove + "=N");
								moveList.add(tempMove + "=B");
							}
							
							else if (!side && p.getRank() == 0 && b[r][c] instanceof Pawn) {
								moveList.add(tempMove + "=Q");
								moveList.add(tempMove + "=R");
								moveList.add(tempMove + "=N");
								moveList.add(tempMove + "=B");
							}
							
							else {
								moveList.add(tempMove);
							}
						}
					}
				}
			}
		}
		
		return moveList;
	}
	
	public Piece[][] getBoard() {
		return b;
	}
	
	//Updates move lists of pieces
	public void update() {
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				b[r][c].update(this);
			}
		}
	}
	
	//Calculates score of current position
	public byte score() {
		byte score = 0;
		
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				if (b[r][c].getSide()) {
					score += (b[r][c]).getValue();
				}
				
				else {
					score -= (b[r][c]).getValue();
				}
			}
		}
		
		return score;
	}
	
	public boolean isValid(int r, int c) {
		return r >= 0 && r < b.length && c >= 0 && c < b[r].length;
	}
	
	public void setBoard(Piece[][] bd) {
		b = bd;
	}
	
	private King getKing(boolean si) {
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				if (b[r][c] instanceof King && b[r][c].getSide() == si) {
					return (King) b[r][c];
				}
			}
		}
		
		return null;
	}
	
	public boolean isLegalMove(Boolean si, String s) {
		s = s.replace("+", "");
		s = s.replace("#", "");
		
		return legalMoves(si).indexOf(s) != -1;
	}
	
	//Determines if passed side is checking opponent
	public boolean check(Boolean si) {
		for (byte r = 0; r < b.length; r++) {
			for (byte c = 0; c < b[r].length; c++) {
				if (b[r][c].getSide() == si && !(b[r][c] instanceof Null)) {
					for (Piece p : b[r][c].getAttack()) {
						if (p instanceof King) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	//Determines if passed side has mated opponent
	public boolean checkmate(Boolean si) {
		if (!check(si)) {
			return false;
		}
		
		for (String s : legalMoves(!si)) {
			Board nBD = new Board(getBoard());
			nBD.move(s, !si);
			nBD.update();
			if (!(nBD.check(si))) {
				return false;
			}
		}
		//System.out.println("Mate Test");
		return true;
	}
	
	//Converts FEN format string into board
	public static Board fen(String str) {
		Piece[][] b = new Piece[8][8];
		byte r = 7, c = 0;
		String[] s = str.split(" ");
		
		for (int i = 0; i < s[0].length(); i++) {
			if (s[0].charAt(i) == '/') {
				r--;
				c = -1;
			}
			
			else if (s[0].charAt(i) == 'P') {
				b[r][c] = new Pawn(true, r, c);
			}
			
			else if (s[0].charAt(i) == 'N') {
				b[r][c] = new Knight(true, r, c);
			}
			
			else if (s[0].charAt(i) == 'B') {
				b[r][c] = new Bishop(true, r, c);
			}
			
			else if (s[0].charAt(i) == 'R') {
				b[r][c] = new Rook(true, r, c);
			}
			
			else if (s[0].charAt(i) == 'Q') {
				b[r][c] = new Queen(true, r, c);
			}
			
			else if (s[0].charAt(i) == 'K') {
				b[r][c] = new King(true, r, c, s[1].charAt(0)=='1', s[1].charAt(1)=='1');
			}
			
			else if (s[0].charAt(i) == 'p') {
				b[r][c] = new Pawn(false, r, c);
			}
			
			else if (s[0].charAt(i) == 'n') {
				b[r][c] = new Knight(false, r, c);
			}
			
			else if (s[0].charAt(i) == 'b') {
				b[r][c] = new Bishop(false, r, c);
			}
			
			else if (s[0].charAt(i) == 'r') {
				b[r][c] = new Rook(false, r, c);
			}
			
			else if (s[0].charAt(i) == 'q') {
				b[r][c] = new Queen(false, r, c);
			}
			
			else if (s[0].charAt(i) == 'k') {
				b[r][c] = new King(false, r, c, s[1].charAt(2)=='1', s[1].charAt(3)=='1');
			}
			
			else {
				for (byte j = 0; j < Byte.parseByte(s[0].charAt(i)+""); j++) {
					b[r][c+j] = new Null(r, (byte)(c+j));
				}
				
				c += Byte.parseByte(s[0].charAt(i)+"") - 1;
			}
			
			c++;
		}
		
		return new Board(b);
	}
}