package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Album;

public class inventorycontroller {

    // Deklarasi elemen-elemen FXML
    @FXML
    private TableView<Album> albumTable;
    @FXML
    private TableColumn<Album, String> colAlbumName;
    @FXML
    private TableColumn<Album, String> colArtist;
    @FXML
    private TableColumn<Album, Integer> colTotal;
    @FXML
    private TableColumn<Album, Integer> colAvailable;

    @FXML
    private TextField nameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField availableField;

    // List data album
    private ObservableList<Album> albumList;

    @FXML
    public void initialize() {
        // Inisialisasi tabel dan pengaturan kolom
        albumList = FXCollections.observableArrayList();
        colAlbumName.setCellValueFactory(new PropertyValueFactory<>("albumName"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

        // Tambahkan data ke tabel
        albumTable.setItems(albumList);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            String albumName = nameField.getText();
            String artist = artistField.getText();
            int total = Integer.parseInt(totalField.getText());
            int available = Integer.parseInt(availableField.getText());

            if (albumName.isEmpty() || artist.isEmpty()) {
                showAlert("Input Error", "Nama album dan artis tidak boleh kosong.");
                return;
            }

            if (total < 0 || available < 0 || available > total) {
                showAlert("Input Error", "Jumlah total dan tersedia harus valid.");
                return;
            }

            Album newAlbum = new Album(albumName, artist, total, available);
            albumList.add(newAlbum);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Total dan tersedia harus berupa angka.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Album selectedAlbum = albumTable.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            albumList.remove(selectedAlbum);
        } else {
            showAlert("Selection Error", "Pilih album yang ingin dihapus.");
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        Album selectedAlbum = albumTable.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            try {
                String albumName = nameField.getText();
                String artist = artistField.getText();
                int total = Integer.parseInt(totalField.getText());
                int available = Integer.parseInt(availableField.getText());

                if (albumName.isEmpty() || artist.isEmpty()) {
                    showAlert("Input Error", "Nama album dan artis tidak boleh kosong.");
                    return;
                }

                if (total < 0 || available < 0 || available > total) {
                    showAlert("Input Error", "Jumlah total dan tersedia harus valid.");
                    return;
                }

                selectedAlbum.setAlbumName(albumName);
                selectedAlbum.setArtist(artist);
                selectedAlbum.setTotal(total);
                selectedAlbum.setAvailable(available);

                albumTable.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Total dan tersedia harus berupa angka.");
            }
        } else {
            showAlert("Selection Error", "Pilih album yang ingin diperbarui.");
        }
    }

    @FXML
    private void handleRent(ActionEvent event) {
        Album selectedAlbum = albumTable.getSelectionModel().getSelectedItem();
        if (selectedAlbum != null) {
            if (selectedAlbum.getAvailable() > 0) {
                selectedAlbum.setAvailable(selectedAlbum.getAvailable() - 1);
                albumTable.refresh();
            } else {
                showAlert("Rent Error", "Stok album tidak tersedia.");
            }
        } else {
            showAlert("Selection Error", "Pilih album yang ingin disewa.");
        }
    }

    private void clearFields() {
        nameField.clear();
        artistField.clear();
        totalField.clear();
        availableField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
