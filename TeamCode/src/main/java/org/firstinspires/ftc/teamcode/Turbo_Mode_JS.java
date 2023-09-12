package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Turbo_Mode_JS {
    public void init() {

    }
    public void loop() {
        if(gamepad1.a == false){
            gamepad1.left_stick_x *= 0.5;
            gamepad1.left_stick_y *= 0.5;
        }
        else if(gamepad1.a == true) if (gamepad1.left_stick_y < 0) {
            gamepad1.left_stick_y *= -1;
        }
    }
}
