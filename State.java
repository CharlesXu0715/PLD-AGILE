public interface State{
    public default void undo(){};
    public default void redo(){};
}