 public class Todo {
     protected String action;
     protected boolean done;

     public Todo(String action) {
         this.action = action;
         this.done = false;
     }
     public void done(boolean status) {
         this.done = status;
     }
     public boolean done() {
         return this.done;
     }
     @Override
     public String toString() {
         return (done ? "X  " : "_  ")
             + action;
     }
 }

 class PriorityTodo extends Todo {
     private int priority;
     public PriorityTodo(String action, int priority) {
         super(action);
         this.priority = priority;
     }
     @Override
     public String toString() {
         return super.toString() + " priority " + priority;
     }
 }

