package com.example.week6.View;

import com.example.week6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import static com.vaadin.flow.component.notification.Notification.Position.BOTTOM_START;

@Route(value = "index1")
public class MainWizardView extends VerticalLayout {
    private TextField tf1, tf2;
    private RadioButtonGroup<String> rbg;
    private ComboBox<String> cb1, cb2, cb3;
    private Button btn1, btn2, btn3, btn4, btn5;
    private HorizontalLayout hl;
    private Notification nf;
    private int indexOfWizard = -1;

    public MainWizardView() {
        tf1 = new TextField();
        tf2 = new TextField("Dollars");
        rbg = new RadioButtonGroup<>("Gender:");
        cb1 = new ComboBox<>();
        cb2 = new ComboBox<>();
        cb3 = new ComboBox<>();
        btn1 = new Button("<<");
        btn2 = new Button("Create");
        btn3 = new Button("Update");
        btn4 = new Button("Delete");
        btn5 = new Button(">>");
        hl = new HorizontalLayout();

        tf1.setPlaceholder("Fullname");
        tf2.setPrefixComponent(VaadinIcon.DOLLAR.create());
        rbg.setItems("Male", "Female");
        cb1.setItems("Student", "Teacher");
        cb1.setPlaceholder("Position");
        cb2.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        cb2.setPlaceholder("School");
        cb3.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        cb3.setPlaceholder("House");
        hl.add(btn1, btn2, btn3, btn4, btn5);

        this.add(tf1, rbg, cb1, tf2, cb2, cb3, hl);

        btn1.addClickListener(event -> {

            Wizards result = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(Wizards.class)
                    .block();
            if(indexOfWizard == -1 || indexOfWizard == 0){
                indexOfWizard = 0;
            }
            else {
                indexOfWizard--;
            }
            tf1.setValue(result.getModel().get(indexOfWizard).getName());
            tf2.setValue(result.getModel().get(indexOfWizard).getMoney()+"");
            if(result.getModel().get(indexOfWizard).getSex().equals("m")){
                rbg.setValue("Male");
            }
            else if(result.getModel().get(indexOfWizard).getSex().equals("f")){
                rbg.setValue("Female");
            }
            cb1.setValue(result.getModel().get(indexOfWizard).getPosition());
            cb2.setValue(result.getModel().get(indexOfWizard).getSchool());
            cb3.setValue(result.getModel().get(indexOfWizard).getHouse());
        });

        btn2.addClickListener(event -> {
            String name = tf1.getValue();
            String sex = rbg.getValue();
            if(sex.equals("Male")){
                sex = "m";
            }
            else if(sex.equals("Female")){
                sex = "f";
            }
            String position = cb1.getValue();
            if(position.equals("Student")){
                position = "student";
            }
            else if(position.equals("Teacher")){
                position = "teacher";
            }
            String money = tf2.getValue();
            String school = cb2.getValue();
            String house = cb3.getValue();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name", name);
            formData.add("sex", sex);
            formData.add("position", position);
            formData.add("money", money);
            formData.add("school", school);
            formData.add("house", house);
            boolean result = WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();

            if(result){
                nf = Notification.show("Create Success", 3000, BOTTOM_START);
            }
            else{
                nf = Notification.show("Create Fail", 3000, BOTTOM_START);
            }
        });

        btn3.addClickListener(event -> {
            Wizards old = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(Wizards.class)
                    .block();
            String name = tf1.getValue();
            String sex = rbg.getValue();
            if(sex.equals("Male")){
                sex = "m";
            }
            else if(sex.equals("Female")){
                sex = "f";
            }
            String position = cb1.getValue();
            if(position.equals("Student")){
                position = "student";
            }
            else if(position.equals("Teacher")){
                position = "teacher";
            }
            String money = tf2.getValue();
            String school = cb2.getValue();
            String house = cb3.getValue();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("oldName", old.getModel().get(indexOfWizard).getName());
            formData.add("name", name);
            formData.add("sex", sex);
            formData.add("position", position);
            formData.add("money", money);
            formData.add("school", school);
            formData.add("house", house);
            boolean result = WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/updateWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();

            if(result){
                nf = Notification.show("Update Success", 3000, BOTTOM_START);
            }
            else{
                nf = Notification.show("Update Fail", 3000, BOTTOM_START);
            }
        });

        btn4.addClickListener(event -> {
            String name = tf1.getValue();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name", name);
            boolean result = WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(boolean.class)
                    .block();

            if(result){
                nf = Notification.show("Delete Success", 3000, BOTTOM_START);
            }
            else{
                nf = Notification.show("Delete Fail", 3000, BOTTOM_START);
            }
        });

        btn5.addClickListener(event -> {
            Wizards result = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/wizards")
                    .retrieve()
                    .bodyToMono(Wizards.class)
                    .block();
            if(indexOfWizard == -1){
                indexOfWizard = 0;
            }
            else if(indexOfWizard+1 == result.getModel().size()){
                indexOfWizard = result.getModel().size() - 1;
            }
            else {
                indexOfWizard++;
            }
            tf1.setValue(result.getModel().get(indexOfWizard).getName());
            tf2.setValue(result.getModel().get(indexOfWizard).getMoney()+"");
            if(result.getModel().get(indexOfWizard).getSex().equals("m")){
                rbg.setValue("Male");
            }
            else if(result.getModel().get(indexOfWizard).getSex().equals("f")){
                rbg.setValue("Female");
            }
            cb1.setValue(result.getModel().get(indexOfWizard).getPosition());
            cb2.setValue(result.getModel().get(indexOfWizard).getSchool());
            cb3.setValue(result.getModel().get(indexOfWizard).getHouse());
        });
    }
}