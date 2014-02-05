public class State 
{
	String state;
	boolean accepting;
	
	public State(String name)
	{
		state = name;
		accepting = false;
	}

	public State(String name, boolean a)
	{
		state = name;
		accepting = a;
	}
}
