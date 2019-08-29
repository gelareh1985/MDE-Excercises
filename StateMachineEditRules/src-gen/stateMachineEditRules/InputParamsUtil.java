package stateMachineEditRules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputParamsUtil {
	
	public Integer inputNumber() {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(br.readLine());
        } catch(NumberFormatException | IOException nfe) {
            System.err.println("Invalid Format!");
        }
        return null;
	}
	
	public String inputString() {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch(IOException nfe) {
            System.err.println("Invalid Format!");
        }
        return null;
	}
	
	public String inputObject() {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch(IOException nfe) {
            System.err.println("Invalid Format!");
        }
        return null;
	}


}
