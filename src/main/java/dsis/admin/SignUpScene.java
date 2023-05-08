package dsis.admin;

import dsis.admin.Confirmation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class SignUpScene {
    private final SceneManager sceneManager;
    private final HashMap<String, String> users;

    public SignUpScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        users = new HashMap<>();
    }

    public Scene getScene() {
        // Create UI elements
        Label titleLabel = new Label("Sign Up");
        Label usernameLabel = new Label("Name:");
        Label facultyLabel = new Label("Faculty:");
        Label departmentLabel = new Label("Department::");
        Label regYearLabel = new Label("Registration Year:");
        Label idLabel = new Label("School ID:");
        TextField usernameTextField = new TextField();
        TextField facultyTextField = new TextField();
        TextField departmentTextField = new TextField();
        TextField regYearTextField = new TextField();
        TextField schoolIDTextField = new TextField();
        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        submitButton.setOnAction(event -> {
            users.put("name", usernameTextField.getText());
            users.put("schoolId", schoolIDTextField.getText());
            users.put("faculty", facultyTextField.getText());
            users.put("department", departmentTextField.getText());
            users.put("regYear", regYearTextField.getText());
            
            try {
                CloudConnect.callFunction("signup", users);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sceneManager.setScene(new Confirmation(sceneManager, "User created successfully!").getScene());
        });
        backButton.setOnAction(event -> sceneManager.showHomeScene());

        VBox layout = new VBox(20, titleLabel, usernameLabel, usernameTextField, facultyLabel, facultyTextField, 
                departmentLabel, departmentTextField, regYearLabel, regYearTextField, idLabel, schoolIDTextField,
                submitButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1200, 720);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}