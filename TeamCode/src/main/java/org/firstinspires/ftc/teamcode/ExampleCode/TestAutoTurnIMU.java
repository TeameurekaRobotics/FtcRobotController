package org.firstinspires.ftc.teamcode.ExampleCode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

@Autonomous(name = "TestAutoTurnIMU", group = "Example")
@Disabled
public class TestAutoTurnIMU extends LinearOpMode {
    private BNO055IMU imu;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        BNO055IMU.Parameters imuParameters;
        Orientation angles;
        Acceleration gravity;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        // Create new IMU Parameters object.
        imuParameters = new BNO055IMU.Parameters();
        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // Express acceleration as m/s^2.
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // Disable logging.
        imuParameters.loggingEnabled = false;
        // Initialize IMU.
        imu.initialize(imuParameters);
        // Prompt user to press start buton.
        telemetry.addData("IMU Example", "Press start to continue...");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Get absolute orientation

                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                /** NOT BEING USED
                // Get acceleration due to force of gravity.
                gravity = imu.getGravity();
                **/

                // Display orientation info.
                telemetry.addData("rot about Z", angles.firstAngle);
                telemetry.addData("rot about Y", angles.secondAngle);
                telemetry.addData("rot about X", angles.thirdAngle);

                /** NOT BEING USED
                // Display gravitational acceleration.
                telemetry.addData("gravity (Z)", gravity.zAccel);
                telemetry.addData("gravity (Y)", gravity.yAccel);
                telemetry.addData("gravity (X)", gravity.xAccel);
                telemetry.update();
                **/

            }
        }
    }
}
