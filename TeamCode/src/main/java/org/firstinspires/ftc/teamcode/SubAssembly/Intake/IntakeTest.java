package org.firstinspires.ftc.teamcode.SubAssembly.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utilities.GamepadWrapper;

@TeleOp(name = "Intake Test", group = "Test")
public class IntakeTest extends LinearOpMode {

        IntakeControl Intake = new IntakeControl();

        @Override
        public void runOpMode() throws InterruptedException {

            // display welcome message
            telemetry.setAutoClear(false);
            telemetry.addLine("Intake Test: ");
            telemetry.update();

            // create extended gamepads (for press and release options)
            GamepadWrapper egamepad1 = new GamepadWrapper(gamepad1);
            GamepadWrapper egamepad2 = new GamepadWrapper(gamepad2);

            // create and initialize sub-assemblies
            Intake.initialize(this);

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

                telemetry.addLine("Press B to intake");

                // check for move input
                if (egamepad1.b.pressed) {
                    Intake.intake();
                }
                if (egamepad1.b.released) {
                    Intake.stop();
                } else {
                    Intake.stop();
                }

                Intake.Telemetry();
                telemetry.update();

                //let the robot have a little rest, sleep is healthy
                sleep(40);
            }

        }
    }

