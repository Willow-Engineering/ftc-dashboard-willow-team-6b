package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Variables_SB")
public class Variables_SB extends OpMode {

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        int teamNumber = 16072;
        double motorSpeed = 0.5;
        boolean touchSensorPressed = true;
        String myName = "Sal Balsamo";

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("touchSensorPressed", touchSensorPressed);
        telemetry.addData("My Name", myName);
    }

    @Override
    public void loop() {

    }
}