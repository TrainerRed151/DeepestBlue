//Brian Pomerantz

import java.io.*;

public class runChess {
	public static void main(String[] args) throws IOException {
//		int depth = 5;
//		if (args.length != 0) {
//			depth = Integer.parseInt(args[0]);
//		}
		
		int selection;
		BrianReader reader = new BrianReader();
		
		System.out.println("\nPerform: ");
		System.out.println("(F)EN");
		System.out.println("(T)est");
		selection = reader.readChar("\nSelection: ");
		
		AI ai;
		Board bd;
		String m;
		long startTime, endTime;
		
		//**********FEN**********
		if (selection == 'F' || selection == 'f') {
			int depth = reader.readInt("\nDepth: ");
			String fenIn = reader.readLine("\nFEN: ");
			boolean aiT = fenIn.charAt(0) == 'W';
			bd = Board.fen(fenIn.substring(2));
			
			ai = new AI(aiT, depth);
			
			cls();
			bd.display();
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			System.out.println((endTime - startTime) / 1.0e9);
			
			//bd.display();
			if (m.charAt(1) != '-'){bd.move(m, aiT);}
			bd.update();
			
			if (bd.checkmate(aiT)) {
				m = m + "#";
			}
			
			else if (bd.check(aiT)) {
				m = m + "+";
			}
			
			reader.readLine("\n" + m);			
			
			//cls();
			//bd.display();
			return;
		}
		
		//**********Tests**********
		else if (selection == 'T' || selection == 't') {
			System.out.println();
			
			double time;
			
			//Test 1 - Mate in 2
			bd = Board.fen("r5k1/pp3ppp/6q1/3r4/4Q3/8/PPP2PPP/4R1K1 0000");
			ai = new AI(true, 3);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Qe4-e8")) {
				System.out.println("Test 1: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 1: Failed");
			}
			
			//Test 2 - Avoid mate in 1
			bd = Board.fen("8/1p4kN/p7/2r5/8/RP5b/5P1P/7K 0000");
			ai = new AI(true, 3);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Ra3-a1")) {
				System.out.println("Test 2: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 2: Failed");
			}
			
			//Test 3 - Mate in 1 (from Test 2)
			ai = new AI(false, 3);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Rc5-c1")) {
				System.out.println("Test 3: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 3: Failed");
			}
			
			//Test 4 - Stalemate
			bd = Board.fen("k7/8/1QK5/8/8/8/8/8 0000");
			ai = new AI(false, 3);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals('\u00AB'+"-"+'\u00AB')) {
				System.out.println("Test 4: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 4: Failed");
			}
			
			//Test 5 - Difficult mate in 3
			bd = Board.fen("B7/8/3p1R2/2pR4/1p2kB2/8/1p1K4/qN5b 0000");
			ai = new AI(true, 5);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Kd2-e1")) {
				System.out.println("Test 5: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 5: Failed");
			}
			
			//Test 6 - Difficult mate in 3
			bd = Board.fen("8/8/8/6K1/4N3/6N1/5RP1/6k1 0000");
			ai = new AI(true, 5);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Rf2-e2")) {
				System.out.println("Test 6: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 6: Failed");
			}
			
			//Test 7 - Resignation
			bd = Board.fen("8/8/8/6K1/4N3/6N1/4R1P1/6k1 0000");
			ai = new AI(false, 4);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("1-0")) {
				System.out.println("Test 7: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 7: Failed");
			}
			
			bd = Board.fen("5q2/8/8/5k2/8/8/6PP/4K2R 1000");
			ai = new AI(true, 4);
			
			//Test 8 - Castling & Check
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("O-O")) {
				System.out.println("Test 8: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 8: Failed");
			}
			
			bd = Board.fen("8/8/8/8/8/5k2/5p1K/8 0000");
			ai = new AI(false, 3);
			
			//Test 9 - Promotion & Stalemate
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			if (m.equals("Pf2-f1=R")) {
				System.out.println("Test 9: Passed (" + time + "s)");
			}
			
			else {
				System.out.println("Test 9: Failed");
			}
			
			//Test 10 - Opening move
			bd = Board.fen("rnbqknbr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKNBR 1111");
			ai = new AI(true, 6);
			
			startTime = System.nanoTime();
			m = ai.move(bd);
			endTime = System.nanoTime();
			time = (endTime - startTime) / 1.0e9;
			
			System.out.println("Test 10: " + m + " (" + time + "s)");
			
			reader.readLine("\nComplete");
			return;
		}
		
		//Computer plays itself
		//(just for my own curiosity)
		else if (selection == 's') {
			int depth = 5;
			FileOutputStream fos = new FileOutputStream("sNot.txt");
			PrintStream ps = new PrintStream(fos);
			ps.println("Depth: " + depth);
			
			bd = new Board();
			AI white = new AI(true, depth);
			AI black = new AI(false, depth);
			
			int i = 1;
			double time;
			
			while (true) {
				startTime = System.nanoTime();
				m = white.move(bd);
				endTime = System.nanoTime();
				time = (endTime - startTime) / 1.0e9;
			
			
				if (m.charAt(1) != '-' || m.equals("O-O") || m.equals("O-O-O")) {
					bd.move(m, true);
				}
				
				else {
					ps.print(i + ".\t" + m);
					System.out.print(i + ".\t" + m);
					return;
				}
				
				bd.update();
			
				if (bd.checkmate(true)) {
					m = m + "#";
					ps.print(i + ".\t" + m + " (" + time + "s)");
					System.out.print(i + ".\t" + m + " (" + time + "s)");
					return;
				}
			
				else if (bd.check(true)) {
					m = m + "+";
				}
				
				ps.print(i + ".\t" + m + " (" + time + "s)");
				System.out.print(i + ".\t" + m + " (" + time + "s)");
				
				
				
				startTime = System.nanoTime();
				m = black.move(bd);
				endTime = System.nanoTime();
				time = (endTime - startTime) / 1.0e9;
			
			
				if (m.charAt(1) != '-') {
					bd.move(m, false);
				}
				
				else {
					ps.println("\t" + m + " (" + time + "s)");
					System.out.println("\t" + m + " (" + time + "s)");
					return;
				}
				
				bd.update();
			
				if (bd.checkmate(false)) {
					m = m + "#";
					ps.println("\t" + m + " (" + time + "s)");
					System.out.println("\t" + m + " (" + time + "s)");
					return;
				}
			
				else if (bd.check(false)) {
					m = m + "+";
				}
				
				ps.println("\t" + m + " (" + time + "s)");
				System.out.println("\t" + m + " (" + time + "s)");
				
				i++;
			}
		}
		

		//**********Play agains computer**********
		
//		bd = new Board();
//			
//		while (true) {
//			ai = new AI(!sideP, depth);
//			cls();
//			bd.display();
//			
//			startTime = System.nanoTime();
//			w = ai.move(bd);
//			endTime = System.nanoTime();
//			System.out.println((endTime - startTime) / 1.0e9);
//			
//			bd.display();
//			
//			s = reader.readLine("\n" + w);
//					
//			if (s.equals("manual override")) {
//				w = reader.readLine("\nWhite's Move: ");
//			}
//					
//			else {
//				w += s;
//			}
//					
//			bd.move(w, true);
//					
//			cls();
//			bd.display();
//			System.out.println(bd.score());
//			b = reader.readLine("\nBlack's Move: ");
//			if (b.equals("exit")) {return;}
//			bd.move(b, false);
//		}
	}
	
	public static void cls() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
}