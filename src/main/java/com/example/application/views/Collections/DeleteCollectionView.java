package com.example.application.views.Collections;

import com.example.application.models.CollectionModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "deletar/arrecadacao/:id", layout = Layout.class) 
public class DeleteCollectionView extends VerticalLayout implements BeforeEnterObserver {

    private int arrecadacaoId;

    public DeleteCollectionView() {
        Button confirmarExclusaoButton = new Button("Confirmar Exclusão");
        confirmarExclusaoButton.addClickListener(e -> {
            excluirCollection();
            UI.getCurrent().navigate(CollectionView.class);
        });

        add(confirmarExclusaoButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String idParameter = event.getRouteParameters().get("id").orElse("");
        try {
            arrecadacaoId = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao deletar doação!");
        }
    }

    private void excluirCollection() {
        CollectionModel.delete(arrecadacaoId);
    }
}
