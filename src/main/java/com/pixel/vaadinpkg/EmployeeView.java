package com.pixel.vaadinpkg;

import com.pixel.repository.EmployeeRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.pixel.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "employee", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {

    private final EmployeeRepository employeeRepository;
    private final Grid<Employee> grid;
    private final TextField nameField;
    private final TextField designationField;
    private final TextField salaryField;

    @Autowired
    public EmployeeView(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        // Setup the Grid
        grid = new Grid<>(Employee.class);
        grid.setColumns("id", "name", "designation", "salary");

        // Setup form fields
        nameField = new TextField("Name");
        designationField = new TextField("Designation");
        salaryField = new TextField("Salary");

        // Add buttons for CRUD operations
        Button saveButton = new Button("Save", event -> saveEmployee());
        Button deleteButton = new Button("Delete", event -> deleteEmployee());
        Button clearButton = new Button("Clear", event -> clearForm());

        // Layout the form and buttons
        FormLayout formLayout = new FormLayout(nameField, designationField, salaryField, saveButton, deleteButton, clearButton);

        // Add the form and grid to the layout
        add(formLayout, grid);

        // Load and display all employees
        grid.setItems(employeeRepository.findAll());

        // Set up the form for editing an employee when a row is selected
        grid.asSingleSelect().addValueChangeListener(event -> editEmployee(event.getValue()));
    }

    // Create or update an employee
    private void saveEmployee() {
        Employee employee = new Employee();
        employee.setName(nameField.getValue());
        employee.setDesignation(designationField.getValue());
        employee.setSalary(Double.parseDouble(salaryField.getValue()));

        // Save the employee to the database
        employeeRepository.save(employee);

        // Refresh the grid
        grid.setItems(employeeRepository.findAll());

        // Clear the form fields
        clearForm();
    }

    // Delete the selected employee
    private void deleteEmployee() {
        Employee employee = grid.asSingleSelect().getValue();
        if (employee != null) {
            employeeRepository.delete(employee);
            grid.setItems(employeeRepository.findAll());
            clearForm();
        }
    }

    // Clear form fields
    private void clearForm() {
        nameField.clear();
        designationField.clear();
        salaryField.clear();
    }

    // Edit an employee by filling the form
    private void editEmployee(Employee employee) {
        if (employee != null) {
            nameField.setValue(employee.getName());
            designationField.setValue(employee.getDesignation());
            salaryField.setValue(String.valueOf(employee.getSalary()));
        } else {
            clearForm();
        }
    }
}
