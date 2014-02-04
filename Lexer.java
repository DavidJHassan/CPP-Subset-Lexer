import java.util.ArrayList;
import java.io.*;

public class Lexer
{

	public static void main(String args[]) throws IOException, FileNotFoundException
	{
		/*Variables Setup*/
		State Initial = new State("initial");
		State Integer = new State("integer");
		State Float = new State("float");
		State Operand = new State("ops");
		State Identifier = new State("identifier");
		State String = new State("string");
		State Final = new State("final");
		
		Condition Alpha = new Condition("alpha");//a-zA-Z
		Condition Digit = new Condition("digit");//0-9
		Condition White = new Condition("white");//whitespace characters
		Condition Quote = new Condition("quote");// "
		Condition Op = new Condition("operand");// =, +=, +, ++, *, *=
		Condition Paren = new Condition("paren"); // (, )
		Condition Semicolon = new Condition("Semicolon");// ;
		Condition Decimal = new Condition("decimal");// .
		Condition eof = new Condition("eof");// EOF
		
		
		ArrayList<Condition> initialRelated = new ArrayList<Condition>();
		ArrayList<Condition> integerRelated = new ArrayList<Condition>();
		ArrayList<Condition> floatRelated = new ArrayList<Condition>();
		ArrayList<Condition> toFloatRelated = new ArrayList<Condition>();
		ArrayList<Condition> operandRelated = new ArrayList<Condition>();
		ArrayList<Condition> stringRelated = new ArrayList<Condition>();
		ArrayList<Condition> toStringRelated = new ArrayList<Condition>();
		ArrayList<Condition> identifierRelated = new ArrayList<Condition>();
		ArrayList<Condition> toIdentifierRelated = new ArrayList<Condition>();
		
		initialRelated.add(White);
		initialRelated.add(Semicolon);
		initialRelated.add(Paren);
		integerRelated.add(Digit);
		floatRelated.add(Digit);
		toFloatRelated.add(Decimal);
		operandRelated.add(Op);
		identifierRelated.add(Digit);
		identifierRelated.add(Alpha);
		toIdentifierRelated.add(Alpha);
		stringRelated.add(Alpha);
		stringRelated.add(Digit);
		stringRelated.add(White);
		stringRelated.add(Quote);
		stringRelated.add(Op);
		stringRelated.add(Paren);
		stringRelated.add(Decimal);
		stringRelated.add(Semicolon);
		toStringRelated.add(Quote);

		ArrayList<Transition> transitions = new ArrayList<Transition>();
		
		Transition FromInitialtoInitial = new Transition(Initial, initialRelated, Initial);
		Transition FromInitialtoInteger = new Transition(Initial, integerRelated, Integer);
		Transition FromInitialtoOperand = new Transition(Initial, operandRelated, Operand);
		Transition FromInitialtoIdentifier = new Transition(Initial, toIdentifierRelated, Identifier);
		Transition FromInitialtoString = new Transition(Initial, toStringRelated, String);
		
		Transition FromIntegertoInitial = new Transition(Integer, initialRelated, Initial);
		Transition FromIntegertoInteger = new Transition(Integer, integerRelated, Integer);
		Transition FromIntegertoFloat = new Transition(Integer, toFloatRelated, Float);
		Transition FromIntegertoOperand = new Transition(Integer, operandRelated, Operand);
		Transition FromIntegertoIdentifier = new Transition(Integer, toIdentifierRelated, Identifier);
		Transition FromIntegertoString = new Transition(Integer, toStringRelated, String);
		
		Transition FromFloattoInitial = new Transition(Float, initialRelated, Initial);
		Transition FromFloattoFloat = new Transition(Float, floatRelated, Float);
		Transition FromFloattoOperand = new Transition(Float, operandRelated, Operand);
		Transition FromFloattoIdentifier = new Transition(Float, toIdentifierRelated, Identifier);
		Transition FromFloattoString = new Transition(Float, toStringRelated, String);
		
		Transition FromOperandtoInitial = new Transition(Operand, initialRelated, Initial);
		Transition FromOperandtoInteger = new Transition(Operand, integerRelated, Integer);
		Transition FromOperandtoOperand = new Transition(Operand, operandRelated, Operand);
		Transition FromOperandtoIdentifier = new Transition(Operand, toIdentifierRelated, Identifier);
		Transition FromOperandtoString = new Transition(Operand, toStringRelated, String);
		
		Transition FromIdentifiertoInitial = new Transition(Identifier, initialRelated, Initial);
		Transition FromIdentifiertoOperand = new Transition(Identifier, operandRelated, Operand);
		Transition FromIdentifiertoIdentifier = new Transition(Identifier, identifierRelated, Identifier);
		Transition FromIdentifiertoString = new Transition(Identifier, toStringRelated, String);
		
		Transition FromStringtoInitial = new Transition(String, toStringRelated, Initial);
		Transition FromStringtoString = new Transition(String, stringRelated, Initial);
		
		transitions.add(FromInitialtoInitial);
		transitions.add(FromInitialtoInteger);
		transitions.add(FromInitialtoOperand);
		transitions.add(FromInitialtoIdentifier);
		transitions.add(FromInitialtoString);
		
		transitions.add(FromIntegertoInitial);
		transitions.add(FromIntegertoInteger);
		transitions.add(FromIntegertoFloat);
		transitions.add(FromIntegertoOperand);
		transitions.add(FromIntegertoIdentifier);
		transitions.add(FromIntegertoString);
		
		transitions.add(FromFloattoInitial);
		transitions.add(FromFloattoFloat);
		transitions.add(FromFloattoOperand);
		transitions.add(FromFloattoIdentifier);
		transitions.add(FromIntegertoString);
		
		transitions.add(FromOperandtoInitial);
		transitions.add(FromOperandtoInteger);
		transitions.add(FromOperandtoOperand);
		transitions.add(FromOperandtoIdentifier);
		transitions.add(FromOperandtoString);
		
		transitions.add(FromIdentifiertoInitial);
		transitions.add(FromIdentifiertoOperand);
		transitions.add(FromIdentifiertoIdentifier);
		transitions.add(FromIdentifiertoString);
		
		transitions.add(FromStringtoInitial);
		transitions.add(FromStringtoString);
		/*End Variables*/
		
		
		
		
		String ALPHA = "[A-Za-z]";
		String DIGIT = "[0-9]";
		String WHITE = "[\n\\ \t\b\012]";
		
		StateMachine SM = new StateMachine(Initial, transitions);
		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(args[0]));
		
