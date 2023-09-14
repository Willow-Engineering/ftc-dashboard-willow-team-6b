package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class Variables_CMH extends OpMode {

    int teamNumber = 16072;
    double motorSpeed = .5;
    boolean touchSensorPressed = true;
    String myName = "Cypress-Magnolia Hodgson";

    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry =  new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }
    @Override
    public void loop() {
        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Touch Sensor", touchSensorPressed);
        telemetry.addData("Hello", myName);
        telemetry.update();
    }
}