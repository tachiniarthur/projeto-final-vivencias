package com.example.application.views.Gains;

import com.example.application.controllers.GainController;
import com.example.application.controllers.UserController;
import com.example.application.models.GainModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;

@Route(value = "create/gain", layout = Layout.class)
public class CreateGainView extends VerticalLayout {

    private final H1 title = new H1("Criar novo ganho");
    private final TextField ganhoTipoField = new TextField("Tipo de Ganho");
    private final DatePicker ganhoDataPicker = new DatePicker("Data de Ganho");
    private final NumberField ganhoValorField = new NumberField("Valor de Ganho");
   
    private final Binder<GainController> binder = new Binder<>(GainController.class);

    public CreateGainView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        title.getStyle().set("font-size", "32px");
        ganhoTipoField.setWidth("300px");
        ganhoDataPicker.setWidth("300px");
        ganhoValorField.setWidth("300px");
       
        ganhoTipoField.getStyle().set("margin", "0px");
        ganhoDataPicker.getStyle().set("margin", "0px");
        ganhoValorField.getStyle().set("margin", "0px");

        ganhoTipoField.getStyle().set("padding", "0px");
        ganhoDataPicker.getStyle().set("padding", "0px");
        ganhoValorField.getStyle().set("padding", "0px");

        binder.forField(ganhoTipoField)
            .asRequired("Por favor, preencha o tipo")
            .withValidator(new StringLengthValidator(
                    "O tipo deve ter pelo menos 2 caracteres", 2, null))
            .bind(GainController::getTipo, GainController::setTipo);

        binder.forField(ganhoDataPicker)
            .asRequired("Por favor, preencha a data")
            .withConverter(new LocalDateToDateConverter())
            .bind(GainController::getData, GainController::setData);

         binder.forField(ganhoValorField)
            .asRequired("Por favor, preencha o valor")
            .bind(GainController::getValor, GainController::setValor);
           
        GainController GainController = new GainController();
        binder.setBean(GainController);

        Button salvarGanhoButton = new Button("Criar ganho");
        salvarGanhoButton.addClassName("allButtons");  
        salvarGanhoButton.setWidth("300px");
        salvarGanhoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarGanhoButton.setAriaLabel("Criar ganho");
        salvarGanhoButton.addClickListener(e -> {
            BinderValidationStatus<GainController> validationStatus = binder.validate();

            if (validationStatus.hasErrors()) {
                    validationStatus.getFieldValidationErrors().forEach(error -> {
                            String errorMessage = error.getMessage().orElse("");
                            Notification.show(errorMessage, 5000, Notification.Position.BOTTOM_START);
                    });

            } else {
                    String tipo = ganhoTipoField.getValue();
                    java.sql.Date data = new java.sql.Date(ganhoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC).toEpochMilli());
                    Double valor = ganhoValorField.getValue();

                    int userId = UserController.getId();
                    boolean insertGain = GainModel.insertGain(tipo, data, valor, userId);

                    if (insertGain) {
                            UI.getCurrent().navigate(GainView.class);
                    } else {
                            Notification.show("Erro ao criar ganho. Tente novamente.", 5000, Notification.Position.BOTTOM_START);
                    }

                }
        });

        add(title, ganhoTipoField, ganhoDataPicker, ganhoValorField, salvarGanhoButton);
       
    }

}