package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
@Disabled
public class BasicLinearOpMode_CMH extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor arm = null;
    Servo leftIntake;
    Servo rightIntake;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);
        leftIntake = hardwareMap.get(Servo.class, "left_intake");
        rightIntake = hardwareMap.get(Servo.class, "right_intake");
        int driveMode = 0;
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            if (driveMode == 0) {
                double drive = -gamepad1.left_stick_y;
                double turn = gamepad1.left_stick_x;
                double leftPower = Range.clip(drive + turn, -1.0, 1.0);
                double rightPower = Range.clip(drive - turn, -1.0, 1.0);
                double armPower = gamepad1.right_stick_y;
                double intakePos = gamepad1.right_trigger;
                leftDrive.setPower(leftPower);
                rightDrive.setPower(rightPower);
                arm.setPower(armPower);
                leftIntake.setPosition(intakePos);
                rightIntake.setPosition(-intakePos);
                telemetry.addData("Motors", "left (%.2f), right (%.2f), arm (%.2f)", leftPower, rightPower, armPower);
            }
            else if (driveMode == 1) {
                double leftPower = -gamepad1.left_stick_y ;
                double rightPower = -gamepad1.right_stick_y ;
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}