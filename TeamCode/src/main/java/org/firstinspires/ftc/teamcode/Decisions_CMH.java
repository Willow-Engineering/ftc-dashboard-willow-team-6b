package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp()
public class Decisions_CMH extends OpMode {
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry =  new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }
    @Override
    public void loop() {
        double ForwardSpeed = -gamepad1.left_stick_y / 2;
        double lStick_x = gamepad1.left_stick_x / 2;
        double lStick_y = gamepad1.left_stick_y / 2;

        if (gamepad1.a) {
            telemetry.addData("X = ", lStick_y);
            telemetry.addData("Y = ", lStick_x);
        }
        else {
            ForwardSpeed *= 2;
            telemetry.addData("X = ", lStick_x);
            telemetry.addData("Y = ", lStick_y);
        }
        telemetry.update();
    }
}