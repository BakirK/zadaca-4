package ba.unsa.etf.rs.zadaca4;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    private ObservableList<Place> places;

    @FXML
    public void initialize() {
        places = dao.getPlaces();
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
            jmbgField.getStyleClass().add("fieldIncorrect");

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

        addressPlace.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(t1 instanceof Place) {
                    postalNumberField.setText(((Place) t1).getPostalNumber());
                }
            }
        });

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
                dateField.setPromptText("d/M/yyyy");
            }
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
                    return dateFormat.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String s) {
                if (s != null && !s.isEmpty()) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
                    return LocalDate.parse(s, dateFormat);
                } else {
                    return null;
                }
            }
        });

        dateField.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(t1) {
                            dateField.getEditor().selectAll();
                        }
                    }
                });
            }
        });

/*
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.trim().isEmpty()) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                } else {
                    checkJMBG(t1);
                }
            }
        });*/
/*
        addressPlace.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(checkPlace(addressPlace, false) == null) {
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
                if(checkPlace(placeOfBirth, true) == null) {
                    placeOfBirth.getStyleClass().removeAll("fieldCorrect");
                    placeOfBirth.getStyleClass().add("fieldIncorrect");
                    birthPlaceCorrectInput = false;
                } else {
                    placeOfBirth.getStyleClass().removeAll("fieldIncorrect");
                    placeOfBirth.getStyleClass().add("fieldCorrect");
                    birthPlaceCorrectInput = true;
                }
            }
        });*/
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
    private void checkJMBG(String jmbg) {
        if (jmbg.length() != 13) {
            jmbgField.getStyleClass().removeAll("fieldCorrect");
            jmbgField.getStyleClass().add("fieldIncorrect");
            jmbgCorrectInput = false;
        }
        if(!jmbg.isEmpty()) {
            try {
                int day = Integer.parseInt(jmbg.substring(0, 2));
                int month = Integer.parseInt(jmbg.substring(2, 4));
                int year = Integer.parseInt(jmbg.substring(4, 7));
                if (year <= 18) {
                    year += 2000;
                } else {
                    year += 1000;
                }
                if (!checkDate(day, month, year)) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                }
                LocalDate date = dateField.getValue();
                if (date.isAfter(LocalDate.now())) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                }
                if (date.getDayOfMonth() != day) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                }
                if (date.getYear() != year) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                }
                int[] numbers = new int[13];
                for (int i = 0; i < 13; i++) {
                    numbers[i] = Integer.parseInt(jmbg.substring(i, i + 1));
                }
                int sum = 7 * (numbers[0] + numbers[6]) + 6 * (numbers[1] + numbers[7]) + 5 * (numbers[2] + numbers[8]) + 4 *
                        (numbers[3] + numbers[9]) + 3 * (numbers[4] + numbers[10]) + 2 * (numbers[5] + numbers[11]);
                sum = 11 - (sum % 11);
                if (sum == 10 || sum == 11) {
                    sum = 0;
                }
                if (numbers[12] != sum) {
                    jmbgField.getStyleClass().removeAll("fieldCorrect");
                    jmbgField.getStyleClass().add("fieldIncorrect");
                    jmbgCorrectInput = false;
                }
                else {
                    jmbgField.getStyleClass().removeAll("fieldIncorrect");
                    jmbgField.getStyleClass().add("fieldCorrect");
                    jmbgCorrectInput = true;
                }

            }
            catch (Exception e) {
                System.out.println("belaj");
                jmbgField.getStyleClass().removeAll("fieldCorrect");
                jmbgField.getStyleClass().add("fieldIncorrect");
                jmbgCorrectInput = false;
            }
        }
        else {
            jmbgCorrectInput = false;
        }
    }

    @FXML
    private void validateInput(ActionEvent actionEvent) {
        Place p1 = checkPlace(placeOfBirth, true);
        Place p2 = checkPlace(addressPlace, false);
        checkJMBG(jmbgField.getText());

        if(nameCorrectInput && surnameCorrectInput && parentNameCorrectInput &&
                addressCorrectInput && birthdayCorrectInput && jmbgCorrectInput &&
                birthPlaceCorrectInput && addressPlaceCorrectInput && postalNumberCorrectInput) {
            boolean adding = false;
            if(owner == null) {
                owner = new Owner();
                adding = true;
            }
            owner.setName(nameField.getText());
            owner.setSurname(surnameField.getText());
            owner.setParentName(parentNameField.getText());
            owner.setJmbg(jmbgField.getText());
            owner.setPlaceOfBirth(p1);
            owner.setLivingPlace(p2);
            owner.setLivingAddress(addressField.getText());
            owner.setDateOfBirth(dateField.getValue());
            if(adding) {
                dao.addOwner(owner);
            } else {
                dao.changeOwner(owner);
            }

            zatvoriProzorPropuhJe(actionEvent);
        }

    }

    @FXML
    private void zatvoriProzorPropuhJe(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


    private Place checkPlace(ComboBox comboBox, boolean first) {
        if (comboBox.getValue() == null) {
            comboBox.getStyleClass().removeAll("fieldCorrect");
            comboBox.getStyleClass().add("fieldIncorrect");
            if(first) birthPlaceCorrectInput = false;
            else addressPlaceCorrectInput = false;
            return null;
        }

        Place place = null;
        String name = comboBox.getValue().toString().trim();
        for (Place p : dao.getPlaces()) {
            if (p.getName().equals(name)) {
                place = p;
                break;
            }
        }
        if (place == null) {
            if (name.trim().isEmpty()) {
                comboBox.getStyleClass().removeAll("fieldCorrect");
                comboBox.getStyleClass().add("fieldIncorrect");
                if(first) birthPlaceCorrectInput = false;
                else addressPlaceCorrectInput = false;
            } else {
                comboBox.getStyleClass().removeAll("fieldIncorrect");
                comboBox.getStyleClass().add("fieldCorrect");
                if(first) birthPlaceCorrectInput = true;
                else addressPlaceCorrectInput = true;
                if(!first) {
                    if (checkPostalNumber()) {
                        place = new Place(-1, name, postalNumberField.getText().trim());
                        postalNumberCorrectInput = true;
                        postalNumberField.getStyleClass().removeAll("fieldIncorrect");
                        postalNumberField.getStyleClass().add("fieldCorrect");
                    } else {
                        postalNumberCorrectInput = false;
                        comboBox.getStyleClass().removeAll("fieldCorrect");
                        comboBox.getStyleClass().add("fieldIncorrect");
                    }
                } else {
                    place = new Place(-1, name, "");
                    postalNumberCorrectInput = true;
                    postalNumberField.getStyleClass().removeAll("fieldIncorrect");
                    postalNumberField.getStyleClass().add("fieldCorrect");
                }
            }
        } else {
            comboBox.getStyleClass().removeAll("fieldIncorrect");
            comboBox.getStyleClass().add("fieldCorrect");
            if(first) birthPlaceCorrectInput = true;
            else addressPlaceCorrectInput = true;
                if(!first) {
                    if (!postalNumberField.getText().equals(place.getPostalNumber()) && !postalNumberField.getText().isEmpty()) {
                        comboBox.getStyleClass().removeAll("fieldCorrect");
                        comboBox.getStyleClass().add("fieldIncorrect");
                        if(first) birthPlaceCorrectInput = false;
                        else addressPlaceCorrectInput = false;
                        return null;
                    } else {
                        postalNumberCorrectInput = true;
                    }
                } else {
                    postalNumberCorrectInput = true;

                }
        }
        return place;
    }

    private boolean checkPostalNumber() {
        if(!postalNumberField.getText().isEmpty()) {
            String adresa = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=";
            adresa += postalNumberField.getText().trim();
            try {
                URL url = new URL(adresa);
                BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String json = "", line = null;
                while ((line = input.readLine()) != null) {
                    json += line;
                }
                input.close();
                if (json.equals("OK")) {
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e) {
                System.out.println("String " + adresa + " ne predstavlja validan URL");
            } catch (IOException e) {
                System.out.println("Greška pri kreiranju ulaznog toka");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Poteškoće sa obradom JSON podataka");
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
