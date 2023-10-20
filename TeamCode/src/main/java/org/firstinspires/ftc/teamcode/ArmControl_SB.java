package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name="ArmControl_SB")
//@Disabled
public class ArmControl_SB extends Basic_Bot_SB {
    private DcMotor arm;
    private DigitalChannel touch;

    static final double     COUNTS_PER_MOTOR_REV    = 288;
    static final double     GEAR_REDUCTION    = 2.7778;
    static final double     COUNTS_PER_GEAR_REV    = COUNTS_PER_MOTOR_REV * GEAR_REDUCTION;
    static final double     COUNTS_PER_DEGREE    = COUNTS_PER_GEAR_REV/360;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        arm = hardwareMap.get(DcMotor.class, "arm");
        touch = hardwareMap.get(DigitalChannel.class, "touch");

        int minPosition = 0;
        int maxPosition = (int)(COUNTS_PER_DEGREE * 45);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.right_bumper && arm.getCurrentPosition() < maxPosition) {
                arm.setPower(0.5);
            }
            else if (gamepad1.left_bumper && arm.getCurrentPosition() > minPosition) {
                arm.setPower(-0.5);
            }
            else if (gamepad1.a) {
                arm.setPower(-0.5);
            }
            else {
                arm.setPower(0);
            }
            if (!touch.getState()) {
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            telemetry.addData("Arm Test", arm.getCurrentPosition());
            telemetry.update();
        }
    }
}
