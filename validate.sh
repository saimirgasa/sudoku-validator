#! /bin/bash

FILE=$(find . -type f -name "$1")

if [ -e "$FILE" ]
then
  java -jar ./target/sudoku-validator-1.0.0-jar-with-dependencies.jar "$FILE"
else
  echo Please provide a file that exist in the current directory!
fi
