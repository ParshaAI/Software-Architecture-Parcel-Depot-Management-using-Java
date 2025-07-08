Parcel Management System – MVC & Design Patterns Implementation
This project is a parcel management system designed with a strong software engineering foundation, incorporating MVC architecture and multiple design patterns for scalability, maintainability, and clarity. It allows managing customer requests, processing parcels, calculating collection fees, and generating reports through an organized and modular structure.

Architecture Overview
MVC (Model-View-Controller)
The system is organized following the MVC pattern, ensuring a clear separation of concerns:

Model (Logic_Model): Contains business logic and manages data. It includes core classes such as Manager, Worker, Customer, and Parcel.

View (Presentation_View): Displays data and interfaces to the user (via console or GUI). It does not directly interact with the Model.

Controller (Manager): Acts as the bridge between Model and View. It handles all workflow and system logic coordination.

Key Design Patterns Used
Singleton Pattern: Used in the Log class to ensure only one logging instance exists across the system.

Factory Pattern: Used to create parcel or customer objects based on varying input conditions.

Strategy Pattern: Enables dynamic changes to how parcel fees are calculated based on ID-based discount strategies.

Observer Pattern: Facilitates communication between components on event changes (e.g., logging updates).

MVC Pattern: Structures the application into clear components, aiding long-term scalability.

Core Features
Receive & Add Parcel to Waiting List
Implemented in the Manager class using loadParcels().

Reads from a CSV file and stores data in a ParcelMap.

Establishes one-to-many relationships between Manager and both Parcel and Customer.

Process Customer Requests & Parcel Handling
Managed by the Worker class.

Handles collection and updates of parcel information.

Controlled and coordinated by the Manager.

Calculate Collection Fee
Fee calculation is performed in the Worker class.

Takes parcel dimensions and weights, applies relevant discount strategies based on parcel ID.

Generate Reports
The Log class records system-wide events and generates a summary report as an output text file.

Additional Functionalities
Find Parcel by ID, Add/Remove Customer, and other utilities are implemented in:

Customer, CustomerQueue, Parcel, and ParcelMap classes.

