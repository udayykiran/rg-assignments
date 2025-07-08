import java.sql.*;

public class EmployeeJDBC {
    private Connection conn;

    public EmployeeJDBC() throws Exception {
        // CREATE DATABASE employeedb;
        //
        //USE employeedb;
        //
        //CREATE TABLE Employee (
        //    id INT PRIMARY KEY,
        //    name VARCHAR(100),
        //    department VARCHAR(100)
        //);

        String url = "jdbc:mysql://localhost:3306/employeedb";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        System.out.println("User: " + username);
        System.out.println("Pass: " + (password != null ? "Set" : "Not Set"));

        conn = DriverManager.getConnection(url, username, password);
    }

    // Create
    public void addEmployee(Employee e) throws SQLException {
        String query = "INSERT INTO employee (id, name, department) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDepartment());
            ps.executeUpdate();
            System.out.println("Employee added.");
        }
    }

    // Read
    public void getAllEmployees() throws SQLException {
        String query = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Department: " + rs.getString("department"));
            }
        }
    }

    // Update
    public void updateEmployeeDepartment(int id, String newDept) throws SQLException {
        String query = "UPDATE employee SET department = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newDept);
            ps.setInt(2, id);
            int updated = ps.executeUpdate();
            System.out.println(updated > 0 ? "Employee updated." : "Employee not found.");
        }
    }

    // Delete
    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            System.out.println(deleted > 0 ? "Employee deleted." : "Employee not found.");
        }
    }

    public static void main(String[] args) {
        try {
            EmployeeJDBC crud = new EmployeeJDBC();

            // Create
            Employee e1 = new Employee(1, "Alice", "HR");
            Employee e2 = new Employee(2, "Bob", "IT");
            crud.addEmployee(e1);
            crud.addEmployee(e2);

            // Read
            System.out.println("All Employees:");
            crud.getAllEmployees();

            // Update
            crud.updateEmployeeDepartment(2, "DevOps");

            // Delete
            crud.deleteEmployee(1);

            System.out.println("Employees After Update & Delete:");
            crud.getAllEmployees();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
