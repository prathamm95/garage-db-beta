package garage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountSummary {

    public static void accountSummary() {

        try {

            ConnectionClass connector = new ConnectionClass();

            Connection conn = connector.connect();

            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();
            Statement statement3 = conn.createStatement();

            ResultSet totalRepairedCost = statement1.executeQuery("SELECT SUM(`repair_cost`) FROM `garage_entries` WHERE `status`='Repaired';");
            ResultSet bikesRepairedCost = statement2.executeQuery("SELECT SUM(`repair_cost`) FROM `garage_entries` WHERE `status`='Repaired' AND `vehicle_type`='Bike';");
            ResultSet carsRepairedCost = statement3.executeQuery("SELECT SUM(`repair_cost`) FROM `garage_entries` WHERE `status`='Repaired' AND `vehicle_type`='Car';");

            if (totalRepairedCost.next()) {
                double totalRepairCost = totalRepairedCost.getDouble(1);
                System.out.println("Total repaired cost = " + totalRepairCost);
            }

            if (bikesRepairedCost.next()) {
                double bikesRepairCost = bikesRepairedCost.getDouble(1);
                System.out.println("Bikes repaired cost = " + bikesRepairCost);
            }

            if (carsRepairedCost.next()) {
                double carsRepairCost = carsRepairedCost.getDouble(1);
                System.out.println("Cars repaired cost = " + carsRepairCost);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
