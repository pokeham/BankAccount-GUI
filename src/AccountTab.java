import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The AccountTab Class holds the Code necessary for generating an account tab
 */
public class AccountTab {
    /**
     * Label accountName: a private member variable used to store the account name
     */
    @FXML
    private Label accountName;
    /**
     * Label accountBalance: a private member variable used to store the account balance
     */
    @FXML
    private Label accountBalance;
    /**
     * RadioButton SWD: a private member variable used to allow the user to decide whether they want to use SWD or USD
     */
    @FXML
    private RadioButton SWD;
    /**
     * RadioButton USD: a private member variable used to allow the user to decide whether they want to use SWD or USD
     */
    @FXML
    private RadioButton USD;
    /**
     * RadioButton withdrawal: a private member variable used to allow the user to decide whether they want to use deposit or withdrawl
     */
    @FXML
    private RadioButton withdrawal;
    /**
     * RadioButton deposit: a private member variable used to allow the user to decide whether they want to use deposit or withdrawl
     */
    @FXML
    private RadioButton deposit;
    /**
     * TextField transactionAmount: a private member variable used to take in user input concerning the amount they want to transact
     */
    @FXML
    private TextField transactionAmount;
    /**
     * Button transaction: a private member variable used to determine if a user wants to go through with a transaction
     */
    @FXML
    private Button transaction;
    /**
     * AnchorPane anchorPane: a private member variable used to house all the components onto a pane
     */
    @FXML
    private AnchorPane anchorPane;
    /**
     * Label invalid: a private member variable used to show the user they inputted an invalid input
     */
    @FXML
    private Label invalid;

    /**
     * AnchorPane getAnchorPane: returns the private member variable anchor pane
     * @return returns the private member variable anchor pane
     */
    public AnchorPane getAnchorPane(){return anchorPane;}

    /**
     * bank account: a private member variable of type bank used to implement the bank class functionality
     */
    public bank account;
    /**
     * int type: a private member variable that is used to store which radio button is chose
     */
    private int type = 0;
    /**
     * boolean currencyType: a private member variable used to determine the type of currency chose
     */
    private boolean currencyType = false;
    /**
     * boolean clicked: a private member variable used to check if a button was clicked
     */
    private boolean clicked = false;
    /**
     * label overdraft: a private member variable used to show the user the transaction they just entered resuled in an overdraft
     */
    private Label overdraft;
    /**
     * Button close: a private member variable used to store the button to close the current tab
     */
    @FXML
    private Button close;
    /**
     * TextArea change: a private member variable used to display the change after a transaction
     */
    private TextArea change;

