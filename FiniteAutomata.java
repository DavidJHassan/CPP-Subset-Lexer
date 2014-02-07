import java.util.ArrayList;


/*Big god class that holds all the FA related information*/
public class FiniteAutomata
{
	State Initial;
	State Integer;
	State Equals;
	State Plus;
	State Times;
	State Semi;
	State Identifier;
	State Decimal;
	State Lparen;
	State Rparen;
	State Quote;
	State Float;
	State String;
	State PlusPlus;
	State PlusEquals;
	State TimesEquals;
	State ClosingQuote;
	
	ArrayList<State> states;
	
	Condition white;
	Condition alpha;
	Condition digit;
	Condition lparen;
	Condition rparen;
	Condition equals;
	Condition plus;
	Condition times;
	Condition semi;
	Condition quote;
	Condition decimal;
	Condition all;

	ArrayList<Condition> conditions;
	
	ArrayList<Condition> integerRelated;
	ArrayList<Condition> equalsRelated;
	ArrayList<Condition> plusRelated;
	ArrayList<Condition> timesRelated;
	ArrayList<Condition> semiRelated;
	ArrayList<Condition> toIdentifierRelated;
	ArrayList<Condition> identifierRelated;
	ArrayList<Condition> decimalRelated;
	ArrayList<Condition> lparenRelated;
	ArrayList<Condition> rparenRelated;
	ArrayList<Condition> quoteRelated;
	ArrayList<Condition> toFloatRelated;
	ArrayList<Condition> floatRelated;
	ArrayList<Condition> stringRelated;
	ArrayList<Condition> plusplusRelated;
	ArrayList<Condition> plusequalsRelated;
	ArrayList<Condition> timesequalsRelated;
	
	Transition FromInitialtoInteger;
	Transition FromInitialtoEquals;
	Transition FromInitialtoPlus;
	Transition FromInitialtoTimes;
	Transition FromInitialtoSemi;
	Transition FromInitialtoIdentifier;
	Transition FromInitialtoDecimal;
	Transition FromInitialtoLparen;
	Transition FromInitialtoRparen;
	Transition FromInitialtoQuote;
	Transition FromIntegertoInteger;
	Transition FromIntegertoFloat;
	Transition FromPlustoPlusPlus;
	Transition FromPlustoPlusEquals;
	Transition FromTimestoTimesEquals;
	Transition FromIdentifiertoIdentifier;
	Transition FromDecimaltoFloat;
	Transition FromQuotetoString;
	Transition FromQuotetoClosingQuote;
	Transition FromFloattoFloat;
	Transition FromStringtoClosingQuote;
	Transition FromStringtoString;
	
	ArrayList<Transition> transitions;
	
