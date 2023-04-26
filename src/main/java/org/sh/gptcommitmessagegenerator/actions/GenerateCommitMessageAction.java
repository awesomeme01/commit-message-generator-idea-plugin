package org.sh.gptcommitmessagegenerator.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitCommitAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;



        }

        String commitMessage = generateCommitMessage();
        if (commitMessage == null) {
            return;
        }

        Messages.showInfoMessage(project, "Generated commit message:\n\n" + commitMessage, "Commit Message");
    }

    private String generateCommitMessage() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("git", "commit");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString();
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
