package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Exercises_4_SB")
public class Exercises_3_SB extends OpMode {

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    @Override
    public void loop() {
        double stickYDifference = gamepad1.left_stick_y - gamepad1.right_stick_y;
        double triggerSum = gamepad1.left_trigger + gamepad1.right_trigger;
        telemetry.addData("Right stick X", gamepad1.left_stick_x);
        telemetry.addData("B button", gamepad1.b);
        telemetry.addData("Y stick difference", stickYDifference);
        telemetry.addData("Triggers sum", triggerSum);

    }
}