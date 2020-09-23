package org.firstinspires.ftc.teamcode.SubAssembly.Intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class IntakeControl {

        /* Declare private class object */
        private LinearOpMode opmode = null;     /* local copy of opmode class */

        private enum Modes {STOP, INTAKE}
        private Modes mode = Modes.STOP;

        private DcMotor IntakeM;
        private ElapsedTime runtime = new ElapsedTime();

        /* Declare public class object */


        /* Subassembly constructor */
        public IntakeControl() {
        }

        public void initialize(LinearOpMode opMode) {
            HardwareMap hwMap;

            opMode.telemetry.addLine("Intake Control" + " initialize");
            opMode.telemetry.update();

            /* Set local copies from opmode class */
            opmode = opMode;
            hwMap = opMode.hardwareMap;

            /* Map hardware devices */
            IntakeM = hwMap.dcMotor.get("IntakeM");
            IntakeM.setDirection(DcMotor.Direction.FORWARD);
            IntakeM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            IntakeM.setPower(0);

        }

        public void intake() {
            mode = Modes.INTAKE;    // tell thread to move
        }

        public void stop() {
            // tell thread to stop and stop motors immediately
            mode = Modes.STOP;
            IntakeM.setPower(0);
        }

        public void TimeDelay(double delayTimeSEC) {
            double startTime = 0;
            double elapsedTime = 0;
            startTime = runtime.seconds();
            do {
                elapsedTime = runtime.seconds() - startTime;
                opmode.sleep(40);
            } while ((elapsedTime < delayTimeSEC) && !opmode.isStopRequested());
        }

        public void intakeTime(double time) {
            mode = Modes.INTAKE;
            TimeDelay(time);
            stop();
        }

        public void Telemetry() {
            opmode.telemetry.addData("Mode ", mode);
        }

        private class updateThread extends Thread {
            public updateThread() {
                mode = Modes.STOP;
            }

        }
    }
