import java.util.ArrayList;
import java.io.*;

public class Lexer
{
	public static ArrayList initializeVariables()//Setting up States, Conditions, Transitions and creating the Transition Table
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
		State Lparen = new State("lparen",true);
		State Rparen = new State("rparen",true);
		State Quote = new State("quote",true);
		State Float = new State("float");
		State String = new State("string");
		State PlusPlus = new State("plusplus");
		State PlusEquals = new State("plusequals");
		State TimesEquals = new State("timesequals");
		State ClosingQuote = new State("string");//the state is actually ClosingQuote however we call it a string for ease later on to distinguish if a token is a string or not
		
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
		states.add(ClosingQuote);

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
		conditions.add(rparen);
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
	    
	        stringRelated.add(white);
	        stringRelated.add(alpha);
	        stringRelated.add(digit);
	        stringRelated.add(lparen);
	        stringRelated.add(rparen);
	        stringRelated.add(equals);
	        stringRelated.add(plus);
	        stringRelated.add(times);
	        stringRelated.add(semi);
	        stringRelated.add(quote);
	        stringRelated.add(decimal);
	        stringRelated.add(all);
	    
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
		
		Transition FromTimestoInitial = new Transition(Times, initialRelated, Initial);
		Transition FromTimestoInteger = new Transition(Times, integerRelated, Integer);
		Transition FromTimestoPlus = new Transition(Times, plusRelated, Plus);
		Transition FromTimestoTimes = new Transition(Times, timesRelated, Times);
		Transition FromTimestoSemi = new Transition(Times, semiRelated, Semi);
		Transition FromTimestoIdentifier = new Transition(Times, toIdentifierRelated, Identifier);
		Transition FromTimestoDecimal = new Transition(Times, decimalRelated, Decimal);
		Transition FromTimestoLparen = new Transition(Times, lparenRelated, Lparen);
		Transition FromTimestoRparen = new Transition(Times, rparenRelated, Rparen);
		Transition FromTimestoQuote = new Transition(Times, quoteRelated, Quote);
		Transition FromTimestoTimesEquals = new Transition(Times, equalsRelated, TimesEquals);
		
		Transition FromSemitoInitial = new Transition(Semi, initialRelated, Initial);
		Transition FromSemitoInteger = new Transition(Semi, integerRelated, Integer);
		Transition FromSemitoEquals = new Transition(Semi, equalsRelated, Equals);
		Transition FromSemitoPlus = new Transition(Semi, plusRelated, Plus);
		Transition FromSemitoTimes = new Transition(Semi, timesRelated, Times);
		Transition FromSemitoSemi = new Transition(Semi, semiRelated, Semi);
		Transition FromSemitoIdentifier = new Transition(Semi, toIdentifierRelated, Identifier);
		Transition FromSemitoDecimal = new Transition(Semi, decimalRelated, Decimal);
		Transition FromSemitoLparen = new Transition(Semi, lparenRelated, Lparen);
		Transition FromSemitoRparen = new Transition(Semi, rparenRelated, Rparen);
		Transition FromSemitoQuote = new Transition(Semi, quoteRelated, Quote);
		
		Transition FromIdentifiertoInitial = new Transition(Identifier, initialRelated, Initial);
		Transition FromIdentifiertoEquals = new Transition(Identifier, equalsRelated, Equals);
		Transition FromIdentifiertoPlus = new Transition(Identifier, plusRelated, Plus);
		Transition FromIdentifiertoTimes = new Transition(Identifier, timesRelated, Times);
		Transition FromIdentifiertoSemi = new Transition(Identifier, semiRelated, Semi);
		Transition FromIdentifiertoIdentifier = new Transition(Identifier, identifierRelated, Identifier);
		Transition FromIdentifiertoDecimal = new Transition(Identifier, decimalRelated, Decimal);
		Transition FromIdentifiertoLparen = new Transition(Identifier, lparenRelated, Lparen);
		Transition FromIdentifiertoRparen = new Transition(Identifier, rparenRelated, Rparen);
		Transition FromIdentifiertoQuote = new Transition(Identifier, quoteRelated, Quote);
		
