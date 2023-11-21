package com.example.application.views.Products;

import com.example.application.models.ProductModel;
import com.example.application.views.Layout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "deletar/produto/:id", layout = Layout.class) 
public class DeleteProductView extends VerticalLayout implements BeforeEnterObserver {

    private int produtoId;

    public DeleteProductView() {
        Button confirmarExclusaoButton = new Button("Confirmar Exclusão");
        confirmarExclusaoButton.addClickListener(e -> {
            excluirProduto();
            UI.getCurrent().navigate(ProductView.class);
        });

        add(confirmarExclusaoButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String idParameter = event.getRouteParameters().get("id").orElse("");
        try {
            produtoId = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao deletar produto");
        }
    }

    private void excluirProduto() {
        ProductModel.delete(produtoId);
    }
}
