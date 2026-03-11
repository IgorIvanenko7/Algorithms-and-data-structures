package ru.t1.education.mvvm;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Создание 2 View, для синхронизации
 */

public class MvvmApp {
    public static void main(String[] args) {

        ShopModel model = new ShopModel();
        ShopViewModel viewModel = new ShopViewModel(model);

        SwingUtilities.invokeLater(() -> {
            new ShopView(viewModel);
            new ShopView(viewModel);
        });
    }
}


class ShopModel {

    private Integer discount = 0;
    private final List<ModelObserver> observers = new ArrayList<>();


    public void setDiscount(Integer discount) {
        this.discount = discount;
        notifyObservers();
    }

    public Integer getDiscount() {
        return discount;
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.onDiscountChanged(discount));
    }
}

interface ModelObserver {
    void onDiscountChanged(Integer discount);
}


class ShopViewModel implements ModelObserver {

    private final ShopModel model;
    private final List<ViewModelObserver> observers = new ArrayList<>();

    private String discountText = "0";

    public ShopViewModel(ShopModel model) {
        this.model = model;
        model.addObserver(this);
    }

    public void setDiscount(String text) {
        try {
            int value = Integer.parseInt(text);
            model.setDiscount(value);
        } catch (NumberFormatException ignored) {}
    }

    public String getDiscountText() {
        return discountText;
    }

    public void addObserver(ViewModelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.onViewModelChanged(discountText));
    }

    @Override
    public void onDiscountChanged(Integer discount) {
        discountText = String.valueOf(discount);
        notifyObservers();
    }
}


interface ViewModelObserver {
    void onViewModelChanged(String value);
}

class ShopView extends JFrame implements ViewModelObserver {

    private final JTextField discountField = new JTextField(10);
    private final JButton applyButton = new JButton("Apply");

    private final ShopViewModel viewModel;

    public ShopView(ShopViewModel viewModel) {

        this.viewModel = viewModel;
        viewModel.addObserver(this);

        setTitle("Shop View");

        setLayout(new FlowLayout());

        add(new JLabel("Discount:"));
        add(discountField);
        add(applyButton);

        discountField.setText(viewModel.getDiscountText());

        applyButton.addActionListener(e ->
                viewModel.setDiscount(discountField.getText()));

        setSize(250,100);
        setVisible(true);
    }

    @Override
    public void onViewModelChanged(String value) {
        discountField.setText(value);
    }
}