		String currentLine = null;
		
		while((currentLine = in.readLine()) != null)
		{
			String currentToken ="";
			String tokenType = "";
			int tokenLength = 0;		
			char [] chars = currentLine.toCharArray();
			State current = SM.current;
			
			for(int i = 0; i<chars.length; i++)
			{
				String currentChar = ""+chars[i];
				//System.out.println(currentChar);
				
				if(currentChar.matches(ALPHA))//Could be an identifier or in a string
				{
					if(SM.current.state == "initial")
					{
						System.out.println(SM.current.state);
						SM.apply(toIdentifierRelated);
						System.out.println(SM.current.state);
					}
					else if(SM.current.state == "identifier")
					{
						SM.apply(identifierRelated);
					}
					else if(SM.current.state == "string")
					{
						SM.apply(stringRelated);
					}
				}
				
				if(currentChar.matches(WHITE))
				{
					if(SM.current.state != "string")
					{
						System.out.println(SM.current.state);
						SM.apply(initialRelated);
						System.out.println(SM.current.state);
					}
				}
				
		
				if(SM.current != null)
				{
					if(current != SM.current && current.state != "initial")
					{
						tokenType = current.state;
						currentToken +=currentChar;
						System.out.println("<"+tokenType+" "+currentToken+">");//found token
					}
				}
				else
				{
					System.out.println("Error");
					SM.current = Initial;
				}
				
				
			}
			
			
		} 
		
		
		
		
		
		
		
	}

}
