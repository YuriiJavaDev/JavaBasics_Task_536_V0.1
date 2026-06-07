## Technical Specification: Modular Desktop Application with Tabbed UI (JavaBasics_Task_563_V0.1)
### Project Objective: Decouple the primary UI navigation logic from the main application frame into a dedicated component, demonstrating high cohesion and strict adherence to the Single Responsibility Principle (SRP).

#### Architectural Requirements:

#### - Strict Package Structure: Maintain consistency with the established multi-layered package layout (com.yurii.pavlenko.app, ui.frames, ui.panels, controller, service, repository, model, actions, util).

#### - Thread-Safe Initialization: Validate that all Swing component instantiation occurs strictly within the Event Dispatch Thread (EDT) boundary using SwingUtilities.invokeLater.

#### - Frame Responsibility: The TaskFrame class must only configure top-level operating system window parameters (bounds, centering, default close operation) and mount the root composite panel via BorderLayout.CENTER. It must not contain internal tabbed layout logic.

#### - Composite Panel Extraction: Create a new class, MainTabbedPanel, within the ui.panels package, inheriting from JPanel. This panel isolates the JTabbedPane management.

#### - Tab Configuration: MainTabbedPanel must define three tabs: "Tasks" (hosting the existing functional TaskPanel), "Tools" (placeholder JPanel), and "AI Chat" (placeholder JPanel).

#### - Zero Designer Dependency: All graphical interfaces must be constructed strictly via hand-written Java code. All references to IntelliJ IDEA's GUI Designer (.form files) are strictly prohibited.
