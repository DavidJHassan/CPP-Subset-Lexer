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

    void apply(ArrayList<Condition> conditions) 
    {
        current = getNextState(conditions);
    }

    State getNextState(ArrayList<Condition> conditions) 
    {
        for(Transition transition : transitions) 
        {
            boolean currentStateMatches = transition.from.equals(current);
            boolean conditionsMatch = transition.conditions.equals(conditions);
            if(currentStateMatches && conditionsMatch) 
            {
                return transition.to;
            }
        }
        return null;
    }
}
