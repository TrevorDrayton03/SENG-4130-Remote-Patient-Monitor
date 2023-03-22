# Remote Patient Monitor

##### Project: SENG4130, Software Design Patterns
##### Author: Trevor Drayton, Sanyam Gupta

## About 

This project is a simulation of a Remote Patient Monitoring application. The application simulates receiving the patient's vitals through a homebase (such as a smart watch). The patient can see some information through their dashboard, but the Clinician has more information.

The app was developed in IntelliJ IDEA using Java Swing as the front end. The dashboards are JavaFX applications that run on their own thread within the application. The dashboards update in real-time, simulating receiving new patient data, and identify dangerous vital readings for the Doctor. The data on the display is all dynamic; the graphs, charts, tables, etc update every second.

The application implements the template, iterator, and singleton design patterns.

## Running

1. Download JavaFX and export it
	- https://gluonhq.com/products/javafx/ (download the Windows SDK)
2. File -> Project Structure -> Library -> Add the lib directory from the JavaFX file
3. Run/Debug Configurations -> Modify Options -> Add VM Options
	- --module-path [your_path_to_javafx]\lib --add-modules javafx.controls,javafx.fxml, javafx.swing
4. Run Main in src/com.company/

## Logging In

As a clinician: user: sanyam, password: clinician.

As a patient: user: trevor, password: patient.

Or, register: make a username and password and write clinician or patient as user types.

## Dashboards

![Clinician Dashboard](https://user-images.githubusercontent.com/56656811/225744869-2254828c-c7c6-4278-b2af-e845524fb51d.png)

![Patient](https://user-images.githubusercontent.com/56656811/225745362-b873e777-7d17-4a1d-bcdf-d86995b8170d.png)

## Class Diagram

![4130Sol1 (5) vpd (3)](https://user-images.githubusercontent.com/56656811/225741246-7af124e7-dafc-4201-9756-e071b2973dcf.png)

## Presentation

[Remote Patient Monitoring.pptx](https://github.com/TrevorDrayton03/SENG4130_REMOTE_PATIENT_MONITORING/files/10995421/Remote.Patient.Monitoring.pptx)
