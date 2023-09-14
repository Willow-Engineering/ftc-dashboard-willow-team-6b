package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Gamepad_JS extends OpMode {

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }
    @Override
    public void loop() {
        double LeftX = gamepad1.left_stick_x / 2;
        double LeftY = gamepad1.left_stick_y / 2;
        boolean A = gamepad1.a;
        boolean B = gamepad1.b;
        if(A = true){
            LeftX *= 2;
            LeftY *= 2;
        }
        if(B = true){
            LeftX = gamepad1.left_stick_y;
            LeftY = gamepad1.left_stick_x;
        }
        telemetry.addData("Left X", LeftX);
        telemetry.addData("Left Y", LeftY);
    }
}
