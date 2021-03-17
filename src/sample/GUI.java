package sample;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//user interface which displays list of members in a table

public class GUI extends MyGymManager{

    public static TableView tableView = new TableView();


    public static void View() throws Exception {

        Stage stageDetail = new Stage();

        Scene sceneDetails = new Scene(new Group());
        stageDetail.setTitle("Membership Details");
        stageDetail.setWidth(800);
        stageDetail.setHeight(500);

        Label labelTitle = new Label("Gym Membership Details");
        labelTitle.setLayoutY(10);
        labelTitle.setLayoutX(30);
        labelTitle.setFont(new Font( 20));


        /*Assigning values for columns
        *getting values from the classes
        */
        TableColumn<DefaultMember, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));

        TableColumn<DefaultMember, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DefaultMember, String> colSchool = new TableColumn<>("School");
        colSchool.setCellValueFactory(new PropertyValueFactory<>("school"));

        TableColumn<DefaultMember, String> colAge = new TableColumn<>("Age");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<DefaultMember, String> colDate = new TableColumn<>("Starting Date");
        colDate.setCellValueFactory(new PropertyValueFactory<>("membershipStartDate"));

        TableColumn<DefaultMember, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(new PropertyValueFactory<>("memberType"));

        //Adding columns for table
        tableView.getColumns().addAll(colID, colName, colSchool,colAge,colDate,colType);
        tableView.setItems(MyGymManager.getGymMember());

        //Searching field
        TextField search = new TextField();
        search.setPromptText("Enter the id here");
        search.setLayoutX(500);
        search.setLayoutY(20);

        //set the sizes
        final VBox vboxTable = new VBox();
        vboxTable.setSpacing(5);
        vboxTable.setPadding(new Insets(15, 10, 10, 10));
        vboxTable.getChildren().addAll(labelTitle, tableView,search);
        vboxTable.setPrefWidth(780);
        vboxTable.setPrefHeight(500);
        colAge.setPrefWidth(125);
        colDate.setPrefWidth(135);
        colName.setPrefWidth(125);
        colID.setPrefWidth(125);
        colSchool.setPrefWidth(125);
        colType.setPrefWidth(125);

        ((Group) sceneDetails.getRoot()).getChildren().addAll(vboxTable,search);

        stageDetail.setScene(sceneDetails);
        stageDetail.show();
    }






}
