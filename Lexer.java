import java.util.ArrayList;
import java.io.*;

public class Lexer
{
	public static ArrayList initializeVariables()
	{
		/*Variables Setup*/
		State Initial = new State("initial");
		State Integer = new State("integer");
		State Float = new State("float");
		State Operand = new State("ops");
		State Identifier = new State("identifier");
		State String = new State("string");
		State Final = new State("final");
		
		ArrayList<State> states = new ArrayList<State>();
		states.add(Initial);
		states.add(Integer);
		states.add(Float);
		states.add(Operand);
		states.add(Identifier);
		states.add(String);
		states.add(Final);
		
		Condition Alpha = new Condition("alpha");//a-zA-Z
		Condition Digit = new Condition("digit");//0-9
		Condition White = new Condition("white");//whitespace characters
		Condition Quote = new Condition("quote");// "
		Condition Op = new Condition("operand");// =, +=, +, ++, *, *=
		Condition Paren = new Condition("paren"); // (, )
		Condition Semicolon = new Condition("Semicolon");// ;
		Condition Decimal = new Condition("decimal");// .
		Condition All = new Condition("all");// all are excepted except the / which is handles in StateMachine as special case
		Condition eof = new Condition("eof");// EOF
		
		ArrayList<Condition> conditions = new ArrayList<Condition>();
		conditions.add(Alpha);
		conditions.add(Digit);
		conditions.add(White);
		conditions.add(Op);
		conditions.add(Paren);
		conditions.add(Semicolon);
		conditions.add(Decimal);
		conditions.add(All);
		conditions.add(eof);
		
		
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
		stringRelated.add(All);
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
		//Transition FromOperandtoOperand = new Transition(Operand, operandRelated, Operand);
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
		//transitions.add(FromOperandtoOperand);
		transitions.add(FromOperandtoIdentifier);
		transitions.add(FromOperandtoString);
		
		transitions.add(FromIdentifiertoInitial);
		transitions.add(FromIdentifiertoOperand);
		transitions.add(FromIdentifiertoIdentifier);
		transitions.add(FromIdentifiertoString);
		
		transitions.add(FromStringtoInitial);
		transitions.add(FromStringtoString);
		/*End Variables*/
		
		ArrayList returnArgs = new ArrayList();
		returnArgs.add(states);
		returnArgs.add(conditions);
		returnArgs.add(transitions);
		
		return returnArgs;
		
	}
	
	
	
	
	
	public static StateMachine checkCondition(StateMachine SM, ArrayList<Condition> conditions, String [] regex, State currentState, String currentChar)
	{
		if(currentState.state == "string" && !currentChar.matches(regex[3]))//Special case for when in the string state
		{
			SM.apply(conditions.get(7));
		}
		else if(currentChar.matches(regex[3]))//If character is a quotation mark we have entered or left a string
		{
			SM.apply(conditions.get(3));
		}
		else if(currentChar.matches(regex[0]))//Could be an identifier or in a string
		{
			SM.apply(conditions.get(0));
		}				
		else if(currentChar.matches(regex[1]))//could be entering into a integer/ float or in a string
		{
			SM.apply(conditions.get(1));
		}
		else if(currentChar.matches(regex[2]))//Should return back to initial state unless in string state
		{
			SM.apply(conditions.get(2));
		}
		
		else if(currentChar.matches(regex[4]))
		{
			SM.apply(conditions.get(4));
		}
		else
		{
			System.out.println("Unrecognized symbol: "+currentChar);
		}
		
		return SM;
		
	}


	public static void main(String args[]) throws IOException, FileNotFoundException
	{
		
		ArrayList objects = initializeVariables();
		ArrayList<State> states = (ArrayList<State>)objects.get(0);
		ArrayList<Condition> conditions = (ArrayList<Condition>)objects.get(1);
		ArrayList<Transition> transitions = (ArrayList<Transition>)objects.get(2);
		
		
		
		String ALPHA = "[A-Za-z]";
		String DIGIT = "[0-9]";
		String WHITE = "[\n\\ \t\b\012]";
		String QUOTE = "\"";
		String PAREN = "[()]";
		
		String [] regex = {ALPHA, DIGIT, WHITE, QUOTE, PAREN};
		
		StateMachine SM = new StateMachine(states.get(0), transitions);//Initial, transitions;
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(args[0]));
		String currentLine = null;
		
		while((currentLine = in.readLine()) != null)
		{
			String currentToken ="";
			String tokenType = "";
			int tokenLength = 0;		
			char [] chars = currentLine.toCharArray();
			
			for(int i = 0; i<chars.length; i++)
			{
				String currentChar = ""+chars[i];
				
				SM = checkCondition(SM, conditions, regex,  SM.current, currentChar);
				State current = SM.current;
				
				StateMachine peeker = null;
				
				if( i + 1 < chars.length)
				{
					peeker = new StateMachine(SM.current, transitions);
					peeker = checkCondition(peeker,conditions, regex, SM.current, ""+chars[i+1]);
					if(current != peeker.current)
					{
						currentToken +=currentChar;
						tokenType = current.state;
						System.out.println("<"+tokenType+" "+currentToken+">");//found token
						currentToken = "";
						tokenType = "";
					}
					else
					{
						currentToken +=currentChar;
						tokenType = current.state;
					}
				}
				else//Last char on line. So that is our token found.
				{
					currentToken +=currentChar;
					tokenType = current.state;
					System.out.println("<"+tokenType+" "+currentToken+">");//found token
				}
			}
			
			
		} 
		
		
		
		
		
		
	}

}
