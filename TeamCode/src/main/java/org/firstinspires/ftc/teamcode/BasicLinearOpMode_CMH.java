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
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotorEx.Direction.FORWARD);
        leftIntake = hardwareMap.get(Servo.class, "leftIntake");
        rightIntake = hardwareMap.get(Servo.class, "rightIntake");
        leftIntake.setDirection(Servo.Direction.FORWARD);
        rightIntake.setDirection(Servo.Direction.REVERSE);
        touch = hardwareMap.get(DigitalChannel.class, "touch");
        waitForStart();
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        runtime.reset();
        int armPos = -50;
        boolean dpad_upOld = false;
        boolean dpad_downOld = false;
        while (opModeIsActive()) {
            int gamepad1L;
            if (gamepad1.left_bumper) gamepad1L = 1;
            else gamepad1L = 0;
            /*int gamepad1R;
            if (gamepad1.right_bumper) gamepad1R = 1;
            else gamepad1R = 0;*/
            double drive = (1 - .5 * gamepad1L) * gamepad1.left_stick_y;
            double turn = (1 - .5 * gamepad1L) * .75 * gamepad1.left_stick_x;
            double leftPower = Range.clip(drive - turn, -1.0, 1.0);
            double rightPower = Range.clip(drive + turn, -1.0, 1.0);
            double IntakePos = -.15 * gamepad1.right_trigger + .5;
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            if (gamepad1.y) armPos = -1500;
            else if (gamepad1.b) armPos = -750;
            else if (gamepad1.a) armPos = -50;
            else if (gamepad1.dpad_up && !dpad_upOld) armPos -= 50;
            else if (gamepad1.dpad_down && !dpad_downOld) armPos += 50;
            if (gamepad1.dpad_up) dpad_upOld = true;
            if (gamepad1.dpad_down) dpad_downOld = true;
            arm.setTargetPosition(armPos);
            arm.setVelocity(300);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftIntake.setPosition(IntakePos);
            rightIntake.setPosition(IntakePos);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Intake", "left (%.2f), right (%.2f)", leftIntake.getPosition(), rightIntake.getPosition());
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Arm Position: ", arm.getCurrentPosition());
            telemetry.addData("Arm Target Position: ", armPos);
            telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
            telemetry.update();
        }
    }
}