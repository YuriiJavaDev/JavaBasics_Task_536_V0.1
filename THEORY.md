## Entry point, main window and GUI Designer.

### Entry Point, Main Window, and GUI Designer

---

### Basic Project Template: ‣

### Part 1 — Entry Point: `Main.java`

#### What is it

`Main.java` is the starting point of any Java application. The JVM (Java Virtual Machine) always looks for the `main` method and begins execution there.

#### Why `SwingUtilities.invokeLater`

Swing is not a thread-safe framework. This means that if you create windows and components from a regular thread (for example, directly in `main`), random visual bugs and freezes may occur, especially on different operating systems.

To solve this problem, Swing uses a special thread—the Event Dispatch Thread (EDT). All UI operations must be performed strictly within this thread.

`SwingUtilities.invokeLater(...)` queues the passed code to the EDT thread and executes it there. This is the standard and required way to launch a Swing application.

```java
package com.example.myassistant;

import javax.swing.SwingUtilities;

/**
* Application entry point.
*/
public class Main {
    
    public static void main(String[] args) {
        // launch the UI strictly in the EDT thread, as required by Swing
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
```

**Note:** `() -> { ... }` is a lambda expression. It passes a block of code as an argument to `invokeLater`. Swing will take this code and execute it in the appropriate thread.

---

### Part 2 — Main Window: `MainFrame`

#### What is `JFrame`

`JFrame` is a Swing class that represents an operating system window: with a title bar, minimize/maximize/close buttons, and a content area.

Our `MainFrame` **inherits** `JFrame`, meaning it itself becomes a window, receiving all its functionality.

#### What is `JTabbedPane`

`JTabbedPane` is a tabbed component (like tabs in a browser). Each tab contains a separate panel (`JPanel`). This panel organizes the three sections of our application.

#### Split into two methods

Note the class structure: the logic is split into two methods.

```java
public MainFrame() {
    initComponents(); // create and place components
    configureFrame(); // configure window settings
}
```

This isn't a mandatory language rule, but a **good practice**: each method does one thing. `initComponents` is responsible for the content, `configureFrame` is responsible for the window settings. This code is easier to read and modify.

```java
package com.example.myassistant.ui;

import javax.swing.*;
import java.awt.*;

/**
* The main application window. Contains three tabs: tasks, tools, and chat.
  */
  public class MainFrame extends JFrame {
    
    private JTabbedPane tabbedPane;
    
    public MainFrame() {
        initComponents();
        configureFrame();
    }
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Tasks", new JPanel()); // temporary stub
        tabbedPane.addTab("Tools", new JPanel()); // temporary stub
        tabbedPane.addTab("AI Chat", new JPanel()); // temporary stub
        
        //this.add(tabbedPane, BorderLayout.CENTER);// this is the current MainFrame object.
        add(tabbedPane, BorderLayout.CENTER); //JFrame = default BorderLayout
    }
    
    private void configureFrame() {
        setTitle("MyAssistant");
        setSize(900, 600);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // opens in the center of the screen
    }
}
```

**`BorderLayout.CENTER`** — Positions the `tabbedPane` so that it occupies the entire available window space. `BorderLayout` is the default layout manager for `JFrame`.

**`setLocationRelativeTo(null)`** — Passing `null` means "relative to the screen," meaning the window will open in the center of the monitor.

**`EXIT_ON_CLOSE`** — closing the window terminates the application. Without this line, the application would continue running in the background after the window is closed.

---

### Part 3 — GUI Designer: Form Creation and Connection

#### What is GUI Designer

GUI Designer is a visual interface design tool built into IntelliJ IDEA. Instead of manually coding component placement code, you drag and drop them.

When you create a form, IntelliJ creates two related files:

| File | What it contains |
| --- | --- |
| `TaskPanel.form` | XML describing the visual layout |
| `TaskPanel.java` | Java class with fields for components |

These files always work together. Changes in the designer are reflected in `.form`, and the logic is written in `.java`.

#### How to create a form

**New → GUI Form** — IntelliJ creates both files (you need to create it in the **panels** package). A field appears in the generated `.java` file—the form's root panel:

```java
private JPanel mainPanel;
```

IntelliJ associates this field with the root component in the designer. This is the panel that needs to be passed to `JTabbedPane`.

#### How to connect a form to the main window

In the form class, add a method that returns the root panel:

```java
package com.example.myassistant.ui.panels;

import javax.swing.*;

public class TaskPanel {
    
    private JPanel mainPanel;
    
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
```

In `MainFrame`, replace the stub with a real panel:

```java
import com.example.myassistant.ui.panels.TaskPanel;

// before:
tabbedPane.addTab("Tasks", new JPanel());

// now:
tabbedPane.addTab("Tasks", new TaskPanel().getMainPanel());
```

#### Why we don't pass `TaskPanel` directly

`JTabbedPane.addTab` expects `Component`. `TaskPanel` doesn't inherit `JPanel` or `JComponent`—it's a regular class. Therefore, we pass not the object itself, but the panel it contains—via `getMainPanel()`.

---

### Common Beginner Mistakes

- Declaring a class field **outside curly braces**—Java doesn't allow this; all fields and methods must be inside `{}`
- Forgetting `SwingUtilities.invokeLater` and creating a window directly in `main`—it works, but violates Swing rules and can cause problems
- Not adding an import for a class from another package—IntelliJ underlines it in red; Press Alt+Enter to auto-add.
- Typos in method names - Java is case-sensitive: `getMainPanel()` and `getMAinPanel()` are different methods.
- Failure to call `setVisible(true)` - the window is created but not displayed.
