// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

//These libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.CANSparkMax; // Affected by the REVLib 2025 release, rename, https://docs.revrobotics.com/revlib/24-to-25
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType; // Affected by the REVLib 2025 release, rename, https://docs.revrobotics.com/revlib/24-to-25

public class Pickup {

    CANSparkMax m_pickupScoop = new CANSparkMax(9, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 9
    
    CANSparkMax[] CANIDs= {m_pickupScoop};

    RelativeEncoder p_Encoder = m_pickupScoop.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);


    public void Initial (){
        
    }

//MAKE SURE NONE OF THE AUTO SPEEDS ARE >0.5 SINCE WE CHANGED THE AUTO SPEED TO -2, OTHERWISE THE VALUE WILL BE

    public void SetSpeed (double speed){
        double speedPercentage = 0.2; //range 0.0 to 1.0
        m_pickupScoop.set(speed*speedPercentage);
    }

    public void SetSpeedTeleOp (double speed){
        double speedPercentage = 0.5; //range 0.0 to 1.0
        m_pickupScoop.set(speed*speedPercentage);
    }

    public void TestMotor (double speed, int CAN_ID){
        //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 9-13       
        if (CAN_ID != 9){
            String S = Integer.toString(CAN_ID);
            System.out.println("CAN_ID #" + S + " is not assigned to a pickup motor");
        }
        else{
            CANIDs[(CAN_ID-9)].set(speed);
        }
    }

    public double getEncoder(){
        //Note: Intake positive encoder, Outtake negative encoder
        return p_Encoder.getPosition();
    }
}
