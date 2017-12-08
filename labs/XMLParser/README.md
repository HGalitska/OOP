To run: run main() method in Main.java.

xml directory contains file candy.xml
xsd directory contains file candy.xsd, which is xml schema for above file

xml file is validated against xsd scheme, using XMLValidator (xmlWork/XMLValidator.java)
XMLParser(xmlWork/XMLParser.java) parses xml file into array of Candy(candy/Candy.java) objects
It uses SaxParser and my own XMLHandler (extends DefaultHandler)

Candy objects are Comparable by "Energy" field. I use Sorter (xmlWork/Sorter.java) to implement Comparator interface.

In file ToHTML.java I transform candy.xml file into candies.html file, using xsl/candies.xsl file.
