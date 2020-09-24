package org.firstinspires.ftc.teamcode.SubAssembly.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColorControl {
    /* Declare private class object */
    private LinearOpMode opmode = null;     /* local copy of opmode class */
    private ColorSensor colorSensorT;
    private ColorSensor colorSensorB;
    private int COLOR_THRESHOLD = 6000;

    /* Subassembly constructor */
    public ColorControl() {
    }

    public void init(LinearOpMode opMode) {
        HardwareMap hwMap;
        opMode.telemetry.addLine("Color Control" + " initialize");
        opMode.telemetry.update();

        /* Set local copies from opmode class */
        opmode = opMode;
        hwMap = opMode.hardwareMap;

        /* Map hardware devices */
        colorSensorT = hwMap.get(ColorSensor.class, "colorSensorT");
        colorSensorB = hwMap.get(ColorSensor.class, "colorSensorB");
    }

    public void Telemetry() {
        opmode.telemetry.addData("Blue value t: ", getBlueT());
        opmode.telemetry.addData("Blue value b: ", getBlueB());
        opmode.telemetry.addData("Red value t: ", getRedT());
        opmode.telemetry.addData("Red value b: ", getRedB());
        if (isBlueT())
            opmode.telemetry.addLine("BLUE T");
        if (isBlueB())
            opmode.telemetry.addLine("BLUE B");
        if (isRedT())
            opmode.telemetry.addLine("RED T");
        if (isRedB())
            opmode.telemetry.addLine("RED B");
    }

    public int getRedT() { return colorSensorT.red(); }
    public int getRedB() { return colorSensorB.red(); }

    public int getBlueT() {
        return colorSensorT.blue();
    }
    public int getBlueB() {return colorSensorB.blue();}

    public boolean isRedT() {
        return getRedT() >= COLOR_THRESHOLD;
    }
    public boolean isRedB() {
        return getRedB() >= COLOR_THRESHOLD;
    }

    public boolean isBlueT() {
        return getBlueT() >= COLOR_THRESHOLD;
    }
    public boolean isBlueB() {
        return getBlueB() >= COLOR_THRESHOLD;
    }

}
