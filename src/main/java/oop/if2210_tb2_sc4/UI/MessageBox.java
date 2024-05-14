package oop.if2210_tb2_sc4.UI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
public class MessageBox {

    private static MessageBox instance;

    public static MessageBox getInstance() {
        if (instance == null) {
            instance = new MessageBox();
        }
        return instance;
    }

    private static Label ErrorTitle;
    private static TextArea ErrorMessage;
    private AnchorPane Body;
    private Pane Root;

    public static void setRoot(Pane root) {
        instance.Root = root;
    }

    public static void setBody(AnchorPane Body) {
        instance.Body = Body;
        ErrorTitle = (Label) Body.lookup("#ErrorTitle");
        ErrorMessage = (TextArea) Body.lookup("#ErrorMessage");
    }

    public MessageBox() {

    }

    private void SetDisableRootFocus(Pane root, boolean state) {
        for (Node node : root.getChildren()) {
            if (node != Body) { // Skip the current message box body
                node.setMouseTransparent(state);
                if (node instanceof Pane) {
                    SetDisableRootFocus((Pane) node, state);
                }
            }
        }
    }

    public static void showErrorMessage(String title, String message) {
        instance.Root.getChildren().add(instance.Body);
        instance.SetDisableRootFocus(instance.Root, true);
        ErrorTitle.setText(title);
        ErrorMessage.setText(message);
    }

    public void CloseMessageBox(MouseEvent mouseEvent) {
        SetDisableRootFocus(instance.Root, false);
        instance.Root.getChildren().remove(instance.Body);
    }
}
