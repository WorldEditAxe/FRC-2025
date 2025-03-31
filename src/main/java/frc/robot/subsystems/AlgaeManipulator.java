package frc.robot.subsystems;

import frc.lib.motor.Motor;
import frc.lib.subsystem.Subsystem;
import frc.robot.Constants.AlgaeManipConstants;
import frc.robot.Constants.AlgaeManipConstants.AlgaeManipState;
import frc.robot.Constants.AlgaeManipConstants.AlgaeManipAngleState;

public class AlgaeManipulator extends Subsystem<Double> {
  private final Motor leftMotor = Motor.nova(AlgaeManipConstants.LEFT_MOTOR_ID);
  private final Motor rightMotor = Motor.nova(AlgaeManipConstants.RIGHT_MOTOR_ID);
  private final Motor angleMotor = Motor.neo(AlgaeManipConstants.ANGLE_MOTOR_ID)
      .setPID(AlgaeManipConstants.ANGLE_MOTOR_PID)
      .useExternalEncoder();

  public AlgaeManipulator() {
    super(AlgaeManipState.class, AlgaeManipAngleState.class);
  }

  public void updateMotors() {
    leftMotor.set(getState(AlgaeManipState.class).leftMotorSpeed);
    rightMotor.set(getState(AlgaeManipState.class).rightMotorSpeed);
    angleMotor.setRef(getState(AlgaeManipAngleState.class).position);
  }

  public boolean isAtTarget() {
    return angleMotor.isAtTarget(getState(AlgaeManipAngleState.class).position);
  }

  public void stop() {
    leftMotor.stop();
    rightMotor.stop();
    angleMotor.stop();
  }
}
