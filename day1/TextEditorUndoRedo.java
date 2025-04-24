class TextState {
    String content;
    TextState prev;
    TextState next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState head;
    private TextState tail;
    private TextState current;
    private int size;
    private final int HISTORY_LIMIT = 10;

    public TextEditor() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    public void type(String text) {
        TextState newState = new TextState(text);

        if (current != null && current.next != null) {
            current.next = null;
            tail = current;
        }

        if (tail != null) {
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
        } else {
            head = tail = newState;
        }

        current = newState;
        size++;

        if (size > HISTORY_LIMIT) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public void showCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("Editor is empty.");
        }
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.type("Hello");
        editor.type("Hello World");
        editor.type("Hello World!");

        editor.showCurrentState(); 

        editor.undo();
        editor.showCurrentState(); 

        editor.undo();
        editor.showCurrentState(); 

        editor.redo();
        editor.showCurrentState();

        editor.type("New line after undo");
        editor.showCurrentState();

        editor.redo(); 
    }
}
