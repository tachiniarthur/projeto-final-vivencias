package com.example.application.views.Gains;

import com.example.application.controllers.GainController;
import com.example.application.controllers.UserController;
import com.example.application.models.GainModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "ganhos", layout = Layout.class)
public class GainView extends VerticalLayout {

    private final Grid<GainController> ganhoGrid = new Grid<>(GainController.class);
   
    public GainView() {
       
        Button novoGanhoButton = new Button("Novo ganho", new Icon(VaadinIcon.PLUS));
        novoGanhoButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        novoGanhoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        novoGanhoButton.setAriaLabel("Adicionar ganho");
        novoGanhoButton.addClickListener(e -> {
            UI.getCurrent().navigate(CreateGainView.class);
        });

        ganhoGrid.setColumns("tipo", "data", "valor");

        ganhoGrid.addComponentColumn(item -> {
            Button editButton = new Button("Editar");
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
            editButton.addClickListener(e -> {
                UI.getCurrent().navigate("edit/ganho/" + item.getId());
            });
            return editButton;
        });
       
        ganhoGrid.addComponentColumn(item -> {
            Button deleteButton = new Button("Excluir");
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(e -> {
                UI.getCurrent().navigate("delete/ganho/" + item.getId());
            });
            return deleteButton;
        });
           

        add(novoGanhoButton, ganhoGrid);
        listarGanhos();
    }

    private void listarGanhos() {
        Integer userId = UserController.getId();
        System.out.println(userId);
        ganhoGrid.setItems(GainModel.getAll(userId));
    }
}