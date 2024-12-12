import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainAppController {

    @FXML
    private TableView<Task> taskTable; // Tabel untuk menampilkan daftar tugas
    @FXML
    private TableColumn<Task, String> titleCol, priorityCol, statusCol; // Kolom untuk judul, prioritas, dan status tugas
    @FXML
    private TableColumn<Task, LocalDate> dueDateCol; // Kolom untuk tanggal jatuh tempo
    @FXML
    private TextField titleField; // Field untuk memasukkan judul tugas
    @FXML
    private ComboBox<String> priorityBox; // ComboBox untuk memilih prioritas
    @FXML
    private DatePicker dueDatePicker; // DatePicker untuk memilih tanggal jatuh tempo

    private ObservableList<Task> taskList; // Daftar tugas yang dapat diamati

    @FXML
    public void initialize() {
        taskList = FXCollections.observableArrayList(TaskFileManager.loadTasks());



        // TODO: konfigurasi kolom tabel untuk setiap atribut task
    
        // hint: gunakan setCellValueFactory dan PropertyValueFactory
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // TODO: konfigurasi lebar kolom agar lebih rapi
        titleCol.setPrefWidth(200);
        priorityCol.setPrefWidth(100);
        statusCol.setPrefWidth(100);
        dueDateCol.setPrefWidth(100);

        // TODO: Klik pada baris tugas dalam tabel akan mengubah status menjadi "Completed"
        taskTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 &&!taskTable.getSelectionModel().isEmpty()) {
                Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
                selectedTask.setStatus("Completed");
                TaskFileManager.saveTasks(new ArrayList<>(taskList));
            }
        });

        // TODO: Ubah data tugas saat baris terpilih diubah
        taskTable.setEditable(true);
    

        // hint: gunakan setPrefWidth

        taskTable.setItems(taskList);


        // TODO: Tambahkan options prioritas ke ComboBox
        priorityBox.getItems().addAll("X", "X", "X");
    }

    @FXML
    private void addTask() {
        try {
            if (titleField.getText().isEmpty() || priorityBox.getValue() == null || dueDatePicker.getValue() == null) {
                throw new MissingFieldException("All fields must be filled");
            }

            if (dueDatePicker.getValue().isBefore(LocalDate.now())) {
                throw new InvalidDateException("Due date cannot be in the past");
            }

            Task task = new Task(
                titleField.getText(),
                priorityBox.getValue(),
                dueDatePicker.getValue(),
                "Incomplete"
            );

            taskList.add(task);

            clearData();
            TaskFileManager.saveTasks(new ArrayList<>(taskList));
        } catch (MissingFieldException | InvalidDateException e) {
            showError(e.getMessage());
        }
    }

    class MissingFieldException extends Exception {
        public MissingFieldException(String message) {
            super(message);
        }
    }

    class InvalidDateException extends Exception {
        public InvalidDateException(String message) {
            super(message);
        }
    }

    // TODO: hapus semua value dari field pada form
    // hint: gunakan function getSelectionModel().clearSelection() untuk ComboBox dan getEditor().clear() untuk DatePicker
    private void clearData(){

    }

    @FXML
    private void deleteTask() {
        try {
            Task selected = taskTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("No task selected for deletion");
            }

            taskList.remove(selected);
            TaskFileManager.saveTasks(new ArrayList<>(taskList));
        } catch (TaskNotSelectedException e) {
            showError(e.getMessage());
        }
    }

    class TaskNotSelectedException extends Exception {
        public TaskNotSelectedException(String message) {
            super(message);
        }
    }

    @FXML
    private void markTaskComplete() {
        try {
            Task selected = taskTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                throw new TaskNotSelectedException("No task selected to mark as complete");
            }
            selected.setStatus("Complete");
            taskTable.refresh();
            TaskFileManager.saveTasks(new ArrayList<>(taskList));
        } catch (TaskNotSelectedException e) {
            showError(e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    public void setMainApp(Main main) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMainApp'");
    }

    // TODO: Buat kelas MissingFieldException

    // TODO: Buat kelas InvalidTaskException
    
    // TODO: Buat kelas TaskNotSelectedException

    // TODO: Buat kelas InvalidDateException

    // TODO: Buat kelas MissingDateException

}