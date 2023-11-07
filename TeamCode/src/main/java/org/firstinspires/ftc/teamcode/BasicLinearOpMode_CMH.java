package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorDigitalTouch;

@TeleOp(name="BasicLinearOpMode_CMH", group="Linear OpMode")
public class BasicLinearOpMode_CMH extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotorEx arm = null;
    private DigitalChannel touch = null;
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
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotorEx.Direction.FORWARD);
        leftIntake = hardwareMap.get(Servo.class, "leftIntake");
        rightIntake = hardwareMap.get(Servo.class, "rightIntake");
        leftIntake.setDirection(Servo.Direction.FORWARD);
        rightIntake.setDirection(Servo.Direction.REVERSE);
        touch = hardwareMap.get(DigitalChannel.class, "touch");
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
                //double armPower = gamepad1.right_stick_y;
                double leftIntakePos = -.1 * gamepad1.right_trigger + .5;
                double rightIntakePos = -.1 * gamepad1.right_trigger + .5;
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
                else if (gamepad1.x && arm.getCurrentPosition() > -1600) {
                    arm.setPower(-300);
                }
                else {
                    arm.setVelocity(0);
                }
                
                leftIntake.setPosition(leftIntakePos);
                rightIntake.setPosition(rightIntakePos);
                telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                telemetry.addData("Intake", "left (%.2f), right (%.2f)", leftIntake.getPosition(), rightIntake.getPosition());
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Arm Position,", arm.getCurrentPosition());
            telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
            telemetry.update();
        }
    }
}