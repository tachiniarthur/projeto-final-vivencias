package com.example.application.views.Spents;

import com.example.application.controllers.SpentController;
import com.example.application.controllers.UserController;
import com.example.application.models.SpentModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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

@Route(value = "create/spent", layout = Layout.class)
public class CreateSpentView extends VerticalLayout {

    private final H1 title = new H1("Criar novo gasto");
    private final TextField gastoTipoField = new TextField("Tipo de Gasto");
    private final DatePicker gastoDataPicker = new DatePicker("Data de Gasto");
    private final NumberField gastoValorField = new NumberField("Valor de Gasto");
    private final ComboBox<String> gastoFormaPagamentoField = new ComboBox<>("Forma de Pagamento");

    private final Binder<SpentController> binder = new Binder<>(SpentController.class);
   
    public CreateSpentView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        title.getStyle().set("font-size", "32px");
        gastoTipoField.setWidth("300px");
        gastoDataPicker.setWidth("300px");
        gastoValorField.setWidth("300px");
        gastoFormaPagamentoField.setWidth("300px");
        gastoFormaPagamentoField.setItems("Cartão de crédito", "Cartão de débito", "Pix", "Dinheiro");

        gastoTipoField.getStyle().set("margin", "0px");
        gastoDataPicker.getStyle().set("margin", "0px");
        gastoValorField.getStyle().set("margin", "0px");
        gastoFormaPagamentoField.getStyle().set("margin", "0px");

        gastoTipoField.getStyle().set("padding", "0px");
        gastoDataPicker.getStyle().set("padding", "0px");
        gastoValorField.getStyle().set("padding", "0px");
        gastoFormaPagamentoField.getStyle().set("padding", "0px");

        binder.forField(gastoTipoField)
            .asRequired("Por favor, preencha o tipo")
            .withValidator(new StringLengthValidator(
                    "O tipo deve ter pelo menos 2 caracteres", 2, null))
            .bind(SpentController::getTipo, SpentController::setTipo);

        binder.forField(gastoDataPicker)
            .asRequired("Por favor, preencha a data")
            .withConverter(new LocalDateToDateConverter())
            .bind(SpentController::getData, SpentController::setData);

        binder.forField(gastoValorField)
            .asRequired("Por favor, preencha o valor")
            .bind(SpentController::getValor, SpentController::setValor);

        binder.forField(gastoFormaPagamentoField)
            .asRequired("Por favor, selecione a forma de pagamento")
            .bind(SpentController::getFormaPagamento, SpentController::setFormaPagamento);
           
        SpentController spentController = new SpentController();
        binder.setBean(spentController);

        Button salvarGastoButton = new Button("Criar gasto");
        salvarGastoButton.addClassName("allButtons");  
        salvarGastoButton.setWidth("300px");
        salvarGastoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarGastoButton.setAriaLabel("Criar gasto");
        salvarGastoButton.addClickListener(e -> {
            BinderValidationStatus<SpentController> validationStatus = binder.validate();

            if (validationStatus.hasErrors()) {
                    validationStatus.getFieldValidationErrors().forEach(error -> {
                            String errorMessage = error.getMessage().orElse("");
                            Notification.show(errorMessage, 5000, Notification.Position.BOTTOM_START);
                    });

            } else {
                    String tipo = gastoTipoField.getValue();
                    java.sql.Date data = new java.sql.Date(gastoDataPicker.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC).toEpochMilli());
                    Double valor = gastoValorField.getValue();
                    String formaPagamento = gastoFormaPagamentoField.getValue();

                    int userId = UserController.getId();
                    boolean insertSpent = SpentModel.insertSpent(tipo, data, valor, formaPagamento, userId);

                    if (insertSpent) {
                            UI.getCurrent().navigate(SpentView.class);
                    } else {
                            Notification.show("Erro ao criar gasto. Tente novamente.", 5000, Notification.Position.BOTTOM_START);
                    }

                }
        });

        add(title, gastoTipoField, gastoDataPicker, gastoValorField, gastoFormaPagamentoField, salvarGastoButton);
    }
}