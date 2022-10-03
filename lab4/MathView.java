package com.example.lab4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index1")
public class MathView extends VerticalLayout {
    private TextField tf1, tf2, tf3;
    private Button Button1, Button2, Button3, Button4, Button5, Button6;
    private HorizontalLayout H;

    public MathView(){
        tf1 = new TextField("Number 1");
        tf2 = new TextField("Number 2");
        tf3 = new TextField("Answer");
        tf3.setEnabled(false);
        Button1 = new Button("+");
        Button2 = new Button("-");
        Button3 = new Button("X");
        Button4 = new Button("/");
        Button5 = new Button("Mod");
        Button6 = new Button("Max");
        H = new HorizontalLayout(Button1, Button2, Button3, Button4, Button5, Button6);

        add(tf1, tf2, H, tf3);

        Button1.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });

        Button2.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });

        Button3.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });

        Button4.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });
        Button5.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });
        Button6.addClickListener(event -> {
            double num1 = Double.parseDouble(tf1.getValue());
            double num2 = Double.parseDouble(tf2.getValue());
            double max = Math.max( num1,  num2);

            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/max/"+max)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            tf3.setValue(out);
        });
    }
    }