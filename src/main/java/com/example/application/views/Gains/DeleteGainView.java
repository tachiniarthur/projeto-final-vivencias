package com.example.application.views.Gains;

import com.example.application.models.GainModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "delete/ganho/:id", layout = Layout.class) 
public class DeleteGainView extends VerticalLayout implements BeforeEnterObserver {

    private int ganhoId;

    public DeleteGainView() {
        Button confirmarExclusaoButton = new Button("Confirmar Exclusão");
        confirmarExclusaoButton.addClickListener(e -> {
            excluirGanho();
            UI.getCurrent().navigate(GainView.class);
        });

        add(confirmarExclusaoButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String idParameter = event.getRouteParameters().get("id").orElse("");
        try {
            ganhoId = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao deletar ganho");
        }
    }

    private void excluirGanho() {
        GainModel.delete(ganhoId);
    }
}
