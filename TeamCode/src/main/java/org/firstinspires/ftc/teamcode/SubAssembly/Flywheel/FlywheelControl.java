package org.firstinspires.ftc.teamcode.SubAssembly.Flywheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FlywheelControl {
        final double WHEEL_SPEED = 1.0;

        /* Declare private class object */
        private LinearOpMode opmode = null;     /* local copy of opmode class */

        Thread liftThread = new updateThread();
        private enum Modes {STOP, LAUNCH}
        private Modes mode = Modes.STOP;

        private DcMotor FlywheelRightM;
        private DcMotor FlywheelLeftM;
        private ElapsedTime runtime = new ElapsedTime();

        /* Declare public class object */


        /* Subassembly constructor */
        public FlywheelControl() {
        }

        public void initialize(LinearOpMode opMode) {
            HardwareMap hwMap;

            opMode.telemetry.addLine("Flywheel Control" + " initialize");
            opMode.telemetry.update();

            /* Set local copies from opmode class */
            opmode = opMode;
            hwMap = opMode.hardwareMap;

            /* Map hardware devices */
            FlywheelRightM = hwMap.dcMotor.get("FlywheelRightM");
            FlywheelLeftM = hwMap.dcMotor.get("FlywheelLeftM");
            FlywheelRightM.setDirection(DcMotor.Direction.FORWARD);
            FlywheelLeftM.setDirection(DcMotor.Direction.FORWARD);
            /*FlywheelRightM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FlywheelLeftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/
            FlywheelRightM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FlywheelLeftM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FlywheelRightM.setPower(0);
            FlywheelLeftM.setPower(0);

            //liftThread.start();
        }

        /* Subassembly destructor */
       /* public void finalize() {
            liftThread.interrupt();
        }*/

        public void launch() {
            mode = Modes.LAUNCH;    // tell thread to move
        }

        public void stop() {
            // tell thread to stop and stop motors immediately
            mode = Modes.STOP;
            FlywheelRightM.setPower(0);
            FlywheelLeftM.setPower(0);
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

        public void launchTime(double time) {
            mode = Modes.LAUNCH;
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

            // called when tread.start is called. thread stays in loop to do what it does until exit is
            // signaled by main code calling thread.interrupt.
          /*  @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        switch (mode) {
                            default:
                            case STOP:
                                LifterLeftM.setPower(0);
                                LifterRightM.setPower(0);
                                break;
                            case UP:
                                if (isLimitTop()) {
                                    mode = Modes.STOP;
                                    LifterLeftM.setPower(0);
                                    LifterRightM.setPower(0);
                                } else {
                                    LifterLeftM.setPower(LIFT_SPEED);
                                    LifterRightM.setPower(LIFT_SPEED);
                                }
                                break;
                            case DOWN:
                                if (isLimitBottom()) {
                                    mode = Modes.STOP;
                                    LifterLeftM.setPower(0);
                                    LifterRightM.setPower(0);
                                } else {
                                    LifterLeftM.setPower(-LIFT_SPEED);
                                    LifterRightM.setPower(-LIFT_SPEED);
                                }
                                break;
                        }

                        Thread.sleep(100);
                    }
                }*/
                // interrupted means time to shutdown. note we can stop by detecting isInterrupted = true
                // or by the interrupted exception thrown from the sleep function.
               // catch (InterruptedException e) {
                }
            }