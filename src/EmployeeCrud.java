import java.util.*;

class EmployeeCrud {
    private List<Employee> list = new ArrayList<>();

    // Create
    public void addEmployee(Employee e) {
        list.add(e);
    }

    // Read
    public void showAllEmployees() {
        for (Employee e : list) {
            System.out.println("ID: " + e.getId() + ", Name: " + e.getName() + ", Department: " + e.getDepartment());
        }
    }

    // Update
    public void updateEmployee(int id, String newDept) {
        for (Employee e : list) {
            if (e.getId() == id) {
                e.setDepartment(newDept);
                break;
            }
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        list.removeIf(e -> e.getId() == id);
    }

    public static void main(String[] args) {
        EmployeeCrud crud = new EmployeeCrud();

        // Create
        Employee e1 = new Employee(1, "A", "HR");
        Employee e2 = new Employee(2, "B", "IT");
        Employee e3 = new Employee(3, "C", "Finance");

        crud.addEmployee(e1);
        crud.addEmployee(e2);
        crud.addEmployee(e3);

        System.out.println("All Employees:");
        crud.showAllEmployees();

        // Update
        System.out.println("\nUpdating B's department to Support...");
        crud.updateEmployee(2, "Support");

        System.out.println("All Employees After Update:");
        crud.showAllEmployees();

        // Delete
        System.out.println("\nDeleting employee with ID 1...");
        crud.deleteEmployee(1);

        System.out.println("All Employees After Deletion:");
        crud.showAllEmployees();
    }
}
