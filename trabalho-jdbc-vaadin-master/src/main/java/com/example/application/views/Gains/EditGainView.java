package com.example.application.views.Gains;

import java.sql.Date;

import com.example.application.controllers.GainController;
import com.example.application.models.GainModel;
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

@Route(value = "edit/ganho/:id", layout = Layout.class)
public class EditGainView extends VerticalLayout implements BeforeEnterObserver {

    private int ganhoId;
    private GainController ganho;
    private TextField tipoField = new TextField("Tipo");
    private DatePicker dataPicker = new DatePicker("Data");
    private NumberField valorField = new NumberField("Valor");

    public EditGainView() {
        Button salvarButton = new Button("Salvar");
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(e -> salvarGanho());

        FormLayout formLayout = new FormLayout();
        formLayout.add(tipoField, dataPicker, valorField, salvarButton);

        add(formLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String ganhoIdStr = event.getRouteParameters().get("id").orElse(null);
        if (ganhoIdStr != null) {
            try {
                ganhoId = Integer.parseInt(ganhoIdStr);
                ganho = GainModel.getGainById(ganhoId);
                if (ganho != null) {
                    preencherCampos();
                } else {
                    event.forwardTo(GainView.class);
                }
            } catch (NumberFormatException e) {
                event.forwardTo(GainView.class);
            }
        } else {
            event.forwardTo(GainView.class);
        }
    }

    private void preencherCampos() {
        tipoField.setValue(ganho.getTipo());
        dataPicker.setValue(((Date) ganho.getData()).toLocalDate());
        valorField.setValue(ganho.getValor());
    }

    private void salvarGanho() {
        String novoTipo = tipoField.getValue();
        java.util.Date novaData = java.sql.Date.valueOf(dataPicker.getValue());
        double novoValor = valorField.getValue();

        ganho.setTipo(novoTipo);
        ganho.setData(novaData);
        ganho.setValor(novoValor);

        GainModel.update(ganhoId, ganho);
        UI.getCurrent().navigate(GainView.class);

        Notification.show("Ganho atualizado com sucesso!");
    }
}
