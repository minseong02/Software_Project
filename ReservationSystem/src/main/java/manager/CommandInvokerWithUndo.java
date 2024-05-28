/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.util.Stack;

/**
 *
 * @author PARKSOHYUN
 */
public class CommandInvokerWithUndo {
    private Stack<Command> commandStack = new Stack<>();
    
    public void setCommand(Command command) {
        commandStack.push(command);
    }
    
    public void executeCommand() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.peek();
            command.execute();
        }
    }
    
    public void undoLastCommand() {
        if(!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
        }
    }
    
}
