package com.example.application.views.Spents;

import com.example.application.models.SpentModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "delete/gasto/:id", layout = Layout.class) 
public class DeleteSpentView extends VerticalLayout implements BeforeEnterObserver {

    private int gastoId;

    public DeleteSpentView() {
        Button confirmarExclusaoButton = new Button("Confirmar Exclusão");
        confirmarExclusaoButton.addClickListener(e -> {
            excluirGanho();
            UI.getCurrent().navigate(SpentView.class);
        });

        add(confirmarExclusaoButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String idParameter = event.getRouteParameters().get("id").orElse("");
        try {
            gastoId = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao deletar gasto!");
        }
    }

    private void excluirGanho() {
        SpentModel.delete(gastoId);
    }
}
