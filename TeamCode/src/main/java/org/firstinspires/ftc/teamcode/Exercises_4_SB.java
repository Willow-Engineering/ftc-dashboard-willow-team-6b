package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Exercises_4_SB")
public class Exercises_4_SB extends OpMode {

    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    @Override
    public void loop() {
        double joystickLeftX = gamepad1.left_stick_x / 2;
        double joystickLeftY = gamepad1.left_stick_y / 2;
        double joystickRightX = gamepad1.right_stick_x / 2;
        double joystickRightY = gamepad1.right_stick_y / 2;
        if(gamepad1.b) {
            joystickLeftX = gamepad1.left_stick_y / 2;
            joystickLeftY = gamepad1.left_stick_x / 2;
            joystickRightX = gamepad1.right_stick_y / 2;
            joystickRightY = gamepad1.right_stick_x / 2;
        }
        while(gamepad1.a) {
            if(gamepad1.b) {
                joystickLeftX = gamepad1.left_stick_y;
                joystickLeftY = gamepad1.left_stick_x;
                joystickRightX = gamepad1.right_stick_y;
                joystickRightY = gamepad2.right_stick_x;
            }
            else {
                joystickLeftX = gamepad1.left_stick_x;
                joystickLeftY = gamepad1.left_stick_y;
                joystickRightX = gamepad1.right_stick_x;
                joystickRightY = gamepad2.right_stick_y;
            }
        }
        telemetry.addData("Left joystick X", joystickLeftX);
        telemetry.addData("Left joystick Y", joystickLeftY);
        telemetry.addData("Right joystick X", joystickRightX);
        telemetry.addData("Right joystick Y", joystickRightY);
    }
}