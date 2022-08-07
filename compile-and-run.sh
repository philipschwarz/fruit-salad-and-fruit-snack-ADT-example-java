#!/bin/bash

# Download the jdk from https://jdk.java.net/19/
PATH=~/Downloads/jdk-19.jdk/Contents/Home/bin/:$PATH

javac src/fruit/adt/example/Main.java -d target --enable-preview -source 19

java -ea --enable-preview -classpath target fruit.adt.example.Main