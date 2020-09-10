# Take Home Assignment 

This assignment tests Back-end and Front-end development.

## Introduction

Thank you for taking time to do this home assignment! 

The assignment asks you to do some simple things. The obvious implementation 
should be easy for you. What we're interested in seeing is what you
know from experience to be not so obvious.

Imagine this has to run in production.

What, besides the obvious, do you believe is necessary? Please code it that way :-)

There are 2 parts to the assignment:

1) Some [code](#Coding) to be writting
2) Some [questions](#Questions) about your code.

## Tech choices

Please implement the stories below, including a few tests for the back-end, in one of the following languages: 

- Back-end: gRPC, using Kotlin, Go. 
- Front-end: gRPC, React. 

If you use any dependencies, please add the associate containers to the project.

This repo will be connected to Heroku so that your app can be deployed and inspected functionally. Please let us know what implementation languages you're going to use so we can set it up for you.

You are free to use the CIS Automotive API (https://api.autodealerdata.com/docs).

## UI

You are free to design your own UI. 

Or you can take some clues from a UI we've uploaded to Zeplin. The UI is in the public domain
and we've uploaded it to Zeplin for your convenience. Let us know if you want to use it.

The design: https://www.xdguru.com/cars-classified-website-xd-template/

Zeplin: https://zpl.io/2j6Q1lx



## Intermediate Review

We offer you the option to ask one of us to give you intermediate feedback on your code before you hand your assignment in.

Please use the "Review" button in Github to ask us.


## Assessment

We "grade" based on the following criteria:

* Did you implement according to the requirements?
* Readability is important to us.
* We look at your git commit log. It would be good if it shows us your process.
* Understanding your tactical decisions should be made clear.

## Coding Part

### As a car dealer, I want to browse the cars I have in stock and give purchase recommendations tailored to the needs of my customers.

More specifically,

### As a car dealer, I want to search for cars by year and make.

Example:

- Given the year 2018, I should get the Citroen C3 and Honda Fit.
- Given the brand Citroën, I should get the Citroen C3 2018.
- Leaving the search string blank should return a list of all cars.

### As a car dealer, I want to be able to add new cars to my store.

Example:

* I will enter the car's model, make, version, year of release, price, fuel consumption, and annual maintenance cost. The car will show up in the results returned by story #1.

### As a car dealer, I want to recommend to my clients the car with the lowest total annual cost over a period of four (4) years, given the price of fuel (€/L) and the expected distance to travel each month (km/month).

Relevant car parameters are price of the car (€), fuel consumption (km/l), and annual maintenance cost.

Example:

- Given that I expect to travel 250 km each month for the next 4 years, and the expected
price of fuel is 0.66 €/L, what is the ranking of cars according to their total annual cost?

### Questions
---

Based on these stories, please do as follows:

* Describe the architecture you will use and the motivation for its choice? (max 500 characters)

* You have a team of 3 developers. How would you tackle working together on the stories?

* Can you describe 1 thing that can go wrong with your code once in production?
