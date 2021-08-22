/*
package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class historyClass {
    @FXML
    private AnchorPane historyPane;
    @FXML
    private JFXButton historyLoadButton;
    @FXML
    private JFXTextArea textArea;
    @FXML
    private JFXButton closeHButton;
    @FXML
    private JFXButton shareTwitter;

    /////LOAD CALCULATIONS BUTTON/////
    @FXML
    void historyLoad(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("C:\\Users\\mania\\IdeaProjects\\CSIA"));
        fileChooser.setInitialDirectory(new File("C:\\Users"));
        File selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null) {
            textArea.setText(readFile(selectFile));
        }
    }

    /////READING THE FILE FROM EQUATIONS//////
    private String readFile(File selectFile){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(selectFile));
            String text;
            while ((text = bufferedReader.readLine()) != null) stringBuffer.append(text);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(historyClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(historyClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(historyClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringBuffer.toString();
    }

    public void initialize(URL url, ResourceBundle rb) {}

    //////////// SHARING APIs //////////////////////////////
    ////////// FACEBOOK///////////


    ////////// TWITTER ///////////
    ///CONSUMER KEYS AND SECRETS FROM TWITTER DEVELOPMENT///
    String consumerKey = "CkfbQqw5wXtBNiVxeuqhoVIMX";
    String consumerSecret = "uZPbItzG5N7vRI2ZqxD9Jhvxsn5S0pTs12sZWhJBuW5DSjBEoH";
    String token = "925437035812356097-R3I5WxehQlBpgTrdcUiDS6pid2zlyPA";
    String accessTokenSecret = "CglLMZITx1hLPhTTF6vHMspdo4xx8hHyH77uT4B7IQkLQ";

    ///TWITTER SHARE BUTTON//
    @FXML
    void twitShare(ActionEvent event) {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        //AccessToken accessToken = new AccessToken(token, accessTokenSecret);

        RequestToken requestToken = null;
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (null == accessToken)
        {
            TextInputDialog dialog = new TextInputDialog(requestToken.getAuthorizationURL());
            dialog.setHeaderText("Pendumilation - Share on Twitter");
            dialog.setContentText("Please copy and paste this URL\n in your browser and authorise Pendumilation :)");
            dialog.showAndWait();

            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setHeaderText("Pendumilation - Share on Twitter");
            dialog1.setContentText("Enter the PIN(if aviailable): ");
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");

            String pin = null;
            Optional<String> result = dialog1.showAndWait();

            pin = result.get();

            try {
                if (pin.length() > 0)
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                else
                    accessToken = twitter.getOAuthAccessToken();
            } catch(TwitterException te) {
                if (401 == te.getStatusCode())
                    System.out.println("Unable to get the access token.");
                else
                    te.printStackTrace();
            }
        }
        //persist to the accessToken for future reference.
        try {
            storeAccessToken(twitter.verifyCredentials(), accessToken);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        Status status = null;
        try {
            status = twitter.updateStatus(textArea.getText());
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("Successfully updated the status to [" + status.getText() + "].");
            alert3.setHeaderText("Pendumilation - Share on Twitter");
        } catch (TwitterException e) {
            e.printStackTrace();
        }


        //System.out.println("Successfully updated the status to [" + status.getText() + "].");
        //System.exit(0);
    }

    private static void storeAccessToken(User user, AccessToken accessToken)
    {
        System.out.println(user.getScreenName());
    }

    /// GET TEXT FROM TEXT AREA//
//        String text = textArea.getText();
//        twitter.setOAuthAccessToken(accessToken);
//
//        ///SEND TWEET///
//        try {
//            //twitter.updateStatus("Hello from Twitter4J (Test)");
//            twitter.updateStatus(text);
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }


    //////////// CLOSE BUTTON //////////
    @FXML
    void makeHClose(ActionEvent event) {
        Stage stage = (Stage) closeHButton.getScene().getWindow();
        stage.close();
        //Platform.exit();
        //System.exit(0);
    }

}
*/
