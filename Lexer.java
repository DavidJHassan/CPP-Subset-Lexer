import java.util.ArrayList;
import java.io.*;

public class Lexer
{
	public static ArrayList initializeVariables()
	{
		/*Variables Setup*/
		State Initial = new State("initial",true);
		State Integer = new State("integer",true);
		State Equals = new State("equals",true);
		State Plus = new State("plus",true);
		State Times = new State("times",true);
		State Semi = new State("semicolon",true);
		State Identifier = new State("identifier",true);
		State Decimal = new State("decimal",true);
		State Lparen = new state("lparen",true);
		State Rparen = new State("rparen",true);
		State Quote = new State("quote",true);
		State Float = new State("float");
		State String = new State)"string");
		State PlusPlus = new State("plusplus");
		State PlusEquals = new State("plusequals");
		State TimesEquals = new State("timesequals");
		
		ArrayList<State> states = new ArrayList<State>();
		states.add(Initial);
		states.add(Integer);
		states.add(Equals);
		states.add(Plus);
		states.add(Times);
		states.add(Semi);
		states.add(Identifier);
		states.add(Decimal);
		states.add(Lparen);
		states.add(Rparen);
		states.add(Quote);
		states.add(Float);
		states.add(String);
		states.add(PlusPlus);
		states.add(PlusEquals);
		states.add(TimesEquals);

		Condition white = new Condition("white");//whitespace characters
		Condition alpha = new Condition("alpha");//a-zA-Z
		Condition digit = new Condition("digit");//0-9
		Condition lparen = new Condition("lparen"); // (
		Condition rparen = new Condition("lparen"); // (
		Condition equals = new Condition("equals");// =
		Condition plus = new Condition("plus");// +
		Condition times = new Condition("times");// *
		Condition semi = new Condition("semi");// ;
		Condition quote = new Condition("quote");// "
		Condition decimal = new Condition("decimal");// .
		Condition all = new Condition("all");// all are excepted except the / which is handles in StateMachine as special case

		
		ArrayList<Condition> conditions = new ArrayList<Condition>();
		conditions.add(white);
		conditions.add(alpha);
		conditions.add(digit);
		conditions.add(lparen);
		conditions.add(rparens);
		conditions.add(equals);
		conditions.add(plus);
		conditions.add(times);
		conditions.add(semi);
		conditions.add(quote);
		conditions.add(decimal);
		conditions.add(all);
		
		ArrayList<Condition> initialRelated = new ArrayList<Condition>();
		ArrayList<Condition> integerRelated = new ArrayList<Condition>();
		ArrayList<Condition> equalsRelated = new ArrayList<Condition>();
		ArrayList<Condition> plusRelated = new ArrayList<Condition>();
		ArrayList<Condition> timesRelated = new ArrayList<Condition>();
		ArrayList<Condition> semiRelated = new ArrayList<Condition>();
		ArrayList<Condition> toIdentifierRelated = new ArrayList<Condition>();
		ArrayList<Condition> identifierRelated = new ArrayList<Condition>();
		ArrayList<Condition> decimalRelated = new ArrayList<Condition>();
		ArrayList<Condition> lparenRelated = new ArrayList<Condition>();
		ArrayList<Condition> rparenRelated = new ArrayList<Condition>();
		ArrayList<Condition> quoteRelated = new ArrayList<Condition>();
		ArrayList<Condition> toFloatRelated = new ArrayList<Condition>();
		ArrayList<Condition> floatRelated = new ArrayList<Condition>();
		ArrayList<Condition> stringRelated = new ArrayList<Condition>();
		ArrayList<Condition> plusplusRelated= new ArrayList<Condition>();
		ArrayList<Condition> plusequalsRelated= new ArrayList<Condition>();
		ArrayList<Condition> timesequalsRelated= new ArrayList<Condition>();
		
		initialRelated.add(white);
		integerRelated.add(digit);
		equalsRelated.add(equals);
		plusRelated.add(plus);
		timesRelated.add(times);
		semiRelated.add(semi);
		toIdentifierRelated.add(alpha);
		identifierRelated.add(digit);
		identifierRelated.add(alpha);
		decimalRelated.add(decimal);
		lparenRelated.add(lparen);
		rparenRelated.add(rparen);
		quoteRelated.add(quote);
		toFloatRelated.add(decimal);
		floatRelated.add(digit);
	    stringRelated.add(all);
	    plusplusRelated.add(plusplus);
	    plusequals.add(plusequals);
	    timesequals.add(timesequals);
	    
		Transition FromInitialtoInitial = new Transition(Initial, initialRelated, Initial);
		Transition FromInitialtoInteger = new Transition(Initial, integerRelated, Integer);
		Transition FromInitialtoEquals = new Transition(Initial, equalsRelated, Equals);
		Transition FromInitialtoPlus = new Transition(Initial, plusRelated, Plus);
		Transition FromInitialtoTimes = new Transition(Initial, timesRelated, Times);
		Transition FromInitialtoSemi = new Transition(Initial, semiRelated, Semi);
		Transition FromInitialtoIdentifier = new Transition(Initial, toIdentifierRelated, Identifier);
		Transition FromInitialtoDecimal = new Transition(Initial, decimalRelated, Decimal);
		Transition FromInitialtoLparen = new Transition(Initial, lparenRelated, Lparen);
		Transition FromInitialtoRparen = new Transition(Initial, rparenRelated, Rparen);
		Transition FromInitialtoQuote = new Transition(Initial, quoteRelated, Quote);
		
		Transition FromIntegertoInitial = new Transition(Integer, initialRelated, Initial);
		Transition FromIntegertoInteger = new Transition(Integer, integerRelated, Integer);
		Transition FromIntegertoEquals = new Transition(Integer, equalsRelated, Equals);
		Transition FromIntegertoPlus = new Transition(Integer, plusRelated, Plus);
		Transition FromIntegertoTimes = new Transition(Integer, timesRelated, Times);
		Transition FromIntegertoSemi = new Transition(Integer, semiRelated, Semi);
		Transition FromIntegertoIdentifier = new Transition(Integer, toIdentifierRelated, Identifier);
		Transition FromIntegertoLparen = new Transition(Integer, lparenRelated, Lparen);
		Transition FromIntegertoRparen = new Transition(Integer, rparenRelated, Rparen);
		Transition FromIntegertoQuote = new Transition(Integer,quoteRelated, Quote);
		Transition FromIntegertoFloat = new Transition(Integer, toFloatRelated, Float);
		
		Transition FromEqualstoInitial = new Transition(Equals, initialRelated, Initial);
		Transition FromEqualstoInteger = new Transition(Equals, integerRelated, Integer);
		Transition FromEqualstoEquals = new Transition(Equals, equalsRelated, Equals);
		Transition FromEqualstoPlus = new Transition(Equals, plusRelated, Plus);
		Transition FromEqualstoTimes = new Transition(Equals, timesRelated, Times);
		Transition FromEqualstoSemi = new Transition(Equals, semiRelated, Semi);
		Transition FromEqualstoIdentifier = new Transition(Equals, identifierRelated, Identifier);
		Transition FromEqualstoDecimal = new Transition(Equals, decimalRelated, Decimal);
		Transition FromEqualstoLparen = new Transition(Equals, lparenRelated, Lparen);
		Transition FromEqualstoRparen = new Transition(Equals, rparenRelated, Rparen);
		Transition FromEqualstoQuote = new Transition(Equals, quoteRelated, Quote);
		
		Transition FromPlustoInitial = new Transition(Plus, initialRelated, Initial);
		Transition FromPlustoInteger = new Transition(Plus, integerRelated, Integer);
		Transition FromPlustoTimes = new Transition(Plus, timesRelated, Times);
		Transition FromPlustoSemi = new Transition(Plus, semiRelated, Semi);
		Transition FromPlustoIdentifier = new Transition(Plus, toIdentifierRelated, Identifier);
		Transition FromPlustoDecimal = new Transition(Plus, decimalRelated, Decimal);
		Transition FromPlustoLparen = new Transition(Plus, lparenRelated, Lparen);
		Transition FromPlustoRparen = new Transition(Plus, rparenRelated, Rparen);
		Transition FromPlustoQuote = new Transition(Plus, quoteRelated, Quote);
		Transition FromPlustoPlusPlus = new Transition(Plus, plusRelated, PlusPlus);
		Transition FromPlustoPlusEquals = new Transition(Plus, equalsRelated, PlusEquals);
		
		
		
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
		Transition FromStringtoString = new Transition(String, stringRelated, String);
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
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
			SM.apply(conditions.get(8));
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
		else if(currentChar.matches(regex[6]))
		{
			SM.apply(conditions.get(6));
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
		String ALL = ".";
		String SEMI = ";";
		
		String [] regex = {ALPHA, DIGIT, WHITE, QUOTE, PAREN, ALL, SEMI};
		
		StateMachine SM = new StateMachine("SM", states.get(0), transitions);//Initial, transitions;
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
					
					peeker = new StateMachine("peeker", SM.current, transitions);
					peeker = checkCondition(peeker,conditions, regex, SM.current, ""+chars[i+1]);
					
					currentToken +=currentChar;
					tokenType = current.state;
					
					if(current != peeker.current)
					{
						if(current.state == "string")//Special case to stop peeker from leaving string state befor egrabbing the quotation mark	
						{
							i++;
							currentToken +=""+chars[i];
							tokenType = current.state;
							SM.current = states.get(0);
						}
						
						if(!currentToken.matches(WHITE))//make sure our token isnt initial state garbage **could change to not print if in initial state
							System.out.println("<"+tokenType+" "+currentToken+">");//found token
						currentToken = "";
						tokenType = "";
					}
					
				}
				else//Last char on line. So that is our token found.
				{
					currentToken +=currentChar;
					tokenType = current.state;
					System.out.println("<"+tokenType+" "+currentToken+">");//found token
				}
			}
			
			SM.current = states.get(0); //New line so set our state machine back to it's initial state	
		} 
	
	}

}