    /**
     * The AccountTab constructor generates the account tab and p[laces all components within it and defines their functionality
     * @param account1 a bank object to be assigned to account
     * @param cashier a cashier object to be assigned to cashier
     * @param pane a pane object used to display the components
     * @param tab a Tab object used to hold the Pane
     */
    public AccountTab(bank account1,Cashier cashier,TabPane pane,Tab tab){
        anchorPane = new AnchorPane();
        account = account1;
        accountName = new Label("Account Name: " + account.getName());
        accountName.setLayoutX(50);
        accountName.setLayoutY(25);
        accountName.setFont(new Font("System BOLD",18));
        BigDecimal temp = BigDecimal.valueOf(cashier.getExchangeRate());
        System.out.println(temp);
        accountBalance = new Label("Account Balance: \n" + account.getBalance().toString()+"(USD) : " + (account.getBalance().divide(temp,2,RoundingMode.HALF_UP)) + "(SWD)");
        accountBalance.setLayoutX(50);
        accountBalance.setLayoutY(50);
        accountBalance.setFont(new Font("System BOLD",18));

        ToggleGroup tranactionType = new ToggleGroup();
        withdrawal = new RadioButton("Withdrawal?");
        withdrawal.setLayoutX(65);
        withdrawal.setLayoutY(100);
        withdrawal.setSelected(true);
        withdrawal.setToggleGroup(tranactionType);
        deposit = new RadioButton("Deposit?");
        deposit.setLayoutX(65);
        deposit.setLayoutY(120);
        deposit.setToggleGroup(tranactionType);
        tranactionType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton temp = (RadioButton)tranactionType.getSelectedToggle();
                if(temp != null){
                    System.out.println(temp.getText());
                    if(temp.getText().equals("Deposit?")){
                        type = 1;
                    }
                    else{
                        type = 0;
                    }
                }
            }
        });




        ToggleGroup currency = new ToggleGroup();
        SWD = new RadioButton("SWD?");
        SWD.setLayoutX(65);
        SWD.setLayoutY(160);
        SWD.setToggleGroup(currency);
        USD = new RadioButton("USD?");
        USD.setLayoutX(65);
        USD.setLayoutY(180);
        USD.setSelected(true);
        USD.setToggleGroup(currency);

        currency.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton temp2 = (RadioButton)currency.getSelectedToggle();
                if(temp2 != null){
                    System.out.println(temp2.getText());
                    if(temp2.getText().equals("USD?")){
                        currencyType = false;
                    }else{
                        currencyType =true;
                    }
                }
            }
        });

        transactionAmount = new TextField();
        transactionAmount.setPromptText("eg. 100.01");
        transactionAmount.setLayoutY(220);
        transactionAmount.setLayoutX(65);
        transactionAmount.setMaxWidth(120);
        transactionAmount.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                transaction.setDisable(false);
            }
        });
        transaction = new Button("Submit");
        transaction.setLayoutY(220);
        transaction.setLayoutX(200);
        transaction.setDisable(true);
        invalid = new Label("INVALID INPUT");
        invalid.setTextFill(Color.RED);
        invalid.setFont(new Font("System BOLD",18));
        invalid.setLayoutX(65);
        invalid.setLayoutY(250);
        invalid.setVisible(false);
        overdraft = new Label("OVERDRAFT");
        overdraft.setTextFill(Color.RED);
        overdraft.setFont(new Font("System BOLD", 18));
        overdraft.setLayoutX(65);
        overdraft.setLayoutY(270);
        overdraft.setVisible(false);
        transaction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                change.clear();
                try{
                    int temp2 = cashier.transaction(Float.parseFloat(transactionAmount.getText()),account,currencyType,type);
                    invalid.setVisible(false);
                    if(temp2 == 0){
                        overdraft.setVisible(true);
                    }else{
                        BigDecimal temp3 =BigDecimal.valueOf(Cashier.getExchangeRate());
                        accountBalance.setText("Account Balance: \n" + account.getBalance().toString()+"(USD) : " + (account.getBalance().divide(temp3,2,RoundingMode.HALF_UP)) + "(SWD)");
                        Map<BigDecimal,Integer> map = cashier.calculateChange(new BigDecimal(transactionAmount.getText()),currencyType);
                        String toString = map.entrySet().stream().map(e -> e.getKey()+": "+e.getValue()).collect(Collectors.joining("\n"));
                        change.setText(toString);
                        overdraft.setVisible(false);
                    }
                }
                catch (NumberFormatException e){
                    invalid.setVisible(true);
                }
            }
        });
        close = new Button("Close Account");
        close.setLayoutX(450);
        close.setLayoutY(25);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getTabs().remove(tab);
            }
        });
        change = new TextArea();
        change.setEditable(false);
        change.setLayoutX(350);
        change.setLayoutY(50);
        change.setMaxWidth(210);

        anchorPane.getChildren().add(withdrawal);
        anchorPane.getChildren().add(deposit);
        anchorPane.getChildren().add(accountName);
        anchorPane.getChildren().add(accountBalance);
        anchorPane.getChildren().add(SWD);
        anchorPane.getChildren().add(USD);
        anchorPane.getChildren().add(transactionAmount);
        anchorPane.getChildren().add(transaction);
        anchorPane.getChildren().add(invalid);
        anchorPane.getChildren().add(overdraft);
        anchorPane.getChildren().add(close);
        anchorPane.getChildren().add(change);
    }
}
