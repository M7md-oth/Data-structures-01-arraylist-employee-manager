package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {

	ArrayList<employee> arr = new ArrayList<>();
	employee m = new employee();
	int count = 0;

	public void start(Stage primaryStage) {

		BorderPane Border = new BorderPane();
		Label l2 = new Label("Welcome (project one)");
		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");

		MenuBar MainBar = new MenuBar();
		Menu Zero = new Menu("Other");
		MenuItem Save = new MenuItem("Save To File");
		MenuItem Load = new MenuItem("Read Screen");
		MenuItem tabelv = new MenuItem("Tabel view");

		Zero.getItems().addAll(Load, Save, tabelv);

		Menu TOW = new Menu("OP");
		MenuItem ins = new MenuItem("Insert");
		MenuItem del = new MenuItem("Delete");
		MenuItem ser = new MenuItem("Search");
		MenuItem dis = new MenuItem("Display");
		TOW.getItems().addAll(ins, del, ser, dis);

		MainBar.getMenus().addAll(TOW, Zero);
		Border.setTop(MainBar);
		Border.setStyle("-fx-background-color: gray;");
		Border.setCenter(l2);

		Save.setOnAction(e -> {
			try (PrintWriter w = new PrintWriter(new FileWriter("C:\\Users\\mOhS2\\OneDrive\\Desktop\\Data Structure\\out.txt"))) {
				for (employee t : arr) {
					w.println(t.getId() + "," + t.getName() + "," + t.getAge() + "," + t.getDep() + "," + t.getDate()
							+ "," + t.getGender());

				}
			} catch (IOException es) {
				es.printStackTrace();
			}
		});
		tabelv.setOnAction(e -> {
			tabelview(primaryStage);
		});
		ins.setOnAction(e -> {
			Insert(primaryStage);
		});

		dis.setOnAction(e -> {
			display(primaryStage);

		});

		del.setOnAction(e -> {
			delete(primaryStage);
		});
		Load.setOnAction(e -> {
			read(primaryStage);
		});
		ser.setOnAction(e -> {
			search(primaryStage);
		});
		Scene scene = new Scene(Border, 650, 450);
		primaryStage.setTitle("Main screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void read(Stage readStage) {
		TextArea T1 = new TextArea();
		T1.setPrefHeight(400);
		Button Back = new Button("Back to the main screen");
		Button Load = new Button("Load data ");
		Label l2 = new Label("Read data screen");
		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");
		VBox layout = new VBox(20);
		Load.setOnAction(e -> {
			Load(readStage, T1);
		});
		Back.setOnAction(e -> {
			try {
				start(readStage);
			} catch (Exception e1) {
				System.out.println("mo");
			}
		});
		layout.getChildren().addAll(l2, T1, Load, Back);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setStyle("-fx-background-color: gray;");
		Scene scene = new Scene(layout, 700, 550);
		readStage.setTitle("Read data screen");
		readStage.setScene(scene);
		readStage.show();
	}

	public void Load(Stage readStage, TextArea T1) {
		FileChooser Chooser = new FileChooser();
		File file = Chooser.showOpenDialog(readStage);
		if (file != null) {
			StringBuilder sb = new StringBuilder();
			try (Scanner scanner = new Scanner(file)) {
				if (scanner.hasNextLine()) {
					scanner.nextLine();
				}

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] data = line.split(",");

					String ID = data[0];
					String name = data[1];
					int age = Integer.parseInt(data[2]);
					String dep = data[3];
					String date = data[4];
					String gender = data[5];
					m = new employee(ID, name, age, dep, date, gender);
					sb.append(m.toString() + "\n");
					arr.add(m);
				}

				T1.setText(sb.toString());
			} catch (IOException e) {
				System.out.println("error");
			}

		}
	}

	public void display(Stage insStage) {
		Label l = new Label("");
		Label l2 = new Label("Display screen");
		TextArea tt = new TextArea();
		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");
		VBox VB = new VBox(10);
		HBox HB = new HBox(10);
		HBox HB2 = new HBox(10);
		VBox v2 = new VBox(10);
		VBox v3 = new VBox(10);

		Button Back = new Button("Back");
		Button ByAge = new Button("Display by age");
		TextField age = new TextField();

		Button ByGen = new Button("Display by Gen");
		TextField gen = new TextField();

		Button ByDep = new Button("Display by dep");
		TextField dep = new TextField();

		ByAge.setOnAction(e -> {
			age.getText();

			for (employee t : arr) {
				count = 0;
				int m = t.getAge();

				for (employee s : arr) {
					if (m == s.getAge()) {
						count++;
					}
				}
				tt.setText("the number of employee in this age " + m + " is " + count + "\n");

			}

		});
		ByGen.setOnAction(e -> {
			String a = gen.getText();
			for (employee t : arr) {
				if (a.equalsIgnoreCase(t.getGender())) {
					count++;
				}
			}

			tt.setText("The number of  " + a + " is " + count);
			count = 0;
		});

		ByDep.setOnAction(e -> {
			String a = dep.getText();
			for (employee t : arr) {
				if (a.equalsIgnoreCase(t.getDep())) {
					count++;
				}
			}

			tt.setText("The number of department " + a + "  is " + count);
			count = 0;
		});

		Back.setOnAction(e -> {
			try {
				start(insStage);
			} catch (Exception e1) {
				System.out.println("Error returning to main screen");
			}
		});

		v2.getChildren().addAll(ByAge, ByGen, ByDep);
		v3.getChildren().addAll(age, gen, dep);
		HB2.getChildren().addAll(v2, v3);
		HB.getChildren().addAll(Back);
		HB.setAlignment(Pos.BASELINE_CENTER);
		VB.getChildren().addAll(l2, tt, HB2, HB, l);
		VB.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(VB, 600, 350);
		VB.setStyle("-fx-background-color: gray;");
		insStage.setTitle("Insert New employee");
		insStage.setScene(scene);
		insStage.show();
	}

	public void Insert(Stage insStage) {
		Label l = new Label("");
		Label l2 = new Label("Insert screen");

		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");
		VBox VB = new VBox(10);
		HBox HB = new HBox(10);
		HBox HB2 = new HBox(10);

		TextField TF1 = new TextField();
		TF1.setPromptText("Enter Employee ID");

		TextField TF2 = new TextField();
		TF2.setPromptText("Enter Employee Name");

		TextField TF3 = new TextField();
		TF3.setPromptText("Enter Employee age");

		ToggleGroup to = new ToggleGroup();
		RadioButton m2 = new RadioButton("Male");
		RadioButton f = new RadioButton("Female");
		m2.setToggleGroup(to);
		f.setToggleGroup(to);

		TextField TF5 = new TextField();
		TF5.setPromptText("Enter Employee department");

		TextField TF6 = new TextField();
		TF6.setPromptText("Enter Employee date");

		Button submit = new Button("Insert");
		Button Back = new Button("Back");

		submit.setOnAction(e -> {
			String ID = TF1.getText();
			String date = TF6.getText();
			String name = TF2.getText();
			int age = Integer.parseInt(TF3.getText());
			Toggle gen = to.getSelectedToggle();
			String gender = ((RadioButton) gen).getText();
			String dep = TF5.getText();
			m = new employee(ID, name, age, dep, date, gender);

			boolean exists = false;
			for (employee t : arr) {
				if (ID.equalsIgnoreCase(t.getId())) {
					exists = true;
					break;
				}
			}
			if (exists) {
				l.setText("The employee is already added");
			} else {
				arr.add(m);
				l.setText("The employee is added successfully");
			}
			TF1.clear();
			TF2.clear();
			TF3.clear();
			TF5.clear();
			TF6.clear();
		});

		Back.setOnAction(e -> {
			try {
				start(insStage);
			} catch (Exception e1) {
				System.out.println("Error returning to main screen");
			}
		});
		HB2.getChildren().addAll(m2, f);
		HB2.setAlignment(Pos.CENTER);
		HB.getChildren().addAll(submit, Back);
		HB.setAlignment(Pos.BASELINE_CENTER);
		VB.getChildren().addAll(l2, TF1, TF2, TF3, TF5, TF6, HB2, HB, l);
		VB.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(VB, 600, 350);
		VB.setStyle("-fx-background-color: gray;");
		insStage.setTitle("Insert New employee");
		insStage.setScene(scene);
		insStage.show();
	}

	public void delete(Stage insStage) {
		Label l = new Label("");
		Label l2 = new Label("Delete screen");
		Label l1 = new Label("  Enter employee ID  ");
		TextArea text = new TextArea();

		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");
		VBox VB = new VBox(10);

		TextField TF1 = new TextField();
		TF1.setPromptText("Enter Employee ID");
		HBox HB = new HBox(10);

		Button submit = new Button("Delete");
		Button Back = new Button("Back");

		submit.setOnAction(e -> {
			text.clear();
			String ID = TF1.getText();
			TF1.clear();
			boolean isDeleted = false;
			for (int i = arr.size() - 1; i >= 0; i--) {
				employee t = arr.get(i);
				if (ID.equalsIgnoreCase(t.getId())) {

					text.setText("The emloyee : " + arr.get(i) + "Is deleted ");
					arr.remove(i);
					isDeleted = true;
					l.setText("The employee is deleted successfully");
					break;
				}
			}
			if (!isDeleted) {
				text.setText("The emloyee : " + ID + " Is not found ");
				l.setText("Error, the ID is not found");
			}
		});

		Back.setOnAction(e -> {
			try {
				start(insStage);
			} catch (Exception e1) {
				System.out.println("Error returning to main screen");
			}
		});
		HBox HB2 = new HBox(10);
		HB2.getChildren().addAll(submit, Back);
		HB2.setAlignment(Pos.BASELINE_CENTER);
		HB.getChildren().addAll(l1, TF1);
		VB.getChildren().addAll(l2, HB, text, HB2, l);
		VB.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(VB, 600, 350);
		VB.setStyle("-fx-background-color: gray;");
		insStage.setTitle("Delete employee");
		insStage.setScene(scene);
		insStage.show();
	}

	public void search(Stage insStage) {
		TextArea T1 = new TextArea();
		T1.setPrefHeight(400);
		Label l1 = new Label("Enter ID To search   ");
		Label l2 = new Label("Search screen");

		l2.setFont(new Font("Verdana", 24));
		l2.setStyle("-fx-text-fill: white;");
		VBox VB = new VBox(10);
		HBox h1 = new HBox(10);

		TextField TF1 = new TextField();
		TF1.setPromptText("Enter Employee ID");
		HBox h = new HBox(l1, TF1);
		Button submit = new Button("Search");
		Button Back = new Button("Back");

		submit.setOnAction(e -> {
			String ID = TF1.getText();
			TF1.clear();

			boolean isDeleted = false;
			for (int i = arr.size() - 1; i >= 0; i--) {
				employee t = arr.get(i);
				if (ID.equalsIgnoreCase(t.getId())) {

					T1.setText("The emloyee with the input id :\n " + arr.get(i));
					isDeleted = true;
					break;
				}
			}
			if (!isDeleted) {
				T1.setText("The emloyee : " + ID + " Is not found ");
			}
		});
		Back.setOnAction(e -> {
			try {
				start(insStage);
			} catch (Exception e1) {
				System.out.println("Error returning to main screen");
			}
		});
		h1.getChildren().addAll(submit, Back);
		h1.setAlignment(Pos.BASELINE_CENTER);
		VB.getChildren().addAll(l2, h, T1, h1);
		VB.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(VB, 600, 350);
		VB.setStyle("-fx-background-color: gray;");
		insStage.setTitle("Search employee");
		insStage.setScene(scene);
		insStage.show();
	}

	public void tabelview(Stage tabel) {
		TableView<employee> tableView = new TableView<>();
		Button b = new Button("Back");

		TableColumn<employee, String> id = new TableColumn<>("ID");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<employee, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<employee, Integer> age = new TableColumn<>("Age");
		age.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<employee, String> dep = new TableColumn<>("Department");
		dep.setCellValueFactory(new PropertyValueFactory<>("department"));
		TableColumn<employee, String> date = new TableColumn<>("Date");
		date.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<employee, String> gender = new TableColumn<>("Gender");
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

		tableView.getColumns().addAll(id, name, age, dep, date, gender);
		tableView.getItems().addAll(arr);

		b.setOnAction(e -> {
			try {
				start(tabel);
			} catch (Exception e1) {
				System.out.println("Error returning to main screen");
			}
		});
		VBox vbox = new VBox(tableView,b);
		Scene scene = new Scene(vbox);
		tabel.setScene(scene);
		tabel.setTitle("Employee Table View");
		tabel.setWidth(600);
		tabel.setHeight(350);
		tabel.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}