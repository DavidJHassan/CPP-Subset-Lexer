import java.util.ArrayList;

class StateMachine 
{
    ArrayList<Transition> transitions;
    State current;

    StateMachine(State start, ArrayList<Transition> transitions) 
    {
        this.current = start;
        this.transitions = transitions;
    }

    void apply(Condition condition) 
    {
		current = getNextState(condition);
    }

    State getNextState(Condition condition) 
    {
        for(Transition transition : transitions) 
        {
            boolean currentStateMatches = transition.from.equals(current);
            boolean conditionsMatch = false;

			for(Condition t : transition.conditions)
			{
				if(t.equals(condition))
				{
					conditionsMatch = true;
				}
			}
			
			
            if(currentStateMatches && conditionsMatch) 
            {
                return transition.to;
            }
        }
        return null;
    }
}
