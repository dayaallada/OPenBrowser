/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opening.browser;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Daya A
 */

public class OpeningBrowser extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);      
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
class MyWebViewClient extends WebViewClient {
@Override
public void onReceivedHttpAuthRequest(WebView view,
        HttpAuthHandler handler, String host, String realm) {

    handler.proceed("me@test.com", "mypassword");

}
}
class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        
        webEngine.load("http://192.168.0.127/Thingworx/Mashups/capacity");
        browser.setWebViewClient(new MyWebViewClient ());
    //    webEngine.
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
    @Override
public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
    handler.proceed("username", "password");
}

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}