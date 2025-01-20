package com.pixel.vaadinpkg;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;

public class MainLayout extends AppLayout {

    public MainLayout() {
        Anchor employeeLink = new Anchor("/employee", "Employee View");
        addToNavbar(employeeLink);
    }
}
