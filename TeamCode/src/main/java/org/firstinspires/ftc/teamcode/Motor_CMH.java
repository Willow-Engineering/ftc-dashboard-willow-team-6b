package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class Motor_CMH extends OpMode {
    int myVar = 0;
    private DcMotor motor1;
    @Override
    public void init() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry =  new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }
    public void loop() {
        if(gamepad1.a) {
            myVar += 5;
        }
    }
}