		Transition FromDecimaltoFloat = new Transition(Decimal, floatRelated, Float);
		
		Transition FromLparentoInitial = new Transition(Lparen, initialRelated, Initial);
		Transition FromLparentoInteger = new Transition(Lparen, integerRelated, Integer);
		Transition FromLparentoEquals = new Transition(Lparen, equalsRelated, Equals);
		Transition FromLparentoPlus = new Transition(Lparen, plusRelated, Plus);
		Transition FromLparentoTimes = new Transition(Lparen, timesRelated, Times);
		Transition FromLparentoSemi = new Transition(Lparen, semiRelated, Semi);
		Transition FromLparentoIdentifier = new Transition(Lparen, toIdentifierRelated, Identifier);
		Transition FromLparentoDecimal = new Transition(Lparen, decimalRelated, Decimal);
		Transition FromLparentoLparen = new Transition(Lparen, lparenRelated, Lparen);
		Transition FromLparentoRparen = new Transition(Lparen, rparenRelated, Rparen);
		Transition FromLparentoQuote = new Transition(Lparen, quoteRelated, Quote);
		
		Transition FromRparentoInitial = new Transition(Rparen, initialRelated, Initial);
		Transition FromRparentoInteger = new Transition(Rparen, integerRelated, Integer);
		Transition FromRparentoEquals = new Transition(Rparen, equalsRelated, Equals);
		Transition FromRparentoPlus = new Transition(Rparen, plusRelated, Plus);
		Transition FromRparentoTimes = new Transition(Rparen, timesRelated, Times);
		Transition FromRparentoSemi = new Transition(Rparen, semiRelated, Semi);
		Transition FromRparentoIdentifier = new Transition(Rparen, toIdentifierRelated, Identifier);
		Transition FromRparentoDecimal = new Transition(Rparen, decimalRelated, Decimal);
		Transition FromRparentoLparen = new Transition(Rparen, lparenRelated, Lparen);
		Transition FromRparentoRparen = new Transition(Rparen, rparenRelated, Rparen);
		Transition FromRparentoQuote = new Transition(Rparen, quoteRelated, Quote);
		
		Transition FromQuotetoString = new Transition(Quote, stringRelated, String);
		Transition FromQuotetoClosingQuote = new Transition(Quote, quoteRelated, ClosingQuote);
		
		Transition FromFloattoInitial = new Transition(Float, initialRelated, Initial);
		Transition FromFloattoEquals = new Transition(Float, equalsRelated, Equals);
		Transition FromFloattoPlus = new Transition(Float, plusRelated, Plus);
		Transition FromFloattoTimes = new Transition(Float, timesRelated, Times);
		Transition FromFloattoSemi = new Transition(Float, semiRelated, Semi);
		Transition FromFloattoIdentifier = new Transition(Float, toIdentifierRelated, Identifier);
		Transition FromFloattoDecimal = new Transition(Float, decimalRelated, Decimal);
		Transition FromFloattoLparen = new Transition(Float, lparenRelated, Lparen);
		Transition FromFloattoRparen = new Transition(Float, rparenRelated, Rparen);
		Transition FromFloattoQuote = new Transition(Float, quoteRelated, Quote);
		Transition FromFloattoFloat = new Transition(Float, floatRelated, Float);
		
		Transition FromStringtoClosingQuote = new Transition(String, quoteRelated, ClosingQuote);
		Transition FromStringtoString = new Transition(String, stringRelated, String);
		
