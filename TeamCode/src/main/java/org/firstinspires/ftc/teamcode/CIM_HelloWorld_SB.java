package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class CIM_HelloWorld_SB extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello","World");
        // sends the caption and value to the driver station
    }

    @Override
    public void loop() {
// left blank to remove the OpMode's loop functionality
    }
}
