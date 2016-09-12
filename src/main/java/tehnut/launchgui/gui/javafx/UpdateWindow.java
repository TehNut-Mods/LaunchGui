package tehnut.launchgui.gui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import net.minecraft.client.resources.I18n;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.utils.LogHelper;
import tehnut.launchgui.utils.Utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UpdateWindow extends Application {

    private Stage window;

    public static void initWindow() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.window = primaryStage;

        primaryStage.setTitle(I18n.format("gui.launchgui.update.title", ConfigHandler.modpackName));
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(25, 25, 25, 25));

        VBox titleBox = new VBox(getTitle(), new Separator());
        titleBox.setPadding(new Insets(5, 5, 5, 5));
        titleBox.setSpacing(10);
        border.setTop(titleBox);

        VBox bodyBox = new VBox(new VBox(getBodyText()));
        bodyBox.setPadding(new Insets(5, 5, 5, 5));
        bodyBox.setSpacing(10);
        border.setCenter(bodyBox);

        HBox buttonBox = new HBox(getButtons());
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.setSpacing(10);
        border.setBottom(new VBox(new Separator(), buttonBox));

        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }

    private Node[] getBodyText() {
        List<Node> nodes = new ArrayList<Node>();

        String[] toDraw = Utils.replaceTextCodes(ConfigHandler.updateGuiLines).split("\n");
        for (String draw : toDraw) {
            String wrapped = draw.replaceAll("(.{55})", "$1\n");
            String[] cutWrapped = wrapped.split("\n");

            for (String cut : cutWrapped) {
                Label label = new Label(cut);
                label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                nodes.add(label);
            }
        }

        return nodes.toArray(new Node[nodes.size()]);
    }

    private Node[] getButtons() {
        Button acknowledgeButton = new Button(ConfigHandler.continueButtonText);
        acknowledgeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
        acknowledgeButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button informationButton = new Button(ConfigHandler.updateInformationButtonText);
        informationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Utils.browse(new URI(ConfigHandler.updateInformationUrl));
                } catch (Exception e) {
                    LogHelper.error("Failed to load the page at " + ConfigHandler.updateInformationUrl + "!");
                    e.printStackTrace();
                }
            }
        });
        informationButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        return new Node[] { acknowledgeButton, informationButton };
    }

    private Node getTitle() {
        Label title = new Label(I18n.format("gui.launchgui.update.avail"));
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        return title;
    }
}