		Transition FromPlusPlustoInitial = new Transition(PlusPlus, initialRelated, Initial);
		Transition FromPlusPlustoInteger = new Transition(PlusPlus, integerRelated, Integer);
		Transition FromPlusPlustoEquals = new Transition(PlusPlus, equalsRelated, Equals);
		Transition FromPlusPlustoPlus = new Transition(PlusPlus, plusRelated, Plus);
		Transition FromPlusPlustoTimes = new Transition(PlusPlus, timesRelated, Times);
		Transition FromPlusPlustoSemi = new Transition(PlusPlus, semiRelated, Semi);
		Transition FromPlusPlustoIdentifier = new Transition(PlusPlus, toIdentifierRelated, Identifier);
		Transition FromPlusPlustoDecimal = new Transition(PlusPlus, decimalRelated, Decimal);
		Transition FromPlusPlustoLparen = new Transition(PlusPlus, lparenRelated, Lparen);
		Transition FromPlusPlustoRparen = new Transition(PlusPlus, rparenRelated, Rparen);
		Transition FromPlusPlustoQuote = new Transition(PlusPlus, quoteRelated, Quote);
		
		Transition FromPlusEqualstoInitial = new Transition(PlusEquals, initialRelated, Initial);
		Transition FromPlusEqualstoInteger = new Transition(PlusEquals, integerRelated, Integer);
		Transition FromPlusEqualstoEquals = new Transition(PlusEquals, equalsRelated, Equals);
		Transition FromPlusEqualstoPlus = new Transition(PlusEquals, plusRelated, Plus);
		Transition FromPlusEqualstoTimes = new Transition(PlusEquals, timesRelated, Times);
		Transition FromPlusEqualstoSemi = new Transition(PlusEquals, semiRelated, Semi);
		Transition FromPlusEqualstoIdentifier = new Transition(PlusEquals, toIdentifierRelated, Identifier);
		Transition FromPlusEqualstoDecimal = new Transition(PlusEquals, decimalRelated, Decimal);
		Transition FromPlusEqualstoLparen = new Transition(PlusEquals, lparenRelated, Lparen);
		Transition FromPlusEqualstoRparen = new Transition(PlusEquals, rparenRelated, Rparen);
		Transition FromPlusEqualstoQuote = new Transition(PlusEquals, quoteRelated, Quote);
	
		Transition FromTimesEqualstoInitial = new Transition(TimesEquals, initialRelated, Initial);
		Transition FromTimesEqualstoInteger = new Transition(TimesEquals, integerRelated, Integer);
		Transition FromTimesEqualstoEquals = new Transition(TimesEquals, equalsRelated, Equals);
		Transition FromTimesEqualstoPlus = new Transition(TimesEquals, plusRelated, Plus);
		Transition FromTimesEqualstoTimes = new Transition(TimesEquals, timesRelated, Times);
		Transition FromTimesEqualstoSemi = new Transition(TimesEquals, semiRelated, Semi);
		Transition FromTimesEqualstoIdentifier = new Transition(TimesEquals, toIdentifierRelated, Identifier);
		Transition FromTimesEqualstoDecimal = new Transition(TimesEquals, decimalRelated, Decimal);
		Transition FromTimesEqualstoLparen = new Transition(TimesEquals, lparenRelated, Lparen);
		Transition FromTimesEqualstoRparen = new Transition(TimesEquals, rparenRelated, Rparen);
		Transition FromTimesEqualstoQuote = new Transition(TimesEquals, quoteRelated, Quote);
		
		Transition FromClosingQuotetoInitial = new Transition(ClosingQuote, initialRelated, Initial);
		Transition FromClosingQuotetoInteger = new Transition(ClosingQuote, integerRelated, Integer);
		Transition FromClosingQuotetoEquals = new Transition(ClosingQuote, equalsRelated, Equals);
		Transition FromClosingQuotetoPlus = new Transition(ClosingQuote, plusRelated, Plus);
		Transition FromClosingQuotetoTimes = new Transition(ClosingQuote, timesRelated, Times);
		Transition FromClosingQuotetoSemi = new Transition(ClosingQuote, semiRelated, Semi);
		Transition FromClosingQuotetoIdentifier = new Transition(ClosingQuote, identifierRelated, Identifier);
		Transition FromClosingQuotetoDecimal = new Transition(ClosingQuote, decimalRelated, Decimal);
		Transition FromClosingQuotetoLparen = new Transition(ClosingQuote, lparenRelated, Lparen);
		Transition FromClosingQuotetoRparen = new Transition(ClosingQuote, rparenRelated, Rparen);
		Transition FromClosingQuotetoQuote = new Transition(ClosingQuote, quoteRelated, Quote);
		
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		transitions.add(FromInitialtoInitial);
		transitions.add(FromInitialtoInteger); 
		transitions.add(FromInitialtoEquals);
		transitions.add(FromInitialtoPlus);  
		transitions.add(FromInitialtoTimes);
		transitions.add(FromInitialtoSemi);
		transitions.add(FromInitialtoIdentifier);  
		transitions.add(FromInitialtoDecimal);  
		transitions.add(FromInitialtoLparen);  
		transitions.add(FromInitialtoRparen);  
		transitions.add(FromInitialtoQuote);  
		
