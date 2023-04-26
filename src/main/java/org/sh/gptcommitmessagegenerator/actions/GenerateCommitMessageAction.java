package org.sh.gptcommitmessagegenerator.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;
public class GenerateCommitMessageAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final Project project = event.getProject();
        if (project == null) {
            return;
        }

        final String commitMessage = generateCommitMessage();
        if (commitMessage == null) {
            return;
        }
        final ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        final ToolWindow toolWindow = toolWindowManager.getToolWindow("Vcs.Commit.PrimaryCommitActions");
//        toolWindow.getContentManager().addContent();
    }//TODO add functionality to edit commit message

    private String generateCommitMessage() {
        return "feat(): Sample generated message";
    }
}
