package garage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Scanner;

public class Repair {

    public static void repair() {

        Vehicle vehicle = new Vehicle();

        ConnectionClass connector = new ConnectionClass();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the 1st ID:");
        int enterID1 = scanner.nextInt();

        System.out.print("Enter the 2nd ID:");
        int enterID2 = scanner.nextInt();

        String status;
        vehicle.setStatus("Repairing");
        status = vehicle.getStatus();

        Timestamp date = new Timestamp(System.currentTimeMillis());

        try {
            Connection conn = connector.connect();

            PreparedStatement prepStatmnt1;
            PreparedStatement prepStatmnt2;

            prepStatmnt1 = conn.prepareStatement("select * from garage_entries where id = ?");
            prepStatmnt2 = conn.prepareStatement("select * from garage_entries where id = ?");

            prepStatmnt1.setInt(1, enterID1);
            prepStatmnt2.setInt(1, enterID2);

            ResultSet resultSet1 = prepStatmnt1.executeQuery();
            ResultSet resultSet2 = prepStatmnt2.executeQuery();

            if (resultSet1.next() == false && resultSet2.next() == false) {

                System.out.println("No such record found in the database. Re-enter a correct input.");

                System.out.print("Enter the 1st ID:");
                enterID1 = scanner.nextInt();
                System.out.print("Enter the 2nd ID:");
                enterID2 = scanner.nextInt();

                prepStatmnt1 = conn.prepareStatement("select * from garage_entries where id = ?");
                prepStatmnt1.setInt(1, enterID1);
                prepStatmnt2 = conn.prepareStatement("select * from garage_entries where id = ?");
                prepStatmnt2.setInt(1, enterID2);

                resultSet1 = prepStatmnt1.executeQuery();
                resultSet2 = prepStatmnt2.executeQuery();

            } else {

                PreparedStatement prepStatmnt1s;
                PreparedStatement prepStatmnt2s;

                prepStatmnt1s = conn.prepareStatement("update garage_entries set status = ?, date = ? where id = ?");
                prepStatmnt1s.setString(1, status);
                prepStatmnt1s.setTimestamp(2, date);
                prepStatmnt1s.setInt(3, enterID1);
                prepStatmnt2s = conn.prepareStatement("update garage_entries set status = ?, date = ? where id = ?");
                prepStatmnt2s.setString(1, status);
                prepStatmnt2s.setTimestamp(2, date);
                prepStatmnt2s.setInt(3, enterID2);

                prepStatmnt1s.executeUpdate();
                prepStatmnt2s.executeUpdate();

                System.out.println("Started repairing");

                Thread.sleep(20000);

                vehicle.setStatus("Repaired");
                status = vehicle.getStatus();

                date = new Timestamp(System.currentTimeMillis());

                PreparedStatement prepStatmnt1f;
                PreparedStatement prepStatmnt2f;

                prepStatmnt1f = conn.prepareStatement("update garage_entries set status = ?, date = ? where id = ?");
                prepStatmnt1f.setString(1, status);
                prepStatmnt1f.setTimestamp(2, date);
                prepStatmnt1f.setInt(3, enterID1);
                prepStatmnt2f = conn.prepareStatement("update garage_entries set status = ?, date = ? where id = ?");
                prepStatmnt2f.setString(1, status);
                prepStatmnt2f.setTimestamp(2, date);
                prepStatmnt2f.setInt(3, enterID2);

                prepStatmnt1f.executeUpdate();
                prepStatmnt2f.executeUpdate();

                System.out.println("Repaired");

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
