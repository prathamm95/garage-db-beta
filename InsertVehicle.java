package garage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class InsertVehicle {

    public static void insertVehicle() {

        Vehicle vehicle = new Vehicle();

        try {

            ConnectionClass connector = new ConnectionClass();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter vehicle no:");
            String vehicleNo = scanner.next();
            do {
                if (vehicleNo.equals(null)) {
                    System.out.println("Please insert a valid vehicle number");
                }
                vehicle.setVehicleNo(vehicleNo);
            } while (vehicleNo.equals(null));


            System.out.println("Select vehicle type - Bike/Car?");
            String vehicleType = scanner.next();

                if (vehicleType.equals("Bike") || vehicleType.equals("Car")) {
                    System.out.println("Added successfully");
                } else if (vehicleType != "Bike" || vehicleType != "Car") {
                    System.out.println("Sorry can't service this vehicle");
                    vehicleType = scanner.next();
                } else if (vehicleType.equals(null)) {
                    System.out.println("Invalid request");
                    vehicleType = scanner.next();
                } else if (vehicleType.equals(scanner.nextInt())){
                    System.out.println("Sorry can't service this vehicle");
                }
                vehicle.setVehicleType(vehicleType);


            double repairCost;
            if (vehicleType.equals("Bike")) {
                vehicle.setRepairCost(200.00);
            } else if (vehicleType.equals("Car")) {
                vehicle.setRepairCost(500.00);
            }
            repairCost = vehicle.getRepairCost();

            String status;
            vehicle.setStatus("Waiting");
            status = vehicle.getStatus();

            Timestamp date = new Timestamp(System.currentTimeMillis());

            Connection conn = connector.connect();

            PreparedStatement prepStatmnt;

            prepStatmnt = conn.prepareStatement("insert into garage_entries(vehicle_no, vehicle_type, repair_cost, status, date) values (?, ?, ?, ?, ?);");

            prepStatmnt.setString(1, vehicleNo);
            prepStatmnt.setString(2, vehicleType);
            prepStatmnt.setDouble(3, repairCost);
            prepStatmnt.setString(4, status);
            prepStatmnt.setTimestamp(5, date);

            prepStatmnt.executeUpdate();

            System.out.println("New vehicle data entered successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