		transitions.add(FromIntegertoInitial);
		transitions.add(FromIntegertoInteger);
		transitions.add(FromIntegertoEquals);
		transitions.add(FromIntegertoPlus);
		transitions.add(FromIntegertoTimes);
		transitions.add(FromIntegertoSemi);
		transitions.add(FromIntegertoIdentifier);
		transitions.add(FromIntegertoLparen);
		transitions.add(FromIntegertoRparen);
		transitions.add(FromIntegertoQuote);
		transitions.add(FromIntegertoFloat);
		
		transitions.add(FromEqualstoInitial);
		transitions.add(FromEqualstoInteger);
		transitions.add(FromEqualstoEquals);
		transitions.add(FromEqualstoPlus);
		transitions.add(FromEqualstoTimes);
		transitions.add(FromEqualstoSemi);
		transitions.add(FromEqualstoIdentifier);
		transitions.add(FromEqualstoDecimal);
		transitions.add(FromEqualstoLparen);
		transitions.add(FromEqualstoRparen);
		transitions.add(FromEqualstoQuote);
		
		transitions.add(FromPlustoInitial);
		transitions.add(FromPlustoInteger);
		transitions.add(FromPlustoTimes);
		transitions.add(FromPlustoSemi);
		transitions.add(FromPlustoIdentifier);
		transitions.add(FromPlustoDecimal);
		transitions.add(FromPlustoLparen);
		transitions.add(FromPlustoRparen);
		transitions.add(FromPlustoQuote);
		transitions.add(FromPlustoPlusPlus);
		transitions.add(FromPlustoPlusEquals);
		
		transitions.add(FromTimestoInitial);
		transitions.add(FromTimestoInteger);
		transitions.add(FromTimestoPlus);
		transitions.add(FromTimestoTimes);
		transitions.add(FromTimestoSemi);
		transitions.add(FromTimestoIdentifier);
		transitions.add(FromTimestoDecimal);
		transitions.add(FromTimestoLparen);
		transitions.add(FromTimestoRparen);
		transitions.add(FromTimestoQuote);
		transitions.add(FromTimestoTimesEquals);
		
		transitions.add(FromSemitoInitial);
		transitions.add(FromSemitoInteger);
		transitions.add(FromSemitoEquals);
		transitions.add(FromSemitoPlus);
		transitions.add(FromSemitoTimes);
		transitions.add(FromSemitoSemi);
		transitions.add(FromSemitoIdentifier);
		transitions.add(FromSemitoDecimal);
		transitions.add(FromSemitoLparen);
		transitions.add(FromSemitoRparen);
		transitions.add(FromSemitoQuote);
		
		transitions.add(FromIdentifiertoInitial);
		transitions.add(FromIdentifiertoEquals);
		transitions.add(FromIdentifiertoPlus);
		transitions.add(FromIdentifiertoTimes);
		transitions.add(FromIdentifiertoSemi);
		transitions.add(FromIdentifiertoIdentifier);
		transitions.add(FromIdentifiertoDecimal);
		transitions.add(FromIdentifiertoLparen);
		transitions.add(FromIdentifiertoRparen);
		transitions.add(FromIdentifiertoQuote);
		
		transitions.add(FromDecimaltoFloat);
		
