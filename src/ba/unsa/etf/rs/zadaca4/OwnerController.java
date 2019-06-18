package ba.unsa.etf.rs.zadaca4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OwnerController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField parentNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField jmbgField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox placeOfBirth;
    @FXML
    private ComboBox addressPlace;
    @FXML
    private TextField postalNumberField;
    private Owner owner;
    private VehicleDAO dao;
    private boolean nameCorrectInput = false, surnameCorrectInput = false, parentNameCorrectInput = false,
            addressCorrectInput = false, birthdayCorrectInput = true, jmbgCorrectInput = false,
        birthPlaceCorrectInput = false, addressPlaceCorrectInput = false, postalNumberCorrectInput = false;
    public OwnerController(VehicleDAO dao, Owner owner) {
        this.owner = owner;
        this.dao = dao;
    }


    @FXML
    public void initialize() {
        ObservableList<Place> places = dao.getPlaces();
        placeOfBirth.setItems(places);
        addressPlace.setItems(places);

        if (owner == null) {
            nameField.setText("");
            nameField.getStyleClass().add("fieldIncorrect");

            surnameField.setText("");
            surnameField.getStyleClass().add("fieldIncorrect");

            parentNameField.setText("");
            parentNameField.getStyleClass().add("fieldIncorrect");

            addressField.setText("");
            addressField.getStyleClass().add("fieldIncorrect");

            jmbgField.setText("");
            jmbgField.getStyleClass().add("fieldCorrect");

            dateField.setValue(LocalDate.now());
            dateField.getEditor().getStyleClass().add("fieldIncorrect");

            placeOfBirth.setValue("");
            placeOfBirth.getEditor().getStyleClass().add("fieldIncorrect");

            addressPlace.setValue("");
            addressPlace.getEditor().getStyleClass().add("fieldIncorrect");

            postalNumberField.setText("");
            postalNumberField.getStyleClass().add("fieldIncorrect");
        } else {
            nameField.setText(owner.getName());
            nameField.getStyleClass().add("fieldCorrect");
            nameCorrectInput = true;

            surnameField.setText(owner.getSurname());
            surnameField.getStyleClass().add("fieldCorrect");
            surnameCorrectInput = true;

            parentNameField.setText(owner.getParentName());
            parentNameField.getStyleClass().add("fieldCorrect");
            parentNameCorrectInput = true;

            addressField.setText(owner.getLivingAddress());
            addressField.getStyleClass().add("fieldCorrect");
            addressCorrectInput = true;

            jmbgField.setText(owner.getJmbg());
            jmbgField.getStyleClass().add("fieldCorrect");
            jmbgCorrectInput = true;

            dateField.setValue(owner.getDateOfBirth());
            dateField.getStyleClass().add("fieldCorrect");

            placeOfBirth.setValue(owner.getPlaceOfBirth().getName());
            placeOfBirth.getStyleClass().add("fieldCorrect");
            birthPlaceCorrectInput = true;

            addressPlace.setValue(owner.getLivingPlace().getName());
            addressPlace.getStyleClass().add("fieldCorrect");
            addressPlaceCorrectInput = true;

            postalNumberField.setText(owner.getLivingPlace().getPostalNumber());
            postalNumberField.getStyleClass().add("fieldCorrect");
            postalNumberCorrectInput = true;

        }

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    nameField.getStyleClass().removeAll("fieldCorrect");
                    nameField.getStyleClass().add("fieldIncorrect");
                    nameCorrectInput = false;
                } else {
                    nameField.getStyleClass().removeAll("fieldIncorrect");
                    nameField.getStyleClass().add("fieldCorrect");
                    nameCorrectInput = true;
                }
            }
        });

        surnameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    surnameField.getStyleClass().removeAll("fieldCorrect");
                    surnameField.getStyleClass().add("fieldIncorrect");
                    surnameCorrectInput = false;
                } else {
                    surnameField.getStyleClass().removeAll("fieldIncorrect");
                    surnameField.getStyleClass().add("fieldCorrect");
                    surnameCorrectInput = true;
                }
            }
        });

        parentNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    parentNameField.getStyleClass().removeAll("fieldCorrect");
                    parentNameField.getStyleClass().add("fieldIncorrect");
                    parentNameCorrectInput = false;
                } else {
                    parentNameField.getStyleClass().removeAll("fieldIncorrect");
                    parentNameField.getStyleClass().add("fieldCorrect");
                    parentNameCorrectInput = true;
                }
            }
        });

        addressField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    addressField.getStyleClass().removeAll("fieldCorrect");
                    addressField.getStyleClass().add("fieldIncorrect");
                    addressCorrectInput = false;
                } else {
                    addressField.getStyleClass().removeAll("fieldIncorrect");
                    addressField.getStyleClass().add("fieldCorrect");
                    addressCorrectInput = true;
                }
            }
        });

        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                } else {
                    jmbgField.getStyleClass().removeAll("fieldIncorrect");
                    jmbgField.getStyleClass().add("fieldCorrect");
                    jmbgCorrectInput = true;
                }
            }
        });



        dateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                if (t1.isAfter(LocalDate.now()) || t1 == null) {
                    dateField.getEditor().getStyleClass().removeAll("fieldCorrect");
                    dateField.getEditor().getStyleClass().add("fieldIncorrect");
                    birthdayCorrectInput= false;
                } else {
                    dateField.getEditor().getStyleClass().removeAll("fieldIncorrect");
                    dateField.getEditor().getStyleClass().add("fieldCorrect");
                    birthdayCorrectInput = true;
                }
            }
        });


        dateField.setConverter(new StringConverter<LocalDate>() {
            {
                dateField.setPromptText("dd. MM. yyyy");
            }
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd. MM. yyyy");
                    return dateFormat.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String s) {
                if (s != null && !s.isEmpty()) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd. MM. yyyy");
                    return LocalDate.parse(s, dateFormat);
                } else {
                    return null;
                }
            }
        });

        addressPlace.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(t1 == null) {
                    addressPlace.getStyleClass().removeAll("fieldCorrect");
                    addressPlace.getStyleClass().add("fieldIncorrect");
                    addressPlaceCorrectInput = false;
                } else {
                    addressPlace.getStyleClass().removeAll("fieldIncorrect");
                    addressPlace.getStyleClass().add("fieldCorrect");
                    addressPlaceCorrectInput = true;
                }
            }
        });


        placeOfBirth.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(t1 == null) {
                    placeOfBirth.getStyleClass().removeAll("fieldCorrect");
                    placeOfBirth.getStyleClass().add("fieldIncorrect");
                    birthPlaceCorrectInput = false;
                } else {
                    placeOfBirth.getStyleClass().removeAll("fieldIncorrect");
                    placeOfBirth.getStyleClass().add("fieldCorrect");
                    birthPlaceCorrectInput = true;
                }
            }
        });



    }


    private boolean checkDate(int d, int m, int g) {
        int[] broj_dana = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(g % 4 == 0 && (g % 100 != 0 || g % 400 == 0)) {
        broj_dana[1]++;
        }
        if(g < 1 || d < 1 || m < 1 || m > 12 || d > broj_dana[m - 1] ) {
            return false;
        }
        return true;
}
    private boolean checkJMBG(String jmbg) {
        if (jmbg.length() != 13) {
            return false;
        }

        int day = Integer.parseInt(jmbg.substring(0, 2));
        int month = Integer.parseInt(jmbg.substring(2, 4));
        int year = Integer.parseInt(jmbg.substring(4, 7));
        if (year > 500) {
            year += 1000;
        } else {
            year += 2000;
        }
        if(!checkDate(day, month, year)) {
            return false;
        }


        LocalDate date = dateField.getValue();
        if (date.getDayOfMonth() != day) {
            return false;
        }
        if (date.getYear() != year) {
            return false;
        }
        int[] br = new int[13];
        for (int i=0; i<13; i++) {
            br[i] = Integer.parseInt(jmbg.charAt(i));
            br[i] -= 48;
        }
        int sum = 7*(br[0]+br[6]) + 6*(br[1]+br[7]) + 5*(br[2]+br[8]) + 4*(br[3]+br[9]) + 3*(br[4]+br[10]) + 2*(br[5]+br[11]);
        sum = 11 - (sum % 11);
        if (sum > 9) sum = 0;
        if (br[12] != sum) return false;
        return true;
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
    }
}
