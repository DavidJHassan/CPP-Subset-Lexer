import java.util.ArrayList;

class Transition 
{
	State from;
	ArrayList<Condition> conditions;
	State to;
	
	public Transition(State f, ArrayList<Condition> c, State t)
	{
		from = f;
		conditions = c;
		to = t;
	}
}
