package appland.actions;

import appland.AppMapBundle;
import appland.Icons;
import appland.remote.RemoteRecordingService;
import appland.remote.StopRemoteRecordingDialog;
import appland.settings.AppMapProjectSettingsService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;

public class StopAppMapRecordingAction extends AnAction implements DumbAware {
    public StopAppMapRecordingAction() {
        super(Icons.STOP_RECORDING_ACTION);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var project = e.getProject();
        assert project != null;

        var form = StopRemoteRecordingDialog.show(project);
        if (form != null) {
            var targetPath = Paths.get(form.getLocation());
            AppMapProjectSettingsService.getState(project).setRecentAppMapStorageLocation(targetPath.toString());

            var task = new Task.Backgroundable(project, AppMapBundle.get("action.stopAppMapRemoteRecording.progressTitle"), false) {
                @Override
                public void run(@NotNull ProgressIndicator indicator) {
                    RemoteRecordingService.getInstance().stopRecording(form.getURL(), targetPath);
                }
            };
            task.queue();
        }
    }
}