		transitions.add(FromLparentoInitial);
		transitions.add(FromLparentoInteger);
		transitions.add(FromLparentoEquals);
		transitions.add(FromLparentoPlus);
		transitions.add(FromLparentoTimes);
		transitions.add(FromLparentoSemi);
		transitions.add(FromLparentoIdentifier);
		transitions.add(FromLparentoDecimal);
		transitions.add(FromLparentoLparen);
		transitions.add(FromLparentoRparen);
		transitions.add(FromLparentoQuote);
		
		transitions.add(FromRparentoInitial);
		transitions.add(FromRparentoInteger);
		transitions.add(FromRparentoEquals);
		transitions.add(FromRparentoPlus);
		transitions.add(FromRparentoTimes);
		transitions.add(FromRparentoSemi);
		transitions.add(FromRparentoIdentifier);
		transitions.add(FromRparentoDecimal);
		transitions.add(FromRparentoLparen);
		transitions.add(FromRparentoRparen);
		transitions.add(FromRparentoQuote);
		
		transitions.add(FromQuotetoString);
		
		transitions.add(FromFloattoInitial);
		transitions.add(FromFloattoEquals);
		transitions.add(FromFloattoPlus);
		transitions.add(FromFloattoTimes);
		transitions.add(FromFloattoSemi);
		transitions.add(FromFloattoIdentifier);
		transitions.add(FromFloattoDecimal);
		transitions.add(FromFloattoLparen);
		transitions.add(FromFloattoRparen);
		transitions.add(FromFloattoQuote);
		transitions.add(FromFloattoFloat);
		
		transitions.add(FromStringtoClosingQuote);
		transitions.add(FromStringtoString);
		
		transitions.add(FromPlusPlustoInitial);
		transitions.add(FromPlusPlustoInteger);
		transitions.add(FromPlusPlustoEquals);
		transitions.add(FromPlusPlustoPlus);
		transitions.add(FromPlusPlustoTimes);
		transitions.add(FromPlusPlustoSemi);
		transitions.add(FromPlusPlustoIdentifier);
		transitions.add(FromPlusPlustoDecimal);
		transitions.add(FromPlusPlustoLparen);
		transitions.add(FromPlusPlustoRparen);
		transitions.add(FromPlusPlustoQuote);

		transitions.add(FromPlusEqualstoInitial);
		transitions.add(FromPlusEqualstoInteger);
		transitions.add(FromPlusEqualstoEquals);
		transitions.add(FromPlusEqualstoPlus);
		transitions.add(FromPlusEqualstoTimes);
		transitions.add(FromPlusEqualstoSemi);
		transitions.add(FromPlusEqualstoIdentifier);
		transitions.add(FromPlusEqualstoDecimal);
		transitions.add(FromPlusEqualstoLparen);
		transitions.add(FromPlusEqualstoRparen);
		transitions.add(FromPlusEqualstoQuote);
		
		transitions.add(FromTimesEqualstoInitial);
		transitions.add(FromTimesEqualstoInteger);
		transitions.add(FromTimesEqualstoEquals);
		transitions.add(FromTimesEqualstoPlus);
		transitions.add(FromTimesEqualstoTimes);
		transitions.add(FromTimesEqualstoSemi);
		transitions.add(FromTimesEqualstoIdentifier);
		transitions.add(FromTimesEqualstoDecimal);
		transitions.add(FromTimesEqualstoLparen);
		transitions.add(FromTimesEqualstoRparen);
		transitions.add(FromTimesEqualstoQuote);
		
		transitions.add(FromClosingQuotetoInitial);
		transitions.add(FromClosingQuotetoInteger);
		transitions.add(FromClosingQuotetoEquals);
		transitions.add(FromClosingQuotetoPlus);
		transitions.add(FromClosingQuotetoTimes);
		transitions.add(FromClosingQuotetoSemi);
		transitions.add(FromClosingQuotetoIdentifier);
		transitions.add(FromClosingQuotetoDecimal);
		transitions.add(FromClosingQuotetoLparen);
		transitions.add(FromClosingQuotetoRparen);
		transitions.add(FromClosingQuotetoQuote);
		
