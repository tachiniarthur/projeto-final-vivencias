package com.example.application.views.Spents;

import java.sql.Date;

import com.example.application.controllers.GainController;
import com.example.application.controllers.SpentController;
import com.example.application.models.SpentModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "edit/gasto/:id", layout = Layout.class)
public class EditSpentView extends VerticalLayout implements BeforeEnterObserver {

    private int gastoId;
    private SpentController gasto;
    private TextField tipoField = new TextField("Tipo");
    private DatePicker dataPicker = new DatePicker("Data");
    private NumberField valorField = new NumberField("Valor");

    public EditSpentView() {
        Button salvarButton = new Button("Salvar");
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(e -> salvarGanho());

        FormLayout formLayout = new FormLayout();
        formLayout.add(tipoField, dataPicker, valorField, salvarButton);

        add(formLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String gastoIdStr = event.getRouteParameters().get("id").orElse(null);
        if (gastoIdStr != null) {
            try {
                gastoId = Integer.parseInt(gastoIdStr);
                gasto = SpentModel.getSpentById(gastoId);
                if (gasto != null) {
                    preencherCampos();
                } else {
                    event.forwardTo(SpentView.class);
                }
            } catch (NumberFormatException e) {
                event.forwardTo(SpentView.class);
            }
        } else {
            event.forwardTo(SpentView.class);
        }
    }

    private void preencherCampos() {
        tipoField.setValue(gasto.getTipo());
        dataPicker.setValue(((Date) gasto.getData()).toLocalDate());
        valorField.setValue(gasto.getValor());
    }

    private void salvarGanho() {
        String novoTipo = tipoField.getValue();
        java.util.Date novaData = java.sql.Date.valueOf(dataPicker.getValue());
        double novoValor = valorField.getValue();

        gasto.setTipo(novoTipo);
        gasto.setData(novaData);
        gasto.setValor(novoValor);

        SpentModel.update(gastoId, gasto);
        UI.getCurrent().navigate(SpentView.class);

        Notification.show("Ganho atualizado com sucesso!");
    }
}
