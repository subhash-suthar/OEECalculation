# OEECalculation

Certainly! Here's a sample GitHub README file with formulas and sample input/output for your OEE calculator:

```markdown
# Overall Equipment Efficiency (OEE) Calculator

This repository contains a simple Java program that calculates the Overall Equipment Efficiency (OEE) based on input data.

## Introduction

OEE is a key performance indicator used in manufacturing and production to measure how efficiently equipment or processes are operating. It is calculated by taking into account three factors: Availability, Performance, and Quality.

The formula to calculate OEE is as follows:
```
OEE = Availability * Performance * Quality
```

## Input Data

The program accepts the following input data as JSON:
```json
{
    "runtime": 3600.0,                  // Runtime in seconds
    "plannedProductionTime": 4000.0,    // Planned production time in seconds
    "idealCycleTime": 10.0,             // Ideal cycle time in seconds
    "totalProducedCount": 500,          // Total produced count
    "goodCount": 480                   // Good count
}
```

## Usage

You can run the Java program provided in this repository to calculate the OEE based on the input data. Here's how to use it:

1. Clone this repository to your local machine.

2. Compile and run the program using a Java compiler or IDE.

3. The program will calculate the Availability, Performance, Quality, and OEE based on the provided input data and display the results.

## Sample Input and Output

**Input**:
```json
{
    "runtime": 3600.0,
    "plannedProductionTime": 4000.0,
    "idealCycleTime": 10.0,
    "totalProducedCount": 500,
    "goodCount": 480
}
```

**Output**:
```
Availability: 90%
Performance: 138.89%
Quality: 96%
Overall Equipment Efficiency (OEE): 119.7%
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

You can customize and expand this README file with additional information as needed for your project.
