import java.util.ArrayList;
import java.io.*;

public class Lexer
{
	/*Helper function*/
	public static int checkLoop(ArrayList<Condition> conditions, String currentChar)
	{
		int j = -1;
		int i = 0;
		for(Condition c : conditions)
		{
			if(currentChar.matches(c.condition))
			{
				j = i;
				break;
			}
			i++;
		}
		return j;
	}
	
	/*checks to see what pattern the current character matches and then lets the State Machine determine if such a transition exists. If it does the SM is now in a new state else a dead state */
	public static int checkCondition(StateMachine SM, ArrayList<Condition> conditions, String currentChar)
	{
		int j = checkLoop(conditions, currentChar);
	
		if(j == -1)
		{
			return -1;//Symbol not recognized.
		}
		else
		{	
			SM.apply(conditions.get(j));
			if(SM.current == null)
			{
				return -2;//Transition from state given symbol not valid
			}
			
			return j;//Everything was succesful we return the accepted condition index for reference and debugging if needed
		}
	}
	
	/*Where all the fun and logic is. Also prints the tokens found on a given line*/
	public static void getToken(StateMachine SM, State Initial, ArrayList<Condition> conditions,ArrayList<Transition> transitions, String currentLine)
	{
			String currentToken ="";
			String tokenType = "";
			int tokenLength = 0;		
			char [] chars = currentLine.toCharArray();
			
			for(int i = 0; i<chars.length; i++)
			{
				String currentChar = ""+chars[i];
				int code = checkCondition(SM, conditions, currentChar);
				
				if(code == -1 || code == -2)
				{
					if(!currentChar.matches(conditions.get(0).condition))//If not a whitespace character then an error symbol
					{
						System.out.println("<Error(1), "+currentChar+">");
					}
					
					currentChar = "";
					SM.current = Initial;
				}
				
				StateMachine peeker = null;//Create a peeker state machine
				
				if( i + 1 < chars.length)
				{	
					currentToken +=currentChar;
					tokenType = SM.current.state;
					
					String peekChar = ""+chars[i+1];
					peeker = new StateMachine("peeker", SM.current, transitions);
					code = checkCondition(peeker,conditions, peekChar);//have our peeker look ahead and get a condition code
					
					if(code == -2)//peeker discovered if current state continues it wont be able to continue
					{
						if(SM.current.accepting)// Since current state won't be able to continue we check if its able to accept current token
						{
							SM.current = Initial;
							currentToken = currentToken.trim();
							System.out.println("<"+tokenType+"("+currentToken.length()+"), "+currentToken+">");//found token
							currentToken = "";
							tokenType = "";
						}//else we were unable to return the token as we were not in a accepting state. Next loop an error will be thrown as we got into a dead state. 
					}
				}
				else//Last char on line. So we return our token if we need to return an error of our current token
				{
					currentToken +=currentChar;
					
					
					if(SM.current.accepting)
					{
						tokenType =  SM.current.state;
						currentToken = currentToken.trim();
						System.out.println("<"+tokenType+"("+currentToken.length()+"),"+currentToken+">");//found token
						SM.current = Initial;
					}
					else
					{
						if(!currentChar.matches(conditions.get(0).condition))//If not a whitespace character then an error symbol
						{
							currentToken = currentToken.trim();
							System.out.println("<Error("+currentToken.length()+"),"+currentToken+">");//found error token
							currentChar = "";
							SM.current = Initial;
						}
					}
				}
			}
			SM.current = Initial;
	}

	public static void main(String args[]) throws IOException, FileNotFoundException
	{
		FiniteAutomata FA = new FiniteAutomata();
		ArrayList<State> states = FA.getStateList();
		ArrayList<Condition> conditions = FA.getConditionList();
		ArrayList<Transition> transitions = FA.getTransitionList();//Transition Table
		
		StateMachine SM = new StateMachine("SM", states.get(0), transitions);//states.get(0) is the Initial State
		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(args[0]));
		String currentLine = null;
		
		while((currentLine = in.readLine()) != null)
		{
			getToken(SM, states.get(0), conditions, transitions, currentLine);//Finds all tokens on the current line
		} 
	}
}