		/*End Variables*/
		
		ArrayList returnArgs = new ArrayList();
		returnArgs.add(states);
		returnArgs.add(conditions);
		returnArgs.add(transitions);
		
		return returnArgs;
	}
	
	/*checks to see what pattern the current character matches and then lets the State Machine determine if such a transition exists. If it does the SM is now in a new state else a dead state */
	public static StateMachine checkCondition(StateMachine SM, ArrayList<Condition> conditions, String [] regex, State currentState, String currentChar)
	{
		int j = -1;
		
		for(int i = 0; i< regex.length; i++)
		{
			if(currentChar.matches(regex[i]) || currentChar.equals(regex[i]))
			{
				j = i;
				break;
			}
		}
	
		if(j == -1)
		{
			SM.current = null;
		}
		else
		{
			SM.apply(conditions.get(j));
		}

		return SM;
	}

	public static void main(String args[]) throws IOException, FileNotFoundException
	{
		ArrayList objects = initializeVariables();
		ArrayList<State> states = (ArrayList<State>)objects.get(0);
		ArrayList<Condition> conditions = (ArrayList<Condition>)objects.get(1);
		ArrayList<Transition> transitions = (ArrayList<Transition>)objects.get(2);
		
		/*BEGIN setup up REGEX patterns*/
		String WHITE = "[\n\\ \t\b\012]";
		String ALPHA = "[A-Za-z]";
		String DIGIT = "[0-9]";
		String LPAREN = "[(]";
		String RPAREN = "[)]";
		String EQUALS = "[=]";
		String PLUS = "[+]";
		String TIMES = "[*]";
		String SEMI = "[;]";
		String QUOTE = "\"";
		String DECIMAL = "[\\.]";
		String ALL = "[^\"]";
		
		String [] regex = {WHITE, ALPHA, DIGIT, LPAREN, RPAREN, EQUALS, PLUS, TIMES, SEMI, QUOTE, DECIMAL, ALL};
		/*END setup up REGEX patterns*/
		
		StateMachine SM = new StateMachine("SM", states.get(0), transitions);//Initial, transitions;
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(args[0]));
		String currentLine = null;
		
		while((currentLine = in.readLine()) != null)
		{
			/*BEGING getToken()*/
			String currentToken ="";
			String tokenType = "";
			int tokenLength = 0;		
			char [] chars = currentLine.toCharArray();
			
			for(int i = 0; i<chars.length; i++)
			{
				String currentChar = ""+chars[i];
				
				SM = checkCondition(SM, conditions, regex,  SM.current, currentChar);
				
				if(SM.current == null)//found Error Token/char
				{
					System.out.println("<Error(1) "+currentChar+" >");
					currentChar = "";
					SM.current = states.get(0);
				}
				
				State current = SM.current;
				
				StateMachine peeker = null;
				
				if( i + 1 < chars.length)
				{	
					String peekChar = ""+chars[i+1];
					peeker = new StateMachine("peeker", SM.current, transitions);
					peeker = checkCondition(peeker,conditions, regex, SM.current, peekChar);
					
					if(peeker.current == null)
					{
						peeker.current = states.get(0);
					}
					
					currentToken +=currentChar;
					tokenType = current.state;
					
					//if both our current and peeker are in accepting states but are not the same state as well as don't print anything from the initial state
					if(peeker.current.accepting && (current.state != peeker.current.state) && (current.state != "initial"))
					{
						currentToken = currentToken.trim();
						System.out.println("<"+tokenType+"("+currentToken.length()+") "+currentToken+" >");//found token
						currentToken = "";
						tokenType = "";
					}
				}
				else//Last char on line. So that is our token found.
				{
					if(current.state != "initial")
					{
						currentToken +=currentChar;
						tokenType = current.state;
						currentToken = currentToken.trim();
						System.out.println("<"+tokenType+"("+currentToken.length()+") "+currentToken+" >");//found token
					}
				}
			}
			SM.current = states.get(0);
			/*END getToken() */
		} 
	}
}
