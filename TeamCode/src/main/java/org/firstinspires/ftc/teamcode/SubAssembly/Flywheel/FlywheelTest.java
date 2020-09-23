package org.firstinspires.ftc.teamcode.SubAssembly.Flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Utilities.GamepadWrapper;

@TeleOp(name = "Flywheel Test", group = "Test")
public class FlywheelTest extends LinearOpMode {

        // declare class variables
        FlywheelControl Flywheel = new FlywheelControl();
        double time = 0.5;

        @Override
        public void runOpMode() throws InterruptedException {

            // display welcome message
            telemetry.setAutoClear(false);
            telemetry.addLine("Flywheel Test: ");
            telemetry.update();

            // create extended gamepads (for press and release options)
            GamepadWrapper egamepad1 = new GamepadWrapper(gamepad1);
            GamepadWrapper egamepad2 = new GamepadWrapper(gamepad2);

            // create and initialize sub-assemblies
            Flywheel.initialize(this);

            // wait for PLAY button to be pressed on driver station
            telemetry.addLine(">> Press PLAY to start");
            telemetry.update();
            telemetry.setAutoClear(true);
            waitForStart();

            //telling the code to run until you press that giant STOP button on RC
            while (opModeIsActive()) {

                // update extended gamepads
                egamepad1.updateEdge();
                egamepad2.updateEdge();

                telemetry.addLine("Press A to launch");

                // check for move input
                if (egamepad1.a.pressed) {
                    Flywheel.launch();
                }
                if (egamepad1.a.released) {
                    Flywheel.stop();
                } else {
                    Flywheel.stop();
                }

                Flywheel.Telemetry();
                telemetry.update();

                //let the robot have a little rest, sleep is healthy
                sleep(40);
            }

        }
    }

