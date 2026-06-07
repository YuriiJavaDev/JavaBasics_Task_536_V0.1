package com.yurii.pavlenko.ui.frames;

import com.yurii.pavlenko.controller.TaskController;
import com.yurii.pavlenko.ui.panels.MainTabbedPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Structural window shell configuring screen boundaries, centering positions, and global container mounting.
 */
public class TaskFrame extends JFrame {

    public TaskFrame(TaskController controller) {
        super("Task Manager");
        initComponents(controller);
        configureFrame();
    }

    private void initComponents(TaskController controller) {
        MainTabbedPanel mainTabbedPanel = new MainTabbedPanel(controller);
        add(mainTabbedPanel, BorderLayout.CENTER);
    }

    private void configureFrame() {
        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
