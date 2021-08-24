package Pane;


import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author omarc
 */
public class MenuPane {
    public VBox menuRoot;
    
    public VBox getMenuRoot(){
        
        menuRoot = new VBox(20);
        String style = "-fx-font-weight: bold; -fx-font-size: 20px;-fx-background-color: #44BEC6;";
        
        Text title = new Text("Tres en Raya");
        title.setStyle(style);
        menuRoot.getChildren().add(title);
        
        HBox symbolHb = new HBox(15);
        Label symbolLabel = new Label("Simbolo: ");
        
        ToggleGroup tg = new ToggleGroup();
        RadioButton xRb = new RadioButton("X");
        xRb.setUserData("X");
        xRb.setToggleGroup(tg);
        xRb.setStyle(style);
        
        RadioButton oRb = new RadioButton("O");
        oRb.setUserData("O");
        oRb.setToggleGroup(tg);
        oRb.setStyle(style);
        
        symbolHb.getChildren().addAll(symbolLabel, xRb,oRb);
        menuRoot.getChildren().add(symbolHb);
        
        menuRoot.setPadding(new Insets(20,10,20,10));
        
        return menuRoot;
    }
}
