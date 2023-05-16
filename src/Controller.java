import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.fxml.Initializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Contrtoller class used to determine the layout and action listers of the homescreen
 */
public class Controller
{
    /**
     * The initialize function sets some hidden labels to not visible
     */
    @FXML
    void initialize(){
        invalidInput.setVisible(false);
        invalidExchange.setVisible(false);

    }

    /**
     * The cashier member is used to store a cashier object to handle exchanges
     */
    private Cashier cashier = new Cashier("1");
    /**
     * the account_name text field is used to take in user input detrminign their account name
     */
    @FXML
    private TextField account_name;
    /**
     * the pane member is used to hold all components
     */
    @FXML
    private TabPane pane;
    /**
     * the account_balance member is used to take in user input to determine the account balance
     */
    @FXML
    private TextField account_balance;
    /**
     * The exchangeRate label iis used to display the current exchange rate
     */
    @FXML
    private Label exchangeRate = new Label();
    /**
     * The exchangeInput Text feild is ued to take in user input and assign it to the exchange rate label
     */
    @FXML
    private TextField exchangeInput = new TextField();

    @FXML
    private Button exchangeButton = new Button();
    /**
     * The invalid input label is used to show the user they entered an invalid input
     */
    @FXML
    private Label invalidInput = new Label();
    /**
     * The invalid exchange label is used to show the user they entered an invalid exchange rate
     */
    @FXML
    private Label invalidExchange = new Label();

    /**
     * The assignExchangeRate function is used to assign and display the exchange rate to the GUI
     * @param event the event that prompts this function
     */
    @FXML
    public void assignExchangeRate(ActionEvent event){
        try {
            exchangeRate.setText("1 USD = " + Float.parseFloat(exchangeInput.getText()) + " SWD");
            cashier.setExchangeRate(Float.parseFloat(exchangeInput.getText()));
            System.out.println(cashier.getExchangeRate());
            invalidExchange.setVisible(false);
        }catch(Exception e){
            invalidExchange.setVisible(true);
        }
    }

    /**
     * The CreateAccount function creates an instance of the AccountTab class and adds it to the pane
     * @param event the event that prompts this function
     */
    @FXML
    public void CreateAccount(ActionEvent event){
        try{
            String name = account_name.getText();
            BigDecimal balance = new BigDecimal(account_balance.getText());
            bank temp = new bank(name,balance);
            Tab temp_tab = new Tab(name);
            temp_tab.setClosable(true);
            AccountTab accountTab = new AccountTab(temp,cashier,pane,temp_tab);
            temp_tab.setContent(accountTab.getAnchorPane());
            pane.getTabs().add(temp_tab);
            pane.getSelectionModel().selectLast();
            invalidInput.setVisible(false);
        }catch (Exception e){
            System.out.println("here");
            invalidInput.setVisible(true);
        }

    }


}
