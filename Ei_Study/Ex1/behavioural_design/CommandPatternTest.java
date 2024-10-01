package Ei_Study.Ex1.behavioural_design;

import java.util.Stack;

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Concrete Command for Copy
class CopyCommand implements Command {
    private TextEditor editor;
    private String clipboard;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        clipboard = editor.getSelection();
        System.out.println("Copied text: " + clipboard);
    }

    @Override
    public void undo() {
        clipboard = null;
        System.out.println("Undo copy");
    }
}

// Receiver (Text Editor)
class TextEditor {
    private String text;

    public TextEditor(String text) {
        this.text = text;
    }

    public String getSelection() {
        return text.substring(0, 5);  // Select first 5 characters as an example
    }

    public void paste(String clipboard) {
        text += clipboard;
        System.out.println("Pasted text: " + text);
    }
}

// Invoker
class EditorInvoker {
    private Stack<Command> commandStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
    }

    public void undoCommand() {
        if (!commandStack.isEmpty()) {
            commandStack.pop().undo();
        }
    }
}

// Test
public class CommandPatternTest {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor("HelloWorld");
        EditorInvoker invoker = new EditorInvoker();

        Command copyCommand = new CopyCommand(editor);
        invoker.executeCommand(copyCommand);

        invoker.undoCommand();
    }
}
