package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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
        int armPos = 50;
        boolean dpad_upOld = false;
        boolean dpad_downOld = false;
        while (opModeIsActive()) {
            //drive code
            leftDrive.setPower(Range.clip((1 - .5 * CMH_functions.bool2int(gamepad1.left_bumper)) * (gamepad1.left_stick_y - .75 * gamepad1.left_stick_x), -1.0, 1.0));
            rightDrive.setPower(Range.clip((1 - .5 * CMH_functions.bool2int(gamepad1.left_bumper)) * (gamepad1.left_stick_y + .75 * gamepad1.left_stick_x), -1.0, 1.0));
            //arm code
            if (!gamepad1.right_bumper && !gamepad1.x && armPos % 50 != 0) armPos += 50 - CMH_functions.arcmod(armPos, 50);
            else if (gamepad1.y) armPos = 1500;
            else if (gamepad1.b) armPos = 750;
            else if (gamepad1.a) armPos = 50;
            else if (gamepad1.x) armPos = 15;
            else if (gamepad1.dpad_up && (!dpad_upOld || CMH_functions.bool2int(gamepad1.right_bumper) == 1) && armPos < 1500) armPos += 50 - 45 * CMH_functions.bool2int(gamepad1.right_bumper);
            else if (gamepad1.dpad_down && (!dpad_downOld || CMH_functions.bool2int(gamepad1.right_bumper) == 1) && armPos > 50) armPos -= 50 - 45 * CMH_functions.bool2int(gamepad1.right_bumper);
            dpad_upOld = gamepad1.dpad_up;
            dpad_downOld = gamepad1.dpad_down;
            arm.setTargetPosition(-armPos);
            arm.setVelocity(600);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //intake code
            leftIntake.setPosition(-.15 * gamepad1.right_trigger + .5);
            rightIntake.setPosition(-.15 * gamepad1.right_trigger + .5);

            //telemetry
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftDrive.getPower(), rightDrive.getPower());
            telemetry.addData("Intake", "left (%.2f), right (%.2f)", leftIntake.getPosition(), rightIntake.getPosition());
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Arm Position: ", -arm.getCurrentPosition());
            telemetry.addData("Arm Target Position: ", armPos);
            telemetry.addData("Triggers", "left (%.2f), right (%.2f)", gamepad1.left_trigger, gamepad1.right_trigger);
            telemetry.update();
        }
    }
}