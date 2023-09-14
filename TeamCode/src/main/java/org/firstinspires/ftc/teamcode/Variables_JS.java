package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

@TeleOp
public class Variables_JS {
    int Team = 19824;
    boolean running = false;
    char TeamMember = J;
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        telemetry.addData("team number", Team);
        telemetry.addData("running", running);
        telemetry.addData("teamMember", TeamMember);

    }
    public void loop(){
        telemetry.addData("right stick x", gamepad1.left_stick_x);
        telemetry.addData("right stick y", gamepad1.left_stick_y);
        telemetry.addData("B button pressed", gamepad1.b);
        telemetry.addData("stick y difference", gamepad1.left_stick_y - gamepad1.right_stick_y);
        telemetry.addData("trigger sum", gamepad1.left_trigger + gamepad1.right_trigger);
    }
}
