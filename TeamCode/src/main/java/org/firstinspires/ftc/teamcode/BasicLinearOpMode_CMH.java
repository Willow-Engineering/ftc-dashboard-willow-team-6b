package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="BasicLinearOpMode_CMH", group="Linear OpMode")
public class BasicLinearOpMode_CMH extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotorEx arm = null;
    Servo leftIntake;
    Servo rightIntake;
    @Override
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotorEx.Direction.FORWARD);
        leftIntake = hardwareMap.get(Servo.class, "leftIntake");
        rightIntake = hardwareMap.get(Servo.class, "rightIntake");
        int driveMode = 0;
        waitForStart();
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        runtime.reset();
        while (opModeIsActive()) {
            if (driveMode == 0) {
                double drive = -gamepad1.left_stick_y;
                double turn = gamepad1.left_stick_x;
                double leftPower = Range.clip(drive + turn, -1.0, 1.0);
                double rightPower = Range.clip(drive - turn, -1.0, 1.0);
                double armPower = gamepad1.right_stick_y;
                //double intakePos = gamepad1.right_trigger;
                leftDrive.setPower(leftPower);
                rightDrive.setPower(rightPower);
                if(gamepad1.a) {
                    arm.setTargetPosition(-1500);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setVelocity(300);
                }
                else if(gamepad1.b) {
                    arm.setTargetPosition(-750);
                    arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    arm.setVelocity(300);
                }
                else {
                    arm.setVelocity(0);
                }
                //leftIntake.setPosition(intakePos);
                //rightIntake.setPosition(-intakePos);
                telemetry.addData("Motors", "left (%.2f), right (%.2f), arm (%.2f)", leftPower, rightPower, armPower);
            }
            /*else if (driveMode == 1) {
                double leftPower = -gamepad1.left_stick_y ;
                double rightPower = -gamepad1.right_stick_y ;
            }*/
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Arm Position,", arm.getCurrentPosition());
            telemetry.update();
        }
    }
}