Runs image slideshow on Raspberry Pi as part of a smart picture frame solution. Image categories to be displayed chosen by scanning RFid cards on sensor connected to arduino which sends input to the raspberry Pi. Semester project for INF1510 at UiO Spring 2017. 

Uses Marvin Image framework: http://marvinproject.sourceforge.net/en/index.html

compile command    javac -cp .;.\marvin\framework\marvin_1.5.5.jar *.java
run command          java -cp .;.\marvin\framework\marvin_1.5.5.jar ImgRead

Replace ; with : for linux/mac.


javac -cp .;.\mfz-rxtx-2.2-20081207-win-x64\RXTXcomm.jar;.\marvin\framework\marvin_1.5.5.jar *.java

java -cp .;.\mfz-rxtx-2.2-20081207-win-x64\RXTXcomm.jar;.\marvin\framework\marvin_1.5.5.jar SerialHandler



.\mfz-rxtx-2.2-20081207-win-x64\RXTXcomm.jar;
