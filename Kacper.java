package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import org.firstinspires.ftc.robotcore.external.Telemetry.DisplayFormat;




@TeleOp(name="Kacper", group="Linear Opmode")

public class Kacper extends LinearOpMode {

   private ElapsedTime runtime = new ElapsedTime();
   private DcMotor leftDrive1 = null;
   private DcMotor leftDrive2 = null;
   private DcMotor rightDrive1 = null;
   private DcMotor rightDrive2 = null;
   private Servo Servo1 = null;
   private DistanceSensor sensor;
   private ColorSensor color;
   private Gamepad gamepad;
   private TouchSensor magnetic;
   private RevBlinkinLedDriver dioda;

   
   @Override 
   public void runOpMode (){
      telemetry.addData("Status", "Initialized");
         telemetry.update();
         
         leftDrive1  = hardwareMap.get(DcMotor.class, "left_drive1");
         leftDrive2  = hardwareMap.get(DcMotor.class, "left_drive2");
         rightDrive1 = hardwareMap.get(DcMotor.class, "right_drive1");
         rightDrive2 = hardwareMap.get(DcMotor.class, "right_drive2");
         Servo myServo = hardwareMap.get(Servo.class, "myServo");
         sensor = hardwareMap.get(DistanceSensor.class, "sensor");
         color = hardwareMap.get(ColorSensor.class, "color");
         magnetic = hardwareMap.get(TouchSensor.class, "magnetic");
         dioda = hardwareMap.get(RevBlinkinLedDriver.class, "dioda");
         
         gamepad = gamepad1;
         
         myServo.setPosition(0);
         

         leftDrive1.setDirection(DcMotor.Direction.FORWARD);
         leftDrive2.setDirection(DcMotor.Direction.FORWARD);
         rightDrive1.setDirection(DcMotor.Direction.REVERSE);
         rightDrive2.setDirection(DcMotor.Direction.REVERSE);
         
         
         telemetry.setDisplayFormat(DisplayFormat.HTML);
         
        
         waitForStart();
         runtime.reset();
         
         
         while (opModeIsActive()){
            
            double leftPower;
            double rightPower;
            double servo;
            
            
            double drive = gamepad1.left_stick_y;
            double turn  = gamepad1.right_stick_x;
            boolean obrót = gamepad1.a;
            boolean wykrycie =  magnetic.isPressed();
            
            
            
            
            
          
            
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            
            /*if (gamepad1.a){
               myServo.setPosition(1);
            }
            else{
               myServo.setPosition(0);
            }
            */
            
            double distance = sensor.getDistance(DistanceUnit.CM);
            
            float skala;
           
            
            
            if (distance < 5){
               
               myServo.setPosition(1);
            
            }
            else if (gamepad1.a){
               myServo.setPosition(1);
            }
            
            else{
               myServo.setPosition(0);
               
            }
            
            leftDrive1.setPower(leftPower);
            leftDrive2.setPower(leftPower);
            rightDrive1.setPower(rightPower);
            rightDrive2.setPower(rightPower);
            
            int lightIntensity = color.alpha();
            
            if (lightIntensity > 5 ){
               myServo.setPosition(1);
            } 
            
            double currentServoPosition = myServo.getPosition();
            

           String prawy = ((%.2f) + rightPower);
           String lewy = ((%.2f) + leftPower);
            
            
            
            StringBuilder builder = new StringBuilder();
            builder.append("<font color='#119af5' face=monospace>");
            builder.append("Status Run Time: " + runtime.toString() + ".\n");
            builder.append("Distance (cm)" + distance + "\n");
            builder.append("</font>");
            
            
            builder.append("<font color='#33ff33' face=monospace>");
            builder.append("Aktualna pozycja serwa: "+ currentServoPosition + "\n");
            builder.append("Status przycisku: " + magnetic.isPressed() + "\n");
            builder.append("</font>");
            
            builder.append("<font color='#ff5733' face='monospace'>");
            builder.append("Light Intensity: " + lightIntensity + "\n");
            builder.append("Motors " + "left: " + lewy + "right: "+ prawy + "\n");
            builder.append("</font>");
            
            
            String myString = builder.toString();
            
            
            
            
            
            telemetry.addData("Status", "Run Time: " + runtime.toString(), myString);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Aktualna pozycja serwa", currentServoPosition);
            telemetry.addData("Light Intensity", lightIntensity);
            telemetry.addData("Distance (cm)", String.format("%.2f", distance));
            telemetry.addLine(myString);
         
            telemetry.update();
            
            
             
            
            }

      
      
   }
      
      
   }
