import java.util.ArrayList;

public class Lexer
{
	
	
	public static void main(String args[])
	{
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
		
		stringRelated.add(Alpha);
		stringRelated.add(Digit);
		stringRelated.add(White);
		stringRelated.add(Quote);
		stringRelated.add(Op);
		stringRelated.add(Paren);
		stringRelated.add(Decimal);
		stringRelated.add(Semicolon);
		toStringRelated.add(Quote);
		
		toIdentifierRelated.add(Alpha);
		identifierRelated.add(Digit);
		identifierRelated.add(Alpha);
		
		
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		
		Transition FromInitialtoInitial = new Transition(Initial, initialRelated, Initial);
		Transition FromInitialtoInteger = new Transition(Initial, integerRelated, Integer);
		Transition FromInitialtoOperand = new Transition(Initial, integerRelated, Operand);
		Transition FromInitialtoIdentifier = new Transition(Initial, identifierRelated, Identifier);
		Transition FromInitialtoString = new Transition(Initial, toStringRelated, String);
		
		Transition FromIntegertoInitial = new Transition(Integer, initialRelated, Initial);
		Transition FromIntegertoInteger = new Transition(Integer, integerRelated, Integer);
		Transition FromIntegertoFloat = new Transition(Integer, toFloatRelated, Float);
		Transition FromIntegertoOperand = new Transition(Integer, operandRelated, Operand);
		
		Transition FromFloattoInitial = new Transition(Float, initialRelated, Initial);
		Transition FromFloattoFloat = new Transition(Float, floatRelated, Float);
		Transition FromFloattoOperand = new Transition(Float, operandRelated, Operand);
		
		Transition FromOperandtoInitial = new Transition(Operand, initialRelated, Initial);
		Transition FromOperandtoInteger = new Transition(Operand, integerRelated, Integer);
		Transition FromOperandtoOperand = new Transition(Operand, operandRelated, Operand);
		Transition FromOperandtoString = new Transition(Operand, toStringRelated, String);
		Transition FromOperandtoIdentifier = new Transition(Operand, toIdentifierRelated, Identifier);
		
		Transition FromIdentifiertoInitial = new Transition(Identifier, initialRelated, Initial);
		Transition FromIdentifiertoOperand = new Transition(Identifier, operandRelated, Operand);
		
		Transition FromStringtoInitial = new Transition(String, initialRelated, Initial);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
