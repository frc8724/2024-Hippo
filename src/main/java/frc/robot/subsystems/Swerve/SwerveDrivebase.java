// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Swerve;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

public class SwerveDrivebase extends SubsystemBase {
  private final SwerveDrive       swerveDrive;

  public SwerveDrivebase() {
    double maximumSpeed = Units.feetToMeters(4.5);
    File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");

    try {
      swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(maximumSpeed);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(Translation2d translation, double rotation, boolean fieldRelative)
  {
    // https://yagsl.gitbook.io/yagsl/configuring-yagsl/code-setup
    // another team's implementation:
    // https://github.com/Gryphon-Robotics-4990/Test_YAGSL2/blob/main/src/main/java/frc/robot/RobotContainer.java
    // Chief Delphi: https://www.chiefdelphi.com/t/yet-another-generic-swerve-library-yagsl-v1-release/450844/22
    swerveDrive.drive(translation,
                      rotation,
                      fieldRelative,
                      true);
  }
}
