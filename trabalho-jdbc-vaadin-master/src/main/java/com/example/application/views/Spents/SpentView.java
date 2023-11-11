package com.example.application.views.Spents;

import com.example.application.controllers.SpentController;
import com.example.application.controllers.UserController;
import com.example.application.models.SpentModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "gastos", layout = Layout.class)
public class SpentView extends VerticalLayout {

    private final Grid<SpentController> gastoGrid = new Grid<>(SpentController.class);
   
    public SpentView() {
       
        Button novoGanhoButton = new Button("Novo gasto", new Icon(VaadinIcon.PLUS));
        novoGanhoButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        novoGanhoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        novoGanhoButton.setAriaLabel("Adicionar ganho");
        novoGanhoButton.addClickListener(e -> {
            UI.getCurrent().navigate(CreateSpentView.class);
        });

        gastoGrid.setColumns("tipo", "data", "valor", "formaPagamento");

        gastoGrid.addComponentColumn(item -> {
            Button editButton = new Button("Editar");
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
            editButton.addClickListener(e -> {
                UI.getCurrent().navigate("edit/gasto/" + item.getId());
            });
            return editButton;
        });
       
        gastoGrid.addComponentColumn(item -> {
            Button deleteButton = new Button("Excluir");
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(e -> {
                UI.getCurrent().navigate("delete/gasto/" + item.getId());
            });
            return deleteButton;
        });

        add(novoGanhoButton, gastoGrid);
        listarGastos();
    }

    private void listarGastos() {
        Integer userId = UserController.getId();
        System.out.println(userId);
        gastoGrid.setItems(SpentModel.getAll(userId));
    }

}