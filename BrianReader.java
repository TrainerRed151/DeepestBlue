//Brian Pomerantz

import java.io.*;

public class BrianReader {

	public BrianReader() {
		reader = new InputStreamReader(System.in);
		buffer = new BufferedReader(reader);
	}

	public void pause() {
		System.out.print("\nPress Enter to continue . . . ");
		try	{
			buffer.readLine();
		}
		catch(Exception e)	{
			//System.exit(0);
		}
	}

	public int readInt(String prompt) {
		int value = 0;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine().trim();
				value = (new Integer(s)).intValue();
				break;
			}
			catch(Exception e) {
				System.out.println("\n\nError: your input doesn't represent a valid integer value");
				pause();
				System.out.println();
				//System.exit(0);
			}
		}
		return value;
	}

	public int readInt() {
		return readInt("");
	}

	public double readDouble(String prompt) {
		double value = 0.0D;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine().trim();
				value = (new Double(s)).doubleValue();
				break;
			}
			catch(Exception e) {
				System.out.println("\n\nError: your input doesn't represent a valid double value");
				pause();
				System.out.println();
				//System.exit(0);
			}
		}
		return value;
	}

	public double readDouble() {
		return readDouble("");
	}

	public char readChar(String prompt) {
		int value = 0;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine();
				s = String.valueOf(String.valueOf(s)).concat("?");
				value = s.charAt(0);
				break;
			}
			catch(Exception e) {
				System.out.println(String.valueOf(String.valueOf((new StringBuffer("\n\nError in method readChar:\n")).append(e.toString()).append("\n"))));
				pause();
				//System.exit(0);
			}
		}
		return (char)value;
	}

	public char readChar() {
		return readChar("");
	}

	public String readLine(String prompt) {
		String value = "";
		System.out.print(prompt);
		try {
			value = buffer.readLine();
		}
		catch(Exception e) {
			System.out.println(String.valueOf(String.valueOf((new StringBuffer("\n\nError in Console.readLine\n")).append(e.toString()).append("\n"))));
			pause();
			//System.exit(0);
		}
		return value;
	}

	public String readLine() {
		return readLine("");
	}

	public void cls() {
		Clear console = new Clear();
		console.cls();
	}

	private InputStreamReader reader;
	private BufferedReader buffer;
}

class Clear {
	public native void cls();
	static {
		System.loadLibrary("clsdll");
	}
}