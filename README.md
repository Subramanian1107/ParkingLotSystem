# 🚗 Smart Parking Lot System (LLD)

A **Low-Level Design (LLD)** implementation of a scalable Smart Parking Lot backend system with **multi-floor support**.
This project demonstrates clean architecture, object-oriented design, and real-world design patterns.

---

## 📌 Objective

Design and implement a backend system that can:

* Allocate parking spots based on vehicle type
* Support **multiple floors**
* Handle vehicle check-in and check-out
* Track parking duration
* Calculate parking fees
* Maintain real-time availability of spots
* Handle concurrency safely

---

## 🏗️ System Design Overview

The system follows a **layered architecture**:

```
Controller → Service → Repository → Entity
```

### Key Components:

* **Controller Layer** → Handles user interaction (CLI simulation)
* **Service Layer** → Business logic (Facade pattern)
* **Repository Layer** → Data abstraction (in-memory storage)
* **Entity Layer** → Core domain models
* **Strategy Layer** → Pricing logic abstraction
* **Factory Layer** → Object creation
* **Utility Layer** → ID generation
* **Exception Layer** → Custom error handling

---

## 🏢 Multi-Floor Architecture ⭐

The system supports **multiple floors**, each managing its own parking spots.

### Design Highlights:

* Each `Floor` maintains:

  * Separate queues for each `SpotType`
* Allocation strategy:

  * Iterate **floor-by-floor (lowest first)**
  * Assign nearest available valid spot
* Each `ParkingSpot` stores:

  * `floorNumber` for tracking and release

### Benefits:

* Scalable to large parking structures
* Cleaner separation of concerns
* Efficient allocation without global contention

---

## 📦 Project Structure

```
parking-lot-system/
│
├── app/                # Main class (entry point)
├── controller/         # API / CLI layer
├── service/            # Business logic
├── repository/         # Data storage abstraction
├── entity/             # Domain models
├── strategy/           # Pricing strategies
├── factory/            # Object creation
├── util/               # Utilities (IdGenerator)
├── exception/          # Custom exceptions
```

---

## 🧩 Design Patterns Used

### 1. Facade Pattern

* `ParkingService`
* Provides a single interface for parking operations

### 2. Strategy Pattern

* Pricing varies by vehicle type
* Implemented using:

  * `PricingStrategy`
  * Concrete strategies (Car, Bike, Bus)

### 3. Factory Pattern

* `VehicleFactory`
* Encapsulates vehicle creation

### 4. Repository Pattern

* `TicketRepository`
* Abstracts data storage

### 5. (Conceptual) Singleton

* Services behave like single-instance components

---

## 🚘 Supported Vehicle Types

* Motorcycle 🏍️
* Car 🚗
* Bus 🚌

---

## ⚙️ Features

### ✅ Multi-Floor Parking

* Multiple floors with independent spot management
* Efficient floor-wise allocation

### ✅ Parking Spot Allocation

* Based on vehicle size hierarchy
* Falls back to larger spots if needed

### ✅ Check-In / Check-Out

* Unique ticket generation via `IdGenerator`
* Tracks entry and exit time

### ✅ Fee Calculation

* Strategy-based pricing
* Hourly billing (rounded up)

### ✅ Real-Time Availability

* Updated instantly on entry/exit
* Floor-specific availability

### ✅ Exception Handling

Custom exceptions for better reliability:

* `NoSpotAvailableException`
* `InvalidTicketException`
* `AlreadyCheckedOutException`

### ✅ Thread Safety

* Synchronized allocation
* Atomic ID generation

---

## 🧠 Core Concepts Covered

* Object-Oriented Design (OOD)
* SOLID Principles
* Design Patterns
* Concurrency handling
* Clean architecture

---

## ▶️ How to Run

### 1. Compile

```bash
javac app/Main.java
```

### 2. Run

```bash
java app.Main
```

---

## 🖥️ Sample Flow

```
1. Check In
   → Enter vehicle number & type
   → System allocates a spot (with floor info)
   → Ticket generated

2. Check Out
   → Enter ticket ID
   → System calculates fee
   → Spot is freed
```

---

## 🔮 Future Enhancements

* Multi-entry / exit gates
* REST APIs using Spring Boot
* Database integration (MySQL / PostgreSQL)
* Redis caching for availability
* Real-time dashboard
* Dynamic pricing (peak hours, weekends)
* EV charging stations
* Parking reservation system

---

## 👨‍💻 Author

**Subramanian**
