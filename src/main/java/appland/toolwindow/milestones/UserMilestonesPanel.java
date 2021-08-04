package appland.toolwindow.milestones;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.panels.VerticalLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages a list of collapsible user milestone panels.
 */
public class UserMilestonesPanel extends JPanel {
    private final List<CollapsibleUserMilestonePanel> collapsiblePanels = new LinkedList<>();

    public UserMilestonesPanel(@NotNull Project project) {
        super(new VerticalLayout(5));

        addPanel("Quickstart", new UserMilestoneContentPanel() {
            @Override
            protected void setupPanel() {
                add(new StatusLabel(UserMilestoneStatus.Incomplete, "Install AppMap Agent", () -> MilestoneActions.openQuickstart(project)));
                //add(new StatusLabel(UserMilestoneStatus.Incomplete, "Install AppMap Agent", () -> installAppMapAgent(project)));
            }
        });

        addPanel("Documentation", new UserMilestoneContentPanel() {
            @Override
            protected void setupPanel() {
                add(new UrlLabel("Quickstart", "https://appland.com/docs/quickstart"));
                add(new UrlLabel("AppMap overview", "https://appland.com/docs/appmap-overview"));
                add(new UrlLabel("How to use AppMap diagrams", "https://appland.com/docs/how-to-use-appmap-diagrams"));
                add(new UrlLabel("Guides", "https://appland.com/docs/guides"));
                add(new UrlLabel("Reference", "https://appland.com/docs/reference"));
                add(new UrlLabel("Troubleshooting", "https://appland.com/docs/troubleshooting"));
                add(new UrlLabel("Recording methods", "https://appland.com/docs/recording-methods"));
                add(new UrlLabel("Integrations", "https://appland.com/docs/integrations"));
                add(new UrlLabel("Community", "https://appland.com/docs/community"));
                add(new UrlLabel("FAQ", "https://appland.com/docs/faq"));
            }
        });
    }

    private void addPanel(@NotNull String title, @NotNull UserMilestoneContentPanel panel) {
        var collapsiblePanel = new CollapsibleUserMilestonePanel(panel, true, title);
        collapsiblePanels.add(collapsiblePanel);
        add(collapsiblePanel);

        // collapse all other panels when a panel was expanded
        /*collapsiblePanel.addCollapsingListener((collapsedPanel, isCollapsed) -> {
            if (!isCollapsed) {
                for (CollapsibleUserMilestonePanel p : collapsiblePanels) {
                    if (!collapsedPanel.equals(p)) {
                        p.setCollapsed(true);
                    }
                }
            }
        }, null);*/
    }
}