	public FiniteAutomata()
	{
		Initial = new State("initial");
		Integer = new State("integer",true);
		Equals = new State("equals",true);
		Plus = new State("plus",true);
		Times = new State("times",true);
		Semi = new State("semicolon",true);
		Identifier = new State("identifier",true);
		Decimal = new State("decimal");
		Lparen = new State("lparen",true);
		Rparen = new State("rparen",true);
		Quote = new State("quote");
		Float = new State("float",true);
		String = new State("string");
		PlusPlus = new State("plusplus",true);
		PlusEquals = new State("plusequals",true);
		TimesEquals = new State("timesequals",true);
		ClosingQuote = new State("string",true);
		
		states = new ArrayList<State>();
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

		white = new Condition("[\n\\ \t\b\012]");//White space characters
		alpha = new Condition("[a-z]|[A-Z]");//all alphabet characters
		digit = new Condition("[0-9]");//all digits 0-9
		lparen = new Condition("[(]"); // (
		rparen = new Condition("[)]"); // (
		equals = new Condition("[=]");// =
		plus = new Condition("[+]");// +
		times = new Condition("[*]");// *
		semi = new Condition("[;]");// ;
		quote = new Condition("[\"]");// "
		decimal = new Condition("[\\.]");// .(period)
		all = new Condition("[^\"]");// all except quotation marks

		conditions = new ArrayList<Condition>();
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

		integerRelated = new ArrayList<Condition>();
		equalsRelated = new ArrayList<Condition>();
		plusRelated = new ArrayList<Condition>();
		timesRelated = new ArrayList<Condition>();
		semiRelated = new ArrayList<Condition>();
		toIdentifierRelated = new ArrayList<Condition>();
		identifierRelated = new ArrayList<Condition>();
		decimalRelated = new ArrayList<Condition>();
		lparenRelated = new ArrayList<Condition>();
		rparenRelated = new ArrayList<Condition>();
		quoteRelated = new ArrayList<Condition>();
		toFloatRelated = new ArrayList<Condition>();
		floatRelated = new ArrayList<Condition>();
		stringRelated = new ArrayList<Condition>();
		plusplusRelated= new ArrayList<Condition>();
		plusequalsRelated= new ArrayList<Condition>();
		timesequalsRelated= new ArrayList<Condition>();

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
		
		/* initialize the transition table */
		FromInitialtoInteger = new Transition(Initial, integerRelated, Integer);
		FromInitialtoEquals = new Transition(Initial, equalsRelated, Equals);
		FromInitialtoPlus = new Transition(Initial, plusRelated, Plus);
		FromInitialtoTimes = new Transition(Initial, timesRelated, Times);
		FromInitialtoSemi = new Transition(Initial, semiRelated, Semi);
		FromInitialtoIdentifier = new Transition(Initial, toIdentifierRelated, Identifier);
		FromInitialtoDecimal = new Transition(Initial, decimalRelated, Decimal);
		FromInitialtoLparen = new Transition(Initial, lparenRelated, Lparen);
		FromInitialtoRparen = new Transition(Initial, rparenRelated, Rparen);
		FromInitialtoQuote = new Transition(Initial, quoteRelated, Quote);
		FromIntegertoInteger = new Transition(Integer, integerRelated, Integer);
		FromIntegertoFloat = new Transition(Integer, toFloatRelated, Float);
		FromPlustoPlusPlus = new Transition(Plus, plusRelated, PlusPlus);
		FromPlustoPlusEquals = new Transition(Plus, equalsRelated, PlusEquals);
		FromTimestoTimesEquals = new Transition(Times, equalsRelated, TimesEquals);
		FromIdentifiertoIdentifier = new Transition(Identifier, identifierRelated, Identifier);
		FromDecimaltoFloat = new Transition(Decimal, floatRelated, Float);
		FromQuotetoString = new Transition(Quote, stringRelated, String);
		FromQuotetoClosingQuote = new Transition(Quote, quoteRelated, ClosingQuote);
		FromFloattoFloat = new Transition(Float, floatRelated, Float);
		FromStringtoClosingQuote = new Transition(String, quoteRelated, ClosingQuote);
		FromStringtoString = new Transition(String, stringRelated, String);
		
		transitions = new ArrayList<Transition>();
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
		transitions.add(FromIntegertoInteger);
		transitions.add(FromIntegertoFloat);	
		transitions.add(FromPlustoPlusPlus);
		transitions.add(FromPlustoPlusEquals);
		transitions.add(FromTimestoTimesEquals);
		transitions.add(FromIdentifiertoIdentifier);
		transitions.add(FromDecimaltoFloat);
		transitions.add(FromQuotetoString);
		transitions.add(FromQuotetoClosingQuote);	
		transitions.add(FromFloattoFloat);
		transitions.add(FromStringtoClosingQuote);
		transitions.add(FromStringtoString);
		/*END  initialize the transition table */
	}
	
	ArrayList<State> getStateList()
	{
		return states;
	}
	
	ArrayList<Condition> getConditionList()
	{
		return conditions;
	}
	
	ArrayList<Transition> getTransitionList()
	{
		return transitions;
	}
	
	
	
}
