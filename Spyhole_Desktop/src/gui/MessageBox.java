
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MessageBox {

    /*
     * Error icon.
     */
    public static final int ICON_ERROR = 0x01000000;
    /*
     * Warning icon.
     */
    public static final int ICON_WARNING = 0x02000000;
    /*
     * Information icon.
     */
    public static final int ICON_INFORMATION = 0x04000000;
    /*
     * Question icon.
     */
    public static final int ICON_QUESTION = 0x08000000;

    /*
     * OK button.
     */
    public static final int OK = 0x00010000;
    /*
     * Cancel button.
     */
    public static final int CANCEL = 0x00020000;
    /*
     * Yes button.
     */
    public static final int YES = 0x00040000;
    /*
     * No button.
     */
    public static final int NO = 0x00080000;
    /*
     * Abort button.
     */
    public static final int ABORT = 0x00100000;
    /*
     * Retry button.
     */
    public static final int RETRY = 0x00200000;
    /*
     * Ignore button.
     */
    public static final int IGNORE = 0x00400000;

    
    /*
     * Set first button as default button.
     */
    public static final int DEFAULT_BUTTON1 = 0x00000100;
    /*
     * Set second button as default button.
     */
    public static final int DEFAULT_BUTTON2 = 0x00000200;
    /*
     * Set third button as default button.
     */
    public static final int DEFAULT_BUTTON3 = 0x00000400;
    /*
     * Set fourth button as default button.
     */
    public static final int DEFAULT_BUTTON4 = 0x00000800;

    /*
     * Hide constructor.
     */
    protected MessageBox() {
        // Do nothing.
    }

    
    public static int show(final Window parent, final String message, final String title, final int option) {
        // Default return value is CANCEL.
        final int[] result = new int[] { CANCEL };

        // Create stage without iconized button.
        final Stage dialog = new Stage(StageStyle.UTILITY);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.initModality(Modality.WINDOW_MODAL);
        if (parent != null) {
            // Only set in case of not null.
            dialog.initOwner(parent);
        }

        final VBox totalPane = new VBox();
        dialog.setScene(new Scene(totalPane));
        totalPane.setAlignment(Pos.CENTER);
        totalPane.setSpacing(2);

        final HBox pane = new HBox();
        totalPane.getChildren().add(pane);

        pane.setSpacing(10);

        // Pad left space.
        pane.getChildren().add(new Label("")); //$NON-NLS-1$
        pane.getChildren().add(new Label("")); //$NON-NLS-1$

        {
            final VBox vbox = new VBox();
            pane.getChildren().add(vbox);
            vbox.setAlignment(Pos.CENTER);

            if ((option & ICON_ERROR) == ICON_ERROR) {
                final Group group = MessageIconBuilder.drawErrorIcon(3);
                vbox.getChildren().add(group);
            } else if ((option & ICON_WARNING) == ICON_WARNING) {
                final Group group = MessageIconBuilder.drawWarningIcon(3);
                vbox.getChildren().add(group);
            } else if ((option & ICON_INFORMATION) == ICON_INFORMATION) {
                final Group group = MessageIconBuilder.drawInformationIcon(3);
                vbox.getChildren().add(group);
            } else if ((option & ICON_QUESTION) == ICON_QUESTION) {
                final Group group = MessageIconBuilder.drawQuestionIcon(3);
                vbox.getChildren().add(group);
            }
        }

        {
            final VBox vbox = new VBox();
            pane.getChildren().add(vbox);

            vbox.setAlignment(Pos.CENTER);

            vbox.getChildren().add(new Label(""));//$NON-NLS-1$
            vbox.getChildren().add(new Label(message));

            // Pad right space.
            pane.getChildren().add(new Label("")); //$NON-NLS-1$
            pane.getChildren().add(new Label("")); //$NON-NLS-1$

            // Pad message and buttons.
            vbox.getChildren().add(new Label("")); //$NON-NLS-1$
            vbox.getChildren().add(new Label("")); //$NON-NLS-1$

            boolean isButtonExists = false;

            final int[] BUTTON_LIST = new int[] { OK, YES, NO, ABORT, RETRY, IGNORE, CANCEL };
            final String[] BUTTON_STRING_LIST = new String[] {
                    Messages.getString("MessageBox.OK"), Messages.getString("MessageBox.YES"), Messages.getString("MessageBox.NO"), Messages.getString("MessageBox.ABORT"), Messages.getString("MessageBox.RETRY"), Messages.getString("MessageBox.IGNORE"), Messages.getString("MessageBox.CANCEL") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

            final HBox hboxButtons = new HBox();
            totalPane.getChildren().add(hboxButtons);
            hboxButtons.setSpacing(10);
            hboxButtons.setAlignment(Pos.CENTER);
            hboxButtons.getChildren().add(new Label(""));

            int buttonCounter = 0;
            for (int index = 0; index < BUTTON_LIST.length; index++) {
                if ((option & BUTTON_LIST[index]) == BUTTON_LIST[index]) {
                    final Button btnAdd = new Button();
                    btnAdd.setText(BUTTON_STRING_LIST[index]);
                    isButtonExists = true;
                    final int resultValue = BUTTON_LIST[index];
                    btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            dialog.close();
                            result[0] = resultValue;
                        }
                    });
                    hboxButtons.getChildren().add(btnAdd);
                    buttonCounter++;

                    if ((option & CANCEL) == CANCEL) {
                        btnAdd.setCancelButton(true);
                    }

                    setupDefaultButton(option, buttonCounter, btnAdd);
                }
            }

            // In case of no button found.
            if (isButtonExists == false) {
                final Button btnAdd = new Button();
                hboxButtons.getChildren().add(btnAdd);
                btnAdd.setText(BUTTON_STRING_LIST[0]);
                btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        dialog.close();
                        result[0] = OK;
                    }
                });
                btnAdd.setCancelButton(true);
            }

            hboxButtons.getChildren().add(new Label("")); //$NON-NLS-1$

            totalPane.getChildren().add(new Label(""));//$NON-NLS-1$
        }

        // Below method is supported JavaFX 2.2 or lator.
        dialog.showAndWait();

        return result[0];
    }

    /**
     * Set up default button.
     * 
     * @param option
     * @param buttonCounter
     * @param btnAdd
     */
    private static void setupDefaultButton(final int option, final int buttonCounter, final Button btnAdd) {
        switch (buttonCounter) {
        case 1:
            if ((option & DEFAULT_BUTTON1) == DEFAULT_BUTTON1) {
                btnAdd.setDefaultButton(true);
            }
            break;
        case 2:
            if ((option & DEFAULT_BUTTON2) == DEFAULT_BUTTON2) {
                btnAdd.setDefaultButton(true);
            }
            break;
        case 3:
            if ((option & DEFAULT_BUTTON3) == DEFAULT_BUTTON3) {
                btnAdd.setDefaultButton(true);
            }
            break;
        case 4:
            if ((option & DEFAULT_BUTTON4) == DEFAULT_BUTTON4) {
                btnAdd.setDefaultButton(true);
            }
            break;
        }
    }
}
