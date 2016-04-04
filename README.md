## Synopsis

This project contains the solution for the Sage test. As a review the project should solve the following:  
- A file consists of a sequence of ASCII characters.  
               A sequence of one or more digits followed by a non-digit represents a decimal integer.  
               The file includes no negative numbers.  
               Write a program to  
                               * add all the numbers in the file,  
                               * count how many times each ASCII character occurs in the file,  
                               * output the result to a file.  
               You may assume that the sum of the numbers will not exceed the maximum value of an integer.  
               A sample input file and a valid output file are attached.  
 
- Develop tests or advise a test strategy to adequately test the developed code.  

I followed the design pattern of Dependency Injection. The injection is performed in the constructor of the Client class which receives the Service as argument.  
  
This project has been developed using NetBeans IDE 8.1.

My code can be found in the /src/ folder. My tests can be found in the /test/ folder.

## How to run

Please place your input files in the main Sage folder. Then run the project, in the standard ouput you'll receive a message to provide your file name. Please type the name and press ENTER.  
A file named Output.txt will be generated in the same folder (Sage folder) containing the result.

## API Reference

To avoid increase of the size of the submitted folder, the /dist/ directory containing the javadoc generated API files has not been included. However it can be generated from any modern IDE.

## Tests

To test the solution I performed unit tests for the FileProcessingConsumer and FileProcessingService classes.
The unit tests use JUnit4 and they are located in the /test/ folder

## Author

Fernando Diaz Caballero fdiazcaballero@gmail.com  

## License

GNU GENERAL PUBLIC LICENSE
